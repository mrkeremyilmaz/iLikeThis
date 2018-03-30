package com.example.kerem.ilikethis;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cs102.group1c.ilikethis.model.List;
import cs102.group1c.ilikethis.model.User;

import static com.example.kerem.ilikethis.HomePage.currentCategory;
import static com.example.kerem.ilikethis.LoginActivity.user;


public class AddList extends AppCompatActivity {

    EditText newTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        getSupportActionBar().setTitle("Add List");
        newTitle = (EditText) findViewById(R.id.newListName);
    }


    public void addList(View view) {
        Button buttonClicked = (Button) findViewById(view.getId());
        currentCategory = buttonClicked;
        if (buttonClicked.getText().equals("Book"))
            user.addList(User.LIST_BOOKS, new List(newTitle.getText().toString()));
        else if (buttonClicked.getText().equals("Movie"))
            user.addList(User.LIST_MOVIES, new List(newTitle.getText().toString()));
        else if (buttonClicked.getText().equals("Video Game"))
            user.addList(User.LIST_VIDEO_GAMES, new List(newTitle.getText().toString()));
        else if (buttonClicked.getText().equals("Place"))
            user.addList(User.LIST_PLACES, new List(newTitle.getText().toString()));
        else if (buttonClicked.getText().equals("Website"))
            user.addList(User.LIST_WEBSITES, new List(newTitle.getText().toString()));
        else if (buttonClicked.getText().equals("TV Show"))
            user.addList(User.LIST_TV_SHOWS, new List(newTitle.getText().toString()));
        else if (buttonClicked.getText().equals("Music"))
            user.addList(User.LIST_MUSIC, new List(newTitle.getText().toString()));
        else if (buttonClicked.getText().equals("Note"))
            user.addList(User.LIST_NOTES, new List(newTitle.getText().toString()));
        finish();
    }
}