package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View welcomescreen = findViewById(R.id.welcomescreen);
        View root = welcomescreen.getRootView();
        root.setBackgroundColor(Color.parseColor("#7e63b8"));
    }
}