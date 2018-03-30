package com.example.kerem.ilikethis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import cs102.group1c.ilikethis.model.DatabaseConnection;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        getSupportActionBar().setTitle("Sign up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button signUpButton = (Button) findViewById(R.id.button_signUp_signUp);
        final EditText in_username = (EditText) findViewById(R.id.input_userName_signUp);
        final EditText in_pwd = (EditText) findViewById(R.id.input_password_signUp);
        final EditText in_pwd2 = (EditText) findViewById(R.id.input_password_signUp_check);
        final EditText in_email = (EditText) findViewById(R.id.input_email_signUp);
        final TextView info_signup = (TextView) findViewById(R.id.info_signup);
        CheckBox box_showPwd = (CheckBox) findViewById(R.id.showPassword);


        box_showPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    in_pwd.setTransformationMethod(new PasswordTransformationMethod());
                    in_pwd2.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    in_pwd.setTransformationMethod(null);
                    in_pwd2.setTransformationMethod(null);
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (in_username.getText().toString().length() < 4)
                    info_signup.setText("Min user name length is 4!\n");
                if (!in_pwd.getText().toString().equals(in_pwd2.getText().toString()))
                    info_signup.append("Passwords are not the same!\n");
                if (in_pwd.getText().toString().length() < 8)
                    info_signup.append("Min password length is 8!\n");
                if (in_email.getText().toString().length() == 0 || in_email.getText().toString().indexOf('@') == -1)
                    info_signup.append("Enter a valid email!\n");

                if (in_username.getText().toString().length() >= 4
                        && in_pwd.getText().toString().equals(in_pwd2.getText().toString())
                        && in_pwd.getText().toString().length() >= 8) {
                    int result = DatabaseConnection.addUser(in_username.getText().toString(), in_pwd.getText().toString(), in_email.getText().toString());

                    if (result == -1)
                        info_signup.setText("Please try again later! (-1)");
                    else if (result == -2)
                        info_signup.setText("Please try again later! (-2)");
                    else if (result == 0)
                        info_signup.setText("Successful!");
                    else if (result == 1)
                        info_signup.setText("Username exists, try another username!");
                    else if (result == 2)
                        info_signup.setText("Email is registered, try another email!");
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
