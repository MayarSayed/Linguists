package com.example.go_workingspace;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_workingspace.Data.Contract;

import java.util.ArrayList;
import java.util.Calendar;


public class BookForm extends AppCompatActivity {

    private static String[] Rooms = new String[] {"Small Meeting Room" , "Large Meeting Room", "Training Room" ,"Sharing Area"};
    private static ArrayList<String> WorkingSpace;
    private Spinner  spinner2;

    EditText chooseTime ;
    TimePicker TimePickerDialog ;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextClock tClock;
    private TextView tView;
    private Button btn;
    private int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);


        //Room Type
//       Spinner spin =  findViewById(R.id.Room_type);
  //     spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

       //Creating the ArrayAdapter instance having the country list
    //    ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Rooms);
      //  aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //Setting the ArrayAdapter data on the Spinner
      // spin.setAdapter(aa);
      // spin.setOnItemSelectedListener();

        //working Space Name
        WorkingSpace = new ArrayList<String>();

        String[] projection = {
                Contract.Entry._ID,
                Contract.Entry.COLUMN_NAME,
                Contract.Entry.COLUMN_ADDRESS
        }; // Add Columns you want to display

        Cursor cursor = (Cursor) getContentResolver().query(
                Contract.Entry.OWNER_CONTENT_URI, projection,
                null,
                null,
                null);

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME));
            String address = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_ADDRESS));
            WorkingSpace.add(name + " " + address);
        }

        ArrayAdapter<String> adapter_workingSpace =
                new ArrayAdapter<>(this,android.R.layout.select_dialog_item, WorkingSpace);
        AutoCompleteTextView acTextView =  findViewById(R.id.WorkingSpace_name);
        acTextView.setThreshold(1);
        acTextView.setAdapter(adapter_workingSpace);

        {
            mDisplayDate = findViewById(R.id.date_text_2);
            mDisplayDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            BookForm.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mDateSetListener,
                            year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    //   Log.d(TAG , "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                    String date = month + "/" + day + "/" + year;
                    mDisplayDate.setText(date);
                    mDisplayDate.setClickable(false);
                }
            };
        }

        Button inc = findViewById(R.id.inc);
        Button dec = findViewById(R.id.dec);

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter <= 12)
                    counter++;
                TextView quantity = findViewById(R.id.quantity_text_view);
                quantity.setText(String.valueOf(counter));
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter > 1)
                    counter--;
                TextView quantity = findViewById(R.id.quantity_text_view);
                quantity.setText(String.valueOf(counter));
            }
        });

        Button submit = findViewById(R.id.submitBooking);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg("Record Submitted");
                Intent main = new Intent(BookForm.this, MainActivity.class);
                startActivity(main);
            }
        });


    }
    private void msg(String s){
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        toast.show();
       //////////////////////////////////////////
//        chooseTime = findViewById(R.id.ChooseTime);
//
//        chooseTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TimePickerDialog timePickerDialog = new TimePickerDialog(
//                        BookForm.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
//                        chooseTime.setText(hourOfDay + minutes);
//                    }
//                }, 0, 0, false);
//                timePickerDialog.show();
//            }
//        });
        // add items into spinner dynamically

    }
}
