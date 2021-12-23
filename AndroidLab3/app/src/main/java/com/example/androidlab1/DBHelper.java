package com.example.androidlab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "results";
    private static final String COLUMN_NAME = "res";

    public DBHelper(Context context) {
        super(context, "lab3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "res VARCHAR(255))";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<String> getAllResults() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        List<String> resultsList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            resultsList.add(cursor.getString(1));
            while (cursor.moveToNext()) {
                resultsList.add(cursor.getString(1));
            }
        }
        cursor.close();
        db.close();
        return resultsList;
    }

    public boolean addResult(String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, text);
        long rows = db.insert(TABLE_NAME, null, cv);
        db.close();
        return rows != -1;
    }

    public void clearResults() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}