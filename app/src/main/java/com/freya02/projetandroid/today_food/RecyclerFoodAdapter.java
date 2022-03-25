package com.freya02.projetandroid.today_food;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freya02.projetandroid.R;

import java.util.List;

public class RecyclerFoodAdapter extends RecyclerView.Adapter<RecyclerFoodAdapter.FoodViewHolder> {
    protected static class FoodViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageFood;
        private final TextView textFoodName;
        private final TextView textKcal;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imageFood = itemView.findViewById(R.id.imageview_food);
            textFoodName = itemView.findViewById(R.id.textview_foodname);
            textKcal = itemView.findViewById(R.id.textview_kcal);
        }
    }

    private final List<TodayFood> foods;

    public RecyclerFoodAdapter(List<TodayFood> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);

        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        TodayFood todayFood = foods.get(position);

        holder.imageFood.setImageBitmap(BitmapFactory.decodeFile(todayFood.getImagePath()));
        holder.textFoodName.setText(todayFood.getFoodName());
        holder.textKcal.setText(todayFood.getFoodKcal() + "kcal");
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
