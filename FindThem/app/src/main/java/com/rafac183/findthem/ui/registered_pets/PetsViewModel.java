package com.rafac183.findthem.ui.registered_pets;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafac183.findthem.adapter.PeopleAndPetsData;

import java.util.ArrayList;

public class PetsViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<PetsModel>> petsData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public PetsViewModel() {
        loading.setValue(true); // Indicar que se están cargando los datos

        // Obtener los datos de las personas de manera asíncrona
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            PeopleAndPetsData.getPetsList().thenAccept(petsList -> {
                // Notificar a los observadores cuando los datos estén disponibles
                petsData.postValue(petsList);
                loading.postValue(false); // Indicar que la carga ha finalizado
            });
        }
    }

    public LiveData<ArrayList<PetsModel>> getPetsData() {
        return petsData;
    }

    public LiveData<Boolean> isLoading() {
            return loading;
    }
}
