package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLogInEmail,edtLogInPassword;
    private Button btnLogInActivity,btnSignUpLogInActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");

        edtLogInEmail = findViewById(R.id.edtLogInEmail);
        edtLogInPassword = findViewById(R.id.edtLogInPassword);
        edtLogInPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnLogInActivity);
                }
                return false;
            }
        });

        btnLogInActivity = findViewById(R.id.btnLogInActivity);
        btnSignUpLogInActivity = findViewById(R.id.btnSignUpLogInActivity);

        btnLogInActivity.setOnClickListener(LoginActivity.this);
        btnSignUpLogInActivity.setOnClickListener(LoginActivity.this);

        if (ParseUser.getCurrentUser() != null){
            transitionToSocialMediaActivity();
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLogInActivity:

                ParseUser.logInInBackground(edtLogInEmail.getText().toString(), edtLogInPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null && user != null){
                            FancyToast.makeText(LoginActivity.this,user.getUsername() + " is Logged In", Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            transitionToSocialMediaActivity();

                        }
                        else {
                            FancyToast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });

                break;
            case R.id.btnSignUpLogInActivity:

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

                break;
        }

    }

    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transitionToSocialMediaActivity(){

        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}