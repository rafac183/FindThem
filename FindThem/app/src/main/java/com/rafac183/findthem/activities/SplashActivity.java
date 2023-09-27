package com.rafac183.findthem.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivitySplashBinding;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    private ActivitySplashBinding binding;

    int count = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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


        tiempo.schedule(tarea,0, 50);


    }
}
