package com.example.jobbn.bedrifter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.jobbn.MainActivity;
import com.example.jobbn.R;
import com.example.jobbn.SplashActivity;

import java.util.ArrayList;
import java.util.Collection;

public class LoadingActivity extends AppCompatActivity {

    public static int SPLASH_TIME_OUT = 5000;
    private ArrayList<cards> nameList;
    private String comingFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        if (getIntent() != null) {
            nameList = (ArrayList<cards>) getIntent().getSerializableExtra("keyValue");
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, BedriftMatchResultatActivity.class);
                intent.putExtra("keyValue", nameList);
                startActivity(intent);
                finish();

            }
        }, SPLASH_TIME_OUT);


    }
}
