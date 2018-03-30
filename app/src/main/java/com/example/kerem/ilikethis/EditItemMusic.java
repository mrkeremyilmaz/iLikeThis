package com.example.kerem.ilikethis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import cs102.group1c.ilikethis.model.Book;
import cs102.group1c.ilikethis.model.DatabaseConnection;
import cs102.group1c.ilikethis.model.Movie;
import cs102.group1c.ilikethis.model.Music;

import static com.example.kerem.ilikethis.AddItem.arrayListOfdetailsOfItem;
import static com.example.kerem.ilikethis.LoginActivity.user;
import static com.example.kerem.ilikethis.ShowItem.getItemsID;
import static com.example.kerem.ilikethis.ShowItem.musicItem;
import static com.example.kerem.ilikethis.ShowListActivity.listClicked;

public class EditItemMusic extends AppCompatActivity {

    String nameofCategory = "Music";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_music);

        getSupportActionBar().setTitle("Add New Music");

        if (arrayListOfdetailsOfItem != null && arrayListOfdetailsOfItem.size() != 0) {

            EditText titleEditText = (EditText) findViewById(R.id.musicTitle);
            titleEditText.setText(arrayListOfdetailsOfItem.get(1));

            EditText authorEditText = (EditText) findViewById(R.id.musicArtist);
            authorEditText.setText(arrayListOfdetailsOfItem.get(3));

            EditText albumEditText = (EditText) findViewById(R.id.musicAlbum);
            albumEditText.setText(arrayListOfdetailsOfItem.get(2));

            EditText genreEditText = (EditText) findViewById(R.id.musicGenre);
            genreEditText.setText(arrayListOfdetailsOfItem.get(4));

            EditText languageEditText = (EditText) findViewById(R.id.musicLanguage);
            languageEditText.setText(arrayListOfdetailsOfItem.get(5));

            EditText durationEditText = (EditText) findViewById(R.id.musicDuration);
            durationEditText.setText(arrayListOfdetailsOfItem.get(6));

        } else if (musicItem != null) {

            getSupportActionBar().setTitle("Edit Your Music");

            EditText getContentsEditText;

            ((EditText) findViewById(R.id.musicTitle)).setText(musicItem.getTitle() + "");
            getContentsEditText = (EditText) findViewById(R.id.musicArtist);
            getContentsEditText.setText(musicItem.getArtist() + "");
            getContentsEditText = (EditText) findViewById(R.id.musicAlbum);
            getContentsEditText.setText(musicItem.getAlbum() + "");
            getContentsEditText = (EditText) findViewById(R.id.musicGenre);
            getContentsEditText.setText(musicItem.getGenre() + "");
            getContentsEditText = (EditText) findViewById(R.id.musicLanguage);
            getContentsEditText.setText(musicItem.getLanguage() + "");
            getContentsEditText = (EditText) findViewById(R.id.musicDuration);
            getContentsEditText.setText(musicItem.getDuration() + "");
            getContentsEditText = (EditText) findViewById(R.id.musicAdditionalNotes);
            getContentsEditText.setText(musicItem.getAdditionalNotes() + "");
        }


    }

    public void saveItem(View view) {
        Music musicToAdd;

        EditText titleEditText = (EditText) findViewById(R.id.musicTitle);
        EditText authorEditText = (EditText) findViewById(R.id.musicArtist);
        EditText albumEditText = (EditText) findViewById(R.id.musicAlbum);
        EditText genreEditText = (EditText) findViewById(R.id.musicGenre);
        EditText languageEditText = (EditText) findViewById(R.id.musicLanguage);
        EditText durationEditText = (EditText) findViewById(R.id.musicDuration);
        EditText additionalNotesEditText = (EditText) findViewById(R.id.musicAdditionalNotes);


        int duration = 0;

        if (durationEditText.getText().toString() != null && !durationEditText.getText().toString().equals("")) {
            duration = Integer.parseInt(durationEditText.getText().toString());
        }

        if (musicItem == null) {
            musicToAdd = new Music(titleEditText.getText().toString(),
                    albumEditText.getText().toString(),
                    authorEditText.getText().toString(),
                    genreEditText.getText().toString(),
                    languageEditText.getText().toString(), duration);

            if (DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()).size() == 0)
                DatabaseConnection.addItemToDb(musicToAdd);

            musicToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());

            user.addItem(user.getCategories().get(user.LIST_MUSIC).get(listClicked.getId() - 1), musicToAdd);
        } else {

            musicToAdd = (Music) user.getCategories().get(user.LIST_MUSIC).get(listClicked.getId() - 1).getItem(getItemsID);

            musicToAdd.setTitle(titleEditText.getText().toString());
            musicToAdd.setArtist(authorEditText.getText().toString());
            musicToAdd.setAlbum(albumEditText.getText().toString());
            musicToAdd.setGenre(genreEditText.getText().toString());
            musicToAdd.setDuration(duration);
            musicToAdd.setLanguage(languageEditText.getText().toString());
            musicToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());
        }


        startActivity(new Intent(this, ShowItem.class));
    }
}
