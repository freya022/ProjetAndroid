package com.freya02.projetandroid.today_food;

public class TodayFood {
    private final String imagePath;
    private final String foodName;
    private final int foodKcal;

    public TodayFood(String imagePath, String foodName, int foodKcal) {
        this.imagePath = imagePath;
        this.foodName = foodName;
        this.foodKcal = foodKcal;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodKcal() {
        return foodKcal;
    }
}
