package com.example.go_workingspace;

import android.os.Bundle;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cws_list_item);

        RatingBar ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setMax(5);
    }


}
