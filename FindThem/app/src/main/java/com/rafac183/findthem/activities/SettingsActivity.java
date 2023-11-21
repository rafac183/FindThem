package com.rafac183.findthem.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.button.MaterialButton;
import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivitySettingsBinding;
import com.rafac183.findthem.interfaces.ActivityInterface;

public class SettingsActivity extends AppCompatActivity implements ActivityInterface {

    private ActivitySettingsBinding binding;
    private MaterialButton exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        exit = binding.exit;

        /*-----------Methods------------*/
        Hilos();
        Btns();
    }

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
                    public void onLoadCleared(@Nullable Drawable placeholder) { }
                });
    }

    @Override
    public void Hilos() {
        new Thread(() -> SendImg()).start();
    }

    private void Btns(){
        exit.setOnClickListener(v -> {
            startActivity(new Intent(SettingsActivity.this, NavigationActivity.class));
            finish();
        });
    }
}