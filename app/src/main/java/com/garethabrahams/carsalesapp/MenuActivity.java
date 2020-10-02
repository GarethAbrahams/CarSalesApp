package com.garethabrahams.carsalesapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        myDB= new DatabaseHelper(this);

        Button addButton = (Button) findViewById(R.id.MainAddBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        Button viewButton = (Button) findViewById(R.id.mainViewBtn);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });

        Button delButton = (Button) findViewById(R.id.mainDelEditBtn);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DeleteEditActivity.class);
                startActivity(intent);
            }
        });

        Button logout = (Button) findViewById(R.id.LogoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button reset = (Button) findViewById(R.id.resetBtn);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.onUpgrade(myDB.getWritableDatabase(),1,2);
                Toast.makeText(MenuActivity.this, "Reset successful", Toast.LENGTH_SHORT).show();

            }
        });

        Button viewUser = (Button) findViewById(R.id.UserView);
        viewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = myDB.getAllUser();
                if (data.getCount()==0){
                    showMessage("Error","Nothing Found");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while(data.moveToNext()){
                        buffer.append("Username: "+data.getString(0)+"\n");
                        buffer.append("Name: "+data.getString(2)+"\n");
                        buffer.append("Surname: "+data.getString(3)+"\n\n");
                    }
                    showMessage("Results", buffer.toString());
                }
            }
        });
    }

    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

