package com.example.quizapp.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.databinding.MainFragmentBinding;
import com.example.quizapp.models.TriviaCategory;
import com.example.quizapp.ui.questions_activity.QuestionsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private Integer category;
    private static int MAIN_FRAGMENT_CODE = 1;
    private String nameCategoryTitleQuestionActivity;
    private String stringCategory = "Any category";
    private String difficulty = "Any type";
    private String difficul;

    private int amount;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.amount.setText("0");
        onClick();
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.updateCategory();
        mViewModel.mutableLiveData.observeForever(integer -> {
            binding.amount.setText(integer + "");
            binding.seekBar.setProgress(integer);
            amount = integer;
        });

        binding.difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                difficul = getResources().getStringArray(R.array.difficulty)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mViewModel.mutableCategory.observeForever(modelCategory -> {
            List<TriviaCategory> categoryList = modelCategory.getTriviaCategories();
            List<String> name_category = new ArrayList<>();
            for (TriviaCategory triviaCategory : categoryList) {
                name_category.add(triviaCategory.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.support_simple_spinner_dropdown_item, name_category);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.category.setAdapter(adapter);
            binding.category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    category = modelCategory.getTriviaCategories().get(position).getId();
                    nameCategoryTitleQuestionActivity = modelCategory.getTriviaCategories().get(position).getName();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });

    }

    private void onClick() {

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mViewModel.mutableLiveData.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ololo", "buttonStart");
                Intent intent = new Intent(requireContext(), QuestionsActivity.class);
                intent.putExtra(QuestionsActivity.KEY, category.intValue());
                intent.putExtra(QuestionsActivity.KEYNAME, nameCategoryTitleQuestionActivity);
                startActivityForResult(intent, MAIN_FRAGMENT_CODE);

            }
        });
        binding.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.plus();
            }
        });
        binding.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.minus();
            }
        });
    }

}
