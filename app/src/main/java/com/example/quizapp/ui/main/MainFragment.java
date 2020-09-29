package com.example.quizapp.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.ui.questions_activity.QuestionsActivity;

public class MainFragment extends Fragment {
    private MainViewModel viewModel;

    private SeekBar seekBar;
    private TextView amount;
    private int amountIndex = 5;
    Button plus, minus, start;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.mResult.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                amount.setText(String.valueOf(integer));
            }
        });


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);

        amount = view.findViewById(R.id.amount);
        seekBar = view.findViewById(R.id.seek_bar);
        seekBar.setProgress(5);
        amount.setText("5");


        seekBar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                amount.setText(String.valueOf(progress));
                amountIndex = progress;
            }
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.minus();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.plus();
            }
        });

        start.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), QuestionsActivity.class);
            intent.putExtra(QuestionsActivity.RESULT_QUESTIONS_AMOUNT_KEY, viewModel.mResult.getValue());
            startActivity(intent);
        });


    }

    private void initialization(View view) {
        amount = view.findViewById(R.id.amount);
        seekBar = view.findViewById(R.id.seek_bar);
        plus = view.findViewById(R.id.buttonPlus);
        minus = view.findViewById(R.id.buttonMinus);
        start = view.findViewById(R.id.start_game);


    }

}