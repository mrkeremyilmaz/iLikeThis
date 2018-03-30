package com.example.kerem.ilikethis;


import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ScrollView;

import static com.example.kerem.ilikethis.LoginActivity.user;

public class ShareItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ScrollView scrollView = new ScrollView(this);
        GridLayout shareItemLayout = new GridLayout(this);
        shareItemLayout.setColumnCount(2);
        Button itemButton;
        CheckBox itemBox;

        for (int i = 0; i < user.getCategories().get(ShareActivity.currentCategoryIndex).get(ShareListActivity.currentListIndex).size(); i++) //user.getCategories().get(ShareActivity.currentCategoryIndex).get(ShareListActivity.currentListIndex).size()
        {
            itemButton = new Button(this);
            itemButton.setText(user.getCategories().get(ShareActivity.currentCategoryIndex).get(ShareListActivity.currentListIndex).getItem(i).getTitle()); //user.getCategories().get(ShareActivity.currentCategoryIndex).get(ShareListActivity).getTitle()
            itemButton.setId(i);

            Resources r = getResources();
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250,
                    r.getDisplayMetrics());
            itemButton.setWidth(px);

            shareItemLayout.addView(itemButton);

            itemBox = new CheckBox(this);
            itemBox.setText("Choose");
            itemBox.setId(i + user.getCategories().get(ShareActivity.currentCategoryIndex).get(ShareListActivity.currentListIndex).size()); //the number in for loop

            final int value = i;
            itemBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        user.getCategories().get(ShareActivity.currentCategoryIndex).get(ShareListActivity.currentListIndex).setSelected(true);
                        ((Button) findViewById(value)).setEnabled(false);
                    } else {
                        user.getCategories().get(ShareActivity.currentCategoryIndex).get(ShareListActivity.currentListIndex).setSelected(false);
                        ((Button) findViewById(value)).setEnabled(true);
                    }

                }
            });


            itemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            shareItemLayout.addView(itemBox);
        }

        scrollView.addView(shareItemLayout);
        setContentView(scrollView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
