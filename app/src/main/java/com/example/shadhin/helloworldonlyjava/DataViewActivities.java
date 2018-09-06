package com.example.shadhin.helloworldonlyjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class DataViewActivities extends AppCompatActivity {
    TextView nameView;
    TextView phoneView;
    TextView birthdayView;
    TextView emailView;
    TextView passwordView;
    //AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_data_view);




        nameView = (TextView) findViewById(R.id.name_display);
        phoneView = (TextView) findViewById(R.id.phone_display);
        birthdayView = (TextView) findViewById(R.id.birthday_display);
        emailView = (TextView) findViewById(R.id.email);
        passwordView = (TextView) findViewById(R.id.password);

        String sessionId1 = getIntent().getStringExtra("nick_name1");
        String sessionId2 = getIntent().getStringExtra("phone_number1");
        String sessionId3 = getIntent().getStringExtra("birthday1");
        String sessionId4 = getIntent().getStringExtra("email1");
        String sessionId5 = getIntent().getStringExtra("password1");
        String sessionId6 = getIntent().getStringExtra("repassword1");
        nameView.setText(sessionId1);
        phoneView.setText(sessionId2);
        birthdayView.setText(sessionId3);
        emailView.setText(sessionId4);
        passwordView.setText(sessionId5);

    }
}
