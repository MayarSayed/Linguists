package com.example.go_workingspace;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.go_workingspace.Data.Contract;

public class OwnerCursorAdaptor extends CursorAdapter {
    public OwnerCursorAdaptor(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cws_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView itemName = (TextView) view.findViewById(R.id.name_text_view);
        TextView itemAddress = (TextView) view.findViewById(R.id.address);
        //TextView reviews = view.findViewById(R.id.reviews);

        RatingBar ratingBar = view.findViewById(R.id.rating_bar_item);

        String name = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME));
        String address = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_ADDRESS));
        float rating = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_RATING));
        ratingBar.setRating(rating);
        //int counter = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_RATING_COUNTER));
        //reviews.setText(counter);

        itemName.setText(name);
        itemAddress.setText(address);
    }
}
