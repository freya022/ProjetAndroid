package com.freya02.projetandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freya02.projetandroid.today_food.DatabaseFood;
import com.freya02.projetandroid.today_food.RecyclerFoodAdapter;
import com.freya02.projetandroid.today_food.TodayFood;

import java.util.ArrayList;
import java.util.List;

public class TodayFoodActivity extends BaseActivity {
    private final DatabaseFood h = new DatabaseFood(TodayFoodActivity.this);

    private final List<TodayFood> todayFoods = new ArrayList<>();
    private final RecyclerFoodAdapter adapter = new RecyclerFoodAdapter(todayFoods);

    private final ActivityResultLauncher<Void> register = registerForActivityResult(new ActivityResultContract<Void, TodayFood>() {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Void input) {
            return new Intent(TodayFoodActivity.this, AddFoodActivity.class);
        }

        @Override
        public TodayFood parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode == RESULT_OK && intent != null) {
                Bundle extras = intent.getExtras();

                return new TodayFood(extras.getString("imagePath"),
                        extras.getString("name"),
                        extras.getInt("kcal"));
            }

            return null;
        }
    }, todayFood -> {
        if (todayFood != null) {
            todayFoods.add(todayFood);

            h.insertFood(todayFood);

            adapter.notifyItemInserted(todayFoods.size() - 1);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_food);

        RecyclerView view = findViewById(R.id.todayFoodRecycler);

        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));

        List<TodayFood> fetchedTodayFoods = h.getAll();
        todayFoods.addAll(fetchedTodayFoods);
    }

    public void onAddFoodClicked(View view) {
        register.launch(null);
    }

    private static class Holder {
        private final String name;
        private final int kcal;
        private final String imagePath;

        public Holder(String name, int kcal, String imagePath) {
            this.name = name;
            this.kcal = kcal;
            this.imagePath = imagePath;
        }
    }
}