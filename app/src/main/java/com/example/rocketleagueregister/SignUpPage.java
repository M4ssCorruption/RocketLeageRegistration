package com.example.rocketleagueregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
DBHelper myDb;
SQLiteDatabase sqLiteDatabase;
String getExtendedUsername, getEntered;
        EditText getTournamentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up_page);
        Button btnConfirm=findViewById(R.id.btnConfirm);

        getExtendedUsername=getIntent().getStringExtra("extendUsername");
        getTournamentName= (EditText)findViewById(R.id.edtTournament);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getTournamentName.getText().toString().equals("")) {
                    Toast.makeText(SignUpPage.this, "You cannot enter the tournament without a tournament name!", Toast.LENGTH_LONG).show();

                }else {
                    myDb = new DBHelper(getApplicationContext());
                    sqLiteDatabase = myDb.getReadableDatabase();
                    Cursor cursor = myDb.searchData(getExtendedUsername, sqLiteDatabase);
                    if(cursor.moveToNext()){
                        getEntered=cursor.getString(7);
                        if (getEntered.equals("false") && !getTournamentName.getText().toString().equals("")){
                            updateTournamentName();
                            Intent intent= new Intent(SignUpPage.this,MainMenu.class);
                            intent.putExtra("loginUser",getExtendedUsername);
                            startActivity(intent);
                        }
                    }


                }


            }
        });
        Button btnReturn=findViewById(R.id.btnReturn3);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignUpPage.this,MainMenu.class);
                startActivity(intent);

            }
        });

    }
    public void updateTournamentName(){
        myDb = new DBHelper(getApplicationContext());
        sqLiteDatabase = myDb.getWritableDatabase();

        boolean withdrawUpdate= myDb.updateSignup(
                getExtendedUsername,
                getTournamentName.getText().toString(),
                "true");
        if (withdrawUpdate == true) {
            Toast.makeText(SignUpPage.this, getTournamentName.getText().toString()+" has been entered into the tournament!", Toast.LENGTH_LONG).show();

        }else
            Toast.makeText(SignUpPage.this, "Error", Toast.LENGTH_LONG).show();

    }


}