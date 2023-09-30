package com.rafac183.findthem.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rafac183.findthem.databinding.CardViewRegisteredBinding;
import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

public class FindViewHolder extends RecyclerView.ViewHolder {

    private final CardViewRegisteredBinding binding;
    public FindViewHolder(View item){
        super(item);
        binding = CardViewRegisteredBinding.bind(item);
    }

    public void RenderPeople(PeopleModel persons, FindInterface onclick) {
        binding.textViewName.setText(persons.getName());
        binding.textViewDescription.setText(persons.getDescription());
        Glide.with(binding.imageViewRegistered.getContext()).load(persons.getImage()).into(binding.imageViewRegistered);

        binding.cardViewRegistered.setOnClickListener(v -> onclick.onCLickCV(persons, null));
    }

    public void RenderPets(PetsModel pets, FindInterface onclick) {
        binding.textViewName.setText(pets.getName());
        binding.textViewDescription.setText(pets.getDescription());
        Glide.with(binding.imageViewRegistered.getContext()).load(pets.getImage()).into(binding.imageViewRegistered);

        binding.cardViewRegistered.setOnClickListener(v -> onclick.onCLickCV(null, pets));
    }
}
