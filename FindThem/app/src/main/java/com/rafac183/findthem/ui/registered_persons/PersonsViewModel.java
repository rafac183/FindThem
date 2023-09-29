package com.rafac183.findthem.ui.registered_persons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafac183.findthem.ui.home.HomeData;
import com.rafac183.findthem.ui.home.HomeModel;

import java.util.ArrayList;


public class PersonsViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<PersonsModel>> personsData = new MutableLiveData<>();

    public PersonsViewModel() {
        // Obtener datos de MyData y almacenarlos en homeData
        ArrayList<PersonsModel> data = PersonsData.getPersonsList();
        personsData.setValue(data);
    }

    public LiveData<ArrayList<PersonsModel>> getPersonsData() {
        return personsData;
    }
}