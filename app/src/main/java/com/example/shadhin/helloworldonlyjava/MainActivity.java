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
    EditText nickName;
    EditText phoneNumber;
    EditText birthDay;
    EditText email;
    EditText password;
    EditText rePassword;
    TextView birthdayTextView;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nickName = findViewById(R.id.nick_name);
        phoneNumber = findViewById(R.id.phone_numer);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.reenter_password);
        birthdayTextView = findViewById(R.id.birthday_date_view);
        birthDay = findViewById(R.id.birthday);


        birthDay.setInputType(InputType.TYPE_NULL);

        birthDay.setOnClickListener(new View.OnClickListener() {
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
                                birthDay.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthdayTextView.setText("Selected Date: " + birthDay.getText());
                // setContentView(R.layout.form_data_view);
                Intent intent = new Intent(MainActivity.this, DataViewActivities.class);

                intent.putExtra("nick_name1", nickName.getText().toString());
                intent.putExtra("phone_number1", phoneNumber.getText().toString());
                intent.putExtra("birthday1", birthDay.getText().toString());
                intent.putExtra("email1", email.getText().toString());
                intent.putExtra("password1", password.getText().toString());
                intent.putExtra("repassword1", rePassword.getText().toString());
                startActivity(intent);
            }
        });
    }
}
