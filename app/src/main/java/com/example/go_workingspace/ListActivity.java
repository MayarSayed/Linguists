package com.example.go_workingspace;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        getSupportActionBar().setTitle("Go-working Spaces Explore");

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

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = searchText.getText().toString().trim();
                String[] projection = {
                        Contract.Entry._ID,
                        Contract.Entry.COLUMN_NAME,
                        Contract.Entry.COLUMN_ADDRESS,
                        Contract.Entry.COLUMN_RATING
                };
                String selection = Contract.Entry.COLUMN_ADDRESS + " LIKE '%" + search + "%' OR " + Contract.Entry.COLUMN_NAME + " LIKE '%" + search + "%'";
                String[] selectionArgs = {

                };
                Cursor cursor = (Cursor) getContentResolver().query(
                        Contract.Entry.OWNER_CONTENT_URI, projection,
                        selection,
                        null,
                        null);


                OwnerCursorAdaptor ownerCursorAdaptor = new OwnerCursorAdaptor(ListActivity.this, cursor);

                listView = (ListView) findViewById(R.id.list);

                listView.setAdapter(ownerCursorAdaptor);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
