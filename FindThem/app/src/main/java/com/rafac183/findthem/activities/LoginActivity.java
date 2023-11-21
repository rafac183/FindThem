package com.rafac183.findthem.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rafac183.findthem.databinding.ActivityLoginBinding;
import com.rafac183.findthem.R;
import com.rafac183.findthem.interfaces.ActivityInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements ActivityInterface {
    private ActivityLoginBinding binding;
    private EditText tvEmail, tvUsername, tvPass;
    private Button login;
    private FirebaseAuth authentication;
    private FirebaseFirestore authStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvUsername = binding.textUser;
        tvPass = binding.textPass;
        login = binding.BtnLogin;

        authentication = FirebaseAuth.getInstance();
        authentication.signOut();
        authStore = FirebaseFirestore.getInstance();

        /*----------Methods----------*/
        SendIcon();
        Hilos();
        Btns();
    }


    /*------------Button If the user dont have account---------*/
    public void DontHaveAccount(View v){
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    /*-----------Button Login-----------*/
    public void Btns(){
        login.setOnClickListener(v -> {
            String username = tvUsername.getText().toString().trim();
            String password = tvPass.getText().toString().trim();
            String[] email = new String[1];

            if(TextUtils.isEmpty(username)){
                tvUsername.setError("Please enter a username");
            }
            if (TextUtils.isEmpty(password)) {
                tvPass.setError("Password is required");
            }
            authStore.collection("users").whereEqualTo("username", username).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    if (!task.getResult().isEmpty()) {
                        for (QueryDocumentSnapshot document :task.getResult()){
                            email[0] = document.get("email").toString();
                        }
                        login(username, email, password);
                    }else {
                        Toast.makeText(LoginActivity.this, "Unregistered User", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(e -> finish());

        });
    }

    private void login(String username, String[] email, String password){
        authentication.signInWithEmailAndPassword(email[0], password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Logged is Successfully", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(LoginActivity.this, SplashActivity.class);
                    myIntent.putExtra("mostrarProgressBar", true);
                    myIntent.putExtra("user", username);
                    startActivity(myIntent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*----------Send Images----------*/
    public void SendIcon(){
        String face = "https://i.ibb.co/GVV045F/facebook.png";
        String git = "https://i.ibb.co/Fwx0HWm/github.png";
        String tw = "https://i.ibb.co/tHDNvQh/twitter.png";

        Glide.with(this).load(face).into(binding.ivFace);
        Glide.with(this).load(git).into(binding.ivGit);
        Glide.with(this).load(tw).into(binding.ivTw);

    }
    @Override
    public void SendImg(){
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

    @Override
    public void Hilos() {
        new Thread(() -> SendImg()).start();
    }
}
