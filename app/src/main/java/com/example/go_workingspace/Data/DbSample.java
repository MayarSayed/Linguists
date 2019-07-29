package com.example.go_workingspace.Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

public class DbSample extends AppCompatActivity {
    DbHelper mDbHelper;
    private Cursor getData() {
        String[] projection = {
                Contract.Entry._ID,
                Contract.Entry.COLUMN_NAME
        };

        Cursor cursor = (Cursor) getContentResolver().query(
                Contract.Entry.USER_CONTENT_URI, projection,
                null,
                null,
                null);
        return cursor;
    }
    private void insertUser(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_NAME, "Ibrahim");

        Uri uri = getContentResolver().insert(Contract.Entry.HISTORY_ID_URI, values);
        long newRowId = uri.getPort();
    }
}



