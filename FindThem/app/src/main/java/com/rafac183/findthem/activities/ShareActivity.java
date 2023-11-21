package com.rafac183.findthem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.google.android.material.button.MaterialButton;
import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivityShareBinding;

public class ShareActivity extends AppCompatActivity {

    private ActivityShareBinding binding;
    private MaterialButton exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivityShareBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        exit = binding.exit;

        binding.tvShare.setText("Share Our App!");

        /*---------Methods----------*/
        Btns();
    }

    private void Btns(){
        exit.setOnClickListener(v -> {
            startActivity(new Intent(ShareActivity.this, NavigationActivity.class));
            finish();
        });
    }
}