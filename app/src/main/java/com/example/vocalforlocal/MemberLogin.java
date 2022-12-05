package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemberLogin extends AppCompatActivity {

    EditText username, password;
    Button login,register;
//    @SuppressLint("MissingInflatedId"

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        username = (EditText) findViewById(R.id.loginUsername);
        password = (EditText) findViewById(R.id.passwordLogin);

        login = (Button) findViewById(R.id.loginBtn);
        register = (Button) findViewById(R.id.loginRegister);

        sp = getSharedPreferences("RegisterInfo",0);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUsername = username.getText().toString();
                String loginPassword = password.getText().toString();

                String registeredUsername = sp.getString("username","");
                String registeredPassword = sp.getString("password","");

                if(loginUsername.equals(registeredUsername) && loginPassword.equals(registeredPassword)){
                    Intent home = new Intent(MemberLogin.this,HomeActivity.class);
                    startActivity(home);
                }
                else {
                    Toast.makeText(MemberLogin.this,"Please enter valid username or password",Toast.LENGTH_SHORT).show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(MemberLogin.this,RegisterActivity.class);
                startActivity(reg);
            }
        });
    }
}