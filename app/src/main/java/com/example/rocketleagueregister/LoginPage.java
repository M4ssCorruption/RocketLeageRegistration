package com.example.rocketleagueregister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
DBHelper myDb;
SQLiteDatabase sqLiteDatabase;
String user_name,password,tempPassword,temp_name;
boolean emptyTextEmptyHolder;
private EditText edtUsername,edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


    Button btnLogin1=findViewById(R.id.btnLogin1);
        myDb = new DBHelper(LoginPage.this);

    edtUsername= (EditText) findViewById(R.id.txtUsername);
    edtPassword= (EditText) findViewById(R.id.txtPassword);
    password=edtPassword.getText().toString();
        btnLogin1.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            CheckEditTextStatus();
        LoginFunction();
        }


    });
        Button btnReturn=findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginPage.this,HomeScreen.class);
                startActivity(intent);

            }
        });
        Button btnRegister=findViewById(R.id.btnRegister1);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginPage.this,RegisterPage.class);
                startActivity(intent);

            }
        });
    }
    @SuppressLint("Range")
    public void LoginFunction(){
        if(emptyTextEmptyHolder){
            user_name=edtUsername.getText().toString();

            myDb = new DBHelper(getApplicationContext());
            sqLiteDatabase = myDb.getWritableDatabase();
            Cursor cursor= sqLiteDatabase.query(DBHelper.USER_TABLE_NAME,null," "+DBHelper.COLUMN_5+ "=?",new String[]{user_name},null,null,null);

            while(cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();
                    tempPassword = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_6));
                    temp_name= cursor.getString(1);
                    cursor.close();
                }
            }
            CheckFinalResult();

        }  else {


            Toast.makeText(LoginPage.this,"Cannot login, there are empty fields!",Toast.LENGTH_LONG).show();

        }
    }

    public void CheckFinalResult(){
        if(tempPassword.equalsIgnoreCase(password)){
            Toast.makeText(LoginPage.this, "Welcome "+temp_name+"!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginPage.this, MainMenu.class);


            intent.putExtra("loginUser",user_name);

            startActivity(intent);


        }else{
            Toast.makeText(LoginPage.this,"Username or Password is incorrect, Please Try Again.",Toast.LENGTH_LONG).show();

    }
    tempPassword = "NOT_FOUND" ;

}
    public void CheckEditTextStatus(){


        user_name = edtUsername.getText().toString();
        password = edtPassword.getText().toString();


        if( TextUtils.isEmpty(user_name) || TextUtils.isEmpty(password)){

            emptyTextEmptyHolder = false ;

        }
        else {

            emptyTextEmptyHolder = true ;
        }
    }
    }


