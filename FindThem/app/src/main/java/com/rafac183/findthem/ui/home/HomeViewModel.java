package com.rafac183.findthem.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<HomeModel>> homeData = new MutableLiveData<>();

    public HomeViewModel() {
        // Obtener datos de MyData y almacenarlos en homeData
        List<HomeModel> data = MyData.getHomeList();
        homeData.setValue(data);
    }

    public LiveData<List<HomeModel>> getHomeData() {
        return homeData;
    }
}