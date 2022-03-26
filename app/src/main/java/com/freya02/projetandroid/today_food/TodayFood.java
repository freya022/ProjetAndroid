package com.freya02.projetandroid.today_food;

public class TodayFood {
    private final int id;
    private final String imagePath;
    private final String foodName;
    private final int foodKcal;

    public TodayFood(int id, String imagePath, String foodName, int foodKcal) {
        this.id = id;
        this.imagePath = imagePath;
        this.foodName = foodName;
        this.foodKcal = foodKcal;
    }

    public TodayFood(String imagePath, String foodName, int foodKcal) {
        this.id = 0;
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

    public int getId() {
        return id;
    }
}
