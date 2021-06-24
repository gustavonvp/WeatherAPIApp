package com.example.weatherapiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class DBHelper2 extends SQLiteOpenHelper {


    SQLiteDatabase db;

    public DBHelper2(@Nullable Context context) {
        super(context, "Userdata2.db" , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            String str ="CREATE TABLE UserLogin(username TEXT PRIMARY KEY, password TEXT);";
            db.execSQL( str );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL( "DROP TABLE IF EXISTS UserLogin;");
            onCreate( db );
    }

    public Boolean CriarUtilizador(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put( "username", username );
        cv.put( "password", password );
        long result = DB.insert( "UserLogin", null, cv );

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int ValidarLogin(String username, String password) {
        String[] selectionArgs = new String[]{username, password};
        SQLiteDatabase db = getReadableDatabase();
        try {

            int i = 0;
            Cursor c = null;
            c = db.rawQuery( "SELECT * FROM UserLogin WHERE username=? AND password=?", selectionArgs );
            c.moveToFirst();
            i = c.getCount();
            c.close();
            return i;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
