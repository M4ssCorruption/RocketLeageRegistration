package com.example.rocketleagueregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
private EditText edtUserName,edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Button btnViewParticipants = findViewById(R.id.btnLogin);
        edtUserName = (EditText) findViewById(R.id.edtUsername1);
        edtPassword = (EditText) findViewById(R.id.edtPassword1);

        btnViewParticipants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate();
            }
        });
        Button btnReturn = findViewById(R.id.btnReturn6);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogin.this, HomeScreen.class);
                startActivity(intent);


            }
        });
    }
        public void validate() {
            if (edtUserName.getText().toString().equals("admin") && edtPassword.getText().toString().equals("12345")) {
                Toast.makeText(this, "Welcome back, Administrator!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AdminLogin.this, ViewParticipants.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Username or Password is incorrect!",Toast.LENGTH_LONG).show();
            }

    }


}