package com.garethabrahams.carsalesapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    DatabaseHelper myDB;
    Intent userIntent;
    String user, pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB= new DatabaseHelper(this);
        username = (EditText)findViewById(R.id.Username);
        password = (EditText)findViewById(R.id.password);

        Button login = (Button) findViewById(R.id.LoginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().length()==0||password.getText().length()==0)
                    Toast.makeText(MainActivity.this, "Missing username or password", Toast.LENGTH_SHORT).show();
                else{
                    userIntent = getIntent();
                    user = userIntent.getStringExtra("username");
                    pass = userIntent.getStringExtra("password");
                    if(username.getText().toString().equals("admin")&&password.getText().toString().equals("admin")){
                            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                            startActivity(intent);
                    }
                    else if(username.getText().toString().equals(user)&&password.getText().toString().equals(pass)){
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button register = (Button) findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
