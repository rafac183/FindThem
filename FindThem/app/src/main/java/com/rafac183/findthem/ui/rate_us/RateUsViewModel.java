package com.rafac183.findthem.ui.rate_us;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RateUsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public RateUsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rateus fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}