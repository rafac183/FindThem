package com.rafac183.findthem.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rafac183.findthem.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    int count = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar progressBar = findViewById(R.id.DeterminatePB);
        final Timer tiempo = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                count++;
                progressBar.setProgress(count);

                if(count == 100){
                    Intent intent = new Intent(SplashActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    tiempo.cancel();
                }
            }
        };


        tiempo.schedule(tarea,0, 100);


    }
}
