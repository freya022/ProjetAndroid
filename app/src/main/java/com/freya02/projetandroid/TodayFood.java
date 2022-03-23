package com.freya02.projetandroid;

import androidx.annotation.DrawableRes;

public class TodayFood {
    private final int flagId;
    private final String foodName;
    private final int foodKcal;

    public TodayFood(@DrawableRes int flagId, String foodName, int foodKcal) {
        this.flagId = flagId;
        this.foodName = foodName;
        this.foodKcal = foodKcal;
    }

    public int getFlagId() {
        return flagId;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodKcal() {
        return foodKcal;
    }
}
