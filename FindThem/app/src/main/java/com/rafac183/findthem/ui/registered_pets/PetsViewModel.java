package com.rafac183.findthem.ui.registered_pets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafac183.findthem.adapter.PeopleAndPetsData;

import java.util.ArrayList;

public class PetsViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<PetsModel>> petsData = new MutableLiveData<>();

    public PetsViewModel() {
        // Obtener datos de MyData y almacenarlos en homeData
        ArrayList<PetsModel> data = PeopleAndPetsData.getPetsList();
        petsData.setValue(data);
    }

    public LiveData<ArrayList<PetsModel>> getPetsData() {
        return petsData;
    }
}