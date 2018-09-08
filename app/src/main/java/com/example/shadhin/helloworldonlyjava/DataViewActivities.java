package com.example.shadhin.helloworldonlyjava;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.ArrayList;

public class DataViewActivities extends AppCompatActivity {
    TextView nameView;
    TextView phoneView;
    TextView birthdayView;
    TextView emailView;
    TextView passwordView;
    DBManager dbManager;
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

        dbManager = new DBManager(this);
        ContentValues values = new ContentValues();
        values.put(DBManager.COL_USERNAME, sessionId1);
        values.put(DBManager.COL_PHONE, sessionId2);
        values.put(DBManager.COL_BIRTHDAY, sessionId3);
        values.put(DBManager.COL_EMAIL, sessionId4);
        values.put(DBManager.COL_PASSWORD, sessionId5);
        long id = dbManager.insert(values);
        if (id > 0) {
           // Toast.makeText(getApplicationContext(), "Data is added and user id : " + id, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Can not inserted : ", Toast.LENGTH_LONG).show();
        }


        //adapter class
        ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
        MyCustomAdapter myadapter;

        Cursor cursor = dbManager.query(null, null, null, null);
        if (cursor.moveToFirst()) {
            String tableData = "";
            do {
                /*tableData+=cursor.getString(cursor.getColumnIndex(DBManager.COL_ID))+","+
                        cursor.getString(cursor.getColumnIndex(DBManager.COL_USERNAME))+","+
                        cursor.getString(cursor.getColumnIndex(DBManager.COL_EMAIL))+","+
                        cursor.getString(cursor.getColumnIndex(DBManager.COL_BIRTHDAY))+","+
                        cursor.getString(cursor.getColumnIndex(DBManager.COL_PHONE))+"::";*/

                listnewsData.add(new AdapterItems(
                        cursor.getString(cursor.getColumnIndex(DBManager.COL_USERNAME)),
                        cursor.getString(cursor.getColumnIndex(DBManager.COL_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(DBManager.COL_PHONE)),
                        cursor.getString(cursor.getColumnIndex(DBManager.COL_BIRTHDAY))));
            } while (cursor.moveToNext());
           Toast.makeText(getApplicationContext(), tableData, Toast.LENGTH_LONG).show();
        }
        //add data and view it
        myadapter = new MyCustomAdapter(listnewsData);


        ListView lsNews = (ListView) findViewById(R.id.lv_user);
        lsNews.setAdapter(myadapter);//intisal with data

    }


    //display news list
    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdpater;

        public MyCustomAdapter(ArrayList<AdapterItems> listnewsDataAdpater) {
            this.listnewsDataAdpater = listnewsDataAdpater;
        }

        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_users, null);

            final AdapterItems s = listnewsDataAdpater.get(position);

            /*TextView id = (TextView) myView.findViewById(R.id.a_id);
            id.setText(s.id);*/
            TextView name = (TextView) myView.findViewById(R.id.a_name_id);
            name.setText(s.nickName);
            TextView phone = (TextView) myView.findViewById(R.id.a_phone_id);
            phone.setText(s.phone);
            TextView email = (TextView) myView.findViewById(R.id.a_email_id);
            email.setText(s.email);
            TextView birthday = (TextView) myView.findViewById(R.id.a_birthday_id);
            birthday.setText(s.birthday);

            return myView;
        }

    }
}
