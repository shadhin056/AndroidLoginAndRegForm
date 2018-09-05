package com.example.shadhin.helloworldonlyjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DataViewActivities extends AppCompatActivity{
    TextView name;
    TextView phone;
    TextView bdaynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_data_view);

        name=(TextView)findViewById(R.id.name_display);
        phone=(TextView)findViewById(R.id.phone_display);
        bdaynow=(TextView)findViewById(R.id.birthday_display_now);
        String sessionId1= getIntent().getStringExtra("nick_name1");
        String sessionId2= getIntent().getStringExtra("phone_number1");
        String sessionId3= getIntent().getStringExtra("birthday1");
        name.setText(sessionId1);
        phone.setText(sessionId2);
        bdaynow.setText(sessionId3);

    }
}
