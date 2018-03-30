package com.example.kerem.ilikethis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import cs102.group1c.ilikethis.model.Book;
import cs102.group1c.ilikethis.model.DatabaseConnection;
import cs102.group1c.ilikethis.model.Item;
import cs102.group1c.ilikethis.model.User;

import static com.example.kerem.ilikethis.AddItem.arrayListOfdetailsOfItem;
import static com.example.kerem.ilikethis.ShowItem.bookItem;
import static com.example.kerem.ilikethis.ShowItem.getItemsID;
import static com.example.kerem.ilikethis.ShowListActivity.listClicked;
import static com.example.kerem.ilikethis.HomePage.currentCategory;
import static com.example.kerem.ilikethis.LoginActivity.user;

public class EditItemBook extends AppCompatActivity {
    String nameofCategory = "Book";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_book);

        getSupportActionBar().setTitle("Add New Book");

        if (arrayListOfdetailsOfItem != null && arrayListOfdetailsOfItem.size() != 0) {

            EditText titleEditText = (EditText) findViewById(R.id.bookTitle);
            titleEditText.setText(arrayListOfdetailsOfItem.get(1));

            EditText authorEditText = (EditText) findViewById(R.id.bookAuthor);
            authorEditText.setText(arrayListOfdetailsOfItem.get(2));

            EditText pagesEditText = (EditText) findViewById(R.id.bookPages);
            pagesEditText.setText(arrayListOfdetailsOfItem.get(3));

            EditText quotesEditText = (EditText) findViewById(R.id.bookQuotes);
            quotesEditText.setText(arrayListOfdetailsOfItem.get(4));

            EditText genreEditText = (EditText) findViewById(R.id.bookGenre);
            genreEditText.setText(arrayListOfdetailsOfItem.get(5));

            EditText publishedYearEditText = (EditText) findViewById(R.id.bookPublishedYear);
            publishedYearEditText.setText(arrayListOfdetailsOfItem.get(6));

            EditText languageEditText = (EditText) findViewById(R.id.bookLanguage);
            languageEditText.setText(arrayListOfdetailsOfItem.get(7));


        } else if (bookItem != null) {

            getSupportActionBar().setTitle("Edit Your Book");

            EditText getContentsEditText;

            ((EditText) findViewById(R.id.bookTitle)).setText(bookItem.getTitle() + "");
            getContentsEditText = (EditText) findViewById(R.id.bookAuthor);
            getContentsEditText.setText(bookItem.getAuthor() + "");
            getContentsEditText = (EditText) findViewById(R.id.bookPages);
            getContentsEditText.setText(bookItem.getNumofPages() + "");
            getContentsEditText = (EditText) findViewById(R.id.bookGenre);
            getContentsEditText.setText(bookItem.getGenre() + "");
            getContentsEditText = (EditText) findViewById(R.id.bookPublishedYear);
            getContentsEditText.setText(bookItem.getYearPublished() + "");
            getContentsEditText = (EditText) findViewById(R.id.bookLanguage);
            getContentsEditText.setText(bookItem.getLanguage() + "");
            getContentsEditText = (EditText) findViewById(R.id.bookQuotes);
            getContentsEditText.setText(bookItem.getQuotes() + "");
            getContentsEditText = (EditText) findViewById(R.id.bookAdditionalNotes);
            getContentsEditText.setText(bookItem.getAdditionalNotes() + "");
        }
    }

    public void saveItem(View view) {

        Book bookToAdd;

        EditText titleEditText = (EditText) findViewById(R.id.bookTitle);
        EditText authorEditText = (EditText) findViewById(R.id.bookAuthor);
        EditText pagesEditText = (EditText) findViewById(R.id.bookPages);
        EditText quotesEditText = (EditText) findViewById(R.id.bookQuotes);
        EditText genreEditText = (EditText) findViewById(R.id.bookGenre);
        EditText publishedYearEditText = (EditText) findViewById(R.id.bookPublishedYear);
        EditText languageEditText = (EditText) findViewById(R.id.bookLanguage);
        EditText additionalNotesEditText = (EditText) findViewById(R.id.bookAdditionalNotes);
        int pages = 0;
        int year = 0;

        if (pagesEditText.getText().toString() != null && !pagesEditText.getText().toString().equals("")) {
            pages = Integer.parseInt(pagesEditText.getText().toString());
        }
        if (publishedYearEditText.getText().toString() != null && !publishedYearEditText.getText().toString().equals("")) {
            year = Integer.parseInt(publishedYearEditText.getText().toString());
        }


        if (bookItem == null) {
            bookToAdd = new Book(titleEditText.getText().toString(),
                    authorEditText.getText().toString(),
                    pages,
                    quotesEditText.getText().toString(),
                    genreEditText.getText().toString(),
                    year,
                    languageEditText.getText().toString(), "");

            bookToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());

            user.addItem(user.getCategories().get(user.LIST_BOOKS).get(listClicked.getId() - 1), bookToAdd);

            if (DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()) == null ||DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()).size() == 0)
                DatabaseConnection.addItemToDb(bookToAdd);

        } else {

            bookToAdd = (Book) user.getCategories().get(user.LIST_BOOKS).get(listClicked.getId() - 1).getItem(getItemsID);

            bookToAdd.setTitle(titleEditText.getText().toString());
            bookToAdd.setAuthor(authorEditText.getText().toString());
            bookToAdd.setGenre(genreEditText.getText().toString());
            bookToAdd.setQuotes(quotesEditText.getText().toString());
            bookToAdd.setNumOfPages(pages);
            bookToAdd.setYearPublished(year);
            bookToAdd.setLanguage(languageEditText.getText().toString());
            bookToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());
        }


        startActivity(new Intent(this, ShowItem.class));


    }

}


