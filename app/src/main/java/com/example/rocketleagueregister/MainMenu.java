package com.example.rocketleagueregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
String getUsername,tournameName;
DBHelper myDb;
SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button btnSignUp=findViewById(R.id.btnSignUp);
        Button btnWithdraw= findViewById(R.id.btnWithdraw);
        myDb=new DBHelper(this);
        getUsername= getIntent().getStringExtra("loginUser");





        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb = new DBHelper(getApplicationContext());
                sqLiteDatabase = myDb.getReadableDatabase();
                Cursor cursor = myDb.searchData(getUsername, sqLiteDatabase);
                if(cursor.moveToNext()){
                    tournameName=cursor.getString(7);
                    if(tournameName.equals("true")){
                        checkWithdraw();
                    }else if(tournameName.equals("false")){
                        Toast.makeText(MainMenu.this, "You did not sign up or you have already withdrawn!", Toast.LENGTH_SHORT).show();
                    }
                    }else{

                    }

                }


        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb = new DBHelper(getApplicationContext());
                sqLiteDatabase = myDb.getReadableDatabase();
                Cursor cursor = myDb.searchData(getUsername, sqLiteDatabase);
                if(cursor.moveToNext()){
                    tournameName= cursor.getString(7);
                    if(tournameName.equals("false")){
                        checkEntered();
                    }else{
                        checkEntered();
                    }

                }else{

                }




            }
        });
        Button btnViewPlayers=findViewById(R.id.btnPlayersEntered);

        btnViewPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewData();

            }
        });

        Button btnViewBrackets=findViewById(R.id.btnViewBrackets);

        btnViewBrackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Button btnReturn=findViewById(R.id.btnReturn2);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainMenu.this,LoginPage.class);
                startActivity(intent);

            }
        });
    }
    public void checkEntered(){

        myDb = new DBHelper(getApplicationContext());
        sqLiteDatabase = myDb.getReadableDatabase();
        Cursor cursor = myDb.searchData(getUsername, sqLiteDatabase);



        if(cursor.moveToFirst()) {
            tournameName=cursor.getString(7);
            if(tournameName.equals("true")){
                Toast.makeText(MainMenu.this, "You are already entered in the tournament. ", Toast.LENGTH_SHORT).show();

            }else{
                Intent intent = new Intent(MainMenu.this, SignUpPage.class);
                intent.putExtra("extendUsername",getUsername);
                startActivity(intent);
            }



        }else{
            Intent intent = new Intent(MainMenu.this, SignUpPage.class);
            startActivity(intent);
        }

    }
    public void checkWithdraw(){

        myDb = new DBHelper(getApplicationContext());
        sqLiteDatabase = myDb.getWritableDatabase();

        boolean withdrawUpdate= myDb.updateWithdraw(
                getUsername,
                "false");
        if (withdrawUpdate == true) {

            Toast.makeText(MainMenu.this, "You have been successfully withdrawn from the tournament", Toast.LENGTH_LONG).show();
            return;
        }else
            Toast.makeText(MainMenu.this, "You are not signed up.", Toast.LENGTH_LONG).show();

    }
    public void viewData(){

        Cursor res;
        res= myDb.getAllData();


            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {

                if(res.getString(7).equals("false")||res.getString(6).equals("Not Signed Up")){
                    continue;
                }else {
                    buffer.append("Player: " + res.getString(6) + "\n=======================\n");
                }

            }

            displayData("Players Entered", buffer.toString());

    }
    public void displayData(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}