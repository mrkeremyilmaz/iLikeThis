package com.example.kerem.ilikethis;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import cs102.group1c.ilikethis.model.DatabaseConnection;
import cs102.group1c.ilikethis.model.Music;
import cs102.group1c.ilikethis.model.Place;
import cs102.group1c.ilikethis.model.VideoGame;

import static com.example.kerem.ilikethis.AddItem.arrayListOfdetailsOfItem;
import static com.example.kerem.ilikethis.LoginActivity.user;
import static com.example.kerem.ilikethis.ShowItem.getItemsID;
import static com.example.kerem.ilikethis.ShowItem.videogameItem;
import static com.example.kerem.ilikethis.ShowListActivity.listClicked;

public class EditItemVideoGame extends AppCompatActivity {

    String nameofCategory = "VideoGame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_video_game);

        getSupportActionBar().setTitle("Add New Video Game");

        if (arrayListOfdetailsOfItem != null && arrayListOfdetailsOfItem.size() != 0) {
            EditText titleEditText = (EditText) findViewById(R.id.VideoGameTitle);
            titleEditText.setText(arrayListOfdetailsOfItem.get(1));

            EditText authorEditText = (EditText) findViewById(R.id.VideoGameDeveloper);
            authorEditText.setText(arrayListOfdetailsOfItem.get(3));

            EditText genreEditText = (EditText) findViewById(R.id.VideoGameGenre);
            genreEditText.setText(arrayListOfdetailsOfItem.get(2));

            EditText infoEditText = (EditText) findViewById(R.id.VideoGameInfo);
            infoEditText.setText(arrayListOfdetailsOfItem.get(4));

            EditText yearEditText = (EditText) findViewById(R.id.VideoGameYear);
            yearEditText.setText(arrayListOfdetailsOfItem.get(5));

        } else if (videogameItem != null) {

            getSupportActionBar().setTitle("Edit Your Video Game");

            EditText getContentsEditText;

            ((EditText) findViewById(R.id.VideoGameTitle)).setText(videogameItem.getTitle() + "");
            getContentsEditText = (EditText) findViewById(R.id.VideoGameDeveloper);
            getContentsEditText.setText(videogameItem.getDeveloper() + "");
            getContentsEditText = (EditText) findViewById(R.id.VideoGameGenre);
            getContentsEditText.setText(videogameItem.getGenre() + "");
            getContentsEditText = (EditText) findViewById(R.id.VideoGameYear);
            getContentsEditText.setText(videogameItem.getYear() + "");
            getContentsEditText = (EditText) findViewById(R.id.VideoGameInfo);
            getContentsEditText.setText(videogameItem.getInfo() + "");
            getContentsEditText = (EditText) findViewById(R.id.VideoGameAdditionalNotes);
            getContentsEditText.setText(videogameItem.getAdditionalNotes() + "");
        }
    }


    public void saveItem(View view) {
        VideoGame videoGameToAdd;

        EditText titleEditText = (EditText) findViewById(R.id.VideoGameTitle);
        EditText authorEditText = (EditText) findViewById(R.id.VideoGameDeveloper);
        EditText genreEditText = (EditText) findViewById(R.id.VideoGameGenre);
        EditText infoEditText = (EditText) findViewById(R.id.VideoGameInfo);
        EditText yearEditText = (EditText) findViewById(R.id.VideoGameYear);
        EditText additionalNotesEditText = (EditText) findViewById(R.id.VideoGameAdditionalNotes);


        int year = 0;

        if (yearEditText.getText().toString() != null && !yearEditText.getText().toString().equals("")) {
            year = Integer.parseInt(yearEditText.getText().toString());
        }

        if (videogameItem == null) {
            videoGameToAdd = new VideoGame(titleEditText.getText().toString(),
                    authorEditText.getText().toString(),
                    genreEditText.getText().toString(),
                    infoEditText.getText().toString(), year);


            if (DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()) == null || DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()).size() == 0) {
            DatabaseConnection.addItemToDb(videoGameToAdd);
        }
            videoGameToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());

            user.addItem(user.getCategories().get(user.LIST_VIDEO_GAMES).get(listClicked.getId() - 1), videoGameToAdd);
        } else {

            videoGameToAdd = (VideoGame) user.getCategories().get(user.LIST_VIDEO_GAMES).get(listClicked.getId() - 1).getItem(getItemsID);

            videoGameToAdd.setTitle(titleEditText.getText().toString());
            videoGameToAdd.setDeveloper(authorEditText.getText().toString());
            videoGameToAdd.setGenre(genreEditText.getText().toString());
            videoGameToAdd.setInfo(infoEditText.getText().toString());
            videoGameToAdd.setYear(year);
            videoGameToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());
        }
        startActivity(new Intent(this, ShowItem.class));
    }
}
