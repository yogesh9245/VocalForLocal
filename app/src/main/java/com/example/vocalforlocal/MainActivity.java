package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button memberLogin,BookService;
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

        BookService = (Button) findViewById(R.id.book);
        BookService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Bookservice",Toast.LENGTH_SHORT).show();
                Intent bookservice = new Intent(MainActivity.this,BookServiceActivity.class);
                startActivity(bookservice);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.aboutItem:
                // do your code
                Toast.makeText(MainActivity.this,"Presses 1",Toast.LENGTH_SHORT).show();
                Intent about = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(about);
                return true;
            case R.id.ContactItem:
                Intent contact = new Intent(MainActivity.this,ContactActivity.class);
                startActivity(contact);
                // do your code
                return true;
            case R.id.homeItem:
                // do your code

                Intent home = new Intent(MainActivity.this,MainActivity.class);
                startActivity(home);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}