package com.freya02.projetandroid.activity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.freya02.projetandroid.DistanceDatabase;
import com.freya02.projetandroid.today_food.DatabaseFood;

public class StatisticsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //En kilomètres
        double todayDistance = new DistanceDatabase(this).getTodayDistance();
        ProgressBar todayKmBar = findViewById(R.id.todayKmBar);
        todayKmBar.setProgress((int) (todayDistance * 1000.0));

        int todayKcal = new DatabaseFood(this).getTodayKcal();
        ProgressBar todayKcalBar = findViewById(R.id.todayKcalBar);
        todayKcalBar.setProgress(todayKcal);
    }
}