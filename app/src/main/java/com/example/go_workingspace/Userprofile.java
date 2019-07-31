package com.example.go_workingspace;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_workingspace.Data.Contract;

public class Userprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        String[] projection = {
                Contract.Entry._ID,
                Contract.Entry.COLUMN_NAME,
                Contract.Entry.COLUMN_USERNAME,
                Contract.Entry.COLUMN_EMAIL
        }; // Add Columns you want to display

        String selection = Contract.Entry._ID + "+?";

        String[] selectionArgs = {
            String.valueOf(Contract.Entry.currentId)
        };

        Cursor cursor = (Cursor) getContentResolver().query(
                Contract.Entry.USER_CONTENT_URI, projection,
                selection,
                selectionArgs,
                null);

        cursor.moveToNext();

        String name = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME));
        String email = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_EMAIL));
        String username = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_USERNAME));

        TextView myUserName = (TextView) findViewById(R.id.theUserName );
        myUserName.setText(username);

        TextView myUserFullName = (TextView) findViewById(R.id.theUserFullName);
        myUserFullName.setText(name);
        TextView myUserPassword = (TextView) findViewById(R.id.userPassword);
        myUserPassword.setText("******");
        TextView myUserEmail = (TextView) findViewById(R.id.userEmail);
        myUserEmail.setText(email);
    }

}
