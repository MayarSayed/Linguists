package com.example.go_workingspace;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.TextView;

public class Userprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        TextView myUserName = (TextView) findViewById(R.id.theUserName );
        myUserName.setText("MayarSayed");
        TextView myUserFullName = (TextView) findViewById(R.id.theUserFullName);
        myUserFullName.setText("Mayar Sayed");
        TextView myUserPassword = (TextView) findViewById(R.id.userPassword);
        myUserPassword.setText("11111");
        TextView myUserEmail = (TextView) findViewById(R.id.userEmail);
        myUserEmail.setText("mayaar.sayed@gmail.com");
    }

}
