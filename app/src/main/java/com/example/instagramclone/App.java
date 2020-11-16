package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("NH5csWHI0UhXdW9GsUiUGaEvdpIILqxBTHcoymnw")
                // if desired
                .clientKey("pXRfKhvZEZguvLA4qj9fniDVIZ3Jfv29CosOOPPV")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
