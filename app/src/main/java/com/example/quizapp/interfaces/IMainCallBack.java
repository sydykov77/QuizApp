package com.example.quizapp.interfaces;

public interface IMainCallBack <T>{
    void onSuccess(T result);
    void onFailure(Throwable e);
}
