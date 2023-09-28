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
import android.widget.CheckBox;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements ActivityInterface {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*---------Methods--------*/
        SendImg();
    }

    /*-------------Button Create Account------------*/
    public void BtnSignIn(View v) {
        /*if (seleccionarChk.isChecked() == true){
            Toast tost = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
            tost.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 90, 0);
            tost.show();
        } else {
            mensaje = "No Seleccionado";
            Toast tost = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
            tost.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 90, 0);
            tost.show();
        }*/
        Toast.makeText(this, "Registro Completado", Toast.LENGTH_SHORT).show();
    }

    /*-------------Button If the User Have Account------------*/
    public void HaveAccount(View v){
        Intent myIntent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void SendImg() {
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
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }
}
