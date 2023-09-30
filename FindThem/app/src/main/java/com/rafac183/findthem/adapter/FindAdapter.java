package com.rafac183.findthem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafac183.findthem.R;
import com.rafac183.findthem.ui.registered_people.PeopleModel;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

import java.util.ArrayList;

public class FindAdapter extends RecyclerView.Adapter<FindViewHolder> {
    private final ArrayList<PeopleModel> peopleList;
    private final ArrayList<PetsModel> petsList;
    private final FindInterface onClickListener;
    private boolean isPeopleList;

    public FindAdapter(ArrayList<PeopleModel> peopleList, ArrayList<PetsModel> petsList, FindInterface onClickListener) {
        this.peopleList = peopleList;
        this.petsList = petsList;
        this.onClickListener = onClickListener;

        // Establecer la condición para determinar cuál lista se usará
        isPeopleList = peopleList != null && !peopleList.isEmpty();
    }

    @NonNull
    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_registered, parent, false);
        return new FindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {
        if(peopleList == null){
            PetsModel petsModel = petsList.get(position);
            holder.RenderPets(petsModel, onClickListener);
        } else {
            PeopleModel peopleModel = peopleList.get(position);
            holder.RenderPeople(peopleModel, onClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return isPeopleList ? peopleList.size() : petsList.size();
    }

}
