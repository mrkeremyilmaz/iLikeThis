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
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import cs102.group1c.ilikethis.model.User;

import static com.example.kerem.ilikethis.LoginActivity.user;

public class ShareListActivity extends AppCompatActivity {

    static int currentListIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ScrollView scrollView = new ScrollView(this);
        GridLayout shareListLayout = new GridLayout(this);
        shareListLayout.setColumnCount(2);
        Button listButton;
        CheckBox listBox;

        for (int i = 0; i < user.getListCount(ShareActivity.currentCategoryIndex); i++) {
            listButton = new Button(this);
            listButton.setText(user.getCategories().get(ShareActivity.currentCategoryIndex).get(i).getTitle());
            listButton.setId(i);

            Resources r = getResources();
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250,
                    r.getDisplayMetrics());
            listButton.setWidth(px);


            shareListLayout.addView(listButton);

            listBox = new CheckBox(this);
            listBox.setText("Choose");
            listBox.setId(i + user.getListCount(ShareActivity.currentCategoryIndex)); //the number in for loop

            final int value = i;
            listBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        ((Button) findViewById(value)).setEnabled(false);
                        for (int i = 0; i < user.getListCount(User.LIST_MOVIES); i++)
                            user.getCategories().get(User.LIST_MOVIES).get(i).setSelected(true);
                    } else {
                        ((Button) findViewById(value)).setEnabled(true);
                        for (int i = 0; i < user.getListCount(User.LIST_MOVIES); i++)
                            user.getCategories().get(User.LIST_MOVIES).get(i).setSelected(false);
                    }

                }
            });

            listButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(v.getId() + "");
                    currentListIndex = v.getId();
                    startActivity(new Intent(ShareListActivity.this, ShareItemActivity.class));
                }
            });

            shareListLayout.addView(listBox);
        }

        scrollView.addView(shareListLayout);
        setContentView(scrollView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
