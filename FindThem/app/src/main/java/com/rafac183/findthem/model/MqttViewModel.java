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
        loading.setValue(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            MqttData.getPeopleList().thenAccept(peopleList -> {
                peopleData.postValue(peopleList);
                loading.postValue(false);
            }).exceptionally(throwable -> {
                loading.postValue(false);
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