package com.example.civicuc.ui.caida;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CaidaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CaidaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is caida fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}