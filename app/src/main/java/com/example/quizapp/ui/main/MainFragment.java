package com.example.quizapp.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;

public class MainFragment extends Fragment {

    private SeekBar seekBar;
    private TextView amount;
    private int amountIndex = 5;




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





    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);

        amount = view.findViewById(R.id.amount);
        seekBar = view.findViewById(R.id.amount_seek_bar);
        seekBar.setProgress(5);

        seekBar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                amount.setText(String.valueOf(progress));
                amountIndex = progress;
            }
        });

    }

    private void initialization(View view) {
        amount = view.findViewById(R.id.amount);
        seekBar = view.findViewById(R.id.amount_seek_bar);

    }

}