package com.example.kerem.ilikethis;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ScrollView;

import cs102.group1c.ilikethis.model.User;

import static com.example.kerem.ilikethis.AccessShared.sharedUser;
import static com.example.kerem.ilikethis.LoginActivity.user;

public class ShowSharedListActivity extends AppCompatActivity {

    static int sharedCurrentListIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ScrollView scrollView = new ScrollView(this);
        GridLayout sharedListLayout = new GridLayout(this);
        sharedListLayout.setColumnCount(1);
        Button listButton;

        for (int i = 0; i < sharedUser.getListCount(ShowSharedActivity.sharedCurrentCategoryIndex); i++) {
            listButton = new Button(this);
            System.out.println(sharedUser.getListCount(ShowSharedActivity.sharedCurrentCategoryIndex));
            System.out.println(ShowSharedActivity.sharedCurrentCategoryIndex + "shared");
            listButton.setText(sharedUser.getCategories().get(ShowSharedActivity.sharedCurrentCategoryIndex).get(i).getTitle());
            listButton.setId(i);

            Resources r = getResources();
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350,
                    r.getDisplayMetrics());
            listButton.setWidth(px);

            sharedListLayout.addView(listButton);

            listButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(v.getId());
                    sharedCurrentListIndex = v.getId();
                    startActivity(new Intent(ShowSharedListActivity.this, ShowSharedItemActivity.class));
                }
            });

        }

        scrollView.addView(sharedListLayout);
        setContentView(scrollView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
