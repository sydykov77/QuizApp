package com.example.quizapp.ui.history;

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

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.adapter.HistoryAdapter;
import com.example.quizapp.databinding.HistoryFragmentBinding;
import com.example.quizapp.models.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private HistoryAdapter adapterHistory;
    private HistoryViewModel mViewModel;
    HistoryFragmentBinding historyFragmentBinding;
    private ArrayList<HistoryModel> historyList = new ArrayList<>();


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        historyFragmentBinding = HistoryFragmentBinding.bind(inflater.inflate(R.layout.history_fragment, container, false));
        //App.getInstance().getDatabase().historyDao().getAll();
        return historyFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterHistory = new HistoryAdapter(historyList);
        historyFragmentBinding.recyclerView.setAdapter(adapterHistory);
       /* mViewModel.mutableLiveData.observeForever(historyModels -> {
            //adapterHistory.setData(historyModels);
        });*/
        App.getInstance().getDatabase().historyDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<HistoryModel>>() {
            @Override
            public void onChanged(List<HistoryModel> historyModels) {
                historyList.addAll(historyModels);
                adapterHistory.notifyDataSetChanged();
            }
        });
    }
}
