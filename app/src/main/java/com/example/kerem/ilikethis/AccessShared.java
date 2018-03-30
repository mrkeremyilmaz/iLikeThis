package com.example.kerem.ilikethis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cs102.group1c.ilikethis.model.User;
import cs102.group1c.ilikethis.model.XMLParser;

import static com.example.kerem.ilikethis.LoginActivity.appPath;


public class AccessShared extends AppCompatActivity {
    protected static User sharedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_shared);

        getSupportActionBar().setTitle("Access Shared");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.accessShared);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.stringXml);
                XMLParser parserNew = new XMLParser(appPath);
                String name = parserNew.sharedUsername(text.getText().toString());
                parserNew.appendShared(text.getText().toString());
                try {
                    System.out.println("useruseruser: " + (XMLParser.readFile(appPath + "/")));
                } catch (Exception e) {
                }
                sharedUser = parserNew.getShared(parserNew.sharedUsername(text.getText().toString()));
                parserNew.toArrayList();

                System.out.println("000000" + sharedUser.getCategories().get(0));


                startActivity(new Intent(AccessShared.this, ShowSharedActivity.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
