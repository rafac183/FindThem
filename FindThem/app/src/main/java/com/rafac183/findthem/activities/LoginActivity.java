package com.rafac183.findthem.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.snackbar.Snackbar;
import com.rafac183.findthem.databinding.ActivityLoginBinding;
import com.rafac183.findthem.R;
import com.rafac183.findthem.interfaces.ActivityInterface;

public class LoginActivity extends AppCompatActivity implements ActivityInterface {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*----------Methods----------*/
        Hilos();
    }


    /*------------Button If the user dont have account---------*/
    public void DontHaveAccount(View v){
        Intent myIntent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(myIntent);
    }

    /*-----------Button Login-----------*/
    public void LargeBtn(View v){
        String uName = binding.textUser.getText().toString().trim();
        String uPass = binding.textPass.getText().toString().trim();

        if (!uName.equals("admin") && !uPass.equals("admin123")) {
            Snackbar.make(v, "Datos Incorrectos", Snackbar.LENGTH_SHORT).show();
        } else {
            Intent myIntent = new Intent(LoginActivity.this, SplashActivity.class);
            myIntent.putExtra("mostrarProgressBar", true);
            myIntent.putExtra("user", uName);
            startActivity(myIntent);
            finish();
        }
    }

    /*----------Send Images----------*/
    @Override
    public void SendImg(){
        String bg = "https://i.ibb.co/LzNt7W9/bg.png";
        String face = "https://i.ibb.co/GVV045F/facebook.png";
        String git = "https://i.ibb.co/Fwx0HWm/github.png";
        String tw = "https://i.ibb.co/tHDNvQh/twitter.png";

        Glide.with(this).load(face).into(binding.ivFace);
        Glide.with(this).load(git).into(binding.ivGit);
        Glide.with(this).load(tw).into(binding.ivTw);

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

    @Override
    public void Hilos() {
        new Thread(() -> SendImg());
    }
}
