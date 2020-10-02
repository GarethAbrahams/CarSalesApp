package com.garethabrahams.carsalesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CarSales.db";

    public static final String CAR_TABLE = "Car_Table";
    public static final String USER_TABLE = "User_Table";

    public static final String USERNAME_COL = "Username";
    public static final String PASSWORD_COL = "Password";
    public static final String NAME_COL = "Name";
    public static final String SURNAME_COL = "Surname";

    public static final String CA_NUM_COL = "CA_NUM";
    public static final String BRAND_COL = "BRAND";
    public static final String MODEL_COL = "MODEL";
    public static final String YEAR_COL = "YEAR";
    public static final String PRICE_COL = "PRICE";
    public static final String STATUS_COL = "Status";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+CAR_TABLE+"(CA_NUM PRIMARY KEY, BRAND TEXT, MODEL TEXT, YEAR TEXT, PRICE TEXT,STATUS TEXT)");
        db.execSQL("CREATE TABLE "+USER_TABLE+"(USERNAME PRIMARY KEY, PASSWORD TEXT, NAME TEXT, SURNAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion==1){
            db.execSQL("DROP TABLE IF EXISTS "+CAR_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
            onCreate(db);
        }
    }

    public boolean insertUser(String username, String password, String name, String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME_COL, username);
        contentValues.put(PASSWORD_COL, password);
        contentValues.put(NAME_COL, name);
        contentValues.put(SURNAME_COL, surname);
        long result = db.insert(USER_TABLE, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean insertData(String CA_num, String brand, String model, String year, String price,String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CA_NUM_COL, CA_num);
        contentValues.put(BRAND_COL, brand);
        contentValues.put(MODEL_COL, model);
        contentValues.put(YEAR_COL, year);
        contentValues.put(PRICE_COL, price);
        contentValues.put(STATUS_COL, status);
        long result = db.insert(CAR_TABLE, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor credCheck (String user,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean status;
        Cursor data = db.rawQuery("select * from "+USER_TABLE+" where Username = "+"'"+user+"'",null );
        return data;
    }
    public Cursor getAllUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+USER_TABLE,null );
        return results;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+CAR_TABLE,null );
        return results;
    }

    public Cursor findbyCaNumber(String CA_no){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+CAR_TABLE+" where CA_NUM = "+"'"+CA_no+"'",null );
        return results;
    }

    public Cursor findAvailCars(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+CAR_TABLE+" where STATUS = 'Available'",null );
        return results;
    }

    public Cursor findSoldCars(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+CAR_TABLE+" where STATUS = 'Sold'",null );
        return results;
    }

    public boolean deleteData(String CA_no){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(CAR_TABLE, "CA_NUM = "+"'"+CA_no+"'",null);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(String CA_num, String brand, String model, String year, String price,String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CA_NUM_COL, CA_num);
        contentValues.put(BRAND_COL, brand);
        contentValues.put(MODEL_COL, model);
        contentValues.put(YEAR_COL, year);
        contentValues.put(PRICE_COL, price);
        contentValues.put(STATUS_COL, status);
        long result = db.update(CAR_TABLE,contentValues,"CA_NUM = "+"'"+CA_num+"'",null);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

}
