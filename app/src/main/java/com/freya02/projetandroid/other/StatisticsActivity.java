package com.freya02.projetandroid.other;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.DistanceDatabase;
import com.freya02.projetandroid.R;

public class StatisticsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //En kilomètres
        double todayDistance = new DistanceDatabase(this).getTodayDistance();
        ProgressBar todayKmBar = findViewById(R.id.todayKmBar);

        todayKmBar.setProgress((int) (todayDistance * 1000.0));
    }
}