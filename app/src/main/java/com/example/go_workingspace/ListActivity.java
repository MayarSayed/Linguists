package com.example.go_workingspace;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_workingspace.Data.Contract;
import com.example.go_workingspace.Data.DbHelper;

public class ListActivity extends AppCompatActivity {

    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //RatingBar ratingBar = findViewById(R.id.rating_bar);
        //ratingBar.setMax(10);

        insertOwner("Island", "Abdo Basha, Abassia", 4.2, "island", "password", "island@gmail.com");
        insertOwner("Zone", "Abdo Basha, Abassia", 4.7, "zone", "password", "Zone@gmail.com");
        displayData();
    }

    private void insertOwner(String name, String address, double rating, String username, String password, String email){
        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_NAME, name);
        values.put(Contract.Entry.COLUMN_ADDRESS, address);
        values.put(Contract.Entry.COLUMN_RATING, rating);
        values.put(Contract.Entry.COLUMN_USERNAME, username);
        values.put(Contract.Entry.COLUMN_PASSWORD, password);
        values.put(Contract.Entry.COLUMN_EMAIL, email);
        Uri uri = getContentResolver().insert(Contract.Entry.OWNER_CONTENT_URI, values);
    }

    private void displayData(){
        String[] projection = {
                Contract.Entry._ID,
                Contract.Entry.COLUMN_NAME,
                Contract.Entry.COLUMN_ADDRESS,
                Contract.Entry.COLUMN_RATING
        };

        Cursor cursor = (Cursor) getContentResolver().query(
                Contract.Entry.OWNER_CONTENT_URI, projection,
                null,
                null,
                null);

        // Find the ListView which will be populated with the pet data
        ListView listView = (ListView) findViewById(R.id.list);

        OwnerCursorAdaptor ownerCursorAdaptor = new OwnerCursorAdaptor(this, cursor);

        listView.setAdapter(ownerCursorAdaptor);
    }


}
