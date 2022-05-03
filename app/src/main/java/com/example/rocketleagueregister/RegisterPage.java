package com.example.rocketleagueregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    DBHelper myDb;
private EditText edtName,edtSurname,edtAge,edtUserName,edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        Button btnReturn=findViewById(R.id.btnReturn1);
        Button btnlogin=findViewById(R.id.btnLogin2);
        myDb=new DBHelper(this);
        edtName= (EditText) findViewById(R.id.edtName);
        edtSurname= (EditText) findViewById(R.id.edtSurname);
        edtAge= (EditText) findViewById(R.id.edtAge);
        edtUserName= (EditText) findViewById(R.id.edtUsername);
        edtPassword= (EditText) findViewById(R.id.edtPassword);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RegisterPage.this,HomeScreen.class);
                startActivity(intent);

            }
        });
        Button btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checkRegister();

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPage.this,LoginPage.class);
                startActivity(intent);
            }
        });
    }
public void checkRegister(){
 if (edtName.getText().toString().equals("")||
         edtSurname.getText().toString().equals("")||
         edtAge.getText().toString().equals("")||
         edtUserName.getText().toString().equals("")||
         edtPassword.getText().toString().equals("")){
     Toast.makeText(this,"Cannot register, there are empty fields!",Toast.LENGTH_LONG).show();
 }else {
     int age = Integer.parseInt(edtAge.getText().toString());
     boolean isInserted = myDb.insertData(edtName.getText().toString(),
             edtSurname.getText().toString(),
             age,
             edtUserName.getText().toString(),
             edtPassword.getText().toString(),"Not Signed Up","false");
     if (isInserted = true) {
         Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show();
         Intent intent = new Intent(RegisterPage.this, LoginPage.class);
         startActivity(intent);
     } else {

         Toast.makeText(this, "Registration Unsuccessful", Toast.LENGTH_LONG).show();
     }
 }
}


}