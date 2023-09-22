package com.rafac183.findthem.ui.registered_persons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PersonsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is registered persons fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}