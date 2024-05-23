package com.example.makpakde;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "my_database";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USER = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_FULLNAME = "fullname";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_PASSWORD = "password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + " ("
                + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_COLUMN_FULLNAME + " TEXT, "
                + USER_COLUMN_USERNAME + " TEXT, "
                + USER_COLUMN_PASSWORD + " TEXT )");

    }

    public void insertDataUser(String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_COLUMN_USERNAME, username);
        values.put(USER_COLUMN_PASSWORD, password);

        db.insert(TABLE_USER, null, values);
    }

    public void updateRecordUserFullname(int id, String fullname){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_COLUMN_FULLNAME, fullname);
        db.update(TABLE_USER, values, USER_COLUMN_ID + " = ? ", new String[]{String.valueOf(id)});
    }

    public void updateRecordUserPassword(int id, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_COLUMN_PASSWORD, password);
        db.update(TABLE_USER, values, USER_COLUMN_ID + " = ? ", new String[]{String.valueOf(id)});
    }

    public void deleteRecordUser(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USER, USER_COLUMN_ID + " = " + id, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
