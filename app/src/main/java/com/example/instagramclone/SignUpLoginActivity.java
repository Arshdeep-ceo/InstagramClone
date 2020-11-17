package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private Button btnSignUp,btnLogIn;
    private EditText edtUserNameSignUp,edtPasswordSignUp,edtUserNameLogIn,edtPasswordLogIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_login_activity);

        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtUserNameLogIn = findViewById(R.id.edtUserNameLogIn);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogin);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username") + " is SignUp successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            Intent intent = new Intent(SignUpLoginActivity.this,WelcomeActivity.class);
                            startActivity(intent);

                        }
                        else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });

            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUserNameLogIn.getText().toString(), edtPasswordLogIn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,user.get("username") + " is Logged In successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            Intent intent = new Intent(SignUpLoginActivity.this,WelcomeActivity.class);
                            startActivity(intent);

                        }
                        else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });
            }
        });

    }
}
