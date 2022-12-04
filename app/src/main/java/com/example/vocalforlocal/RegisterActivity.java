package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,phone,email;
    Button register;

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.userLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        phone = (EditText) findViewById(R.id.phoneLogin);
        email = (EditText) findViewById(R.id.emailLogin);

        register = (Button) findViewById(R.id.registerBtnregister);

        sp = getSharedPreferences("RegisterInfo",0);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                String phoneValue = phone.getText().toString();
                String emailValue = email.getText().toString();

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username",usernameValue);
                editor.putString("password",passwordValue);
                editor.putString("phone",phoneValue);
                editor.putString("email",emailValue);
                editor.apply();
                Toast.makeText(RegisterActivity.this,"User created",Toast.LENGTH_SHORT).show();

            }
        });
    }
}