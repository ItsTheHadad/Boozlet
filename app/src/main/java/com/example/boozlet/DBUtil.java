package com.example.boozlet;

import android.content.Context;

public class DBUtil {

    private static DBUtil instance;

    private static Context appContext; // necessary in a fragments?

    private DBUtil(Context context){this.appContext = context;}

    private static DBUtil getInstance() {return instance;}

    public static DBUtil initDBUtil( Context context){
        if (instance == null)
            instance = new DBUtil(context);
        return instance;
    }




}
