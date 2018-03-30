package com.example.kerem.ilikethis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import cs102.group1c.ilikethis.model.Book;
import cs102.group1c.ilikethis.model.DatabaseConnection;
import cs102.group1c.ilikethis.model.Movie;

import static com.example.kerem.ilikethis.AddItem.arrayListOfdetailsOfItem;
import static com.example.kerem.ilikethis.LoginActivity.user;
import static com.example.kerem.ilikethis.ShowItem.getItemsID;
import static com.example.kerem.ilikethis.ShowItem.movieItem;
import static com.example.kerem.ilikethis.ShowListActivity.listClicked;

public class EditItemMovie extends AppCompatActivity {

    String nameofCategory = "Movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_movie);

        getSupportActionBar().setTitle("Add New Movie");

        if (arrayListOfdetailsOfItem != null && arrayListOfdetailsOfItem.size() != 0) {

            EditText titleEditText = (EditText) findViewById(R.id.movieTitle);
            titleEditText.setText(arrayListOfdetailsOfItem.get(1));

            EditText authorEditText = (EditText) findViewById(R.id.movieDirector);
            authorEditText.setText(arrayListOfdetailsOfItem.get(2));

            EditText countryEditText = (EditText) findViewById(R.id.movieCountry);
            countryEditText.setText(arrayListOfdetailsOfItem.get(3));

            EditText genreEditText = (EditText) findViewById(R.id.movieGenre);
            genreEditText.setText(arrayListOfdetailsOfItem.get(4));

            EditText publishedYearEditText = (EditText) findViewById(R.id.movieYear);
            publishedYearEditText.setText(arrayListOfdetailsOfItem.get(5));

            EditText durationEditText = (EditText) findViewById(R.id.movieDuration);
            durationEditText.setText(arrayListOfdetailsOfItem.get(6));

            EditText cast1EditText = (EditText) findViewById(R.id.movieCast1);
            cast1EditText.setText(arrayListOfdetailsOfItem.get(7));

            EditText cast2EditText = (EditText) findViewById(R.id.movieCast2);
            cast2EditText.setText(arrayListOfdetailsOfItem.get(8));

            EditText cast3EditText = (EditText) findViewById(R.id.movieCast3);
            cast3EditText.setText(arrayListOfdetailsOfItem.get(9));
        } else if (movieItem != null) {

            getSupportActionBar().setTitle("Edit Your Movie");

            EditText getContentsEditText;

            ((EditText) findViewById(R.id.movieTitle)).setText(movieItem.getTitle() + "");
            getContentsEditText = (EditText) findViewById(R.id.movieDirector);
            getContentsEditText.setText(movieItem.getDirector() + "");
            getContentsEditText = (EditText) findViewById(R.id.movieCountry);
            getContentsEditText.setText(movieItem.getCountry() + "");
            getContentsEditText = (EditText) findViewById(R.id.movieGenre);
            getContentsEditText.setText(movieItem.getGenre() + "");
            getContentsEditText = (EditText) findViewById(R.id.movieYear);
            getContentsEditText.setText(movieItem.getYear() + "");
            getContentsEditText = (EditText) findViewById(R.id.movieDuration);
            getContentsEditText.setText(movieItem.getDuration() + "");
            getContentsEditText = (EditText) findViewById(R.id.movieAdditionalNotes);
            getContentsEditText.setText(movieItem.getAdditionalNotes() + "");
            getContentsEditText = (EditText) findViewById(R.id.movieCast1);
            getContentsEditText.setText(movieItem.getCast().get(0) + "");
            getContentsEditText = (EditText) findViewById(R.id.movieCast2);
            getContentsEditText.setText(movieItem.getCast().get(1) + "");
            getContentsEditText = (EditText) findViewById(R.id.movieCast3);
            getContentsEditText.setText(movieItem.getCast().get(2) + "");
        }
    }


    public void saveItem(View view) {

        Movie movieToAdd;

        EditText titleEditText = (EditText) findViewById(R.id.movieTitle);
        EditText authorEditText = (EditText) findViewById(R.id.movieDirector);
        EditText countryEditText = (EditText) findViewById(R.id.movieCountry);
        EditText genreEditText = (EditText) findViewById(R.id.movieGenre);
        EditText publishedYearEditText = (EditText) findViewById(R.id.movieYear);
        EditText durationEditText = (EditText) findViewById(R.id.movieDuration);
        EditText additionalNotesEditText = (EditText) findViewById(R.id.movieAdditionalNotes);
        EditText cast1EditText = (EditText) findViewById(R.id.movieCast1);
        EditText cast2EditText = (EditText) findViewById(R.id.movieCast2);
        EditText cast3EditText = (EditText) findViewById(R.id.movieCast3);

        int duration = 0;
        int year = 0;

        if (durationEditText.getText().toString() != null && !durationEditText.getText().toString().equals("")) {
            duration = Integer.parseInt(durationEditText.getText().toString());
        }
        if (publishedYearEditText.getText().toString() != null && !publishedYearEditText.getText().toString().equals("")) {
            year = Integer.parseInt(publishedYearEditText.getText().toString());
        }

        if (movieItem == null) {
            movieToAdd = new Movie(titleEditText.getText().toString(),
                    authorEditText.getText().toString(),
                    countryEditText.getText().toString(),
                    genreEditText.getText().toString(),
                    year, duration);

            if (DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()) == null || DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()).size() == 0)
                DatabaseConnection.addItemToDb(movieToAdd);

            movieToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());
            movieToAdd.addCastMember(cast1EditText.getText().toString());
            movieToAdd.addCastMember(cast2EditText.getText().toString());
            movieToAdd.addCastMember(cast3EditText.getText().toString());

            user.addItem(user.getCategories().get(user.LIST_MOVIES).get(listClicked.getId() - 1), movieToAdd);
        } else {

            movieToAdd = (Movie) user.getCategories().get(user.LIST_MOVIES).get(listClicked.getId() - 1).getItem(getItemsID);

            movieToAdd.setTitle(titleEditText.getText().toString());
            movieToAdd.setDirector(authorEditText.getText().toString());
            movieToAdd.setGenre(genreEditText.getText().toString());
            movieToAdd.setCountry(countryEditText.getText().toString());
            movieToAdd.setYear(year);
            movieToAdd.setDuration(duration);
            movieToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());
            movieToAdd.getCast().remove(0);
            movieToAdd.getCast().remove(1);
            movieToAdd.getCast().remove(2);
            movieToAdd.addCastMember(cast1EditText.getText().toString());
            movieToAdd.addCastMember(cast2EditText.getText().toString());
            movieToAdd.addCastMember(cast3EditText.getText().toString());
        }

        startActivity(new Intent(this, ShowItem.class));
    }
}
