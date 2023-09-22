package com.rafac183.findthem.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafac183.findthem.Model.LoginModel;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final LoginModel loginModel = new LoginModel();

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}