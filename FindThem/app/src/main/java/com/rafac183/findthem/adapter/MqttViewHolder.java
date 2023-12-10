package com.rafac183.findthem.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rafac183.findthem.databinding.CardViewMqttBinding;
import com.rafac183.findthem.databinding.CardViewRegisteredBinding;
import com.rafac183.findthem.interfaces.FindInterface;
import com.rafac183.findthem.interfaces.MqttInterface;
import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

public class MqttViewHolder extends RecyclerView.ViewHolder {

    private final CardViewMqttBinding binding;
    public MqttViewHolder(View item){
        super(item);
        binding = CardViewMqttBinding.bind(item);
    }

    public void RenderMqtt(PeopleModel persons, MqttInterface onClickBtns) {
        String completeName = persons.getName() + " " + persons.getLastname();
        String description = "Gender: " + persons.getGender() + "\nPhone: " + persons.getPhone();
        binding.textViewName.setText(completeName);
        binding.textViewDescription.setText(description);
        Glide.with(binding.imageViewRegistered.getContext()).load(persons.getImage()).into(binding.imageViewRegistered);

        binding.LYCV.setTag(persons.getId());

        binding.btnActivate.setOnClickListener(v -> onClickBtns.onCLickActivate());
        binding.btnDefuse.setOnClickListener(v -> onClickBtns.onCLickDefuse());
    }
}
