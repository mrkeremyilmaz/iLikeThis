package com.example.kerem.ilikethis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HowToAdditem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_home_page);

        getSupportActionBar().setTitle("How To Use");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
