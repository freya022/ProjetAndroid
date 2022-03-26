package com.freya02.projetandroid.today_food;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFood extends SQLiteOpenHelper {
    public DatabaseFood(@Nullable Context context) {
        super(context, "databaseFood", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Food(_id INTEGER PRIMARY KEY,photo TEXT,nom TEXT,kcal INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Food");
        onCreate(db);
    }

    public void insertFood(TodayFood u) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("photo", u.getImagePath());
        cv.put("nom", u.getFoodName());
        cv.put("kcal", u.getFoodKcal());

        db.insert("Food", null, cv);
    }

    public void updateFood(TodayFood u) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("photo", u.getImagePath());
        cv.put("nom", u.getFoodName());
        cv.put("kcal", u.getFoodKcal());

        db.update("Food", cv, "_id=?", new String[]{String.valueOf(u.getId())});
        db.close();
    }

    public void deleteFood(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Food", "_id=?", new String[]{String.valueOf(id)});

        db.close();
    }

    public List<TodayFood> getAll() {
        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor c = db.rawQuery("SELECT * FROM Food", null)) {

            List<TodayFood> todayFoods = new ArrayList<>();

            if (c.moveToFirst()) {
                do {
                    todayFoods.add(new TodayFood(c.getString(1), c.getString(2), c.getInt(3)));
                } while (c.moveToNext());
            }

            return todayFoods;
        }
    }

    public int getTodayKcal() {
        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor c = db.rawQuery("SELECT sum(kcal) FROM Food", null)) {

            if (c.moveToFirst()) {
                return c.getInt(0);
            }

            return 0;
        }
    }

    public TodayFood getOne(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("Food", new String[]{"_id", "photo", "nom", "kcal"}, "_id=?",
                new String[]{String.valueOf(id)}, null, null, null);
        c.moveToFirst();
        TodayFood u = new TodayFood(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3));
        //System.out.println(u.toString());
        return u;
    }

    public TodayFood getOneWithName(String nom) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("Food", new String[]{"_id", "photo", "nom", "kcal"}, "nom=?",
                new String[]{nom}, null, null, null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            TodayFood u = new TodayFood(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3));
            return u;
        }
        return null;
    }
}