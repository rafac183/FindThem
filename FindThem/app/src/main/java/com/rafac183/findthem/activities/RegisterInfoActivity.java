package com.rafac183.findthem.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivityRegisterInfoBinding;

public class RegisterInfoActivity extends AppCompatActivity {
    private ActivityRegisterInfoBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
