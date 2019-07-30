package com.example.go_workingspace.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class DbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = DbHelper.class.getSimpleName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "spaces.db";

    private Context mContext;

    public static final String SQL_CREATE_USER = "CREATE TABLE " + Contract.Entry.USER_TABLE_NAME + " (" +
            Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Entry.COLUMN_USERNAME + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_EMAIL + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_NAME + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_BIRTHDAY + " TEXT NOT NULL DEFAULT \"16/08/1997\");";

    public static final String SQL_CREATE_OWNER = "CREATE TABLE " + Contract.Entry.OWNER_TABLE_NAME + " (" +
            Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Entry.COLUMN_USERNAME + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_EMAIL + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_NAME + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_PASSWORD + " TEXT NOT NULL, " +
            Contract.Entry.COLUMN_RATING + " REAL DEFAULT 0," +
            Contract.Entry.COLUMN_WIFI + " INTEGER DEFAULT 1, " +
            Contract.Entry.COLUMN_DRINKS + " INTEGER DEFAULT 1, " +
            Contract.Entry.COLUMN_MEETING_ROOMS + " INTEGER DEFAULT 1, " +
            Contract.Entry.COLUMN_SHARED + " INTEGER DEFAULT 1, " +
            Contract.Entry.COLUMN_OUTDOOR + " INTEGER DEFAULT 1, " +
            Contract.Entry.COLUMN_PHONE + " TEXT, " +
            Contract.Entry.COLUMN_RATING_COUNTER + " INTEGER DEFAULT 0, " +
            Contract.Entry.COLUMN_ADDRESS + " TEXT NOT NULL);";

    public static final String SQL_CREATE_CURRENT = "CREATE TABLE " + Contract.Entry.CURRENT_TABLE_NAME + " (" +
            Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Entry.COLUMN_CURRENT_OWNER + " INTEGER NOT NULL, " +
            Contract.Entry._ID + " INTEGER NOT NULL);";

    /*public static final String SQL_CREATE_HISTORY = "CREATE TABLE " + Contract.Entry.HISTORY_TABLE_NAME + " (" +
            Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.Entry.COLUMN_PLACE_ID + " INTEGER NOT NULL, " +
            Contract.Entry.COLUMN_USER_ID + " INTEGER NOT NULL, " +
            Contract.Entry.COLUMN_DATE + " TEXT NOT NULL);";*/

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_OWNER);
        db.execSQL(SQL_CREATE_CURRENT);
        //db.execSQL(SQL_CREATE_HISTORY);
        try {
            init(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void init(Context c) throws Exception {
        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_NAME, "Ansary");
        values.put(Contract.Entry.COLUMN_USERNAME, "ansary");
        values.put(Contract.Entry.COLUMN_PASSWORD, "ansary");
        values.put(Contract.Entry.COLUMN_BIRTHDAY, "Ansary");
        values.put(Contract.Entry.COLUMN_EMAIL, "mohamedalansary@gmail.com");
        Uri uri = c.getContentResolver().insert(Contract.Entry.USER_CONTENT_URI, values);
        insertOwner("Island", "Abdo Basha, Abassia", 4.2, "island", "password", "island@gmail.com", c);
        insertOwner("Zone", "Abdo Basha, Abassia", 4.7, "zone", "password", "Zone@gmail.com", c);
    }

    private void insertOwner(String name, String address, double rating, String username, String password, String email, Context c){
        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_NAME, name);
        values.put(Contract.Entry.COLUMN_ADDRESS, address);
        values.put(Contract.Entry.COLUMN_RATING, rating);
        values.put(Contract.Entry.COLUMN_USERNAME, username);
        values.put(Contract.Entry.COLUMN_PASSWORD, password);
        values.put(Contract.Entry.COLUMN_EMAIL, email);

        Uri uri = c.getContentResolver().insert(Contract.Entry.OWNER_CONTENT_URI, values);
    }
}
