package com.example.quizapp.ui.questions_activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.adapter.QuizAdapter;
import com.example.quizapp.models.ModelQuiz;

import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    public static final String KEYNAME = "keyName";
    private SeekBar seekBar2;
    private RecyclerView horizontalRecyclerView;
    private QuizAdapter horizontalAdapter;
    private List<ModelQuiz> listHoriz;
    private TextView textViewQuestion, tvSeekBar, tvCategory;
    public static final String KEY = "key";
    private ModelQuiz modelQuiz;
    private LinearLayout layout, layout1;
    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
    }
}