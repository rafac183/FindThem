package com.rafac183.findthem.ui.registered_people;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafac183.findthem.adapter.PeopleAndPetsData;

import java.util.ArrayList;


public class PeopleViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<PeopleModel>> peopleData = new MutableLiveData<>();

    public PeopleViewModel() {
        // Obtener datos de MyData y almacenarlos en homeData
        ArrayList<PeopleModel> data = PeopleAndPetsData.getPeopleList();
        peopleData.setValue(data);
    }

    public LiveData<ArrayList<PeopleModel>> getPeopleData() {
        return peopleData;
    }
}