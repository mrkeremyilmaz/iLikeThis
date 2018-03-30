package com.example.kerem.ilikethis;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import cs102.group1c.ilikethis.model.Book;
import cs102.group1c.ilikethis.model.Item;
import cs102.group1c.ilikethis.model.List;
import cs102.group1c.ilikethis.model.Movie;
import cs102.group1c.ilikethis.model.Music;
import cs102.group1c.ilikethis.model.Note;
import cs102.group1c.ilikethis.model.Place;
import cs102.group1c.ilikethis.model.TVShow;
import cs102.group1c.ilikethis.model.User;
import cs102.group1c.ilikethis.model.VideoGame;
import cs102.group1c.ilikethis.model.Website;

import static com.example.kerem.ilikethis.HomePage.currentCategory;
import static com.example.kerem.ilikethis.LoginActivity.user;
import static com.example.kerem.ilikethis.ShowListActivity.listClicked;
import static com.example.kerem.ilikethis.AddItem.arrayListOfdetailsOfItem;

public class ShowItem extends AppCompatActivity implements View.OnClickListener {
    protected static Book bookItem;
    protected static Music musicItem;
    protected static Movie movieItem;
    protected static Note noteItem;
    protected static Place placeItem;
    protected static TVShow tvShowItem;
    protected static VideoGame videogameItem;
    protected static Website websiteItem;
    protected static int getItemsID;

    List list;
    protected static int idOfItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Items");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ScrollView scrollView = new ScrollView(this);
        scrollView.setBackgroundColor(Color.BLUE);

        RelativeLayout innerRelLayout = new RelativeLayout(this);
        innerRelLayout.setBackgroundColor(Color.RED);

        Button button;

        RelativeLayout.LayoutParams buttonDetails;

        int categoryConstant;
        if (currentCategory.getText().toString().equals("Book"))
            categoryConstant = User.LIST_BOOKS;
        else if (currentCategory.getText().toString().equals("Movie"))
            categoryConstant = User.LIST_MOVIES;
        else if (currentCategory.getText().toString().equals("Video Game"))
            categoryConstant = User.LIST_VIDEO_GAMES;
        else if (currentCategory.getText().toString().equals("Place"))
            categoryConstant = User.LIST_PLACES;
        else if (currentCategory.getText().toString().equals("Website"))
            categoryConstant = User.LIST_WEBSITES;
        else if (currentCategory.getText().toString().equals("TV Show"))
            categoryConstant = User.LIST_TV_SHOWS;
        else if (currentCategory.getText().toString().equals("Music"))
            categoryConstant = User.LIST_MUSIC;
        else if (currentCategory.getText().toString().equals("Note"))
            categoryConstant = User.LIST_NOTES;
        else
            categoryConstant = 10;

        list = user.getCategories().get(categoryConstant).get(listClicked.getId() - 1);
        for (int i = 1; i <= list.size(); i++) {
            button = new Button(this);
            button.setId(i);
            button.setText(list.getItem((i - 1)).getTitle()); //why i instead of i - 1 ?????
            button.setTransformationMethod(null);

            buttonDetails = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
            buttonDetails.addRule(RelativeLayout.BELOW, i - 1);

            innerRelLayout.addView(button, buttonDetails);
            button.setOnClickListener(this);

        }

        scrollView.setBackgroundColor(Color.WHITE);
        scrollView.addView(innerRelLayout);
        setContentView(scrollView);
    }

    @Override
    public void onClick(View v) {
        arrayListOfdetailsOfItem = null;
        Intent i;

        if (HomePage.currentCategory.getText().toString().equals("Book")) {
            getItemsID = v.getId() - 1;
            i = new Intent(ShowItem.this, EditItemBook.class);

            bookItem = (Book) user.getCategories().get(user.LIST_BOOKS).get(listClicked.getId() - 1).getItem(v.getId() - 1);

        } else if (HomePage.currentCategory.getText().toString().equals("Website")) {
            i = new Intent(ShowItem.this, EditItemWebsite.class);

            websiteItem = (Website) user.getCategories().get(user.LIST_WEBSITES).get(listClicked.getId() - 1).getItem(v.getId() - 1);
        } else if (HomePage.currentCategory.getText().toString().equals("Movie")) {
            i = new Intent(ShowItem.this, EditItemMovie.class);

            movieItem = (Movie) user.getCategories().get(user.LIST_MOVIES).get(listClicked.getId() - 1).getItem(v.getId() - 1);

        } else if (HomePage.currentCategory.getText().toString().equals("TV Show")) {
            i = new Intent(ShowItem.this, EditItemTVShow.class);

            tvShowItem = (TVShow) user.getCategories().get(user.LIST_TV_SHOWS).get(listClicked.getId() - 1).getItem(v.getId() - 1);
        } else if (HomePage.currentCategory.getText().toString().equals("Video Game")) {
            i = new Intent(ShowItem.this, EditItemVideoGame.class);

            videogameItem = (VideoGame) user.getCategories().get(user.LIST_VIDEO_GAMES).get(listClicked.getId() - 1).getItem(v.getId() - 1);
        } else if (HomePage.currentCategory.getText().toString().equals("Music")) {
            i = new Intent(ShowItem.this, EditItemMusic.class);

            musicItem = (Music) user.getCategories().get(user.LIST_MUSIC).get(listClicked.getId() - 1).getItem(v.getId() - 1);
        } else if (HomePage.currentCategory.getText().toString().equals("Place")) {
            i = new Intent(ShowItem.this, EditItemPlace.class);

            placeItem = (Place) user.getCategories().get(user.LIST_PLACES).get(listClicked.getId() - 1).getItem(v.getId() - 1);
        } else {
            i = new Intent(ShowItem.this, EditItemNote.class);

            noteItem = (Note) user.getCategories().get(user.LIST_NOTES).get(listClicked.getId() - 1).getItem(v.getId() - 1);
        }
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_additem, menu);

        return true;
    }

    public boolean addItem(MenuItem item) {

        if (!HomePage.currentCategory.getText().toString().equals("Note"))
            startActivity(new Intent(this, AddItem.class));
        else
            startActivity(new Intent(this, EditItemNote.class));

        return true;
    }

    public boolean howToAddItem(MenuItem item) {
        startActivity(new Intent(this, HowToAdditem.class));
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}