package com.example.quizapp.data;

import com.example.quizapp.models.QuizModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.quizapp.models.QuizModel.TypeQuiz.OPTIONS;
import static com.example.quizapp.models.QuizModel.TypeQuiz.YES_NO;

public class QuestionsBD {
    private List<QuizModel> quizModels;

    public QuestionsBD() {
        quizModels = new ArrayList<>();
        QuizModel quizModel;

        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0)
                quizModel = new QuizModel(
                        "Вопрос ",
                        new String[]{"неправильно", "неправильно", "неправильно", "правильно"},
                        "правильно",
                        OPTIONS);
            else
                quizModel = new QuizModel(
                        "проверка ",
                        new String[]{"да", "нет"},
                        "да",
                        YES_NO);
            quizModels.add(quizModel);
        }
    }

    public List<QuizModel> getAll() {
        return quizModels;
    }

    public List<QuizModel> getQuestions(int quantity) {
        List<QuizModel> result = new ArrayList<>();
        if (quantity > quizModels.size()) result = getAll();
        else for (int i = 0; i < quantity; i++) result.add(quizModels.get(i));
        return result;
    }
}