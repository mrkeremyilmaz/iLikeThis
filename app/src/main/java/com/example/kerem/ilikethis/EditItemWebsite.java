package com.example.kerem.ilikethis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import cs102.group1c.ilikethis.model.DatabaseConnection;
import cs102.group1c.ilikethis.model.Place;
import cs102.group1c.ilikethis.model.Website;

import static com.example.kerem.ilikethis.AddItem.arrayListOfdetailsOfItem;
import static com.example.kerem.ilikethis.LoginActivity.user;
import static com.example.kerem.ilikethis.ShowItem.bookItem;
import static com.example.kerem.ilikethis.ShowItem.getItemsID;
import static com.example.kerem.ilikethis.ShowItem.websiteItem;
import static com.example.kerem.ilikethis.ShowListActivity.listClicked;

public class EditItemWebsite extends AppCompatActivity {

    String nameofCategory = "Website";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_website);

        getSupportActionBar().setTitle("Add New Website");

        if (arrayListOfdetailsOfItem != null && arrayListOfdetailsOfItem.size() != 0) {
            EditText titleEditText = (EditText) findViewById(R.id.websiteTitle);
            titleEditText.setText(arrayListOfdetailsOfItem.get(1));

            EditText useOfSiteEditText = (EditText) findViewById(R.id.websiteUseOfSite);
            useOfSiteEditText.setText(arrayListOfdetailsOfItem.get(2));

        } else if (websiteItem != null) {

            getSupportActionBar().setTitle("Edit Your Website");

            EditText getContentsEditText;

            ((EditText) findViewById(R.id.websiteTitle)).setText(websiteItem.getTitle() + "");
            getContentsEditText = (EditText) findViewById(R.id.websiteUseOfSite);
            getContentsEditText.setText(websiteItem.getUseOfSite() + "");
            getContentsEditText = (EditText) findViewById(R.id.websiteAdditionalNotes);
            getContentsEditText.setText(websiteItem.getAdditionalNotes() + "");

        }
    }

    public void saveItem(View view) {
        Website websiteToAdd;

        EditText titleEditText = (EditText) findViewById(R.id.websiteTitle);
        EditText useOfSiteEditText = (EditText) findViewById(R.id.websiteUseOfSite);
        EditText additionalNotesEditText = (EditText) findViewById(R.id.websiteAdditionalNotes);

        if (websiteItem == null) {
            websiteToAdd = new Website(titleEditText.getText().toString(),
                    useOfSiteEditText.getText().toString());

            if (DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()) == null || DatabaseConnection.searchCategory(nameofCategory, titleEditText.getText().toString()).size() == 0)
                DatabaseConnection.addItemToDb(websiteToAdd);

            websiteToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());

            user.addItem(user.getCategories().get(user.LIST_WEBSITES).get(listClicked.getId() - 1), websiteToAdd);
        } else {

            websiteToAdd = (Website) user.getCategories().get(user.LIST_WEBSITES).get(listClicked.getId() - 1).getItem(getItemsID);

            websiteToAdd.setTitle(titleEditText.getText().toString());
            websiteToAdd.setUseOfSite(useOfSiteEditText.getText().toString());
            websiteToAdd.setAdditionalNotes(additionalNotesEditText.getText().toString());
        }
        startActivity(new Intent(this, ShowItem.class));
    }
}
