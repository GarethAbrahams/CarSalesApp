package com.garethabrahams.carsalesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText nameEdit,surnameEdit,usernameEdit,passwordEdt;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myDB= new DatabaseHelper(this);
        usernameEdit = (EditText)findViewById(R.id.RegUsernameText);
        passwordEdt = (EditText)findViewById(R.id.RegPasswordText);
        nameEdit = (EditText)findViewById(R.id.RegNameText);
        surnameEdit = (EditText)findViewById(R.id.RegSurnameText);

        Button RegBack = (Button) findViewById(R.id.regBackBtn);
        RegBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button RegAddUser = (Button) findViewById(R.id.RegAddUserBtn);
        RegAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usernameEdit.getText().length()==0){
                    Toast.makeText(RegisterActivity.this, "Please insert information", Toast.LENGTH_SHORT).show();
                }else{
                    boolean isInserted = myDB.insertUser(usernameEdit.getText().toString(),passwordEdt.getText().toString(),
                            nameEdit.getText().toString(),surnameEdit.getText().toString());

                    if (isInserted==true){
                        Toast.makeText(RegisterActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                        intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.putExtra("username",usernameEdit.getText().toString());
                        intent.putExtra("password",passwordEdt.getText().toString());
                        startActivity(intent);
                        usernameEdit.getText().clear();
                        passwordEdt.getText().clear();
                        nameEdit.getText().clear();
                        surnameEdit.getText().clear();
                    }else{
                        Toast.makeText(RegisterActivity.this, "user already exist", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

/*        Button RegAddUser = (Button) findViewById(R.id.RegAddUserBtn);
        RegAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtra("username",username.getText().toString());
                intent.putExtra("password",password.getText().toString());
                startActivity(intent);
            }
        });*/

    }
}
