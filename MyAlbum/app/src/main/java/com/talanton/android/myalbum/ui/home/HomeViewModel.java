package com.talanton.android.myalbum.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        Log.d("MyAlbum", "HomeViewModel:HomeViewModel()");
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        Log.d("MyAlbum", "HomeViewModel:getText()");
        return mText;
    }
}