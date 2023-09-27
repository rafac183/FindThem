package com.rafac183.findthem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.rafac183.findthem.databinding.ActivityLoginBinding;
import com.rafac183.findthem.model.LoginModel;
import com.rafac183.findthem.R;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private final LoginModel loginModel = new LoginModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void BtnLogin(View v){
        String uName = binding.textUser.getText().toString();
        String uPass = binding.textPass.getText().toString();


        if (!uName.equals("admin") && !uPass.equals("admin123")) {
            Snackbar.make(v, "Datos Incorrectos", Snackbar.LENGTH_SHORT).show();
        } else {
            Intent myIntent = new Intent(LoginActivity.this, SplashActivity.class);
            startActivity(myIntent);
        }
        loginModel.setuName(uName);
        loginModel.setuPass(uPass);
    }

    public void DontHaveAccount(View v){
        Intent myIntent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(myIntent);
    }

    public void SendProfile() {

    }
}
