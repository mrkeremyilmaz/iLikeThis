package com.example.kerem.ilikethis;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.example.kerem.ilikethis.LoginActivity.parser;
import static com.example.kerem.ilikethis.LoginActivity.user;

public class HomePage extends AppCompatActivity {

    protected static Button currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button userProfileButton = (Button) findViewById(R.id.userProfileButton);
        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, ProfilePage.class));
            }
        });

        FloatingActionButton shareButton = (FloatingActionButton) findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, ShareActivity.class));
            }
        });

        parser.toXml();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        parser.toXml();
    }

    @Override
    protected void onResume() {
        super.onResume();
        parser.toXml();
    }

    @Override
    protected void onStop() {
        super.onStop();
        parser.toXml();
    }

    @Override
    protected void onPause() {
        super.onPause();
        parser.toXml();
    }


    public void categoryButtonsFunction(View view) {
        currentCategory = (Button) findViewById(view.getId());
        startActivity(new Intent(this, ShowListActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    public void addListFromHomePage(View view) {
        Intent i = new Intent(this, AddList.class);

        startActivity(i);
    }

    public boolean aboutUs(MenuItem item) {
        startActivity(new Intent(this, AboutUs.class));
        return true;
    }

    public boolean contactUs(MenuItem item) {
        startActivity(new Intent(this, ContactUs.class));
        return true;
    }

    public boolean accessShared(MenuItem item) {
        startActivity(new Intent(this, AccessShared.class));
        return true;
    }

    public boolean howToHomePage(MenuItem item) {
        startActivity(new Intent(this, HowToHomePage.class));
        return true;
    }



}