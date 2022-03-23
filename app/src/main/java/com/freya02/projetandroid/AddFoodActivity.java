package com.freya02.projetandroid;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddFoodActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logo);

        List<TodayFood> todayFoods = new ArrayList<>(Arrays.asList(
                new TodayFood(0, "PastaBox", 239),
                new TodayFood(0, "PastaBox", 239),
                new TodayFood(0, "PastaBox", 239)
        ));

        RecyclerView view = findViewById(R.id.todayFoodRecycler);

        RecyclerFoodAdapter adapter = new RecyclerFoodAdapter(todayFoods);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));

        Button addFoodButton = findViewById(R.id.addFoodButton);
        addFoodButton.setOnClickListener(v -> {
            todayFoods.add(new TodayFood(0, "food", 42));

            adapter.notifyItemInserted(todayFoods.size() - 1);
        });
    }
}