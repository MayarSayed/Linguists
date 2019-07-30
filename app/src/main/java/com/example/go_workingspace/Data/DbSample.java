package com.example.go_workingspace.Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_workingspace.OwnerCursorAdaptor;
import com.example.go_workingspace.R;

public class DbSample extends AppCompatActivity {
    private void insertOwner(String name, String address, double rating, String username, String password, String email){
        ContentValues values = new ContentValues(); // Like array but with (key, value) instead of (index, value)
        values.put(Contract.Entry.COLUMN_NAME, name); // for the column "name" put value name in the new row
        values.put(Contract.Entry.COLUMN_ADDRESS, address);
        values.put(Contract.Entry.COLUMN_RATING, rating);
        values.put(Contract.Entry.COLUMN_USERNAME, username);
        values.put(Contract.Entry.COLUMN_PASSWORD, password);
        values.put(Contract.Entry.COLUMN_EMAIL, email);
        Uri uri = getContentResolver().insert(Contract.Entry.OWNER_CONTENT_URI, values); // OWNER_CONTENT_URI for owner table
    }

    private void displayDataAsList(){
        String[] projection = {
                Contract.Entry._ID,
                Contract.Entry.COLUMN_NAME,
                Contract.Entry.COLUMN_ADDRESS,
                Contract.Entry.COLUMN_RATING
        }; // Add Columns you want to display

        Cursor cursor = (Cursor) getContentResolver().query(
                Contract.Entry.OWNER_CONTENT_URI, projection,
                null,
                null,
                null);

        // Find the ListView which will be populated with the pet data
        ListView listView = (ListView) findViewById(R.id.list); // listView to show data in

        OwnerCursorAdaptor ownerCursorAdaptor = new OwnerCursorAdaptor(this, cursor);

        listView.setAdapter(ownerCursorAdaptor);
    }

    private Cursor displayDataAsList2(){
        String[] projection = {
                Contract.Entry._ID,
                Contract.Entry.COLUMN_NAME,
                Contract.Entry.COLUMN_ADDRESS
        }; // Add Columns you want to display

        Cursor cursor = (Cursor) getContentResolver().query(
                Contract.Entry.OWNER_CONTENT_URI, projection,
                null,
                null,
                null);

        String name = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME));
        String address = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_ADDRESS));

        return cursor;
    }

    private void insertUser(String name, String address, double rating, String username, String password, String email, String birthday){
        ContentValues values = new ContentValues(); // Like array but with (key, value) instead of (index, value)
        values.put(Contract.Entry.COLUMN_NAME, name); // for the column "name" put value name in the new row
        values.put(Contract.Entry.COLUMN_ADDRESS, address);
        values.put(Contract.Entry.COLUMN_RATING, rating);
        values.put(Contract.Entry.COLUMN_USERNAME, username);
        values.put(Contract.Entry.COLUMN_PASSWORD, password);
        values.put(Contract.Entry.COLUMN_EMAIL, email);
        values.put(Contract.Entry.COLUMN_BIRTHDAY, birthday);
        Uri uri = getContentResolver().insert(Contract.Entry.USER_CONTENT_URI, values); // OWNER_CONTENT_URI for owner table
    }

}



