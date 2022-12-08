package com.example.vocalforlocal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button getDetails;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db=openOrCreateDatabase("BookingDB", Context.MODE_PRIVATE, null);

        getDetails = (Button) findViewById(R.id.getCustomerDetails);

        getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = db.rawQuery("SELECT name,phone,address,email FROM customersBook", null);

                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("Name: " + c.getString(0) + "\n");
                    buffer.append("Phone: " + c.getString(1) + "\n");
                    buffer.append("Address " + c.getString(2) + "\n");
                    buffer.append("Email: " + c.getString(3) + "\n\n");
                }
                showMessage("Customer Details", buffer.toString());
            }
        });


    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}