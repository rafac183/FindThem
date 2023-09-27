package com.rafac183.findthem.ui.home;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rafac183.findthem.adapter.FindAdapter;
import com.rafac183.findthem.adapter.FindInterface;
import com.rafac183.findthem.databinding.FragmentHomeBinding;
import com.rafac183.findthem.model.HomeModel;

public class HomeFragment extends Fragment implements FindInterface {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initRecyclerView();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void initRecyclerView(){
        LinearLayoutManager manager = new GridLayoutManager(binding.recyclerFragmentHome.getContext(), 2); //Con esto puedo agregar un numero de filas especificas envez de 1
        //LinearLayoutManager manager = new LinearLayoutManager(binding.recyclerFragmentHome.getContext());
        DividerItemDecoration decoration = new DividerItemDecoration(binding.recyclerFragmentHome.getContext(), manager.getOrientation());
        binding.recyclerFragmentHome.setHasFixedSize(true); //Extra
        binding.recyclerFragmentHome.setItemAnimator(new DefaultItemAnimator());//Extra
        binding.recyclerFragmentHome.setLayoutManager(manager);
        binding.recyclerFragmentHome.setAdapter(new FindAdapter(MyData.getHomeList(), this)); //tambien puede ser esto superHero -> onClickCardView(superHero) o esto this::onClickCardView
    }


    @Override
    public void onClickListener(HomeModel homeModel) {
        Toast.makeText(binding.recyclerFragmentHome.getContext(), "Hola", Toast.LENGTH_SHORT).show();
    }
}