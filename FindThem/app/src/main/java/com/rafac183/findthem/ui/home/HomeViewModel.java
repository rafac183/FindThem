package com.rafac183.findthem.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<HomeModel>> homeData = new MutableLiveData<>();

    public HomeViewModel() {
        // Obtener datos de MyData y almacenarlos en homeData
        ArrayList<HomeModel> data = HomeData.getHomeList();
        homeData.setValue(data);
    }

    public LiveData<ArrayList<HomeModel>> getHomeData() {
        return homeData;
    }
}