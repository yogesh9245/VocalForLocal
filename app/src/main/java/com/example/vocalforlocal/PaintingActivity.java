package com.example.vocalforlocal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaintingActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    String phoneNo;
    String message;

    SQLiteDatabase db;
    EditText name, phone,address,email;
    Button submitPainting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting);

        name = (EditText) findViewById(R.id.nameDetailPainting);
        phone = (EditText) findViewById(R.id.PhoneDetailPainting);
        address = (EditText) findViewById(R.id.AddressDetailPainting);
        email = (EditText) findViewById(R.id.emailPaintingDetail);

        submitPainting = (Button)findViewById(R.id.submitPainting);

        db=openOrCreateDatabase("BookingDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS customersBook(name VARCHAR,phone number,address VARCHAR,email VARCHAR);");

        submitPainting.setOnClickListener(new View.OnClickListener() {
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

                    sendSMSMessage();

                    Cursor c = db.rawQuery("SELECT * FROM workingMember WHERE designation='"+"Painter"+"'", null);
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

    protected void sendSMSMessage() {
        phoneNo = phone.getText().toString();
        message = "Message sent";

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

}