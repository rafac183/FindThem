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
import com.google.firebase.firestore.FirebaseFirestore;
import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivityRegisterInfoBinding;
import com.rafac183.findthem.interfaces.ActivityInterface;

import java.util.HashMap;
import java.util.Map;

public class RegisterInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ActivityInterface {
    private ActivityRegisterInfoBinding binding;
    private MaterialButton exit, enterData;
    private FirebaseFirestore authStore;
    String[] gender = {"Choose one", "Male", "Female"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        exit = binding.exit;
        enterData = binding.enterData;
        authStore = FirebaseFirestore.getInstance();

        /*--------Methods--------*/
        Hilos();
        Btns();
        Spinner();
    }

    public void LargeBtn(View v) {
        Toast.makeText(binding.rlParent.getContext(), "Estamos Trabajando en Modificaciones! No Desespere!", Toast.LENGTH_SHORT).show();
    }

    /*----------Send Images-----------*/
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
                        // Establece el fondo del RelativeLayout
                        binding.rlParent.setBackground(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) { }
                });
    }

    @Override
    public void Hilos() {
        new Thread(() -> SendImg()).start();
    }

    private void Btns(){
        enterData.setOnClickListener(v -> {
            String name = binding.edName.getText().toString().trim();
            String lastname = binding.edLastName.getText().toString().trim();
            String phone = binding.edPhone.getText().toString().trim();
            int code;
            if (TextUtils.isEmpty(name)) {
                binding.edName.setError("Enter a Name");
            }
            if (TextUtils.isEmpty(lastname)) {
                binding.edLastName.setError("Enter a Last Name");
            }
            if (TextUtils.isEmpty(phone)) {
                binding.edPhone.setError("Enter a Phone");
            }
            if (binding.spinnerGender.getSelectedItemPosition() == 0) {
                View selectedView = binding.spinnerGender.getSelectedView();
                if (selectedView instanceof TextView) {
                    ((TextView) selectedView).setError("Select a Gender");
                }
            } else {
                Object selectedGender = binding.spinnerGender.getSelectedItem();
                Map<String, Object> pet = new HashMap<>();
                pet.put("name",name);
                pet.put("lastname",lastname);
                pet.put("phone",phone);
                pet.put("gender",selectedGender != null ? selectedGender.toString() : "");
                authStore.collection("people").add(pet).addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Person Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), NavigationActivity.class));
                    finish();
                }).addOnFailureListener(e -> {
                    Toast.makeText(getBaseContext(), "Error creating", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), NavigationActivity.class));
                    finish();
                });
            }
        });
        exit.setOnClickListener(v -> {
            startActivity(new Intent(RegisterInfoActivity.this, NavigationActivity.class));
            finish();
        });
    }
    private void Spinner(){
        binding.spinnerGender.setOnItemSelectedListener(this);

        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinnerGender.setAdapter(aa);
        binding.spinnerGender.setSelection(0, false);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
