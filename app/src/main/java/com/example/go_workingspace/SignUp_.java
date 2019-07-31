package com.example.go_workingspace;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.go_workingspace.Data.Contract;

import java.util.Calendar;

public class SignUp_ extends AppCompatActivity {

    private Button mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        mDisplayDate =  findViewById(R.id.date_text);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Calendar cal = Calendar.getInstance();
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    SignUp_.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year,month,day);
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

        Button signUp = findViewById(R.id.SignUpButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText fullNameText = findViewById(R.id.first_name);
                EditText usernameText = findViewById(R.id.user_name);
                EditText phoneText = findViewById(R.id.phone);
                EditText emailText = findViewById(R.id.email);
                EditText passwordText = findViewById(R.id.password);
                EditText rePasswordText = findViewById(R.id.re_password);
                RadioGroup genderRadio = findViewById(R.id.radioGender);

                String fullname = fullNameText.getText().toString();
                String username = usernameText.getText().toString();
                String phone = phoneText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                String rePassword = rePasswordText.getText().toString();
                String birthday = day + "/" + month + "/" + year;
                int genderId = genderRadio.getCheckedRadioButtonId();

                String[] projection = {
                        Contract.Entry._ID,
                        Contract.Entry.COLUMN_EMAIL,
                        Contract.Entry.COLUMN_USERNAME,
                        Contract.Entry.COLUMN_PHONE
                }; // Add Columns you want to display

                String selectionE = Contract.Entry.COLUMN_EMAIL + "=?";
                String[] selectionArgsE = {
                        email
                };
                Cursor cursorE = (Cursor) getContentResolver().query(
                        Contract.Entry.USER_CONTENT_URI, projection,
                        selectionE,
                        selectionArgsE,
                        null);


                String selectionU = Contract.Entry.COLUMN_USERNAME + "=?";
                String[] selectionArgsU = {
                        username
                };
                Cursor cursorU = (Cursor) getContentResolver().query(
                        Contract.Entry.USER_CONTENT_URI, projection,
                        selectionU,
                        selectionArgsU,
                        null);

                String selectionP = Contract.Entry.COLUMN_EMAIL + "=?";
                String[] selectionArgsP = {
                        email
                };
                Cursor cursorP = (Cursor) getContentResolver().query(
                        Contract.Entry.OWNER_CONTENT_URI, projection,
                        selectionP,
                        selectionArgsP,
                        null);

                if(cursorE.moveToNext()){
                    msg("Email already used");
                }
                else if(cursorU.moveToNext()){
                    msg("Username already used try another one");
                }
                else if(cursorP.moveToNext()){
                    msg("Phone number already used");
                }
                else if(TextUtils.isEmpty(fullNameText.getText())){
                    msg("Full name is required");
                }
                else if(TextUtils.isEmpty(usernameText.getText())){
                    msg("Username is required");
                }
                else if(TextUtils.isEmpty(phoneText.getText())){
                    msg("Phone number is required");
                }
                else if(TextUtils.isEmpty(emailText.getText())){
                    msg("Email is required");
                }
                else if(TextUtils.isEmpty(passwordText.getText())){
                    msg("Enter a password");
                }
                else if(TextUtils.isEmpty(rePasswordText.getText())){
                    msg("Confirm your password");
                }
                else if(!(password.equals(rePassword))){
                    msg("Password didn't match");
                }
                else{
                    ContentValues values = new ContentValues();
                    values.put(Contract.Entry.COLUMN_NAME, fullname);
                    values.put(Contract.Entry.COLUMN_USERNAME, username);
                    values.put(Contract.Entry.COLUMN_PASSWORD, password);
                    values.put(Contract.Entry.COLUMN_EMAIL, email);
                    values.put(Contract.Entry.COLUMN_PHONE, phone);
                    values.put(Contract.Entry.COLUMN_BIRTHDAY, birthday);
                    Uri uri = getContentResolver().insert(Contract.Entry.USER_CONTENT_URI, values);
                    String[] projectionI = {
                            Contract.Entry._ID,
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
                    cursor.moveToNext();
                    Contract.Entry.currentId = cursor.getInt(cursor.getColumnIndex(Contract.Entry._ID));
                    msg("Signed Up successfully, Welcome " + fullname);
                    Intent signIn = new Intent(SignUp_.this, SignInActivity.class);
                    Bundle b = new Bundle();
                    b.putLong("id", Contract.Entry.currentId);
                    signIn.putExtras(b);
                    startActivity(signIn);
                }
            }
        });

    }

    private void msg(String s){
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        toast.show();
    }


}
