package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.quizapp.adapter.MainPagerAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MainPagerAdapter adapter;
    private BottomNavigationView bottomNavigationView;
    private MenuItem prevMenuItem;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.main_view_pager);
        toolbar = findViewById(R.id.widget_toolbar);
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
        bottomNavigationView = findViewById(R.id.main_bottom_nav);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        toolbar.setTitle("Quiz");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_main:
                        viewPager.setCurrentItem(0, false);
                        toolbar.setTitle("Quiz");
                        break;
                    case R.id.nav_history:
                        viewPager.setCurrentItem(1, false);
                        toolbar.setTitle("History");
                        break;
                    case R.id.nav_settings:
                        viewPager.setCurrentItem(2, false);
                        toolbar.setTitle("Settings");
                        break;
                }
                return true;
            }
        });


    }


}