package com.example.jobbn.bedrifter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jobbn.utils.MyApplication;
import com.example.jobbn.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BedriftMatchActivity extends AppCompatActivity {

    Button matchKnapp;
    EditText matchKriterier;
    DatabaseReference mDbref, mkompetanseref;
    String string;
    private ArrayList<cards> nameList = new ArrayList<>();
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedrift_match);

        matchKnapp = (Button) findViewById(R.id.match_knapp);
        matchKriterier = (EditText) findViewById(R.id.input_kriterier);
        uid = MyApplication.getInstance().getSharedPreferences().getString("UID", "");


        matchKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbref = FirebaseDatabase.getInstance().getReference();
                mkompetanseref = mDbref.child("Studenter");
                string = matchKriterier.getText().toString();
                Query query = mkompetanseref.orderByChild("kompetanse");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (nameList.size() > 0)
                                nameList.clear();
                            // dataSnapshot for å hente data fra firebase
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                if (data.child("kompetanse").getValue(String.class).toLowerCase().contains(string.toLowerCase())) {
                                    if (data.child("connections").hasChild(uid)) {
                                        if (data.child("connections").child(uid).getValue(Boolean.class)) {
                                            cards item = new cards(data.getKey(), data.child("navn").getValue(String.class));
                                            nameList.add(item);
                                        }
                                    } else {
                                        cards item = new cards(data.getKey(), data.child("navn").getValue(String.class));
                                        nameList.add(item);
                                    }
                                    Log.e("ggg", data.child("navn").getValue(String.class) + "key is "+data.getKey());

                                }
                            }
                            if (nameList.size() > 0) {
                                Intent intent = new Intent(BedriftMatchActivity.this, LoadingActivity.class);
                                intent.putExtra("keyValue", nameList);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("TAG", databaseError.getMessage());

                    }
                });


            }


        });
    }
}
