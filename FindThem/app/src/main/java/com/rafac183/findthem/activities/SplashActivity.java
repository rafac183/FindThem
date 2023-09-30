package com.rafac183.findthem.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivitySplashBinding;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity implements ActivityInterface{
    private ActivitySplashBinding binding;
    private String username;
    int count = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        username = getIntent().getStringExtra("user");

        /*-------Methods--------*/
        SendImg();
        ProgressBar();
    }
    /*----------Load Bar-----------*/
    public void ProgressBar(){
        ProgressBar progressBar = findViewById(R.id.DeterminatePB);
        final Timer tiempo = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                count++;
                progressBar.setProgress(count);
                if(count == 100){
                    Intent intent = new Intent(SplashActivity.this, NavigationActivity.class);
                    intent.putExtra("user", username);
                    startActivity(intent);
                    finish();
                    tiempo.cancel();
                }
            }
        };
        tiempo.schedule(tarea,0, 50);
    }

    @Override
    public void LargeBtn(View v) {}

    /*-----------Send Images-----------*/
    @Override
    public void SendImg() {
        String bg = "https://i.ibb.co/LzNt7W9/bg.png";

        Glide.with(this)
                .load(bg)
                .apply(new RequestOptions()
                        .centerCrop()) // Ajusta la imagen al tamaño del RelativeLayout
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        // Establece el fondo del RelativeLayout
                        binding.rlParent.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {}
                });
    }
}
