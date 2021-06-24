package com.example.weatherapiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;



    public DBHelper(@Nullable Context context) {
        super(context, "Userdata.db" , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(name TEXT primary key, contact TEXT, dob TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String name, String contact, String dob, String email)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        contentValues.put("email",email);
        long result = DB.insert("Userdetails",  null, contentValues);
        if(result == -1) {
            return false;
        }else{
            return true;
        }

    }

    public Boolean updateuserdata(String name, String contact, String dob, String email)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        contentValues.put("email", email);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if(cursor.getCount() > 0)
        {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if(result==-1) {
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }


    }

    public Boolean deletedata(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if(cursor.getCount() > 0)
        {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if(result==-1) {
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }


    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;


    }
}
