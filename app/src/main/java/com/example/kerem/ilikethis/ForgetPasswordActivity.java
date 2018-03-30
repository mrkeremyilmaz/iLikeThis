package com.example.kerem.ilikethis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cs102.group1c.ilikethis.model.User;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);

        getSupportActionBar().setTitle("Forget Password");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText in_username = (EditText) findViewById(R.id.input_userName_forgetPwd);
        final Button button_forgetPwd = (Button) findViewById(R.id.button_sendPwd);
        final TextView sent_info = (TextView) findViewById(R.id.sent_info);
        button_forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String username = in_username.getText().toString();
                    System.out.println(username);
                    User.requestPassword(username);
                    sent_info.setText("Sent successfully!");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
