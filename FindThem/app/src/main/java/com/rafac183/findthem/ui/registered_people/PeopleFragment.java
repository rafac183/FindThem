package com.rafac183.findthem.ui.registered_people;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rafac183.findthem.activities.NavigationActivity;
import com.rafac183.findthem.activities.RegisterInfoActivity;
import com.rafac183.findthem.adapter.FindAdapter;
import com.rafac183.findthem.dialog.ViewDialogConfirmDelete;
import com.rafac183.findthem.dialog.ViewDialogUpdate;
import com.rafac183.findthem.interfaces.FindInterface;
import com.rafac183.findthem.databinding.FragmentPeopleBinding;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

import java.util.ArrayList;
import java.util.Objects;

public class PeopleFragment extends Fragment implements FindInterface {

    private FragmentPeopleBinding binding;
    private Context context;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PeopleViewModel peopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class); //esto se trae la lista

        binding = FragmentPeopleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        peopleViewModel.getPeopleData().observe(getViewLifecycleOwner(), this::initRecyclerView);

        peopleViewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.chargingScreen.setVisibility(View.VISIBLE);
                binding.fabPeople.setVisibility(View.GONE);
            } else {
                binding.chargingScreen.setVisibility(View.GONE);
                binding.fabPeople.setVisibility(View.VISIBLE);
            }
        });

        /*------------Methods------------*/
        onClickAdd();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void initRecyclerView(ArrayList<PeopleModel> peopleList){
        LinearLayoutManager manager = new LinearLayoutManager(binding.recyclerFragmentPeople.getContext()); //Con esto puedo agregar un numero de filas especificas envez de 1
        DividerItemDecoration decoration = new DividerItemDecoration(binding.recyclerFragmentPeople.getContext(), manager.getOrientation());
        binding.recyclerFragmentPeople.setHasFixedSize(true); //Extra
        binding.recyclerFragmentPeople.setItemAnimator(new DefaultItemAnimator());//Extra
        binding.recyclerFragmentPeople.setLayoutManager(manager);
        binding.recyclerFragmentPeople.setAdapter(new FindAdapter(peopleList,null, PeopleFragment.this));
    }

    @Override
    public void onCLickUpdate(PeopleModel peopleModel, PetsModel petsModel) {
        ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
        viewDialogUpdate.showDialogUpdate(requireContext(), peopleModel.getId(), peopleModel.getName(), peopleModel.getLastname(), peopleModel.getPhone(), peopleModel.getGender(), 0);
    }

    @Override
    public void onCLickDelete(PeopleModel peopleModel, PetsModel petsModel) {
        ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
        viewDialogConfirmDelete.showDialogDelete(requireContext(), peopleModel.getId(), 0);
    }

    @Override
    public void onClickAdd() {
        binding.fabPeople.setOnClickListener(view -> {
            Intent myIntent = new Intent(binding.fabPeople.getContext(), RegisterInfoActivity.class);
            startActivity(myIntent);
            requireActivity().finish();
        });
    }
}