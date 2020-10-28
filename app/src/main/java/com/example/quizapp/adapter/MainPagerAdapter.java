package com.example.quizapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.quizapp.ui.history.HistoryFragment;
import com.example.quizapp.ui.main.MainFragment;
import com.example.quizapp.ui.settings.SettingsFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {


    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position){
            case 0:
                fragment =  MainFragment.newInstance();
                break;
            case 1:
                fragment = HistoryFragment.newInstance();
                break;
            case 2:
                fragment = SettingsFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
