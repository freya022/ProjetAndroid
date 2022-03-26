package com.freya02.projetandroid.today_food;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseFood extends SQLiteOpenHelper {
    public DatabaseFood(@Nullable Context context) {
        super(context, "databaseClient", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Food(_id INTEGER PRIMARY KEY,nom TEXT,kcal INTEGER,photo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Food");
        onCreate(db);
    }

    public void insertFood(TodayFood u) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nom", u.getFoodName());
        cv.put("kcal", u.getFoodKcal());
        cv.put("photo", u.getImagePath());

        db.insert("Food", null, cv);
    }

    public void updateFood(TodayFood u) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nom", u.getFoodName());
        cv.put("kcal", u.getFoodKcal());
        cv.put("photo", u.getImagePath());

        db.update("Food", cv, "_id=?", new String[]{String.valueOf(u.getId())});
        db.close();
    }

    public void deleteUtilisateur(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Food", "_id=?", new String[]{String.valueOf(id)});

        db.close();
    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Food", null);
        //System.out.println("" + c.getString(1).toString());
        return c;
    }

    public TodayFood getOne(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("Food", new String[]{"_id", "nom", "kcal", "photo"}, "_id=?",
                new String[]{String.valueOf(id)}, null, null, null);
        c.moveToFirst();
        TodayFood u = new TodayFood(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3));
        //System.out.println(u.toString());
        return u;
    }

    public TodayFood getOneWithName(String nom) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("Food", new String[]{"_id",  "nom", "kcal", "photo"}, "nom=?",
                new String[]{nom}, null, null, null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            TodayFood u = new TodayFood(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3));
            return u;
        }
        return null;
    }
}