package com.rafac183.findthem.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rafac183.findthem.databinding.CardViewRegisteredBinding;
import com.rafac183.findthem.interfaces.FindInterface;
import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

public class FindViewHolder extends RecyclerView.ViewHolder {

    private final CardViewRegisteredBinding binding;
    public FindViewHolder(View item){
        super(item);
        binding = CardViewRegisteredBinding.bind(item);
    }

    public void RenderPeople(PeopleModel persons, FindInterface onClickBtns) {
        String completeName = persons.getName() + " " + persons.getLastname();
        String description = "Gender: " + persons.getGender() + "\nPhone: " + persons.getPhone();
        binding.textViewName.setText(completeName);
        binding.textViewDescription.setText(description);
        Glide.with(binding.imageViewRegistered.getContext()).load(persons.getImage()).into(binding.imageViewRegistered);

        binding.btnEdit.setOnClickListener(v -> onClickBtns.onCLickUpdate(persons, null));
        binding.btnDelete.setOnClickListener(v -> onClickBtns.onCLickDelete(persons, null));
    }

    public void RenderPets(PetsModel pets, FindInterface onClickBtns) {
        String description = "Gender: " + pets.getGender() + "\nCode: " + pets.getCode();
        binding.textViewName.setText(pets.getName());
        binding.textViewDescription.setText(description);
        Glide.with(binding.imageViewRegistered.getContext()).load(pets.getImage()).into(binding.imageViewRegistered);

        binding.btnEdit.setOnClickListener(v -> onClickBtns.onCLickUpdate(null, pets));
        binding.btnDelete.setOnClickListener(v -> onClickBtns.onCLickDelete(null, pets));
    }
}
