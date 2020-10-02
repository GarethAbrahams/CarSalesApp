package com.garethabrahams.carsalesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText caNum,brand,model,year,price;
    String status="Available";
    Button addButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myDB= new DatabaseHelper(this);
        caNum = (EditText) findViewById(R.id.caNum);
        brand = (EditText) findViewById(R.id.brandText);
        model = (EditText) findViewById(R.id.modelText);
        year = (EditText) findViewById(R.id.yearText);
        price = (EditText) findViewById(R.id.priceText);

        backButton = (Button) findViewById(R.id.addBackBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MenuActivity.class);
                startActivity(intent);

            }
        });

        addButton = (Button) findViewById(R.id.AddBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (caNum.getText().length()==0){
                    Toast.makeText(AddActivity.this, "Please insert information", Toast.LENGTH_SHORT).show();
                }else{
                    boolean isInserted = myDB.insertData(caNum.getText().toString(),brand.getText().toString(),
                            model.getText().toString(),year.getText().toString(),price.getText().toString(),status);
                    if (isInserted==true){
                        Toast.makeText(AddActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                        caNum.getText().clear();
                        brand.getText().clear();
                        model.getText().clear();
                        year.getText().clear();
                        price.getText().clear();
                    }else{
                        Toast.makeText(AddActivity.this, "CA number already exist", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }



}

