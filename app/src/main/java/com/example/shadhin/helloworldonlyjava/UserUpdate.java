package com.example.shadhin.helloworldonlyjava;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class UserUpdate extends AppCompatActivity{
    EditText upName;
    EditText upPhone;
    EditText upBDate;
    EditText upEmail;
    EditText upPassword;
    EditText upRePassword;

    String sessionId1;
    String sessionId2;
    String sessionId3;
    String sessionId4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user);
        sessionId1 = getIntent().getStringExtra("nick_name2");
        sessionId2 = getIntent().getStringExtra("phone_number2");
        sessionId3 = getIntent().getStringExtra("birthday2");
        sessionId4 = getIntent().getStringExtra("email2");
        upName=findViewById(R.id.up_nick_name);
        upPhone=findViewById(R.id.up_phone_numer);
        upBDate=findViewById(R.id.up_birthday);
        upEmail=findViewById(R.id.up_email);
        upPassword=findViewById(R.id.up_password);
        upRePassword=findViewById(R.id.up_reenter_password);

        upName.setText(sessionId1);
        upPhone.setText(sessionId2);
        upBDate.setText(sessionId3);
        upEmail.setText(sessionId4);

    }
}
