package com.example.rocketleagueregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ViewParticipants extends AppCompatActivity {
DBHelper myDb;

private EditText edtName,edtSurname,edtAge,edtUserName,edtPassword,searchUser,edtTournamentName;
private CheckBox chkEntered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_participants);
        Button btnReturn=findViewById(R.id.btnReturn5);
        Button btnAdd=findViewById(R.id.btnAdd);
        Button btnDelete=findViewById(R.id.btnDelete);
        Button btnUpdate=findViewById(R.id.btnUpdate);
        Button btnView=findViewById(R.id.btnView);
        Button btnSearch=findViewById(R.id.btnSearch);
        myDb=new DBHelper(this);


        edtName=(EditText) findViewById(R.id.edtName1);
        edtSurname=(EditText) findViewById(R.id.edtSurname1);
        edtAge=(EditText) findViewById(R.id.edtAge1);
        edtUserName=(EditText) findViewById(R.id.edtUsername2);
        edtPassword=(EditText) findViewById(R.id.edtPassword2);
        edtTournamentName=(EditText) findViewById(R.id.edtTournamentName);
        chkEntered=(CheckBox) findViewById(R.id.chbEntered);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ViewParticipants.this,AdminLogin.class);
                startActivity(intent);

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertAccount();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtName.getText().toString().equals("") ||
                        edtSurname.getText().toString().equals("") ||
                        edtAge.getText().toString().equals("") ||
                        edtUserName.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("") ||
                        edtTournamentName.getText().toString().equals("")) {
                    Toast.makeText(ViewParticipants.this, "Cannot update, there are empty fields!", Toast.LENGTH_LONG).show();

                } else
                    updateData();
            }

        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewData();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(ViewParticipants.this,SearchUser.class);
            startActivity(intent);
            }
            });
    }
public void insertAccount() {

    if (edtName.getText().toString().equals("") ||
            edtSurname.getText().toString().equals("") ||
            edtAge.getText().toString().equals("") ||
            edtUserName.getText().toString().equals("") ||
            edtPassword.getText().toString().equals("")||
            edtTournamentName.getText().toString().equals("")) {
        Toast.makeText(this, "Cannot add, there are empty fields!", Toast.LENGTH_LONG).show();

    } else {

        int age = Integer.parseInt(edtAge.getText().toString());



        boolean isInserted = myDb.insertData(edtName.getText().toString(),
                edtSurname.getText().toString(),
                age,
                edtUserName.getText().toString(),
                edtPassword.getText().toString(),
                edtTournamentName.getText().toString(),
                String.valueOf(chkEntered.isChecked()));

        if (isInserted == true) {
            Toast.makeText(this, "New account has been added!", Toast.LENGTH_LONG).show();
            edtName.getText().clear();
            edtSurname.getText().clear();
            edtAge.getText().clear();
            edtUserName.getText().clear();
            edtPassword.getText().clear();
            edtTournamentName.getText().clear();
            chkEntered.setChecked(false);
    }
            else {
                Toast.makeText(this, "Could not add new account!", Toast.LENGTH_LONG).show();

        }}

}
public void viewData(){

        Cursor res;
        res= myDb.getAllData();

        if(res.getCount()==0){
            displayData("Error","There is no accounts!");
            return;
        }
            StringBuffer buffer= new StringBuffer();
            while (res.moveToNext() ){

                buffer.append("User:"+ res.getString(0)+"\n");
                buffer.append("Name :"+ res.getString(1)+"\n");
                buffer.append("Surname :"+ res.getString(2)+"\n");
                buffer.append("Age :"+ res.getString(3)+"\n");
                buffer.append("Username :"+ res.getString(4)+"\n");
                buffer.append("Password :"+ res.getString(5)+"\n");
                buffer.append("Tournament Name :"+ res.getString(6)+"\n");
                buffer.append("Entered :"+ res.getString(7) +"\n=======================\n");




            }

            displayData("Participants Details",buffer.toString());
        }

public void displayData(String title, String message){
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.show();
}
public void updateData() {
    int age = Integer.parseInt(edtAge.getText().toString());

    boolean isUpdated = myDb.updateAccounts(
            edtName.getText().toString(),
            edtSurname.getText().toString(),
            age, edtUserName.getText().toString(), edtPassword.getText().toString(), edtTournamentName.getText().toString(), String.valueOf(chkEntered.isChecked()));
    if (isUpdated == true) {
        Toast.makeText(ViewParticipants.this, "Account Updated!", Toast.LENGTH_LONG).show();

    }else
        Toast.makeText(ViewParticipants.this, "Account could not be updated!", Toast.LENGTH_LONG).show();



}
public void deleteData(){
    Integer deletedRows= myDb.deleteData(edtUserName.getText().toString());
    if(deletedRows>0){
        Toast.makeText(this, "Account Deleted!", Toast.LENGTH_LONG).show();
    }else{
        displayData("Error", "The account does not exist or has already been deleted!");
    }
}





}