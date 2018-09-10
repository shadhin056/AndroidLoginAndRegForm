package com.example.shadhin.helloworldonlyjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AfterLogin extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView phone;
    TextView password;
    TextView birthday;
    String sessionId1;
    String sessionId2;
    String sessionId3;
    String sessionId4;
    String sessionId5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_login);
        name = findViewById(R.id.after_login_name_display);
        email = findViewById(R.id.after_login_email);
        phone = findViewById(R.id.after_login_phone_display);
        birthday = findViewById(R.id.after_login_birthday_display);
        password = findViewById(R.id.after_login_password);

        sessionId1 = getIntent().getStringExtra("nick_name3");
        sessionId2 = getIntent().getStringExtra("phone_number3");
        sessionId3 = getIntent().getStringExtra("birthday3");
        sessionId4 = getIntent().getStringExtra("email3");
        sessionId5 = getIntent().getStringExtra("password3");

        name.setText(sessionId1);
        phone.setText(sessionId2);
        birthday.setText(sessionId3);
        email.setText(sessionId4);
        password.setText(sessionId5);
    }
}
