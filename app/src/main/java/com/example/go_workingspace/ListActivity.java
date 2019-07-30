package com.example.go_workingspace;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.go_workingspace.Data.Contract;
import com.google.android.material.navigation.NavigationView;

public class ListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home)
        {
            Intent main = new Intent(ListActivity.this, MainActivity.class);
            startActivity(main);
        }
        else if (id == R.id.nav_profile)
        {
            Intent user = new Intent(ListActivity.this, Userprofile.class);
            startActivity(user);
        }
        else if (id == R.id.nav_list)
        {
            Intent list = new Intent(ListActivity.this, ListActivity.class);
            startActivity(list);
        }
        else if (id == R.id.nav_Book)
        {
            Intent book = new Intent(ListActivity.this, BookForm.class);
            startActivity(book);
        }
        else if (id == R.id.nav_share) {
            Intent signUp = new Intent(ListActivity.this, SignUp_.class);
            startActivity(signUp);
        }
        else if (id == R.id.nav_help)
        {
            Intent signIn = new Intent(ListActivity.this, SignInActivity.class);
            startActivity(signIn);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
