package com.example.go_workingspace;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.go_workingspace.Data.Contract;
import com.google.android.material.navigation.NavigationView;

public class CwsProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Image ID to show in gallery
    int imageID = R.drawable.main; /*TODO*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cws_profile);

        String[] projection = {
                Contract.Entry._ID,
                Contract.Entry.COLUMN_NAME,
                Contract.Entry.COLUMN_ADDRESS,
                Contract.Entry.COLUMN_RATING,
                Contract.Entry.COLUMN_PHONE,
                Contract.Entry.COLUMN_RATING_COUNTER,
                Contract.Entry.COLUMN_MEETING_ROOMS,
                Contract.Entry.COLUMN_WIFI,
                Contract.Entry.COLUMN_DRINKS,
                Contract.Entry.COLUMN_SHARED,
                Contract.Entry.COLUMN_OUTDOOR
        }; // Add Columns you want to display
        String selection = Contract.Entry._ID + "=?";
        String[] selectionArgs = {String.valueOf(Contract.Entry.currentCwsId)};
        Cursor cursor = (Cursor) getContentResolver().query(
                Contract.Entry.OWNER_CONTENT_URI, projection,
                selection,
                selectionArgs,
                null);

        String name = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME));
        String address = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_ADDRESS));
        float rating = cursor.getFloat(cursor.getColumnIndex(Contract.Entry.COLUMN_RATING));
        String phone = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_PHONE));
        int counter = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_RATING_COUNTER));
        int meeting =  cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_MEETING_ROOMS));
        int wifi = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_WIFI));
        int drinks = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_DRINKS));
        int shared = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_SHARED));
        int outdoor = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_OUTDOOR));

        TextView addressText = findViewById(R.id.address);
        TextView phoneText = findViewById(R.id.cwsPhone);
        View wifiView = findViewById(R.id.wifi);
        View outdoorView = findViewById(R.id.outdoor_area);
        View shareView = findViewById(R.id.shared_area);
        View meetingView = findViewById(R.id.meeting_rooms);
        View drinksView = findViewById(R.id.drinks);

        addressText.setText(address);
        phoneText.setText(phone);
        if(meeting == 0){
            meetingView.setVisibility(View.INVISIBLE);
        }
        if(outdoor == 0){
            outdoorView.setVisibility(View.INVISIBLE);
        }
        if(wifi == 0){
            wifiView.setVisibility(View.INVISIBLE);
        }
        if(drinks == 0){
            drinksView.setVisibility(View.INVISIBLE);
        }
        if(shared == 0){
            shareView.setVisibility(View.INVISIBLE);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Find left arrow
        ImageView leftArrow = findViewById(R.id.previous_photo);

        // Set OnClickListener on left arrow
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Find gallery Image View
                final ImageView gallery = findViewById(R.id.cws_gallery);
                // imageID --; /*TODO*/
                gallery.setImageResource(imageID);
            }
        });

        // Find right arrow
        ImageView rightArrow = findViewById(R.id.next_photo);

        // Set OnClickListener on left arrow
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Find gallery Image View
                final ImageView gallery = findViewById(R.id.cws_gallery);
                // imageID ++; /*TODO*/
                gallery.setImageResource(imageID);
            }
        });

        // Find Location Layout
        LinearLayout location = findViewById(R.id.show_location);

        // Set OnClickListener on Location Layout
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = "Mandala+co+working+space";
                Intent openMaps = new Intent(Intent.ACTION_VIEW);
                openMaps.setData(Uri.parse("geo:30.0144, 31.2357?q=" + address));
                openMaps.setPackage("com.google.android.apps.maps");
                if (openMaps.resolveActivity(getPackageManager()) != null) {
                    startActivity(openMaps);
                }
            }
        });

        // Find Call Layout
        LinearLayout makeCall = findViewById(R.id.make_call);

        // Set OnClickListener on Call Layout
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = "+201115034777"; /*TODO*/
                Intent openDial = new Intent(Intent.ACTION_DIAL);
                openDial.setData(Uri.parse("tel:" + phoneNumber));
                if (openDial.resolveActivity(getPackageManager()) != null) {
                    startActivity(openDial);
                }
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home)
        {
            Intent main = new Intent(CwsProfileActivity.this, MainActivity.class);
            startActivity(main);
        }
        else if (id == R.id.nav_profile)
        {
            Intent user = new Intent(CwsProfileActivity.this, Userprofile.class);
            startActivity(user);
        }
        else if (id == R.id.nav_list)
        {
            Intent list = new Intent(CwsProfileActivity.this, ListActivity.class);
            startActivity(list);
        }
        else if (id == R.id.nav_Book)
        {
            Intent book = new Intent(CwsProfileActivity.this, BookForm.class);
            startActivity(book);
        }
        else if (id == R.id.nav_share) {
            Intent signUp = new Intent(CwsProfileActivity.this, SignUp_.class);
            startActivity(signUp);

        }
        else if (id == R.id.nav_help)
        {
            Intent signIn = new Intent(CwsProfileActivity.this, SignInActivity.class);
            startActivity(signIn);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
