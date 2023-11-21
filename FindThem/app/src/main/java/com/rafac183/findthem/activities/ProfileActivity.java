package com.rafac183.findthem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.google.android.material.button.MaterialButton;
import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private MaterialButton exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        exit = binding.exit;

        binding.tvProfile.setText("Modify your Profile!");

        /*--------------Methods--------------*/
        Btns();
    }

    private void Btns(){
        exit.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, NavigationActivity.class));
            finish();
        });
    }
}