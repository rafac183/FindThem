package com.rafac183.findthem.ui.share;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rafac183.findthem.R;
import com.rafac183.findthem.databinding.FragmentRateUsBinding;
import com.rafac183.findthem.databinding.FragmentShareBinding;
import com.rafac183.findthem.ui.rate_us.RateUsViewModel;

public class ShareFragment extends Fragment {

    private FragmentShareBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ShareViewModel shareViewModel =
                new ViewModelProvider(this).get(ShareViewModel.class);

        binding = FragmentShareBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textShare;
        shareViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}