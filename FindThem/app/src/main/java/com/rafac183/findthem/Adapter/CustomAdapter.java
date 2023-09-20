package com.rafac183.findthem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafac183.findthem.Activities.HomeActivity;
import com.rafac183.findthem.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<DataModel> dataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tVName;
        TextView tVDesc;
        ImageView iVIcon;
        public ViewHolder(View itemView){
            super(itemView);
            this.tVName = (TextView) itemView.findViewById(R.id.textViewName);
            this.tVDesc = (TextView) itemView.findViewById(R.id.textViewDescription);
            this.iVIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    public CustomAdapter(ArrayList<DataModel> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_home, parent, false);
        view.setOnClickListener(HomeActivity.myOnClick);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int listPosition) {
        holder.tVName.setText(dataSet.get(listPosition).getName());
        holder.tVDesc.setText(dataSet.get(listPosition).getDescription());
        holder.iVIcon.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
