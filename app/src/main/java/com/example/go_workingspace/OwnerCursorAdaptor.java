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

        String name = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME));
        String address = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_ADDRESS));

        itemName.setText(name);
        itemAddress.setText(address);
    }
}
