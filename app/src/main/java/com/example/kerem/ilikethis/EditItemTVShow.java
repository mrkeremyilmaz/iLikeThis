package com.example.kerem.ilikethis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import cs102.group1c.ilikethis.model.DatabaseConnection;
import cs102.group1c.ilikethis.model.Movie;
import cs102.group1c.ilikethis.model.TVShow;

import static com.example.kerem.ilikethis.AddItem.arrayListOfdetailsOfItem;
import static com.example.kerem.ilikethis.LoginActivity.user;
import static com.example.kerem.ilikethis.ShowItem.getItemsID;
import static com.example.kerem.ilikethis.ShowItem.tvShowItem;
import static com.example.kerem.ilikethis.ShowListActivity.listClicked;

public class EditItemTVShow extends AppCompatActivity {

    String nameofCategory = "TVShow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_tvshow);

        getSupportActionBar().setTitle("Add New TV Show");

        if (arrayListOfdetailsOfItem != null && arrayListOfdetailsOfItem.size() != 0) {
            EditText titleEditText = (EditText) findViewById(R.id.TVShowTitle);
            titleEditText.setText(arrayListOfdetailsOfItem.get(1));

            EditText authorEditText = (EditText) findViewById(R.id.TVShowDirector);
            authorEditText.setText(arrayListOfdetailsOfItem.get(2));

            EditText countryEditText = (EditText) findViewById(R.id.TVShowCountry);
            countryEditText.setText(arrayListOfdetailsOfItem.get(3));

            EditText genreEditText = (EditText) findViewById(R.id.TVShowGenre);
            genreEditText.setText(arrayListOfdetailsOfItem.get(4));

            EditText yearEditText = (EditText) findViewById(R.id.TVShowYear);
            yearEditText.setText(arrayListOfdetailsOfItem.get(5));

            EditText episodeDurationEditText = (EditText) findViewById(R.id.TVShowEpisodeDuration);
            episodeDurationEditText.setText(arrayListOfdetailsOfItem.get(6));

            EditText seasonEditText = (EditText) findViewById(R.id.TVShowSeasons);
            seasonEditText.setText(arrayListOfdetailsOfItem.get(7));

            EditText cast1EditText = (EditText) findViewById(R.id.TVShowCast1);
            cast1EditText.setText(arrayListOfdetailsOfItem.get(8));

            EditText cast2EditText = (EditText) findViewById(R.id.TVShowCast2);
            cast2EditText.setText(arrayListOfdetailsOfItem.get(9));

            EditText cast3EditText = (EditText) findViewById(R.id.TVShowCast3);
            cast3EditText.setText(arrayListOfdetailsOfItem.get(10));
        } else if (tvShowItem != null) {

            getSupportActionBar().setTitle("Edit Your TV Show");

            EditText getContentsEditText;

            ((EditText) findViewById(R.id.TVShowTitle)).setText(tvShowItem.getTitle() + "");
            getContentsEditText = (EditText) findViewById(R.id.TVShowDirector);
            getContentsEditText.setText(tvShowItem.getDirector() + "");
            getContentsEditText = (EditText) findViewById(R.id.TVShowCountry);
            getContentsEditText.setText(tvShowItem.getCountry() + "");
            getContentsEditText = (EditText) findViewById(R.id.TVShowGenre);
            getContentsEditText.setText(tvShowItem.getGenre() + "");
            getContentsEditText = (EditText) findViewById(R.id.TVShowYear);
            getContentsEditText.setText(tvShowItem.getYear() + "");
            getContentsEditText = (EditText) findViewById(R.id.TVShowEpisodeDuration);
            getContentsEditText.setText(tvShowItem.getEpisodeDuration() + "");
            getContentsEditText = (EditText) findViewById(R.id.TVShowSeasons);
            getContentsEditText.setText(tvShowItem.getSeasons() + "");
            getContentsEditText = (EditText) findViewById(R.id.TVShowAdditionalNotes);
            getContentsEditText.setText(tvShowItem.getAdditionalNotes() + "");
            getContentsEditText = (EditText) findViewById(R.id.TVShowCast1);
            getContentsEditText.setText(tvShowItem.getCast()[0] + "");
            getContentsEditText = (EditText) findViewById(R.id.TVShowCast2);
            getContentsEditText.setText(tvShowItem.getCast()[1] + "");
            getContentsEditText = (EditText) findViewById(R.id.TVShowCast3);
            getContentsEditText.setText(tvShowItem.getCast()[2] + "");
        }
    }


    public void saveItem(View view) {
        TVShow tvShowToAdd;

        EditText titleEditText = (EditText) findViewById(R.id.TVShowTitle);
        EditText authorEditText = (EditText) findViewById(R.id.TVShowDirector);
        EditText countryEditText = (EditText) findViewById(R.id.TVShowCountry);
        EditText genreEditText = (EditText) findViewById(R.id.TVShowGenre);
        EditText publishedYearEditText = (EditText) findViewById(R.id.TVShowYear);
        EditText durationEditText = (EditText) findViewById(R.id.TVShowEpisodeDuration);
        EditText seasonsEditText = (EditText) findViewById(R.id.TVShowSeasons);
        EditText additionalNotesEditText = (EditText) findViewById(R.id.TVShowAdditionalNotes);
        EditText cast1EditText = (EditText) findViewById(R.id.TVShowCast1);
        EditText cast2EditText = (EditText) findViewById(R.id.TVShowCast2);
        EditText cast3EditText = (EditText) findViewById(R.id.TVShowCast3);

        int episodeduration = 0;
        int year = 0;
        int seasons = 0;

        if (durationEditText.getText().toString() != null && !durationEditText.getText().toString().equals("")) {
            episodeduration = Integer.parseInt(durationEditText.getText().toString());
        }
        if (seasonsEditText.getText().toString() != null && !seasonsEditText.getText().toString().equals("")) {
            seasons = Integer.parseInt(seasonsEditText.getText().toString());
        }
        if (publishedYearEditText.getText().toString() != null && !publishedYearEditText.getText().toString().equals("")) {
            year = Integer.parseInt(publishedYearEditText.getText().toString());
        }
        if (tvShowItem == null) {
            tvShowToAdd = new TVShow(titleEditText.getText().toString(),
                    authorEditText.getText().toString(),
                    countryEditText.getText().toString(),
                    genreEditText.getText().toString(),
                    year, seasons, episodeduration,
                    cast1EditText.getText().toString(),
                    cast2EditText.getText().toString(),
                    cast3EditText.getText().toString());

            if (DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()) == null || DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()).size() == 0)
                DatabaseConnection.addItemToDb(tvShowToAdd);

            tvShowToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());

            user.addItem(user.getCategories().get(user.LIST_TV_SHOWS).get(listClicked.getId() - 1), tvShowToAdd);

        } else {

            tvShowToAdd = (TVShow) user.getCategories().get(user.LIST_TV_SHOWS).get(listClicked.getId() - 1).getItem(getItemsID);

            tvShowToAdd.setTitle(titleEditText.getText().toString());
            tvShowToAdd.setDirector(authorEditText.getText().toString());
            tvShowToAdd.setGenre(genreEditText.getText().toString());
            tvShowToAdd.setCountry(countryEditText.getText().toString());
            tvShowToAdd.setYear(year);
            tvShowToAdd.setEpisodeDuration(episodeduration);
            tvShowToAdd.setSeasons(seasons);
            tvShowToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());

            tvShowToAdd.setCast(0, cast1EditText.getText().toString());
            tvShowToAdd.setCast(1, cast2EditText.getText().toString());
            tvShowToAdd.setCast(2, cast3EditText.getText().toString());
        }


        startActivity(new Intent(this, ShowItem.class));
    }

}
