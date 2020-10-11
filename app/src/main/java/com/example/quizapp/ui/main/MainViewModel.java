package com.example.quizapp.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.data.QuizApiService;
import com.example.quizapp.models.ModelCategory;
import com.example.quizapp.models.Result;

import java.util.List;

public class MainViewModel extends ViewModel implements QuizApiService.QuestionCallback {
    MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
    MutableLiveData<ModelCategory> mutableCategory = new MutableLiveData<>();

    public void plus() {
        if (mutableLiveData.getValue() == null) {
            mutableLiveData.setValue(0);
        }
        if (mutableLiveData.getValue() <= 49) {
            mutableLiveData.setValue(mutableLiveData.getValue() + 1);
        }
    }

    public void minus() {
        if (mutableLiveData.getValue() == null) {
            mutableLiveData.setValue(0);
        }
        if (mutableLiveData.getValue() >= 1) {
            mutableLiveData.setValue(mutableLiveData.getValue() - 1);
        }
    }

    void updateCategory() {
        App.QuizApiService.getCategory(this);
    }

    @Override
    public void onSuccess(List<Result> body) {

    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onResponse(ModelCategory question) {
        mutableCategory.setValue(question);

    }

}