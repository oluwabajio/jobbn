package com.example.jobbn.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MyApplication extends Application {

    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static SharedPreferences getSharedPreferences(){
        return myApplication.getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
    }


    public static synchronized MyApplication getInstance() {
        return myApplication;
    }

}