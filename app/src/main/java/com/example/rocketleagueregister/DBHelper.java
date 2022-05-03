package com.example.rocketleagueregister;

import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="RocketLeagueRegistration.db";
    public static final String USER_TABLE_NAME="User_Table";


    public static final String COLUMN_1="ID";
    public static final String COLUMN_2="NAME";
    public static final String COLUMN_3="SURNAME";
    public static final String COLUMN_4="AGE";
    public static final String COLUMN_5="USERNAME";
    public static final String COLUMN_6="PASSWORD";
    public static final String COLUMN_7="TOURNAMENTNAME";
    public static final String COLUMN_8="SIGNUPWITHDRAW";


    private static final String TAG = "DataseHelper";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+USER_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,AGE INTEGER,USERNAME TEXT,PASSWORD TEXT, TOURNAMENTNAME TEXT,SIGNUPWITHDRAW TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String surname,int age, String username, String password,String tournamentName, String entered) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, name);
        contentValues.put(COLUMN_3, surname);
        contentValues.put(COLUMN_4, age);
        contentValues.put(COLUMN_5, username);
        contentValues.put(COLUMN_6, password);
        contentValues.put(COLUMN_7, tournamentName);
        contentValues.put(COLUMN_8, entered);

        long result = db.insert(USER_TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;
        res = db.rawQuery("select * from " + USER_TABLE_NAME, null);


        return res;
    }

    public boolean updateAccounts(String name, String surname, int age, String username,String password,String tournamentName, String entered){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_2,name);
        contentValues.put(COLUMN_3,surname);
        contentValues.put(COLUMN_4,age);
        contentValues.put(COLUMN_5,username);
        contentValues.put(COLUMN_6,password);
        contentValues.put(COLUMN_7,tournamentName);
        contentValues.put(COLUMN_8,entered);
        db.update(USER_TABLE_NAME ,contentValues,"USERNAME = ?",new String[]{username});
        return true;
    }
    public  Integer deleteData(String username){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(USER_TABLE_NAME,"USERNAME = ?",new String[] {username});
    }


    public Cursor searchData(String userName,SQLiteDatabase sqLiteDatabase){

        String[] projections= {COLUMN_1,COLUMN_2,COLUMN_3,COLUMN_4,COLUMN_5,COLUMN_6,COLUMN_7,COLUMN_8};
        String selection= COLUMN_5+" LIKE ? ";
        String[] selection_args= {userName};
        Cursor res = sqLiteDatabase.query(USER_TABLE_NAME,projections,selection,selection_args,null,null,null);
        return res;
    }

    public boolean updateWithdraw(String username ,String entered){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(COLUMN_8,entered);
        db.update(USER_TABLE_NAME ,contentValues,"USERNAME = ?",new String[]{username});
        return true;
    }
    public boolean updateSignup(String username,String tournamentName,String entered) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_7, tournamentName);
        contentValues.put(COLUMN_8, entered);
        db.update(USER_TABLE_NAME, contentValues, "USERNAME = ?", new String[]{username});
        return true;
    }

}
