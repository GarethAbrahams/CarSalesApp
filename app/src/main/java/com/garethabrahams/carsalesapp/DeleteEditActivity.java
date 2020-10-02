package com.garethabrahams.carsalesapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteEditActivity extends AppCompatActivity {
    EditText searchCA,brandEdit,modelEdit,yearEdit,priceEdit;
    DatabaseHelper myDB;
    private String CaNum, brand, model, year, price,status,statusUpdate;
    CheckBox soldbox;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_edit);
        myDB= new DatabaseHelper(this);

        Button backButton = (Button) findViewById(R.id.delBackBtn3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteEditActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        Button searchButton = (Button) findViewById(R.id.delSearchBtn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCA = (EditText)findViewById(R.id.searchText2);
                brandEdit = (EditText)findViewById(R.id.Brandedittext);
                modelEdit = (EditText)findViewById(R.id.ModelEditText);
                yearEdit = (EditText)findViewById(R.id.YearEditText);
                priceEdit = (EditText)findViewById(R.id.PriceEditText);
                soldbox = (CheckBox)findViewById(R.id.soldBox);

                Cursor data = myDB.findbyCaNumber(searchCA.getText().toString());
                if (data.getCount()==0){
                    Toast.makeText(DeleteEditActivity.this, "Nothing Found", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    while(data.moveToNext()){
                        CaNum = data.getString(0);
                        brand = data.getString(1);
                        model = data.getString(2);
                        year = data.getString(3);
                        price = data.getString(4);
                        status = data.getString(5);
                    }
                    brandEdit.setText(brand);
                    modelEdit.setText(model);
                    yearEdit.setText(year);
                    priceEdit.setText(price);
                    if (status.equals("Sold")){
                        soldbox.setChecked(true);
                    }else{
                        soldbox.setChecked(false);
                    }
                }
            }
        });

        Button deleteButton = (Button) findViewById(R.id.Deletebutton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCA = (EditText)findViewById(R.id.searchText2);
                if (searchCA.getText().toString().isEmpty()){// || searchCA.getText().toString().contains("Ca number")){
                    Toast.makeText(DeleteEditActivity.this, "Nothing was deleted", Toast.LENGTH_SHORT).show();
                }else{
                    boolean data = myDB.deleteData(searchCA.getText().toString());
                    Toast.makeText(DeleteEditActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button updateButton = (Button) findViewById(R.id.updateBtn);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchCA.getText().length()==0){
                    Toast.makeText(DeleteEditActivity.this, "Please insert information", Toast.LENGTH_SHORT).show();
                }else{
                    if (soldbox.isChecked()){
                        statusUpdate = "Sold";
                    }else{
                        statusUpdate = "Available";
                    }
                    boolean data = myDB.updateData(searchCA.getText().toString(),brandEdit.getText().toString(),
                            modelEdit.getText().toString(),yearEdit.getText().toString(),priceEdit.getText().toString(),statusUpdate);
                    if (data==true){
                        Toast.makeText(DeleteEditActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                        //caNum.getText().clear();
                        //brand.getText().clear();
                        //model.getText().clear();
                        //year.getText().clear();
                        //price.getText().clear();
                    }else{
                        Toast.makeText(DeleteEditActivity.this, "CA number already exist", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
