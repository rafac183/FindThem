package com.rafac183.findthem.activities;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;
import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivitySignUpBinding;
import com.rafac183.findthem.interfaces.ActivityInterface;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements ActivityInterface {

    private ActivitySignUpBinding binding;
    private EditText tvEmail, tvUsername, tvPass;
    private Button signIn;
    private FirebaseAuth authentication;
    private FirebaseFirestore authStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*---------Variables---------*/
        tvEmail = binding.tvEmail;
        tvUsername = binding.tvUsername;
        tvPass = binding.tvPass;
        signIn = binding.BtnSignIn;
        authentication = FirebaseAuth.getInstance();
        authStore = FirebaseFirestore.getInstance();

        /*---------Methods--------*/
        Hilos();
        Btns();

        if(authentication.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
            finish();
        }
    }



    /*-------------Button If the User Have Account------------*/
    public void HaveAccount(View v){
        Intent myIntent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }

    /*-------------Button Create Account------------*/
    public void Btns() {
        signIn.setOnClickListener(v -> {
            String username = tvUsername.getText().toString().trim();
            String email = tvEmail.getText().toString().trim();
            String password = tvPass.getText().toString().trim();
            Map<String, Object> user = new HashMap<>();
            user.put("username",username);
            user.put("email",email);
            if(TextUtils.isEmpty(email)){
                tvEmail.setError("Email is required");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                tvPass.setError("Password is required");
            }
            if(password.length() < 6){
                tvPass.setError("Password must be at least 6 characters");
                return;
            }
            //FireStore
            authStore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    finish();
                }
            });

            //Firebase Authentication
            authentication.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        });
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

    public void Hilos(){
        new Thread(() -> SendImg());
    }
}
