package com.rafac183.findthem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafac183.findthem.R;
import com.rafac183.findthem.interfaces.MqttInterface;
import com.rafac183.findthem.ui.registered_people.PeopleModel;

import java.util.ArrayList;

public class MqttAdapter extends RecyclerView.Adapter<MqttViewHolder> {
    private final ArrayList<PeopleModel> peopleList;
    private final MqttInterface onClickBtns;

    public MqttAdapter(ArrayList<PeopleModel> peopleList, MqttInterface onClickBtns) {
        this.peopleList = peopleList;
        this.onClickBtns = onClickBtns;
    }

    @NonNull
    @Override
    public MqttViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mqtt, parent, false);
        return new MqttViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MqttViewHolder holder, int position) {
        PeopleModel peopleModel = peopleList.get(position);
        holder.RenderMqtt(peopleModel, onClickBtns);
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

}
