package com.example.kerem.ilikethis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import cs102.group1c.ilikethis.model.Book;
import cs102.group1c.ilikethis.model.DatabaseConnection;
import cs102.group1c.ilikethis.model.Music;
import cs102.group1c.ilikethis.model.Place;

import static com.example.kerem.ilikethis.AddItem.arrayListOfdetailsOfItem;
import static com.example.kerem.ilikethis.LoginActivity.user;
import static com.example.kerem.ilikethis.ShowItem.getItemsID;
import static com.example.kerem.ilikethis.ShowItem.placeItem;
import static com.example.kerem.ilikethis.ShowListActivity.listClicked;

public class EditItemPlace extends AppCompatActivity {

    String nameofCategory = "Place";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_place);

        getSupportActionBar().setTitle("Add New Place");

        if (arrayListOfdetailsOfItem != null && arrayListOfdetailsOfItem.size() != 0) {
            EditText titleEditText = (EditText) findViewById(R.id.placeTitle);
            titleEditText.setText(arrayListOfdetailsOfItem.get(1));

            EditText addressEditText = (EditText) findViewById(R.id.placeAddress);
            addressEditText.setText(arrayListOfdetailsOfItem.get(2));

            EditText workingHoursEditText = (EditText) findViewById(R.id.placeWorkingHours);
            workingHoursEditText.setText(arrayListOfdetailsOfItem.get(3));

            EditText themeEditText = (EditText) findViewById(R.id.placeTheme);
            themeEditText.setText(arrayListOfdetailsOfItem.get(4));

        } else if (placeItem != null) {

            getSupportActionBar().setTitle("Edit Your Place");

            EditText getContentsEditText;

            ((EditText) findViewById(R.id.placeTitle)).setText(placeItem.getTitle() + "");
            getContentsEditText = (EditText) findViewById(R.id.placeAddress);
            getContentsEditText.setText(placeItem.getAddress() + "");
            getContentsEditText = (EditText) findViewById(R.id.placeWorkingHours);
            getContentsEditText.setText(placeItem.getWorkingHours() + "");
            getContentsEditText = (EditText) findViewById(R.id.placeTheme);
            getContentsEditText.setText(placeItem.getTheme() + "");
            getContentsEditText = (EditText) findViewById(R.id.placeAdditionalNotes);
            getContentsEditText.setText(placeItem.getAdditionalNotes() + "");

        }
    }


    public void saveItem(View view) {
        Place placeToAdd;

        EditText titleEditText = (EditText) findViewById(R.id.placeTitle);
        EditText addressEditText = (EditText) findViewById(R.id.placeAddress);
        EditText workingHoursEditText = (EditText) findViewById(R.id.placeWorkingHours);
        EditText themeEditText = (EditText) findViewById(R.id.placeTheme);
        EditText additionalNotesEditText = (EditText) findViewById(R.id.placeAdditionalNotes);

        if (placeItem == null) {
            placeToAdd = new Place(titleEditText.getText().toString(),
                    addressEditText.getText().toString(),
                    workingHoursEditText.getText().toString(),
                    themeEditText.getText().toString());

            if (DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()) == null || DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()).size() == 0)
                DatabaseConnection.addItemToDb(placeToAdd);

            placeToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());

            user.addItem(user.getCategories().get(user.LIST_PLACES).get(listClicked.getId() - 1), placeToAdd);
        } else {

            placeToAdd = (Place) user.getCategories().get(user.LIST_PLACES).get(listClicked.getId() - 1).getItem(getItemsID);

            placeToAdd.setTitle(titleEditText.getText().toString());
            placeToAdd.setAddress(addressEditText.getText().toString());
            placeToAdd.setWorkingHours(workingHoursEditText.getText().toString());
            placeToAdd.setTheme(themeEditText.getText().toString());
            placeToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());
        }

        startActivity(new Intent(this, ShowItem.class));
    }
}
