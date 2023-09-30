package com.rafac183.findthem.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.ActivityRateUsBinding;

public class RateUsActivity extends AppCompatActivity {

    private ActivityRateUsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_primary));
        super.onCreate(savedInstanceState);
        binding = ActivityRateUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*--------Methods----------*/
        SubmitRating();
    }

    public void SubmitRating() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = String.valueOf(binding.ratingBar.getRating());
                switch (rating) {
                    case "0.0":
                    case "0.5":
                        Toast.makeText(binding.ratingBar.getContext(), rating + "? Nada?!", Toast.LENGTH_SHORT).show();
                        break;
                    case "1.0":
                    case "1.5":
                        Toast.makeText(binding.ratingBar.getContext(), rating + "? Eres muy Exigente!", Toast.LENGTH_SHORT).show();
                        break;
                    case "2.0":
                    case "2.5":
                        Toast.makeText(binding.ratingBar.getContext(), rating + "? Que mas Quieres? ", Toast.LENGTH_SHORT).show();
                        break;
                    case "3.0":
                    case "3.5":
                        Toast.makeText(binding.ratingBar.getContext(), rating + "? Bueh Algo", Toast.LENGTH_SHORT).show();
                        break;
                    case "4.0":
                    case "4.5":
                        Toast.makeText(binding.ratingBar.getContext(), rating + "? Perfeccionista!", Toast.LENGTH_SHORT).show();
                        break;
                    case "5.0":
                        Toast.makeText(binding.ratingBar.getContext(), rating + "? Por Fin Buena Nota! ", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}