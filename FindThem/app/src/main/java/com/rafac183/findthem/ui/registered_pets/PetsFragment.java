package com.rafac183.findthem.ui.registered_pets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rafac183.findthem.activities.PetRegisterActivity;
import com.rafac183.findthem.adapter.FindAdapter;
import com.rafac183.findthem.dialog.ViewDialogConfirmDelete;
import com.rafac183.findthem.dialog.ViewDialogUpdate;
import com.rafac183.findthem.interfaces.FindInterface;
import com.rafac183.findthem.databinding.FragmentPetsBinding;
import com.rafac183.findthem.ui.registered_people.PeopleModel;

import java.util.ArrayList;

public class PetsFragment extends Fragment implements FindInterface {

    private FragmentPetsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        PetsViewModel petsViewModel = new ViewModelProvider(this).get(PetsViewModel.class);

        binding = FragmentPetsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*--------------Methods--------------*/
        petsViewModel.getPetsData().observe(getViewLifecycleOwner(), new Observer<ArrayList<PetsModel>>() {
            @Override
            public void onChanged(ArrayList<PetsModel> petsModels) {
                initRecyclerView(petsModels);
            }
        });

        petsViewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.chargingScreen.setVisibility(View.VISIBLE);
                binding.fabPets.setVisibility(View.GONE);
            } else {
                binding.chargingScreen.setVisibility(View.GONE);
                binding.fabPets.setVisibility(View.VISIBLE);
            }
        });
        onClickAdd();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void initRecyclerView(ArrayList<PetsModel> petsList){
        LinearLayoutManager manager = new LinearLayoutManager(binding.recyclerFragmentPets.getContext()); //Con esto puedo agregar un numero de filas especificas envez de 1
        DividerItemDecoration decoration = new DividerItemDecoration(binding.recyclerFragmentPets.getContext(), manager.getOrientation());
        binding.recyclerFragmentPets.setHasFixedSize(true); //Extra
        binding.recyclerFragmentPets.setItemAnimator(new DefaultItemAnimator());//Extra
        binding.recyclerFragmentPets.setLayoutManager(manager);
        binding.recyclerFragmentPets.setAdapter(new FindAdapter(null,petsList, PetsFragment.this));
    }

    @Override
    public void onCLickUpdate(PeopleModel peopleModel, PetsModel petsModel) {
        ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
        viewDialogUpdate.showDialogUpdate(requireContext(), petsModel.getId(), petsModel.getName(), null, null, petsModel.getGender(), petsModel.getCode());
    }

    @Override
    public void onCLickDelete(PeopleModel peopleModel, PetsModel petsModel) {
        ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
        viewDialogConfirmDelete.showDialogDelete(requireContext(), petsModel.getId(), petsModel.getCode());
    }

    public void onClickAdd() {
        binding.fabPets.setOnClickListener(view -> {
            Intent myIntent = new Intent(getContext(), PetRegisterActivity.class);
            startActivity(myIntent);
            requireActivity().finish();
        });
    }
}