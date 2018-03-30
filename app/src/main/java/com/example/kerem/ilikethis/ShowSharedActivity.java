package com.example.kerem.ilikethis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShowSharedActivity extends AppCompatActivity {

    static int sharedCurrentCategoryIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shared);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //FIX THIS
    //Button android:onClick
    public void onSharedCategoryClick(View v) {

        System.out.println(v.getId());
        if (v.getId() == R.id.category0)
            sharedCurrentCategoryIndex = 0;
        else if (v.getId() == R.id.category1)
            sharedCurrentCategoryIndex = 1;
        else if (v.getId() == R.id.category2)
            sharedCurrentCategoryIndex = 4;
        else if (v.getId() == R.id.category6)
            sharedCurrentCategoryIndex = 5;
        else if (v.getId() == R.id.category5)
            sharedCurrentCategoryIndex = 2;
        else if (v.getId() == R.id.category4)
            sharedCurrentCategoryIndex = 3;
        else if (v.getId() == R.id.category3)
            sharedCurrentCategoryIndex = 7;
        else
            sharedCurrentCategoryIndex = 6;
        System.out.println(sharedCurrentCategoryIndex + "category share");
        startActivity(new Intent(ShowSharedActivity.this, ShowSharedListActivity.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
