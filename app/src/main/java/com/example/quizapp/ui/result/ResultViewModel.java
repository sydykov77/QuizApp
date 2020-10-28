package com.example.quizapp.ui.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.models.HistoryModel;
import com.example.quizapp.models.ResultModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultViewModel extends ViewModel {
    MutableLiveData<List<ResultModel>> resultActivity = new MutableLiveData<>();

    void saveResult(HistoryModel historyModel){
        App.getInstance().getDatabase().historyDao().insert(historyModel);
    }

}
// App.getInstance().getRepository().getQuestion(this,difficulty,category,amount);
//App.getInstance().getDatabase().historyDao().insert(historyModel);