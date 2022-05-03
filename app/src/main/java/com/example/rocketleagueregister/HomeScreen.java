package com.example.rocketleagueregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeScreen extends AppCompatActivity {
DBHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        myDb=new DBHelper(this);
        Button btnLogin=findViewById(R.id.btnLoginPage);
        Button btnRegister=findViewById(R.id.btnRegisterPage);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeScreen.this,LoginPage.class);
                startActivity(intent);

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeScreen.this,RegisterPage.class);
                startActivity(intent);
            }
        });
        Button btnManager=findViewById(R.id.btnAdmin);

        btnManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeScreen.this,AdminLogin.class);
                startActivity(intent);

            }
        });
    }

}