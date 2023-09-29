package com.rafac183.findthem.ui.registered_pets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.rafac183.findthem.activities.LoginActivity;
import com.rafac183.findthem.activities.NavigationActivity;
import com.rafac183.findthem.activities.PetRegisterActivity;
import com.rafac183.findthem.databinding.FragmentPetsBinding;

public class PetsFragment extends Fragment {

    private FragmentPetsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PetsViewModel petsViewModel =
                new ViewModelProvider(this).get(PetsViewModel.class);

        binding = FragmentPetsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*--------------Methods--------------*/
        onClickAdd();

        final TextView textView = binding.textSlideshow;
        petsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onClickAdd() {
        binding.fabPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(binding.fabPets.getContext(), PetRegisterActivity.class);
                startActivity(myIntent);
            }
        });
    }
}