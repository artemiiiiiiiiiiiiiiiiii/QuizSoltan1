package irlandec.prophet.quiz;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

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


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStart = (Button)findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent(MainActivity.this, GameLevels.class);
                    startActivity(intent); finish();
                }catch (Exception e){

                } //конец коснтрукции

            }
        });

        Window w = getWindow();
                w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    } */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authorization = authorization.findViewById(R.id.layout);
        authorize = findViewById(R.id.buttonStart);
        register = findViewById(R.id.buttonReg);
        final MaterialEditText login = authorize.findViewById(R.id.loginField);
        final MaterialEditText password = authorize.findViewById(R.id.passwordField);




        Button AuthorizationButton = authorization.findViewById(R.id.buttonVhod);
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














        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(MainActivity.this, Authorization.class);
                startActivity(intent); */
                setContentView(R.layout.registration);
            }
        });

        /*Button buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }); */

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    //Вы точно хотите?

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Для выхода, кликните еще раз", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    //
    private void startGame() {
        Intent intent = new Intent(MainActivity.this, GameLevels.class);
        startActivity(intent);
    }

}





