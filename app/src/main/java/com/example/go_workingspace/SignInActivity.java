package com.example.go_workingspace;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_workingspace.Data.Contract;


public class SignInActivity extends AppCompatActivity {

    private static String LOG_TAG = SignInActivity.class.getSimpleName();
    private int rows = -1, count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if (intent.hasExtra("id")) {
            Bundle b = new Bundle();
            int sId = b.getInt("id");
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("currentId", sId);
            editor.commit();
            Contract.Entry.currentId = sId;
            Intent main = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(main);
        }

        if(prefs.contains("currentId")){
            Contract.Entry.currentId = prefs.getLong("currentId", MODE_PRIVATE);
            Intent main = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(main);
        }
        else{
            setContentView(R.layout.activity_sign_in);

            if (!prefs.getBoolean("firstTime", false)) {
                // <---- run your one time code here
                init();
                // mark first time has ran.
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("firstTime", true);
                editor.commit();
            }
            Button signIn = findViewById(R.id.SignInButton);
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText usernameInput = findViewById(R.id.usernameSignIn);
                    EditText passwordInput = findViewById(R.id.passwordSignIn);
                    String username = usernameInput.getText().toString().trim();
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
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putLong("currentId", cursor.getInt(cursor.getColumnIndex(Contract.Entry._ID)));
                            editor.commit();
                            Contract.Entry.currentId = cursor.getInt(cursor.getColumnIndex(Contract.Entry._ID));
                            Intent main = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(main);
                        }
                        else{
                            msg("Wrong password");
                        }
                    }
                    else {
                        msg("Wrong username");
                    }
                    cursor.close();
                }
            });
            TextView signUp = findViewById(R.id.to_sign_up);
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(SignInActivity.this, SignUp_.class);
                    startActivity(i);
                }
            });
        }
    }

    private void msg(String s){
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        toast.show();
    }
    public void goToSignUp(View view)
    {
        Intent signUp = new Intent(SignInActivity.this, SignUp_.class);
        startActivity(signUp);
    }

    public void init() {
        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_NAME, "Ansary");
        values.put(Contract.Entry.COLUMN_USERNAME, "ansary");
        values.put(Contract.Entry.COLUMN_PASSWORD, "ansary");
        values.put(Contract.Entry.COLUMN_BIRTHDAY, "Ansary");
        values.put(Contract.Entry.COLUMN_EMAIL, "mohamedalansary@gmail.com");
        Uri uri = getContentResolver().insert(Contract.Entry.USER_CONTENT_URI, values);
        insertOwner("Island", "Abdo Basha, Abassia", 4.2, "island", "password", "island@gmail.com");
        insertOwner("Zone", "Abdo Basha, Abassia", 4.7, "zone", "password", "Zone@gmail.com");
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
}