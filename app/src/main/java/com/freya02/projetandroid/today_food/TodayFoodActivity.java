package com.freya02.projetandroid.today_food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.freya02.projetandroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodayFoodActivity extends AppCompatActivity {

    private final DatabaseFood h = new DatabaseFood(TodayFoodActivity.this);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_food);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logo);

        List<TodayFood> todayFoods = new ArrayList<>(Arrays.asList(
                new TodayFood(null, "PastaBox", 239),
                new TodayFood(null, "PastaBox", 239),
                new TodayFood(null, "PastaBox", 239)
        ));

        RecyclerView view = findViewById(R.id.todayFoodRecycler);

        RecyclerFoodAdapter adapter = new RecyclerFoodAdapter(todayFoods);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));

        ActivityResultLauncher<Void> register = registerForActivityResult(new ActivityResultContract<Void, Holder>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, Void input) {
                return new Intent(TodayFoodActivity.this, AddFoodActivity.class);
            }

            @Override
            public Holder parseResult(int resultCode, @Nullable Intent intent) {
                if (resultCode == RESULT_OK && intent != null) {
                    Bundle extras = intent.getExtras();

                    return new Holder(extras.getString("name"),
                            extras.getInt("kcal"),
                            extras.getString("imagePath"));
                }

                return null;
            }
        }, holder -> {
            if (holder != null) {
                TodayFood todayFood = new TodayFood(holder.imagePath, holder.name, holder.kcal);
                todayFoods.add(todayFood);
                h.insertFood(todayFood);
                adapter.notifyItemInserted(todayFoods.size() - 1);
            }
        });

        Button addFoodButton = findViewById(R.id.addFoodButton);
        addFoodButton.setOnClickListener(v -> {
            register.launch(null);
        });
    }
}