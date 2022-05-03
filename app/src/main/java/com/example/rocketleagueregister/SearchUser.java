package com.example.rocketleagueregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchUser extends AppCompatActivity {
    DBHelper myDb;
    SQLiteDatabase sqLiteDatabase;
    private TextView txtUserId, txtName, txtSurname, txtAge, txtUsername, txtPassword, txtTournamentName, txtEntered;
    EditText edtUserName;
    String search_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);


        myDb = new DBHelper(this);
        edtUserName = (EditText) findViewById(R.id.edtSearch);
        Button btnReturn = findViewById(R.id.btnReturn8);

        txtUserId= findViewById(R.id.txtId);
        txtName= findViewById(R.id.txtName);
        txtSurname= findViewById(R.id.txtSurname);
        txtAge= findViewById(R.id.txtAge);
        txtUsername= findViewById(R.id.txtUsername1);
        txtPassword= findViewById(R.id.txtPassword1);
        txtTournamentName= findViewById(R.id.txtTournamentName);
        txtEntered= findViewById(R.id.txtentered);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchUser.this, ViewParticipants.class);
                startActivity(intent);
            }
        });
    }

    public void Search_User(View view) {
        search_name = edtUserName.getText().toString();
        myDb = new DBHelper(getApplicationContext());
        sqLiteDatabase = myDb.getReadableDatabase();
        Cursor cursor = myDb.searchData(search_name, sqLiteDatabase);
        if (cursor.moveToFirst()) {

            String userID = cursor.getString(0);
            String userName = cursor.getString(1);
            String userSurname = cursor.getString(2);
            String userAge = cursor.getString(3);
            String userUsername = cursor.getString(4);
            String userPasword = cursor.getString(5);
            String userTournamentName = cursor.getString(6);
            String userEntered = cursor.getString(7);


            Toast.makeText(SearchUser.this, "User Found!", Toast.LENGTH_SHORT).show();
            txtUserId.setText("User ID: "+userID);
            txtName.setText("Name: "+userName);
            txtSurname.setText("Surname: "+userSurname);
            txtAge.setText("Age: "+userAge);
            txtUsername.setText("Username: "+userUsername);
            txtPassword.setText("Password: "+userPasword);
            txtTournamentName.setText("Tournament Name: "+userTournamentName);
            txtEntered.setText("Entered: "+userEntered);



        } else {
            Toast.makeText(SearchUser.this, "Could not find user", Toast.LENGTH_SHORT).show();
            txtUserId.setText("User ID: ");
            txtName.setText("Name: ");
            txtSurname.setText("Surname: ");
            txtAge.setText("Age: ");
            txtUsername.setText("Username: ");
            txtPassword.setText("Password: ");
            txtTournamentName.setText("Tournament Name: ");
            txtEntered.setText("Entered: ");
        }

    }

}


