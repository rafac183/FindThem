package com.rafac183.findthem.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rafac183.findthem.databinding.CardViewRegisteredBinding;
import com.rafac183.findthem.ui.registered_persons.PersonsModel;

public class FindViewHolder extends RecyclerView.ViewHolder {

    private final CardViewRegisteredBinding binding;
    public FindViewHolder(View item){
        super(item);
        binding = CardViewRegisteredBinding.bind(item);
    }

    public void RenderHome(PersonsModel persons, FindInterface onclick) {
        binding.textViewName.setText(persons.getName());
        binding.textViewDescription.setText(persons.getDescription());
        Glide.with(binding.imageViewHome.getContext()).load(persons.getImage()).into(binding.imageViewHome);

        binding.cardViewHome.setOnClickListener(v -> onclick.onCLickCardView(persons));
    }
}
