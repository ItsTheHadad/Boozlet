package com.example.boozlet;

import android.app.Application;

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

    }
}

        //DBUtil.initDBUtil(this); // maybe per fragment?
        //wanted to put that in the oncreate if dbutil had context
