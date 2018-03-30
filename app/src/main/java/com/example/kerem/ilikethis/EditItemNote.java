package com.example.kerem.ilikethis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import cs102.group1c.ilikethis.model.Book;
import cs102.group1c.ilikethis.model.DatabaseConnection;
import cs102.group1c.ilikethis.model.Note;

import static com.example.kerem.ilikethis.AddItem.arrayListOfdetailsOfItem;
import static com.example.kerem.ilikethis.LoginActivity.user;
import static com.example.kerem.ilikethis.ShowItem.getItemsID;
import static com.example.kerem.ilikethis.ShowItem.noteItem;
import static com.example.kerem.ilikethis.ShowListActivity.listClicked;

public class EditItemNote extends AppCompatActivity {

    String nameofCategory = "Note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_note);

        getSupportActionBar().setTitle("Add New Note");

        if (noteItem != null) {

            getSupportActionBar().setTitle("Edit Your Note");

            EditText getContentsEditText;

            ((EditText) findViewById(R.id.noteTitle)).setText(noteItem.getTitle() + "");
            getContentsEditText = (EditText) findViewById(R.id.NoteText);
            getContentsEditText.setText(noteItem.getNote() + "");
        }

    }


    public void saveItem(View view) {
        Note noteToAdd;

        EditText titleEditText = (EditText) findViewById(R.id.noteTitle);
        EditText additionalNotesEditText = (EditText) findViewById(R.id.NoteText);

        if (noteItem == null) {
            noteToAdd = new Note(titleEditText.getText().toString());

            noteToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());

            user.addItem(user.getCategories().get(user.LIST_NOTES).get(listClicked.getId() - 1), noteToAdd);
        } else {

            noteToAdd = (Note) user.getCategories().get(user.LIST_NOTES).get(listClicked.getId() - 1).getItem(getItemsID);

            noteToAdd.setTitle(titleEditText.getText().toString());
            noteToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());
        }

        startActivity(new Intent(this, ShowItem.class));
    }
}
