package com.example.go_workingspace;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_workingspace.Data.Contract;


public class SignInActivity extends AppCompatActivity {

    private static String LOG_TAG = SignInActivity.class.getSimpleName();
    private int rows = -1, count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        insert();

        Button signIn = findViewById(R.id.SignInButton);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameInput = findViewById(R.id.usernameSignIn);
                EditText passwordInput = findViewById(R.id.passwordSignIn);
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                String[] projection = {
                        Contract.Entry._ID,
                        Contract.Entry.COLUMN_USERNAME,
                        Contract.Entry.COLUMN_EMAIL,
                        Contract.Entry.COLUMN_PASSWORD
                };
                String selection = Contract.Entry.COLUMN_USERNAME + "=?";
                String[] selectionArgs = {
                        username
                };
                Cursor cursor = (Cursor) getContentResolver().query(
                        Contract.Entry.USER_CONTENT_URI, projection,
                        selection,
                        selectionArgs,
                        null);
                if(cursor.moveToNext()){
                    String userPassword = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_PASSWORD));
                    if(password.equals(userPassword)){
                        msg("Signed in successfully");
                        Contract.Entry.currentId = cursor.getInt(cursor.getColumnIndex(Contract.Entry._ID));
                        Intent main = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(main);
                    }
                    else{
                        msg("Wrong username or password");
                    }
                }
                else {
                    msg("Wrong username or password");
                }
                cursor.close();
            }
        });

    }
    private void insert(){
        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_NAME, "Ansary");
        values.put(Contract.Entry.COLUMN_USERNAME, "ansary");
        values.put(Contract.Entry.COLUMN_PASSWORD, "ansary");
        values.put(Contract.Entry.COLUMN_BIRTHDAY, "Ansary");
        values.put(Contract.Entry.COLUMN_EMAIL, "mohamedalansary@gmail.com");
        Uri uri = getContentResolver().insert(Contract.Entry.USER_CONTENT_URI, values);
    }

    private void msg(String s){
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        toast.show();
    }
}