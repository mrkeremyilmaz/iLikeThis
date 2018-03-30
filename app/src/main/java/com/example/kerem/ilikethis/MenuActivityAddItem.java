package com.example.kerem.ilikethis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by User on 11-May-17.
 */

public class MenuActivityAddItem extends AppCompatActivity {
    public boolean addItem(MenuItem item) {
        startActivity(new Intent(this, AddItem.class));
        return true;
    }

    public boolean howToAddItem(MenuItem item) {
        startActivity(new Intent(this, HowToAdditem.class));
        return true;
    }

}
