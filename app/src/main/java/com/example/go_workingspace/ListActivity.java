package com.example.go_workingspace;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_workingspace.Data.Contract;

public class ListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String[] projection = {
                Contract.Entry._ID,
                Contract.Entry.COLUMN_NAME,
                Contract.Entry.COLUMN_ADDRESS,
                Contract.Entry.COLUMN_RATING
        };

        final Cursor cursor = (Cursor) getContentResolver().query(
                Contract.Entry.OWNER_CONTENT_URI, projection,
                null,
                null,
                null);



        final OwnerCursorAdaptor ownerCursorAdaptor = new OwnerCursorAdaptor(this, cursor);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(ownerCursorAdaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor listItem = (Cursor) listView.getItemAtPosition(position);
                Contract.Entry.currentCwsId = listItem.getInt(listItem.getColumnIndex(Contract.Entry._ID));
                Intent cwsProfile = new Intent(ListActivity.this, CwsProfileActivity.class);
                startActivity(cwsProfile);
            }
        });

        final EditText searchText = findViewById(R.id.search_data);
        searchText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String search = searchText.getText().toString();
                    String[] projection = {
                            Contract.Entry._ID,
                            Contract.Entry.COLUMN_NAME,
                            Contract.Entry.COLUMN_ADDRESS,
                            Contract.Entry.COLUMN_RATING
                    };
                    String selection = Contract.Entry.COLUMN_ADDRESS + "=? OR " + Contract.Entry.COLUMN_NAME + "=?";
                    String[] selectionArgs = {
                            search,
                            search
                    };
                    Cursor cursor = (Cursor) getContentResolver().query(
                            Contract.Entry.OWNER_CONTENT_URI, projection,
                            selection,
                            selectionArgs,
                            null);



                    OwnerCursorAdaptor ownerCursorAdaptor = new OwnerCursorAdaptor(ListActivity.this, cursor);

                    listView = (ListView) findViewById(R.id.list);

                    listView.setAdapter(ownerCursorAdaptor);
                }
                return false;
            }
        });
    }
}
