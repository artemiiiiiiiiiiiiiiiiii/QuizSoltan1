package irlandec.prophet.quiz;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level1",1);

        Window w = getWindow();
                w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);finish();
                } catch (Exception e) {

                }


            }
        });

        //переход на первый уровень
        TextView textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (level<=1) {
                        Intent intent = new Intent(GameLevels.this, Level1.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }
                } catch (Exception e) {

                }
            }
        });

       //переход на второй уровень
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(level<=2){
                    Intent intent = new Intent(GameLevels.this, Level2.class);
                    startActivity(intent); finish();} else{

                    }
                } catch (Exception e) {

                }
            }
        });

        final int[] x = {
            R.id.textView1,
            R.id.textView2,
        };

        for(int i=1; i<=level; i++) {
            TextView tv = findViewById(x[i]);
            tv.setText(""+(i+1));
        }



    }
    //Системная кнопка назад
    public void onBackPressed(){

        try {
            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);finish();
        } catch (Exception e) {

        }

    }
}