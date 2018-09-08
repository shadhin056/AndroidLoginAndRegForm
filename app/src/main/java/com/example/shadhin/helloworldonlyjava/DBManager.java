package com.example.shadhin.helloworldonlyjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

public class DBManager {
    private SQLiteDatabase sqlDB;

    static final String DB_NAME = "DB_Login_Reg";
    static final String TABLE_NAME = "Users";
    static final String COL_ID = "Id";
    static final String COL_USERNAME = "UserName";
    static final String COL_EMAIL = "Email";
    static final String COL_PASSWORD = "Password";
    static final String COL_PHONE = "Phone";
    static final String COL_BIRTHDAY = "Birthday";
    static final int DBVersion = 1;
    static final String CreateTable = "Create table IF NOT EXISTS " + TABLE_NAME + "(ID integer PRIMARY KEY AUTOINCREMENT," + COL_USERNAME + " text," + COL_BIRTHDAY
            + " text," + COL_EMAIL + " text," + COL_PASSWORD + " text," + COL_PHONE + " text);";

    static class DatabaseHelperUser extends SQLiteOpenHelper {
        Context context;

        DatabaseHelperUser(Context context) {
            super(context, DB_NAME, null, DBVersion);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Toast.makeText(context, "Table is created", Toast.LENGTH_LONG).show();
            db.execSQL(CreateTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(" Drop table IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public DBManager(Context context) {
        DatabaseHelperUser db = new DatabaseHelperUser(context);
        sqlDB = db.getWritableDatabase();
    }
    public long insert(ContentValues values){
        long id=sqlDB.insert(TABLE_NAME,"",values);
        return id;
    }
    //select username,password from logins where id=1
    public Cursor query(String[] Projection, String Selection , String[] SelectionArgs, String SortOrder){
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);
        Cursor cursor =qb.query(sqlDB,Projection,Selection,SelectionArgs,null,null,SortOrder);
        return cursor;
    }
}






















