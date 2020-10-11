package com.example.quizapp.ui.questions_activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.interfaces.OnItemClickListener;
import com.example.quizapp.models.ModelQuiz;

import java.util.List;

public class QuizViewModel extends ViewModel implements OnItemClickListener {
    //MutableLiveData<List<ModelQuiz>> modelQuizMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> modelQuizMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> answerAmownt = new MutableLiveData<>();
    ModelQuiz modelQuiz;
    List<ModelQuiz> list;


    @Override
    public void onItemClick(int position) {
        if (answerAmownt.getValue()==null){
            answerAmownt.setValue(0);
        }
        answerAmownt.setValue(answerAmownt.getValue()+1);
    }

}
