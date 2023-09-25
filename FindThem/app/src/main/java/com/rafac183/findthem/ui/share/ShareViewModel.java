package com.rafac183.findthem.ui.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafac183.findthem.Model.LoginModel;

public class ShareViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private final LoginModel loginModel = new LoginModel();

    public ShareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}