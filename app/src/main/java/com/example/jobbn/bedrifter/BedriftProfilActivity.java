package com.example.jobbn.bedrifter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobbn.R;
import com.example.jobbn.models.Bedrifter;
import com.example.jobbn.utils.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BedriftProfilActivity extends AppCompatActivity {

    private EditText mBedriftNavn;
    private EditText mBedriftEpost;
    private Button mLagreBedrift;
    private DatabaseReference mDatabase;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedrift_profil);

        initData();

        mBedriftNavn = (EditText) findViewById(R.id.bedrift_navn);
        mBedriftEpost = (EditText) findViewById(R.id.bedrift_epost);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mLagreBedrift = (Button) findViewById(R.id.lagre_bedrift_knapp);
        mLagreBedrift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String navn = mBedriftNavn.getText().toString().trim();
                String epost = mBedriftEpost.getText().toString().trim();




                Bedrifter bedrifter = new Bedrifter(navn, epost);
                FirebaseDatabase.getInstance().getReference().child("Bedrifter").child(sessionManager.getUuid()).setValue(bedrifter).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(BedriftProfilActivity.this, "Profil lagret", Toast.LENGTH_LONG).show();
                        }else {

                            Toast.makeText(BedriftProfilActivity.this, "Error, Profil ble ikke lagret", Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });


        populateEdittexts();
    }

    private void initData() {
        sessionManager = new SessionManager();
    }

    private void populateEdittexts() {
        mBedriftEpost.setText(sessionManager.getEmail());
        mBedriftNavn.setText(sessionManager.getDisplayName());

    }
}
