package com.example.kerem.ilikethis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ScrollView;

import cs102.group1c.ilikethis.model.List;
import cs102.group1c.ilikethis.model.User;

import static com.example.kerem.ilikethis.HomePage.currentCategory;
import static com.example.kerem.ilikethis.LoginActivity.user;


public class ShowListActivity extends AppCompatActivity implements View.OnClickListener {

    protected static Button listClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Lists");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ScrollView scrollView = new ScrollView(this);
        scrollView.setBackgroundColor(Color.BLUE);


        RelativeLayout showListLayout = new RelativeLayout(this);
        showListLayout.setBackgroundColor(Color.RED);

        Button button;

        RelativeLayout.LayoutParams buttonDetails;

        Button buttonClicked = currentCategory;
        int categoryConstant;
        if (buttonClicked.getText().toString().equals("Book"))
            categoryConstant = User.LIST_BOOKS;
        else if (buttonClicked.getText().toString().equals("Movie"))
            categoryConstant = User.LIST_MOVIES;
        else if (buttonClicked.getText().toString().equals("Video Game"))
            categoryConstant = User.LIST_VIDEO_GAMES;
        else if (buttonClicked.getText().toString().equals("Place"))
            categoryConstant = User.LIST_PLACES;
        else if (buttonClicked.getText().toString().equals("Website"))
            categoryConstant = User.LIST_WEBSITES;
        else if (buttonClicked.getText().toString().equals("TV Show"))
            categoryConstant = User.LIST_TV_SHOWS;
        else if (buttonClicked.getText().toString().equals("Music"))
            categoryConstant = User.LIST_MUSIC;
        else if (buttonClicked.getText().toString().equals("Note"))
            categoryConstant = User.LIST_NOTES;
        else
            categoryConstant = 10;

        System.out.println("categoryconstant :" + categoryConstant);
        for (int i = 1; i <= user.getListCount(categoryConstant); i++) {
            String title = user.getCategories().get(categoryConstant).get(i - 1).getTitle();
            button = new Button(this);
            button.setId(i);
            button.setText(title);
            button.setTransformationMethod(null);

            buttonDetails = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
            buttonDetails.addRule(RelativeLayout.BELOW, i - 1);

            showListLayout.addView(button, buttonDetails);

            button.setOnClickListener(this);
        }


        //display
        scrollView.setBackgroundColor(Color.WHITE);
        scrollView.addView(showListLayout);
        setContentView(scrollView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, ShowItem.class);
        listClicked = (Button) findViewById(v.getId());
        System.out.println("list button clicked id: " + v.getId());
        startActivity(i);
    }

}
