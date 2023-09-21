package com.rafac183.findthem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.rafac183.findthem.Model.LoginModel;
import com.rafac183.findthem.R;

public class LoginActivity extends AppCompatActivity {
    private EditText user, pass;
    private String uName, uPass;
    private LoginModel loginModel = new LoginModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.textUser);
        pass = (EditText) findViewById(R.id.textPass);


    }
    public void BtnLogin(View v){
        uName = user.getText().toString();
        uPass = pass.getText().toString();


        if (!uName.equals("admin") && !uPass.equals("admin123")) {
            Snackbar.make(v, "Datos Incorrectos", Snackbar.LENGTH_SHORT).show();
        } else {
            Intent myIntent = new Intent(LoginActivity.this, NavigationActivity.class);
            startActivity(myIntent);
        }
        loginModel.setuName(uName);
        loginModel.setuPass(uPass);
    }

    public void DontHaveAccount(View v){
        Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(myIntent);
    }
}
