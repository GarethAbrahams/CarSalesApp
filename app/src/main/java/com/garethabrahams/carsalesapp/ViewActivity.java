package com.garethabrahams.carsalesapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {
    EditText searchText;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        myDB= new DatabaseHelper(this);

        Button backButton = (Button) findViewById(R.id.viewBackBtn2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


        Button searchButton = (Button) findViewById(R.id.searchBtn2);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText = (EditText)findViewById(R.id.searchText);
                Cursor data = myDB.findbyCaNumber(searchText.getText().toString());
                if (data.getCount()==0){
                    showMessage("Error","Nothing Found");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while(data.moveToNext()){
                        buffer.append("CA No: "+data.getString(0)+"\n");
                        buffer.append("Car: "+data.getString(3)+" "+data.getString(1)+" "+data.getString(2)+"\n");
                        buffer.append("Price: R"+data.getString(4)+"\n");
                        buffer.append("Status: "+data.getString(5)+"\n\n");
                    }
                    showMessage("Results", buffer.toString());
                    searchText.getText().clear();
                }
            }
        });

        Button viewAllButton = (Button) findViewById(R.id.ViewGetAllBtn);
        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = myDB.getAllData();
                if (data.getCount()==0){
                    showMessage("Error","Nothing Found");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while(data.moveToNext()){
                        buffer.append("CA No: "+data.getString(0)+"\n");
                        buffer.append("Car: "+data.getString(3)+" "+data.getString(1)+" "+data.getString(2)+"\n");
                        buffer.append("Price: R"+data.getString(4)+"\n");
                        buffer.append("Status: "+data.getString(5)+"\n\n");
                    }
                   showMessage("Results", buffer.toString());
                }
            }
        });

        Button viewCars = (Button) findViewById(R.id.viewAvailBtn);
        viewCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = myDB.findAvailCars();
                if (data.getCount()==0){
                    showMessage("Error","Nothing Found");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while(data.moveToNext()){
                        buffer.append("CA No: "+data.getString(0)+"\n");
                        buffer.append("Car: "+data.getString(3)+" "+data.getString(1)+" "+data.getString(2)+"\n");
                        buffer.append("Price: R"+data.getString(4)+"\n");
                        buffer.append("Status: "+data.getString(5)+"\n\n");
                    }
                    showMessage("Results", buffer.toString());
                }
            }
        });

        Button viewSoldCars = (Button) findViewById(R.id.viewSoldBtn2);
        viewSoldCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = myDB.findSoldCars();
                if (data.getCount()==0){
                    showMessage("Error","Nothing Found");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while(data.moveToNext()){
                        buffer.append("CA No: "+data.getString(0)+"\n");
                        buffer.append("Car: "+data.getString(3)+" "+data.getString(1)+" "+data.getString(2)+"\n");
                        buffer.append("Price: R"+data.getString(4)+"\n");
                        buffer.append("Status: "+data.getString(5)+"\n\n");
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

