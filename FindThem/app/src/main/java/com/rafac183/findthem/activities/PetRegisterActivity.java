package com.rafac183.findthem.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivityPetRegisterBinding;
import com.rafac183.findthem.interfaces.ActivityInterface;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

import java.util.Date;

public class PetRegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ActivityInterface {

    private ActivityPetRegisterBinding binding;
    private MaterialButton exit, enterData;
    private DatabaseReference reference = null;
    String[] gender = {"Choose one", "Male", "Female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivityPetRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        exit = binding.exit;
        enterData = binding.enterData;
        reference = FirebaseDatabase.getInstance().getReference();

        /*--------Methods--------*/
        Hilos();
        Btns();
        Spinner();
    }


    public void LargeBtn(View v) {
        Toast.makeText(binding.rlParent.getContext(), "Estamos Trabajando en Modificaciones! No Desespere!", Toast.LENGTH_SHORT).show();
    }

    /*---------Send Images---------*/
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

    private void Btns(){
        enterData.setOnClickListener(v -> {
            String id = "User" + new Date().getTime();
            String name = binding.edNamePet.getText().toString().trim();
            String codeStr = binding.edCodePet.getText().toString();
            String img;
            int code;
            if (TextUtils.isEmpty(name)) {
                binding.edNamePet.setError("Enter a Name");
            }
            if (TextUtils.isEmpty(codeStr)) {
                binding.edCodePet.setError("Enter a Code");
            }
            if (binding.spinnerGender.getSelectedItemPosition() == 0) {
                View selectedView = binding.spinnerGender.getSelectedView();
                if (selectedView instanceof TextView) {
                    ((TextView) selectedView).setError("Select a Gender");
                }
            } else {
                code = Integer.parseInt(codeStr);
                Object selectedGender = binding.spinnerGender.getSelectedItem();
                String gender = selectedGender != null ? selectedGender.toString() : "";
                if (gender.equals("Male")){
                    img = "https://i.ibb.co/87Vq8hB/lindo-perro-estudio-removebg-preview.png";
                }else {
                    img = "https://i.ibb.co/Zc7Z5zy/adorable-cachorro-estudio-removebg-preview.png";
                }
                reference.child("Pets").child(id).setValue(new PetsModel(id, name, gender,code, img))
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(getBaseContext(), "Pet Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PetRegisterActivity.this, NavigationActivity.class));
                            finish();
                        }).addOnFailureListener(e -> {
                            Toast.makeText(getBaseContext(), "Error creating", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PetRegisterActivity.this, NavigationActivity.class));
                            finish();
                        });
            }
        });
        exit.setOnClickListener(v -> {
            startActivity(new Intent(PetRegisterActivity.this, NavigationActivity.class));
            finish();
        });
    }
    private void Spinner(){
        binding.spinnerGender.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinnerGender.setAdapter(aa);
        binding.spinnerGender.setSelection(0, false);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
