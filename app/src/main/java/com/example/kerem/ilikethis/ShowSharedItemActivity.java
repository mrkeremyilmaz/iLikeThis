package com.example.kerem.ilikethis;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;

import static com.example.kerem.ilikethis.AccessShared.sharedUser;
import static com.example.kerem.ilikethis.LoginActivity.user;

public class ShowSharedItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ScrollView scrollView = new ScrollView(this);
        GridLayout shareItemLayout = new GridLayout(this);
        shareItemLayout.setColumnCount(1);
        Button itemButton;

        for (int i = 0; i < sharedUser.getCategories().get(ShowSharedActivity.sharedCurrentCategoryIndex).get(ShowSharedListActivity.sharedCurrentListIndex).size(); i++) //user.getCategories().get(ShareActivity.currentCategoryIndex).get(ShareListActivity.currentListIndex).size()
        {
            itemButton = new Button(this);
            itemButton.setText(sharedUser.getCategories().get(ShowSharedActivity.sharedCurrentCategoryIndex).get(ShowSharedListActivity.sharedCurrentListIndex).getItem(i).getTitle()); //user.getCategories().get(ShareActivity.currentCategoryIndex).get(ShareListActivity).getTitle()
            itemButton.setId(i);

            Resources r = getResources();
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 350,
                    r.getDisplayMetrics());
            itemButton.setWidth(px);

            shareItemLayout.addView(itemButton);


            final int value = i;

            itemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // startActivity( new Intent(ShowSharedItemActivity.this, EditItemBook.class));
                }
            });
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
