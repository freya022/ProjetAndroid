package com.freya02.projetandroid.other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "databaseClient", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Client(_id INTEGER PRIMARY KEY,nom TEXT,prenom TEXT,email TEXT,mdp TEXT, genre TEXT,age INTEGER,poids INTEGER, taille INTEGER,locomotion TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Client");
        onCreate(db);
    }

    public void insertUtilisateur(Utilisateur u) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nom", u.getNom());
        cv.put("prenom", u.getPrenom());
        cv.put("email", u.getEmail());
        cv.put("mdp", u.getMdp());
        cv.put("genre", u.getGenre());
        cv.put("age", u.getAge());
        cv.put("poids", u.getPoids());
        cv.put("taille", u.getTaille());
        cv.put("locomotion", u.getLocomotion());

        db.insert("Client", null, cv);
    }

    public void updateUtilisateur(Utilisateur u) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nom", u.getNom());
        cv.put("prenom", u.getPrenom());
        cv.put("email", u.getEmail());
        cv.put("mdp", u.getMdp());
        cv.put("genre", u.getAge());
        cv.put("age", u.getPoids());
        cv.put("poids", u.getTaille());
        cv.put("taille", u.getGenre());
        cv.put("locomotion", u.getLocomotion());

        db.update("Client", cv, "_id=?", new String[]{String.valueOf(u.getId())});
        db.close();
    }

    public void deleteUtilisateur(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Client", "_id=?", new String[]{String.valueOf(id)});

        db.close();
    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Client", null);
        System.out.println("" + c.getString(1).toString());
        return c;
    }

    public Utilisateur getOne(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("Client", new String[]{"_id", "nom", "prenom", "email", "mdp", "genre", "age", "poids", "taille", "locomotion"}, "_id=?",
                new String[]{String.valueOf(id)}, null, null, null);
        c.moveToFirst();
        Utilisateur u = new Utilisateur(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getInt(6), c.getInt(7), c.getInt(8), c.getString(9));
        System.out.println(u.toString());
        return u;
    }

    public Utilisateur getuserWithoutMDP(String mail) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query("Client", new String[]{"_id", "nom", "prenom", "email", "mdp", "genre", "age", "poids", "taille", "locomotion"}, "email=?",
                new String[]{mail}, null, null, null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            Utilisateur u = new Utilisateur(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getInt(6), c.getInt(7), c.getInt(8), c.getString(9));
            return u;
        }
        return null;
    }

    public Utilisateur getOneWithMail(String mail, String mdp) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("Client", new String[]{"_id", "nom", "prenom", "email", "mdp", "genre", "age", "poids", "taille", "locomotion"}, "email=? and mdp=?",
                new String[]{mail, mdp}, null, null, null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            Utilisateur u = new Utilisateur(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getInt(6), c.getInt(7), c.getInt(8), c.getString(9));
            return u;
        }
        return null;
    }
}
