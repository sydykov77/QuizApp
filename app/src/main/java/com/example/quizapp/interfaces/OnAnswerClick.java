package com.example.quizapp.interfaces;

import android.view.View;

public interface OnAnswerClick {
    void onAnswer(View view, int positionQuestion, int positionAnswer);
    void correctAnswer(boolean b);
}