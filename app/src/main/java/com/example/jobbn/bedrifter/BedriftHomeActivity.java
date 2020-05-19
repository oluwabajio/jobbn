package com.example.jobbn.bedrifter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jobbn.MainActivity;
import com.example.jobbn.R;
import com.example.jobbn.chat.MatchesListActivity;
import com.example.jobbn.studenter.StudentHomeActivity;

public class BedriftHomeActivity extends AppCompatActivity {

    private CardView hjem_bedrift, profil_bedrift, match_bedrift, cdMatchesBedrifter, meldinger_bedrift, innstillinger_bedrift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedrift_home);


        hjem_bedrift = (CardView) findViewById(R.id.hjem);
        hjem_bedrift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BedriftHomeActivity.this, BedriftHomeActivity.class);
                startActivity(intent);
            }
        });


        profil_bedrift = (CardView) findViewById(R.id.profil_bedrift);
        profil_bedrift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BedriftHomeActivity.this, BedriftProfilActivity.class);
                startActivity(intent);
            }
        });

        match_bedrift = (CardView) findViewById(R.id.match_bedrift);
        match_bedrift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BedriftHomeActivity.this, BedriftMatchActivity.class);
                startActivity(intent);
            }
        });

        cdMatchesBedrifter = findViewById(R.id.cd_matches_bedrifter);
        cdMatchesBedrifter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BedriftHomeActivity.this, MatchesListActivity.class);
                intent.putExtra("coming_from", "Bedrifter");
                startActivity(intent);
            }
        });


    }
}
