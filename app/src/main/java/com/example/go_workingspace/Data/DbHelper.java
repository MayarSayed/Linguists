package com.example.go_workingspace.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = DbHelper.class.getSimpleName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "spaces.db";

    public static final String SQL_CREATE_USER = "CREATE TABLE " + Contract.Entry.USER_TABLE_NAME + " (" +
            Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Entry.COLUMN_USERNAME + " TEXT PRIMARY KEY NOT NULL, " +
            Contract.Entry.COLUMN_EMAIL + " TEXT PRIMARY KEY NOT NULL, " +
            Contract.Entry.COLUMN_NAME + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_BIRTHDAY + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_LOGED_IN + " INTEGER DEFAULT 0);";

    public static final String SQL_CREATE_OWNER = "CREATE TABLE " + Contract.Entry.OWNER_TABLE_NAME + " (" +
            Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Entry.COLUMN_USERNAME + " TEXT PRIMARY KEY NOT NULL, " +
            Contract.Entry.COLUMN_EMAIL + " TEXT PRIMARY KEY NOT NULL, " +
            Contract.Entry.COLUMN_NAME + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_BIRTHDAY + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_LOGED_IN + " INTEGER DEFAULT 0);";

    public static final String SQL_CREATE_HISTORY = "CREATE TABLE " + Contract.Entry.HISTORY_TABLE_NAME + " (" +
            Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Entry.COLUMN_PLACE_ID + " INTEGER NOT NULL, " +
            Contract.Entry.COLUMN_USER_ID + " INTEGER NOT NULL, " +
            Contract.Entry.COLUMN_DATE + " TEXT NOT NULL);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_OWNER);
        db.execSQL(SQL_CREATE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



}
