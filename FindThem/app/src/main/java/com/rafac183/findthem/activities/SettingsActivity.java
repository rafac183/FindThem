package com.rafac183.findthem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}