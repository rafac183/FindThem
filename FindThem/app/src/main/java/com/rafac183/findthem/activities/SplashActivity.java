package com.rafac183.findthem.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
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
    private boolean mostrarProgressBar = false;
    int count = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        mostrarProgressBar = intent.getBooleanExtra("mostrarProgressBar", false);

        /*-------Methods--------*/
        SendImg();
        ProgressBar();
    }
    /*----------Load Bar-----------*/
    public void ProgressBar(){
        ProgressBar progressBar = binding.DeterminatePB;

        if (mostrarProgressBar){
            progressBar.setVisibility(View.VISIBLE);
            final Timer time = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    count++;
                    progressBar.setProgress(count);
                    if(count == 100){
                        Intent intent = new Intent(SplashActivity.this, NavigationActivity.class);
                        username = getIntent().getStringExtra("user");
                        intent.putExtra("user", username);
                        startActivity(intent);
                        finish();
                        time.cancel();
                    }
                }
            };
            time.schedule(task,0, 50);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }, 2000);
        }

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
