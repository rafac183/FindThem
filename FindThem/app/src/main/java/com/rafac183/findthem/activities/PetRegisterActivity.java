package com.rafac183.findthem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivityPetRegisterBinding;

public class PetRegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityPetRegisterBinding binding;
    String[] gender = {"Masculino", "Femenino"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPetRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.spinnerGender.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinnerGender.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*public void btnChekPet(View v) {
        String mensaje;
        if (binding.isChecked() == true) {
            Intent myIntent = new Intent(PetRegisterActivity.this, HomeActivity.class);
            startActivity(myIntent);
        } else {
            mensaje = "No Seleccionado";
            Toast tost = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
            tost.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 90, 0);
            tost.show();
        }

    }*/
}
