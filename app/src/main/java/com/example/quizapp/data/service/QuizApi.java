package com.example.quizapp.data.service;

import com.example.quizapp.models.ModelCategory;
import com.example.quizapp.models.ModelQuestions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApi {
    @GET("api.php")
    Call<ModelQuestions> getQuestion(
            @Query("amount") int amount,
            @Query("category") int category,
            @Query("difficulty") String difficulty
    );

    @GET("api_category.php")
    Call<ModelCategory>getCategoryQuestion();
}
