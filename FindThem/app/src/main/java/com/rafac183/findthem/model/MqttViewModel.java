package com.rafac183.findthem.model;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafac183.findthem.adapter.MqttData;
import com.rafac183.findthem.adapter.PeopleAndPetsData;
import com.rafac183.findthem.ui.registered_people.PeopleModel;

import java.util.ArrayList;

public class MqttViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<PeopleModel>> peopleData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public MqttViewModel() {
        loading.setValue(true); // Indicar que se están cargando los datos

        // Obtener los datos de las personas de manera asíncrona
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            MqttData.getPeopleList().thenAccept(peopleList -> {
                // Notificar a los observadores cuando los datos estén disponibles
                peopleData.postValue(peopleList);
                loading.postValue(false); // Indicar que la carga ha finalizado
            }).exceptionally(throwable -> {
                // Manejar la excepción si ocurre un error durante la obtención de datos
                loading.postValue(false); // Indicar que la carga ha finalizado con error
                return null;
            });
        }
    }

    public LiveData<ArrayList<PeopleModel>> getPeopleData() {
        return peopleData;
    }

    public LiveData<Boolean> isLoading() {
        return loading;
    }
}