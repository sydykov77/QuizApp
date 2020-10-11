package com.example.quizapp.data;

import android.accounts.NetworkErrorException;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.quizapp.models.ModelCategory;
import com.example.quizapp.models.ModelQuestions;
import com.example.quizapp.models.QuizResponse;
import com.example.quizapp.models.Result;
import com.example.quizapp.models.TriviaCategories;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiService {
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://opentdb.com/")
            .build();
    QuizApi service = retrofit.create(QuizApi.class);


    public void getCategory(QuestionCallback callback) {
        Call<ModelCategory> call = service.getCategoryQuestion();
        call.enqueue(new Callback<ModelCategory>() {
            @Override
            public void onResponse(Call<ModelCategory> call, Response<ModelCategory> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("tag", response.body().toString());
                    callback.onResponse(response.body());
                }

            }

            @Override
            public void onFailure(Call<ModelCategory> call, Throwable t) {
                callback.onFailure(new NetworkErrorException());
            }
        });
    }

    public interface QuestionCallback {

        

        void onFailure(Exception e);

        void onResponse(ModelCategory question);
    }

    public interface QuizApi {
        @GET("api.php")
        Call<ModelQuestions> getQuestion(
                @Query("amount") int amount,
                @Query("category") int category,
                @Query("difficulty") String difficulty
        );

        @GET("api_category.php")
        Call<ModelCategory> getCategoryQuestion();

    }
}
