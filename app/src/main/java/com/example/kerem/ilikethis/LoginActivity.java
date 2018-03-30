package com.example.kerem.ilikethis;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.File;

import cs102.group1c.ilikethis.model.Book;
import cs102.group1c.ilikethis.model.DatabaseConnection;
import cs102.group1c.ilikethis.model.List;
import cs102.group1c.ilikethis.model.Movie;
import cs102.group1c.ilikethis.model.Music;
import cs102.group1c.ilikethis.model.Place;
import cs102.group1c.ilikethis.model.TVShow;
import cs102.group1c.ilikethis.model.User;
import cs102.group1c.ilikethis.model.VideoGame;
import cs102.group1c.ilikethis.model.Website;
import cs102.group1c.ilikethis.model.XMLParser;


public class LoginActivity extends AppCompatActivity {
    protected static User user;
    protected static File xml;
    protected static String appPath;
    public static StrictMode.ThreadPolicy policy;
    protected static XMLParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        getSupportActionBar().setTitle("Log in");

        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(214, 10, 10)));

        final Button buttonSignUp = (Button) findViewById(R.id.button_signUp);
        final Button buttonSignIn = (Button) findViewById(R.id.button_signIn);
        Button buttonForgetPwd = (Button) findViewById(R.id.button_forgetPwd);
        final EditText inputUsername = (EditText) findViewById(R.id.input_userName);
        final EditText inputPassword = (EditText) findViewById(R.id.input_password);
        final TextView login_info = (TextView) findViewById(R.id.login_info);
        CheckBox box_showPwd = (CheckBox) findViewById(R.id.showPassword);


        box_showPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked)
                    inputPassword.setTransformationMethod(new PasswordTransformationMethod());
                else
                    inputPassword.setTransformationMethod(null);
            }
        });
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPath = getApplicationInfo().dataDir;
                System.out.println(appPath);
                try {
                    user = new User(inputUsername.getText().toString(), inputPassword.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                parser = new XMLParser(user, appPath);

                parser.toArrayList();
                xml = new File(appPath);

                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

                parser.toXml();

                if (user != null &&
                        user.getUsername() != null &&
                        user.getUsername().length() != 0) {
                    login_info.setText("Login Successful!");

                    startActivity(new Intent(LoginActivity.this, HomePage.class));
                } else
                    login_info.setText("Login failed!");
            }
        });


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        buttonForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });
        DatabaseConnection.connectToDatabase();
    }
}