package com.example.TriviaGame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final SharePrefarence sharePrefarence=new SharePrefarence(Splash_Activity.this);


        //remove the title bar...
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //remove the notificaton bar......
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_splash_);

        //for Splash

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(sharePrefarence.getName()!=""&&sharePrefarence.getPassword()!=""){


                    Intent intent = new Intent(Splash_Activity.this, StartGame_Activity.class);
                    startActivity(intent);

                }
                   else {
                    Intent intent = new Intent(Splash_Activity.this, Login_Activity.class);
                    startActivity(intent);
                    finishAffinity();

                }



            }
        }, 3000);


    }
}
