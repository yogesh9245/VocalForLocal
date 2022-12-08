package com.example.vocalforlocal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlumbingActivity extends AppCompatActivity {

    SQLiteDatabase db,db1;
    EditText name, phone,address,email;
    Button submitPlumbing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing);

        name = (EditText) findViewById(R.id.nameDetailPlumbing);
        phone = (EditText) findViewById(R.id.PhoneDetailPlumbing);
        address = (EditText) findViewById(R.id.AddressDetailPlumbing);
        email = (EditText) findViewById(R.id.emailPlumbingDetail);

        submitPlumbing = (Button)findViewById(R.id.submitPlumbing);

        db=openOrCreateDatabase("BookingDB", Context.MODE_PRIVATE, null);
        db1=openOrCreateDatabase("BookingDB", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS customersBook(name VARCHAR,phone number,address VARCHAR,email VARCHAR);");
        db1.execSQL("CREATE TABLE IF NOT EXISTS workingMember(username VARCHAR,password VARCHAR,phone number,email VARCHAR,designation VARCHAR);");

        submitPlumbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().length()==0 || phone.getText().toString().length()==0 || address.getText().toString().length()==0 || email.getText().toString().length()==0){
                    showMessage("Error", "Please enter all values");
                    return;
                }

                else{
                    db.execSQL("INSERT INTO customersBook VALUES('"+ name.getText()+"','"+ phone.getText()+"','"+ address.getText()+"','"+ email.getText()+"');");
                    showMessage("Success", "Record added");
                    clearText();

                    Cursor c = db1.rawQuery("SELECT username,phone,email,designation FROM workingMember WHERE designation = 'Plumber'", null);
                    StringBuffer buffer = new StringBuffer();
                    while (c.moveToNext()) {
                        buffer.append("Name: " + c.getString(0) + "\n");
                        buffer.append("Phone: " + c.getString(1) + "\n");
                        buffer.append("Email: " + c.getString(2) + "\n");
                        buffer.append("Designation: " + c.getString(3) + "\n\n");
                    }
// Displaying all records
                    showMessage("Customer Details", buffer.toString());

                }
//                addNotification();
            }
        });
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);

        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });

        builder.show();
    }

    public void clearText()
    {
        name.setText("");
        phone.setText("");
        address.setText("");
        email.setText("");
        name.requestFocus();
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, NotifyActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}