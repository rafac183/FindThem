package com.rafac183.findthem.ui.registered_pets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PetsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PetsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is registered pets fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}