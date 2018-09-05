package com.example.shadhin.helloworldonlyjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity {
    DatePickerDialog picker;
    EditText nick_name;
    EditText phone_number;
    EditText birthday;
    TextView birthday_day_text_view;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nick_name= findViewById(R.id.nick_name);
        phone_number= findViewById(R.id.phone_numer);
        birthday_day_text_view=findViewById(R.id.birthday_date_view);
        birthday= findViewById(R.id.birthday);
        birthday.setInputType(InputType.TYPE_NULL);

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                birthday.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthday_day_text_view.setText("Selected Date: "+ birthday.getText());
               // setContentView(R.layout.form_data_view);
               Intent intent =new Intent(MainActivity.this,DataViewActivities.class);

                intent.putExtra("nick_name1",nick_name.getText().toString());
                intent.putExtra("phone_number1",phone_number.getText().toString());
                intent.putExtra("birthday1",birthday.getText().toString());
                startActivity(intent);
            }
        });
    }
    }
