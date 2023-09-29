package com.rafac183.findthem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafac183.findthem.R;
import com.rafac183.findthem.ui.home.HomeModel;
import com.rafac183.findthem.ui.registered_persons.PersonsModel;

import java.util.ArrayList;

public class FindAdapter extends RecyclerView.Adapter<FindViewHolder> {
    private final ArrayList<PersonsModel> personsList;
    private final FindInterface onClickListener;

    public FindAdapter(ArrayList<PersonsModel> personsList, FindInterface onClickListener) {
        this.personsList = personsList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_registered, parent, false);
        return new FindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {
        PersonsModel personsModel = personsList.get(position);
        holder.RenderHome(personsModel, onClickListener);
    }

    @Override
    public int getItemCount() {
        return personsList.size();
    }
}
