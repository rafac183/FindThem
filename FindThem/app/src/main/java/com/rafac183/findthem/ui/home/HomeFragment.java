package com.rafac183.findthem.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rafac183.findthem.R;
import com.rafac183.findthem.activities.NavigationActivity;
import com.rafac183.findthem.adapter.FindAdapter;
import com.rafac183.findthem.adapter.FindInterface;
import com.rafac183.findthem.databinding.FragmentHomeBinding;
import com.rafac183.findthem.ui.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements FindInterface {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class); //esto se trae la lista

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        if (binding.recyclerFragmentHome.getAdapter() == null || binding.recyclerFragmentHome.getAdapter().getItemCount() == 0) {
            initRecyclerView();
            homeViewModel.getHomeData().observe(getViewLifecycleOwner(), homeList -> {
                // Actualiza el RecyclerView con los nuevos datos
                binding.recyclerFragmentHome.setAdapter(new FindAdapter(homeList, HomeFragment.this));
            });
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void initRecyclerView(){
        LinearLayoutManager manager = new GridLayoutManager(binding.recyclerFragmentHome.getContext(), 2); //Con esto puedo agregar un numero de filas especificas envez de 1
        DividerItemDecoration decoration = new DividerItemDecoration(binding.recyclerFragmentHome.getContext(), manager.getOrientation());
        binding.recyclerFragmentHome.setHasFixedSize(true); //Extra
        binding.recyclerFragmentHome.setItemAnimator(new DefaultItemAnimator());//Extra
        binding.recyclerFragmentHome.setLayoutManager(manager);
    }


    @Override
    public void onCLickCardView(HomeModel homeModel) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_navigation);
        switch (homeModel.getName()) {
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
                Toast.makeText(binding.recyclerFragmentHome.getContext(), "No Item Selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}