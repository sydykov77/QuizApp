package com.example.quizapp.interfaces;

import com.example.quizapp.models.ModelCategory;
import com.example.quizapp.models.ModelQuestions;

public interface IQuizApiCallBack {
    interface ListQuestion extends IMainCallBack<ModelQuestions> {
        void onSuccess(ModelQuestions modelQuestions);

        void onFailure(Throwable throwable);
    }

     interface QuestionCallback extends IMainCallBack<ModelCategory> {

        void onSuccess(ModelCategory category);

        void onFailure(Throwable e);


    }
}
