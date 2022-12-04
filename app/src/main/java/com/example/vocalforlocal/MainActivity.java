package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button memberLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        View welcomescreen = findViewById(R.id.welcomescreen);
//        View root = welcomescreen.getRootView();
//        root.setBackgroundColor(Color.parseColor("#7e63b8"));

        memberLogin = (Button) findViewById(R.id.member);
        memberLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this,MemberLogin.class);
                startActivity(login);
            }
        });
    }
}