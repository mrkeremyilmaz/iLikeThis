package com.example.kerem.ilikethis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class HowToHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_home_page);

        getSupportActionBar().setTitle("How To Add Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}



