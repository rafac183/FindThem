package com.rafac183.findthem.ui.registered_persons;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
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

import com.google.android.material.snackbar.Snackbar;
import com.rafac183.findthem.R;
import com.rafac183.findthem.activities.PetRegisterActivity;
import com.rafac183.findthem.activities.RegisterInfoActivity;
import com.rafac183.findthem.adapter.FindAdapter;
import com.rafac183.findthem.adapter.FindInterface;
import com.rafac183.findthem.databinding.FragmentPeopleBinding;

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
        LinearLayoutManager manager = new LinearLayoutManager(binding.recyclerFragmentPersons.getContext()); //Con esto puedo agregar un numero de filas especificas envez de 1
        DividerItemDecoration decoration = new DividerItemDecoration(binding.recyclerFragmentPersons.getContext(), manager.getOrientation());
        binding.recyclerFragmentPersons.setHasFixedSize(true); //Extra
        binding.recyclerFragmentPersons.setItemAnimator(new DefaultItemAnimator());//Extra
        binding.recyclerFragmentPersons.setLayoutManager(manager);
    }

    public void SetRecyclerView() {
        initRecyclerView();
        peopleViewModel.getPersonsData().observe(getViewLifecycleOwner(), personsList -> {
            // Actualiza el RecyclerView con los nuevos datos
            binding.recyclerFragmentPersons.setAdapter(new FindAdapter(personsList, PeopleFragment.this));
        });
    }

    @Override
    public void onCLickCardView(PeopleModel peopleModel) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_navigation);
        switch (peopleModel.getName()) {
            case "Registered Persons":
                navController.navigate(R.id.nav_persons);
                break;
            case "Registered Pets":
                navController.navigate(R.id.nav_pets);
                break;
            case "Profile":
                navController.navigate(R.id.nav_profile);
                break;
            case "Rate Us":
                navController.navigate(R.id.nav_rate_us);
                break;
            case "Share":
                navController.navigate(R.id.nav_share);
                break;
            case "Settings":
                // Puedes manejar la lógica de configuración aquí
                break;
            default:
                Toast.makeText(binding.recyclerFragmentPersons.getContext(), "No Item Selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }
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