package com.example.vocalforlocal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class BookServiceActivity extends AppCompatActivity {

    Button bookserviceBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_service);

        bookserviceBtn = (Button) findViewById(R.id.bookserviceBtn);
        bookserviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(BookServiceActivity.this, bookserviceBtn);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(BookServiceActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()) {
                            case R.id.painting:
                                // do your code
                                Toast.makeText(BookServiceActivity.this,"Clicked One to see",Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.plumbing:
                                // do your code
                                return true;
                            case R.id.carpentering:
                                // do your code
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show();//showing popup menu
            }
        });
    }
}