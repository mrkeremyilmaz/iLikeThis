package com.example.kerem.ilikethis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by kerem on 11.05.2017.
 */

public class MenuActivity extends AppCompatActivity {
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
