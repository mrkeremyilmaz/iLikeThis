package com.example.kerem.ilikethis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.kerem.ilikethis.LoginActivity.user;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        getSupportActionBar().setTitle("Contact Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button sendMessage = (Button) findViewById(R.id.sendMessageToDevsButton);
        final EditText message = (EditText) findViewById(R.id.messageContactUs);
        final TextView showSuccess = (TextView) findViewById(R.id.showSuccessContactUs);


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.sendEmailToDevs(message.getText().toString());
                showSuccess.setText("Message successfully sent!");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
