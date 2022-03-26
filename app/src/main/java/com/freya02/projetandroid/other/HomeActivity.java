package com.freya02.projetandroid.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.freya02.projetandroid.R;
import com.freya02.projetandroid.today_food.TodayFoodActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logo);

        final TextView name = findViewById(R.id.nom2);
        final TextView prenom = findViewById(R.id.prenom2);

        name.setText(getIntent().getStringExtra("personne_nom"));
        prenom.setText(getIntent().getStringExtra("personne_prenom"));
    }

    public void onTodayFoodClicked(View v) {
        Intent intent = new Intent(this, TodayFoodActivity.class);

        startActivity(intent);
    }

    public void onInfoPageClicked(View v) {
        Intent intent = new Intent(this, InfoActivity.class);

        startActivity(intent);
    }

    public void onStatsPageClicked(View v) {
        Intent intent = new Intent(this, StatisticsActivity.class);

        startActivity(intent);
    }

    public void onAdvicesPageClicked(View v) {
        Intent intent = new Intent(this, AdvicesActivity.class);

        startActivity(intent);
    }
}