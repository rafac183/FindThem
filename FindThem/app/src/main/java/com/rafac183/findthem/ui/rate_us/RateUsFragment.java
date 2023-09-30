package com.rafac183.findthem.ui.rate_us;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rafac183.findthem.databinding.FragmentRateUsBinding;

public class RateUsFragment extends Fragment {

    private FragmentRateUsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RateUsViewModel rateUSViewModel = new ViewModelProvider(this).get(RateUsViewModel.class);
        binding = FragmentRateUsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRate;
        rateUSViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        /*-----------Methods----------*/
        SubmitRating();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void SubmitRating() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = String.valueOf(binding.ratingBar.getRating());
                switch (rating) {
                    case "0.0":
                    case "0.5":
                        Toast.makeText(binding.ratingBar.getContext(), rating + " Nada?!", Toast.LENGTH_SHORT).show();
                        break;
                    case "1.0":
                    case "1.5":
                        Toast.makeText(binding.ratingBar.getContext(), rating + " Eres muy Exigente!", Toast.LENGTH_SHORT).show();
                        break;
                    case "2.0":
                    case "2.5":
                        Toast.makeText(binding.ratingBar.getContext(), rating + " Que mas Quieres? ", Toast.LENGTH_SHORT).show();
                        break;
                    case "3.0":
                    case "3.5":
                        Toast.makeText(binding.ratingBar.getContext(), rating + " Bueh Algo", Toast.LENGTH_SHORT).show();
                        break;
                    case "4.0":
                    case "4.5":
                        Toast.makeText(binding.ratingBar.getContext(), rating + " Perfeccionista!", Toast.LENGTH_SHORT).show();
                        break;
                    case "5.0":
                        Toast.makeText(binding.ratingBar.getContext(), rating + " Por Fin Buena Nota! ", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}