package com.rafac183.findthem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafac183.findthem.R;
import com.rafac183.findthem.ui.home.HomeModel;

import java.util.ArrayList;

public class FindAdapter extends RecyclerView.Adapter<FindViewHolder> {
    private final ArrayList<HomeModel> homeList;
    private final FindInterface onClickListener;

    public FindAdapter(ArrayList<HomeModel> homeList, FindInterface onClickListener) {
        this.homeList = homeList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_home, parent, false);
        return new FindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {
        HomeModel homeModel = homeList.get(position);
        holder.RenderHome(homeModel, onClickListener);
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }
}
