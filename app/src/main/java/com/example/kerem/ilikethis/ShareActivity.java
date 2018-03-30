package com.example.kerem.ilikethis;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ShareActionProvider;

//listView stuff


import cs102.group1c.ilikethis.model.User;

import static com.example.kerem.ilikethis.LoginActivity.parser;
import static com.example.kerem.ilikethis.LoginActivity.user;

public class ShareActivity extends AppCompatActivity {

    static int currentCategoryIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ////
        ////  CHECKBOX LISTENERS. BETTER CODE?!
        ////
        CheckBox bookCheckBox = (CheckBox) findViewById(R.id.bookBox);
        bookCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((Button) findViewById(R.id.category0)).setEnabled(false);
                    for (int i = 0; i < user.getListCount(User.LIST_BOOKS); i++)
                        user.getCategories().get(User.LIST_BOOKS).get(i).setSelected(true);
                } else {
                    ((Button) findViewById(R.id.category0)).setEnabled(true);
                    for (int i = 0; i < user.getListCount(User.LIST_BOOKS); i++)
                        user.getCategories().get(User.LIST_BOOKS).get(i).setSelected(false);
                }
            }
        });

        CheckBox movieCheckBox = (CheckBox) findViewById(R.id.MovieBox);
        movieCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((Button) findViewById(R.id.category1)).setEnabled(false);
                    for (int i = 0; i < user.getListCount(User.LIST_MOVIES); i++)
                        user.getCategories().get(User.LIST_MOVIES).get(i).setSelected(true);
                } else {
                    ((Button) findViewById(R.id.category1)).setEnabled(true);
                    for (int i = 0; i < user.getListCount(User.LIST_MOVIES); i++)
                        user.getCategories().get(User.LIST_MOVIES).get(i).setSelected(false);
                }
            }
        });

        CheckBox tvShowCheckBox = (CheckBox) findViewById(R.id.TvShowBox);
        tvShowCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((Button) findViewById(R.id.category2)).setEnabled(false);
                    for (int i = 0; i < user.getListCount(User.LIST_TV_SHOWS); i++)
                        user.getCategories().get(User.LIST_TV_SHOWS).get(i).setSelected(true);
                } else {
                    ((Button) findViewById(R.id.category2)).setEnabled(true);
                    for (int i = 0; i < user.getListCount(User.LIST_TV_SHOWS); i++)
                        user.getCategories().get(User.LIST_TV_SHOWS).get(i).setSelected(false);
                }
            }
        });

        CheckBox websiteCheckBox = (CheckBox) findViewById(R.id.WebsiteBox);
        websiteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((Button) findViewById(R.id.category3)).setEnabled(false);
                    for (int i = 0; i < user.getListCount(User.LIST_WEBSITES); i++)
                        user.getCategories().get(User.LIST_WEBSITES).get(i).setSelected(true);
                } else {
                    ((Button) findViewById(R.id.category3)).setEnabled(true);
                    for (int i = 0; i < user.getListCount(User.LIST_WEBSITES); i++)
                        user.getCategories().get(User.LIST_WEBSITES).get(i).setSelected(false);
                }
            }
        });

        CheckBox videoGamesCheckBox = (CheckBox) findViewById(R.id.VideoGamesBox);
        videoGamesCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((Button) findViewById(R.id.category5)).setEnabled(false);
                    for (int i = 0; i < user.getListCount(User.LIST_VIDEO_GAMES); i++)
                        user.getCategories().get(User.LIST_VIDEO_GAMES).get(i).setSelected(true);
                } else {
                    ((Button) findViewById(R.id.category5)).setEnabled(true);
                    for (int i = 0; i < user.getListCount(User.LIST_VIDEO_GAMES); i++)
                        user.getCategories().get(User.LIST_VIDEO_GAMES).get(i).setSelected(false);
                }
            }
        });

        CheckBox musicCheckBox = (CheckBox) findViewById(R.id.MusicBox);
        musicCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((Button) findViewById(R.id.category4)).setEnabled(false);
                    for (int i = 0; i < user.getListCount(User.LIST_MUSIC); i++)
                        user.getCategories().get(User.LIST_MUSIC).get(i).setSelected(true);
                } else {
                    ((Button) findViewById(R.id.category4)).setEnabled(true);
                    for (int i = 0; i < user.getListCount(User.LIST_MUSIC); i++)
                        user.getCategories().get(User.LIST_MUSIC).get(i).setSelected(false);
                }
            }
        });

        CheckBox placesCheckBox = (CheckBox) findViewById(R.id.PlaceBox);
        placesCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((Button) findViewById(R.id.category6)).setEnabled(false);
                    for (int i = 0; i < user.getListCount(User.LIST_PLACES); i++)
                        user.getCategories().get(User.LIST_PLACES).get(i).setSelected(true);
                } else {
                    ((Button) findViewById(R.id.category6)).setEnabled(true);
                    for (int i = 0; i < user.getListCount(User.LIST_PLACES); i++)
                        user.getCategories().get(User.LIST_PLACES).get(i).setSelected(false);
                }
            }
        });

        CheckBox notesCheckBox = (CheckBox) findViewById(R.id.notesBox);
        notesCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((Button) findViewById(R.id.category7)).setEnabled(false);
                    for (int i = 0; i < user.getListCount(User.LIST_NOTES); i++)
                        user.getCategories().get(User.LIST_NOTES).get(i).setSelected(true);
                } else {
                    ((Button) findViewById(R.id.category7)).setEnabled(true);
                    for (int i = 0; i < user.getListCount(User.LIST_NOTES); i++)
                        user.getCategories().get(User.LIST_NOTES).get(i).setSelected(false);
                }
            }
        });
    }


    //Button android:onClick
    public void onCategoryClick(View v) {

        System.out.println(v.getId());
        if (v.getId() == R.id.category0)
            currentCategoryIndex = 0;
        else if (v.getId() == R.id.category1)
            currentCategoryIndex = 1;
        else if (v.getId() == R.id.category2)
            currentCategoryIndex = 2;
        else if (v.getId() == R.id.category3)
            currentCategoryIndex = 6;
        else if (v.getId() == R.id.category5)
            currentCategoryIndex = 5;
        else if (v.getId() == R.id.category4)
            currentCategoryIndex = 4;
        else if (v.getId() == R.id.category6)
            currentCategoryIndex = 3;
        else
            currentCategoryIndex = 7;

        startActivity(new Intent(ShareActivity.this, ShareListActivity.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_share, menu);
        MenuItem item = menu.findItem(R.id.shareActivity_menu);
        return true;
    }


    public void shareOnClick(MenuItem item) {
        String shareText = parser.shareSelected();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}