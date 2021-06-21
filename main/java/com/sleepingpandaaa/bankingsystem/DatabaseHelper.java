package com.sleepingpandaaa.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9000000000,'Micheal',472.70,'harshit@gmail.com','XXXXXXXXXXXX1574','ABC584536543')");
        db.execSQL("insert into user_table values(9111111111,'Abhay',8402.44,'ashok@gmail.com','XXXXXXXXXXXX8341','BCA97777832')");
        db.execSQL("insert into user_table values(9222222222,'Aman',1269.23,'sudesh@gmail.com','XXXXXXXXXXXX9994','CAB85410301')");
        db.execSQL("insert into user_table values(9333333333,'Animesh',2440.71,'gaurav@gmail.com','XXXXXXXXXXXX8840','ABC44462210')");
        db.execSQL("insert into user_table values(9444444444,'Sudesh',6643.48,'mansi@gmail.com','XXXXXXXXXXXX2345','DAC74323465')");
        db.execSQL("insert into user_table values(9555555555,'Marry',9450.16,'anmol@gmail.com','XXXXXXXXXXXX5841','CAB48562599')");
        db.execSQL("insert into user_table values(9666666666,'Deepak',5946.00,'deepanshu@gmail.com','XXXXXXXXXXXX7512','ABC43210987')");
        db.execSQL("insert into user_table values(9777777777,'Arun',857.22,'vishnu@gmail.com','XXXXXXXXXXXX7430','BCA56487876')");
        db.execSQL("insert into user_table values(9888888888,'Ayushi',968.46,'srishti@gmail.com','XXXXXXXXXXXX3210','CAB45085965')");
        db.execSQL("insert into user_table values(9999999999,'Aditi',27873.90,'deepak@gmail.com','XXXXXXXXXXXX4872','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
