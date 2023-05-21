package irlandec.prophet.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import irlandec.prophet.quiz.models.User;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    Button authorize, register;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout root;
    LinearLayout notifications1;
    LinearLayout authorization;

    private void showauthorization() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Регистрация");
        dialog.setMessage("Заполните регистрационные поля");
        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.autorization, null);
        dialog.setView(register_window);
        // Открыть меню регистрации
        notifications1 = register_window.findViewById(R.id.layout);
        final MaterialEditText login = register_window.findViewById(R.id.loginField);
        final MaterialEditText password = register_window.findViewById(R.id.passwordField);
        Button AuthorizationButton = register_window.findViewById(R.id.buttonVhod);
        AuthorizationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(login.getText().toString())) {
                    Snackbar.make(notifications1, "Введите почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 5) {
                    Snackbar.make(notifications1, "Пароль должен содержать не менее 5-ти символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(MainActivity.this, GameLevels.class));//Сделать выбор уровней
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(notifications1, "Ошибка авторизации. " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        dialog.show();


    }

    private void showregistration(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Регистрация");
        dialog.setMessage("Заполните регистрационные поля");
        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window2 = inflater.inflate(R.layout.registration, null);
        dialog.setView(register_window2);
        // Открыть меню регистрации
        notifications1 = register_window2.findViewById(R.id.layout);
        final MaterialEditText login = register_window2.findViewById(R.id.loginField);
        final MaterialEditText password = register_window2.findViewById(R.id.passwordField);
        Button RegistrationButton = register_window2.findViewById(R.id.buttonRegister);
        RegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(login.getText().toString())) {
                    Snackbar.make(notifications1, "Введите почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 5) {
                    Snackbar.make(notifications1, "Пароль должен содержать не менее 5 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(login.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User();
                        user.setMail(login.getText().toString());
                        user.setPassword(password.getText().toString());

                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Snackbar.make(notifications1, "Пользователь добавлен", Snackbar.LENGTH_SHORT).show();
                                startGame();
                            }
                        });
                    }
                });

            }
        });
        dialog.show();


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance(); // Инициализация объекта auth
        db = FirebaseDatabase.getInstance(); // Инициализация объекта db
        users = db.getReference("Users");
        authorize = findViewById(R.id.buttonStart);
        register = findViewById(R.id.buttonReg);

        authorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showauthorization();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showregistration();
            }
                // Открыть меню регистрации

        });
    }

            //Вы точно хотите?
            @Override
            public void onBackPressed() {
                super.onBackPressed();

                if (backPressedTime + 2000 > System.currentTimeMillis()) {
                    backToast.cancel();
                    super.onBackPressed();
                    return;
                } else {
                    backToast = Toast.makeText(getBaseContext(), "Для выхода, кликните еще раз", Toast.LENGTH_SHORT);
                    backToast.show();
                }

                backPressedTime = System.currentTimeMillis();
            }


    private void startGame() {
        Intent intent = new Intent(MainActivity.this, GameLevels.class);
        startActivity(intent);
    }

    }
