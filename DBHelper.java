package com.example.a1.zhattyqu2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "taskDb";
    public static final String DATABASE_TASKS = "tasks";

    public static final String DATABASE_TITLE = "title";
    public static final String DATABASE_ID = "_id";
    public static final String DATABASE_NOTE = "note";
    public static final String DATABASE_PLACE = "place";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_TASKS +
                    "(_id INTEGER PRIMARY KEY, " +
                    DATABASE_TITLE + " TEXT, " +
                    DATABASE_NOTE + " TEXT, " +
                    DATABASE_PLACE + " TEXT, " +
                    "type" + " TEXT, "+
                    "status" + " TEXT, "+
                    "priority" + " INTEGER, " +
                        "start_year" + " INTEGER, " +
                        "start_month" + " INTEGER, " +
                        "start_month_day" + " INTEGER, " +
                        "start_time_hour" + " INTEGER, " +
                        "start_time_minute" + " INTEGER, " +
                        "end_year" + " INTEGER, " +
                        "end_month" + " INTEGER, " +
                        "end_month_day" + " INTEGER, " +
                        "end_time_hour" + " INTEGER, " +
                        "end_time_minute" + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
