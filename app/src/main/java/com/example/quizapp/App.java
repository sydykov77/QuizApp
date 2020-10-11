package com.example.quizapp;

import android.app.Application;

import com.example.quizapp.data.QuizApiService;

public class App extends Application {
    public static QuizApiService QuizApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        QuizApiService = new QuizApiService();
    }
}
