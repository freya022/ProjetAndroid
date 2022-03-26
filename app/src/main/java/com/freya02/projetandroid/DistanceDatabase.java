package com.freya02.projetandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DistanceDatabase extends SQLiteOpenHelper {
    public DistanceDatabase(Context context) {
        super(context, "databaseDistance", null, 1);
    }

    private static double distance(double oldLat, double latitude, double oldLong, double longitude) {
        //Pour passer en radians
        oldLong = Math.toRadians(oldLong);
        longitude = Math.toRadians(longitude);
        oldLat = Math.toRadians(oldLat);
        latitude = Math.toRadians(latitude);

        //Formules pour calcul à partir des coordonnées GPS
        double dlon = longitude - oldLong;
        double dlat = latitude - oldLat;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(oldLat) * Math.cos(latitude)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        double r = 6371;

        //Calcul du résultat
        return (c * r);
    }

    public void addDistance(double oldLat, double latitude, double oldLong, double longitude) {
        SQLiteDatabase db = getWritableDatabase();

        if (oldLat == -1 || oldLong == -1) return;

        double distance = distance(oldLat, latitude, oldLong, longitude);

        Cursor cursor = db.rawQuery("insert into Distance (distance) values (?)", new String[]{
                String.valueOf(distance)
        });

        cursor.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Distance(id serial primary key, date datetime default current_timestamp, distance double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Distance");

        onCreate(db);
    }
}
