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
        setContentView(R.layout.cws_list_item);

        RatingBar ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setMax(10);

        mDbHelper = new DbHelper(this);

        insertOwner("Island", "Abdo Basha, Abassia", 4.2);
        insertOwner("Zone", "Abdo Basha, Abassia", 4.3);
        displayData();
    }

    private void insertOwner(String name, String address, double rating){
        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_NAME, name);
        values.put(Contract.Entry.COLUMN_ADDRESS, address);
        values.put(Contract.Entry.COLUMN_RATING, rating);

        Uri uri = getContentResolver().insert(Contract.Entry.HISTORY_ID_URI, values);
        long newRowId = uri.getPort();
    }

    private void displayData(){
        String[] projection = {
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
