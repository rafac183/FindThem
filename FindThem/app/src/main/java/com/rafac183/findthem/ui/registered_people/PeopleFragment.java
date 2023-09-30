package com.rafac183.findthem.ui.registered_people;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rafac183.findthem.R;
import com.rafac183.findthem.activities.RegisterInfoActivity;
import com.rafac183.findthem.adapter.FindAdapter;
import com.rafac183.findthem.adapter.FindInterface;
import com.rafac183.findthem.databinding.FragmentPeopleBinding;
import com.rafac183.findthem.ui.registered_pets.PetsModel;

public class PeopleFragment extends Fragment implements FindInterface {

    private FragmentPeopleBinding binding;
    private PeopleViewModel peopleViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        peopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class); //esto se trae la lista

        binding = FragmentPeopleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*------------Methods------------*/
        SetRecyclerView();
        onClickAdd();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void initRecyclerView(){
        LinearLayoutManager manager = new LinearLayoutManager(binding.recyclerFragmentPeople.getContext()); //Con esto puedo agregar un numero de filas especificas envez de 1
        DividerItemDecoration decoration = new DividerItemDecoration(binding.recyclerFragmentPeople.getContext(), manager.getOrientation());
        binding.recyclerFragmentPeople.setHasFixedSize(true); //Extra
        binding.recyclerFragmentPeople.setItemAnimator(new DefaultItemAnimator());//Extra
        binding.recyclerFragmentPeople.setLayoutManager(manager);
    }

    public void SetRecyclerView() {
        initRecyclerView();
        peopleViewModel.getPeopleData().observe(getViewLifecycleOwner(), peopleList -> {
            // Actualiza el RecyclerView con los nuevos datos
            binding.recyclerFragmentPeople.setAdapter(new FindAdapter(peopleList,null, PeopleFragment.this));
        });
    }

    @Override
    public void onCLickCV(PeopleModel peopleModel, PetsModel petsModel) {
        Toast.makeText(binding.recyclerFragmentPeople.getContext(), "Estamos Trabajando en Modificaciones! No Desespere!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickAdd() {
        binding.fabPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(binding.fabPeople.getContext(), RegisterInfoActivity.class);
                startActivity(myIntent);
            }
        });
    }
}