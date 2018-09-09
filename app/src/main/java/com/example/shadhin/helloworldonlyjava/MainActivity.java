package com.example.shadhin.helloworldonlyjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.custom.SimpleCustomValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
    Button resetButton;
    Button randomButton;
    Button quickDataLoad;
    ImageButton calenderButton;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find id
        nickName = findViewById(R.id.nick_name);
        phoneNumber = findViewById(R.id.phone_numer);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.reenter_password);
        birthdayTextView = findViewById(R.id.birthday_date_view);
        birthDay = findViewById(R.id.up_birthday);
        submitButton = findViewById(R.id.submit);
        resetButton = findViewById(R.id.reset);
        randomButton = findViewById(R.id.random_input);
        calenderButton = findViewById(R.id.calender_btn);
        quickDataLoad = findViewById(R.id.quick_load_data);


        //validation using awesome AwesomeValidation
        //https://github.com/thyrlian/AwesomeValidation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(MainActivity.this, R.id.nick_name, "[a-zA-Z\\s]+", R.string.error_nickName);
        // awesomeValidation.addValidation(MainActivity.this,R.id.phone_numer, RegexTemplate.TELEPHONE,R.string.error_phoneNumber);
        awesomeValidation.addValidation(MainActivity.this, R.id.phone_numer, "[0-9\\s]+", R.string.error_phoneNumber);
        awesomeValidation.addValidation(MainActivity.this, R.id.email, android.util.Patterns.EMAIL_ADDRESS, R.string.error_email);
        // String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        String regexPassword = "[a-zA-Z0-9\\s]+";
        awesomeValidation.addValidation(MainActivity.this, R.id.password, regexPassword, R.string.error_password);
        awesomeValidation.addValidation(MainActivity.this, R.id.reenter_password, R.id.password, R.string.error_rePassword);
        // to validate with a simple custom validator function
        awesomeValidation.addValidation(MainActivity.this, R.id.up_birthday, new SimpleCustomValidation() {
            @Override
            public boolean compare(String input) {
                // check if the age is >= 18
                try {
                    Calendar calendarBirthday = Calendar.getInstance();
                    Calendar calendarToday = Calendar.getInstance();
                    calendarBirthday.setTime(new SimpleDateFormat("dd/MM/yyyy", Locale.US).parse(input));
                    int yearOfToday = calendarToday.get(Calendar.YEAR);
                    int yearOfBirthday = calendarBirthday.get(Calendar.YEAR);
                    if (yearOfToday - yearOfBirthday > 18) {
                        return true;
                    } else if (yearOfToday - yearOfBirthday == 18) {
                        int monthOfToday = calendarToday.get(Calendar.MONTH);
                        int monthOfBirthday = calendarBirthday.get(Calendar.MONTH);
                        if (monthOfToday > monthOfBirthday) {
                            return true;
                        } else if (monthOfToday == monthOfBirthday) {
                            if (calendarToday.get(Calendar.DAY_OF_MONTH) >= calendarBirthday.get(Calendar.DAY_OF_MONTH)) {
                                return true;
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }, R.string.error_birthDay);
        // date pick from calender (when click on editText)
        //birthDay.setInputType(InputType.TYPE_NULL);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickName.setText("");
                email.setText("");
                phoneNumber.setText("");
                birthDay.setText("");
                password.setText("");
                rePassword.setText("");
            }
        });
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickName.setText("shadhin");
                email.setText("shadhinemail@gmail.com");
                phoneNumber.setText("01672708329");
                birthDay.setText("31/12/1993");
                password.setText("123456");
                rePassword.setText("123456");
            }
        });

        calenderButton.setOnClickListener(new View.OnClickListener() {
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
        //after submit the button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    // Toast.makeText(MainActivity.this, "Date Received Successfully", Toast.LENGTH_SHORT).show();
                    //birthdayTextView.setText("Selected Date: " + birthDay.getText());
                    // setContentView(R.layout.form_data_view);
                    Intent intent = new Intent(MainActivity.this, DataViewActivities.class);

                    intent.putExtra("nick_name1", nickName.getText().toString());
                    intent.putExtra("phone_number1", phoneNumber.getText().toString());
                    intent.putExtra("birthday1", birthDay.getText().toString());
                    intent.putExtra("email1", email.getText().toString());
                    intent.putExtra("password1", password.getText().toString());
                    intent.putExtra("repassword1", rePassword.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        quickDataLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataViewActivities.class);
                startActivity(intent);
            }
        });
    }
}
