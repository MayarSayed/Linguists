package com.example.go_workingspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class CwsProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cws_profile);

        // Find Location Layout
        LinearLayout location = findViewById(R.id.show_location);

        // Set OnClickListener on Location Layout
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = "Mandala+co+working+space";
                Intent openMaps = new Intent(Intent.ACTION_VIEW);
                openMaps.setData(Uri.parse("geo:30.0144, 31.2357?q=" + address));
                if (openMaps.resolveActivity(getPackageManager()) != null) {
                    startActivity(openMaps);
                }
            }
        });
    }
}
