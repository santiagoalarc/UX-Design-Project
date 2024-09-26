package com.example.appalarmux.ui.quickAlarm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuickAlarmViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public QuickAlarmViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("This is quick alarm fragment");
    }

    public LiveData<String> getText(){
        return mText;
    }


}