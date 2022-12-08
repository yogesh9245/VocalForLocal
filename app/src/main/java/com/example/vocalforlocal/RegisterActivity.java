package com.example.vocalforlocal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,phone,email,designation;
    Button register;
    SQLiteDatabase db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.userLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        phone = (EditText) findViewById(R.id.phoneLogin);
        email = (EditText) findViewById(R.id.emailLogin);
        designation = (EditText) findViewById(R.id.designationLogin);

        register = (Button) findViewById(R.id.registerBtnregister);

        sp = getSharedPreferences("RegisterInfo",0);

        db=openOrCreateDatabase("BookingDB", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS workingMember(username VARCHAR,password VARCHAR,phone number,email VARCHAR,designation VARCHAR);");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                String phoneValue = phone.getText().toString();
                String emailValue = email.getText().toString();
                String designationValue = designation.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username",usernameValue);
                editor.putString("password",passwordValue);
                editor.putString("phone",phoneValue);
                editor.putString("email",emailValue);
                editor.apply();
                Toast.makeText(RegisterActivity.this,"User created",Toast.LENGTH_SHORT).show();



                db.execSQL("INSERT INTO workingMember VALUES('"+username.getText()+"','"+password.getText()+ "','"+phone.getText()+"','"+ email.getText()+"','"+designation.getText()+"');");
                showMessage("Success", "Record added");


                showMessage("Registeration Info", "Your registration is successfull");
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