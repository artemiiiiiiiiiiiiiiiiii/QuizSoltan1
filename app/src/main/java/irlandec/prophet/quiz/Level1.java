package irlandec.prophet.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; //Переменная для левой картинки
    public int numRight; //Переменная для правой картинки
    Array array = new Array();
    Random random = new Random();
    public int count = 0; //счетчик верных ответов



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //создание переменной text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1one); //Уса


        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        //скругление углов
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        //скругление углов правой
        img_right.setClipToOutline(true);

        //Развернуть на весь экран
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        /*  */

        //вызов диалога в начале игры
        dialog = new Dialog(this); //новое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        //Кнопка, закрывает окно
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки - начало
                try {
                    //вернуться к выбору уровня
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialog.dismiss();
            }
        });

        //Кнопка продолжить
        Button btncontinue = (Button) dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();


        //_______________________________________________________________________

        //вызов диалога в конце игры
        dialogEnd = new Dialog(this); //новое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);

        //Кнопка, закрывает окно
        TextView btnclose2 = (TextView) dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки - начало
                try {
                    //вернуться к выбору уровня
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialogEnd.dismiss();
            }
        });

        //Кнопка продолжить
        Button btncontinue2 = (Button) dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Level1.this, Level2.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }

                dialogEnd.dismiss();
            }
        });



        //_______________________________________________________________________

        //Кнопка назад 1 лвл
        Button btn_back = (Button) findViewById(R.id.button_back_lvl1);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();


                } catch (Exception e) {

                }
            }
        });

        //массив для прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3,
        };

        //подключаем анимацию
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);


        /*numLeft = random.nextInt(6);
        img_left.setImageResource(array.images1[numLeft]);

        numRight = random.nextInt(6);
        while (numLeft==numRight) {
            numRight=random.nextInt(6);
        }

        img_right.setImageResource(array.images1[numRight]);


        // нажатие на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false);
                    if (numLeft % 2 == 0) {
                        img_left.setImageResource(R.drawable.verno);
                    } else {
                        img_left.setImageResource(R.drawable.neverno);
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                    if (numLeft % 2 == 0) {
                        if (count < 3) {
                            count = count + 1;
                        }
                        for (int i = 0; i < 3; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Красит зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }


                    } else {

                        if (count > 0) {

                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 1;
                            }

                        }
                        for (int i = 0; i < 2; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Красит зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }
                    if (count == 3) {

                    } else {

                        numLeft = random.nextInt(6);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);

                        numRight = random.nextInt(6);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(6);
                        }

                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);

                        img_right.setEnabled(true);

                    }

                }

                return true;
            }

        });


        // нажатие на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false);
                    if (numLeft % 2 != 0) {
                        img_right.setImageResource(R.drawable.verno);
                    } else {
                        img_right.setImageResource(R.drawable.neverno);
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                    if (numLeft % 2 != 0) {
                        if (count < 3) {
                            count = count + 1;
                        }
                        for (int i = 0; i < 3; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Красит зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }


                    } else {

                        if (count > 0) {

                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 1;
                            }

                        }
                        for (int i = 0; i < 2; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Красит зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }
                    if (count == 3) {

                    } else {

                        numLeft = random.nextInt(6);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);

                        numRight = random.nextInt(6);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(6);
                        }

                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);

                        img_left.setEnabled(true);

                    }

                }

                return true;
            }

        }); */

        numLeft = getRandomNumber();
        img_left.setImageResource(array.images1[numLeft]);

        numRight = getRandomNumber();
        while (numLeft % 2 == numRight % 2) {
            numRight = getRandomNumber();
        }
        img_right.setImageResource(array.images1[numRight]);

        // Нажатие на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false);
                    if (numLeft % 2 == 0) {
                        img_left.setImageResource(R.drawable.verno);
                    } else {
                        img_left.setImageResource(R.drawable.neverno);
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft % 2 == 0) {
                        if (count < 3) {
                            count = count + 1;
                        }
                        for (int i = 0; i < 3; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Красит зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 1;
                            }
                        }
                        for (int i = 0; i < 2; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Красит зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 3) {

                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if(level>1){

                        } else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",2);
                            editor.commit();
                        }

                        dialogEnd.show();

                    } else {
                        numLeft = getRandomNumber();
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);

                        numRight = getRandomNumber();
                        while (numLeft == numRight || numLeft % 2 == numRight % 2) {
                            numRight = getRandomNumber();
                        }

                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);

                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });

        // Нажатие на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false);
                    if (numLeft % 2 != 0) {
                        img_right.setImageResource(R.drawable.verno);
                    } else {
                        img_right.setImageResource(R.drawable.neverno);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft % 2 != 0) {
                        if (count < 3) {
                            count = count + 1;
                        }
                        for (int i = 0; i < 3; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Красит зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 1;
                            }
                        }
                        for (int i = 0; i < 2; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Красит зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 3) {

                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if(level>1){

                        } else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",2);
                            editor.commit();
                        }

                        dialogEnd.show();


                    } else {
                        numLeft = getRandomNumber();
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);

                        numRight = getRandomNumber();
                        while (numLeft == numRight || numLeft % 2 == numRight % 2) {
                            numRight = getRandomNumber();
                        }

                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);

                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });


    }

    //системная кнопка назад
    @Override
    public void onBackPressed() {

        try {

            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            finish();


        } catch (Exception e) {

        }
    }

    // Метод для генерации случайных чисел
    // В случае уборки, добавить свеху }
    public int getRandomNumber() {
        return random.nextInt(6);
    }
}