package com.rafac183.findthem.ui.registered_pets;

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
import com.rafac183.findthem.activities.PetRegisterActivity;
import com.rafac183.findthem.adapter.FindAdapter;
import com.rafac183.findthem.adapter.FindInterface;
import com.rafac183.findthem.databinding.FragmentPetsBinding;
import com.rafac183.findthem.ui.registered_people.PeopleModel;

public class PetsFragment extends Fragment implements FindInterface {

    private FragmentPetsBinding binding;
    private PetsViewModel petsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        petsViewModel = new ViewModelProvider(this).get(PetsViewModel.class);

        binding = FragmentPetsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*--------------Methods--------------*/
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
        LinearLayoutManager manager = new LinearLayoutManager(binding.recyclerFragmentPets.getContext()); //Con esto puedo agregar un numero de filas especificas envez de 1
        DividerItemDecoration decoration = new DividerItemDecoration(binding.recyclerFragmentPets.getContext(), manager.getOrientation());
        binding.recyclerFragmentPets.setHasFixedSize(true); //Extra
        binding.recyclerFragmentPets.setItemAnimator(new DefaultItemAnimator());//Extra
        binding.recyclerFragmentPets.setLayoutManager(manager);
    }

    public void SetRecyclerView() {
        initRecyclerView();
        petsViewModel.getPetsData().observe(getViewLifecycleOwner(), petsList -> {
            // Actualiza el RecyclerView con los nuevos datos
            binding.recyclerFragmentPets.setAdapter(new FindAdapter(null,petsList, PetsFragment.this));
        });
    }

    @Override
    public void onCLickCV(PeopleModel peopleModel, PetsModel petsModel) {
        Toast.makeText(binding.recyclerFragmentPets.getContext(), "Estamos Trabajando en Modificaciones! No Desespere!", Toast.LENGTH_SHORT).show();
    }

    public void onClickAdd() {
        binding.fabPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(binding.fabPets.getContext(), PetRegisterActivity.class);
                startActivity(myIntent);
            }
        });
    }
}