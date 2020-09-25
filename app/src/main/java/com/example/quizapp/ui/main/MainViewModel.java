package com.example.quizapp.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {


    public MutableLiveData<Integer> mResult = new MutableLiveData();


    public void minus(){
        if (mResult.getValue() ==null){
            mResult.setValue(0);
        }
        mResult.setValue(mResult.getValue()-1);
    }
    public void plus(){
        if (mResult.getValue() ==null){
            mResult.setValue(0);
        }
        mResult.setValue(mResult.getValue()+1);
    }

}



