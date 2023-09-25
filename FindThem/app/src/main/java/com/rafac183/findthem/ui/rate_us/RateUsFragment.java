package com.rafac183.findthem.ui.rate_us;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rafac183.findthem.databinding.FragmentRateUsBinding;

public class RateUsFragment extends Fragment {

    private FragmentRateUsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RateUsViewModel rateUSViewModel =
                new ViewModelProvider(this).get(RateUsViewModel.class);

        binding = FragmentRateUsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRate;
        rateUSViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}