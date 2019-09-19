package com.example.lostfound.ui.send;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class SendViewModel extends ViewModel {

    private FirebaseAuth auth;

    private MutableLiveData<String> mText;

    public SendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}