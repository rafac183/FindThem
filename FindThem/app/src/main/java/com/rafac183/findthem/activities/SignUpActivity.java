package com.rafac183.findthem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.Toast;

import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

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

    public void HaveAccount(View v){
        Intent myIntent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }
}
