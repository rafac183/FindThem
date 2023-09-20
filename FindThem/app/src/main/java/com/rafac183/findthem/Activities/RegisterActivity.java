package com.rafac183.findthem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.rafac183.findthem.R;

public class RegisterActivity extends AppCompatActivity {

    private CheckBox seleccionarChk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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
        Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }
}
