package com.rafac183.findthem.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rafac183.findthem.databinding.CardViewHomeBinding;
import com.rafac183.findthem.ui.home.HomeModel;

public class FindViewHolder extends RecyclerView.ViewHolder {

    private final CardViewHomeBinding binding;
    public FindViewHolder(View item){
        super(item);
        binding = CardViewHomeBinding.bind(item);
    }

    public void RenderHome(HomeModel home, FindInterface onclick) {
        binding.textViewName.setText(home.getName());
        binding.textViewDescription.setText(home.getDescription());
        Glide.with(binding.imageViewHome.getContext()).load(home.getImage()).into(binding.imageViewHome);

        binding.cardViewHome.setOnClickListener(v -> onclick.onClickListener(home));
    }
}
