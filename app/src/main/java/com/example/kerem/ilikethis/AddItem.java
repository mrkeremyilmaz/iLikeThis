package com.example.kerem.ilikethis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cs102.group1c.ilikethis.model.DatabaseConnection;
import cs102.group1c.ilikethis.model.Item;

import static com.example.kerem.ilikethis.ShowItem.bookItem;
import static com.example.kerem.ilikethis.ShowItem.movieItem;
import static com.example.kerem.ilikethis.ShowItem.musicItem;
import static com.example.kerem.ilikethis.ShowItem.noteItem;
import static com.example.kerem.ilikethis.ShowItem.websiteItem;
import static com.example.kerem.ilikethis.ShowItem.videogameItem;
import static com.example.kerem.ilikethis.ShowItem.tvShowItem;
import static com.example.kerem.ilikethis.ShowItem.placeItem;


public class AddItem extends AppCompatActivity {//implements SearchView.OnQueryTextListener{

    ListView listView;
    SearchView searchView;
    List<String> titles;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<ArrayList<String>> titleAndIDs;
    protected static String categoryName;
    protected static ArrayList<String> arrayListOfdetailsOfItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        getSupportActionBar().setTitle("Add Item");

        listView = (ListView) findViewById(R.id.myListView);
        searchView = (SearchView) findViewById(R.id.addSearch);

        titles = new ArrayList<String>();

        Button categoryNameButton = (Button) HomePage.currentCategory;
        categoryName = categoryNameButton.getText().toString();

        titleAndIDs = new ArrayList<ArrayList<String>>();
        arrayListOfdetailsOfItem = new ArrayList<String>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                if (categoryName.equals("Book")) {
                    i = new Intent(AddItem.this, EditItemBook.class);
                    arrayListOfdetailsOfItem = DatabaseConnection.getItemInfo("Book", titleAndIDs.get(position).get(1));

                } else if (categoryName.equals("Website")) {
                    i = new Intent(AddItem.this, EditItemWebsite.class);
                    arrayListOfdetailsOfItem = DatabaseConnection.getItemInfo("Website", titleAndIDs.get(position).get(1));
                } else if (categoryName.equals("Movie")) {
                    i = new Intent(AddItem.this, EditItemMovie.class);
                    arrayListOfdetailsOfItem = DatabaseConnection.getItemInfo("Movie", titleAndIDs.get(position).get(1));
                } else if (categoryName.equals("TV Show")) {
                    i = new Intent(AddItem.this, EditItemTVShow.class);
                    arrayListOfdetailsOfItem = DatabaseConnection.getItemInfo("TVShow", titleAndIDs.get(position).get(1));
                } else if (categoryName.equals("Video Game")) {
                    i = new Intent(AddItem.this, EditItemVideoGame.class);
                    arrayListOfdetailsOfItem = DatabaseConnection.getItemInfo("VideoGame", titleAndIDs.get(position).get(1));
                } else if (categoryName.equals("Music")) {
                    i = new Intent(AddItem.this, EditItemMusic.class);
                    arrayListOfdetailsOfItem = DatabaseConnection.getItemInfo("Music", titleAndIDs.get(position).get(1));
                } else if (categoryName.equals("Place")) {
                    i = new Intent(AddItem.this, EditItemPlace.class);
                    arrayListOfdetailsOfItem = DatabaseConnection.getItemInfo("Place", titleAndIDs.get(position).get(1));
                } else {
                    i = new Intent(AddItem.this, EditItemNote.class);
                    arrayListOfdetailsOfItem = DatabaseConnection.getItemInfo("Note", titleAndIDs.get(position).get(1));
                }
                System.out.println("aaabbb arrayListdetailsOfItem" + arrayListOfdetailsOfItem);
                startActivity(i);
            }
        });
    }


    public void onClickSearch(View view) {
        titleAndIDs = new ArrayList<ArrayList<String>>();


        SearchView simpleSearchView = (SearchView) findViewById(R.id.addSearch); // inititate a search view
        CharSequence query = simpleSearchView.getQuery(); // get the query string currently in the text field
        String newText = (String) query.toString();

        if (categoryName.equals("Video Game"))
            titleAndIDs = DatabaseConnection.searchCategory("VideoGame", newText);

        else if (categoryName.equals("TV Show"))
            titleAndIDs = DatabaseConnection.searchCategory("TVShow", newText);
        else
            titleAndIDs = DatabaseConnection.searchCategory(categoryName, newText);



        titles = DatabaseConnection.getSearchTitles(titleAndIDs);

        System.out.println("aaabbb titleAndIDS" + titleAndIDs);
        System.out.println("aaabbb titles" + titles);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(arrayAdapter);
    }

    public void addItemManually(View view) {

        Intent i;
        arrayListOfdetailsOfItem = null;

        if (categoryName.equals("Book")) {
            i = new Intent(AddItem.this, EditItemBook.class);
            bookItem = null;
        } else if (categoryName.equals("Website")) {
            i = new Intent(AddItem.this, EditItemWebsite.class);
            websiteItem = null;
        } else if (categoryName.equals("Movie")) {
            i = new Intent(AddItem.this, EditItemMovie.class);
            movieItem = null;
        } else if (categoryName.equals("TV Show")) {
            i = new Intent(AddItem.this, EditItemTVShow.class);
            tvShowItem = null;
        } else if (categoryName.equals("Video Game")) {
            i = new Intent(AddItem.this, EditItemVideoGame.class);
            videogameItem = null;
        } else if (categoryName.equals("Music")) {
            i = new Intent(AddItem.this, EditItemMusic.class);
            musicItem = null;
        } else if (categoryName.equals("Place")) {
            i = new Intent(AddItem.this, EditItemPlace.class);
            placeItem = null;
        } else {
            i = new Intent(AddItem.this, EditItemNote.class);
            noteItem = null;
        }
        startActivity(i);
    }
}
