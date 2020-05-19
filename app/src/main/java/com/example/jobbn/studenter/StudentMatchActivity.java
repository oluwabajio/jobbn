package com.example.jobbn.studenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.jobbn.ArrayAdapter2;
import com.example.jobbn.Cards2;
import com.example.jobbn.R;
import com.example.jobbn.bedrifter.BedriftMatchResultatActivity;
import com.example.jobbn.utils.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class StudentMatchActivity extends AppCompatActivity {

    private ArrayList<Cards2> nameList;
    private ArrayAdapter2 arrayAdapter;
    private int i;
    String uid;
    SwipeFlingAdapterView flingContainer;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_match);


        flingContainer = findViewById(R.id.frame);
        uid = new SessionManager().getUuid();
       fetchCompanyList();


        nameList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter2(StudentMatchActivity.this,R.layout.tinder_card_view, nameList);
        flingContainer.setAdapter(arrayAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                nameList.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                //   makeToast(MyActivity.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Cards2 obj = (Cards2) dataObject;
                String userId = obj.getKey();
             //   mDatabase.child("Bedrifter").child(userId).child("connections").child(uid).setValue(true);
                mDatabase.child("Bedrifter").child(userId).child("connections").child("yeps").child(uid).setValue(true);
                Toast.makeText(StudentMatchActivity.this, "Right", Toast.LENGTH_SHORT).show();

                isConnectionMatch(userId);
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
//                al.add("XML ".concat(String.valueOf(i)));
//                arrayAdapter.notifyDataSetChanged();
//                Log.d("LIST", "notified");
//                i++;
            }

            @Override
            public void onScroll(float v) {

            }


        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                // makeToast(MyActivity.this, "Clicked!");
            }
        });






    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    private void fetchCompanyList() {
        mDatabase.child("Bedrifter").addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    // dataSnapshot for å hente data fra firebase
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Cards2 cards2 = new Cards2(data.getKey(), data.child("navn").getValue(String.class));

                        nameList.add(cards2);
                        Toast.makeText(StudentMatchActivity.this,  data.child("navn").getValue(String.class), Toast.LENGTH_LONG).show();
                        arrayAdapter.notifyDataSetChanged();

                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

    private void isConnectionMatch(String userId) {
        DatabaseReference currentUserConnectionsDb = mDatabase.child("Studenter").child(uid).child("connections").child("yeps").child(userId);
        Log.e("TAGG", "usersid = " + userId + " currentuid " + uid);
        currentUserConnectionsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(StudentMatchActivity.this, "new Connection", Toast.LENGTH_LONG).show();
                    String key = FirebaseDatabase.getInstance().getReference().child("Chat").push().getKey();

                    mDatabase.child("Bedrifter").child(dataSnapshot.getKey()).child("connections").child("matches").child(uid).child("ChatId").setValue(key);
                    mDatabase.child("Studenter").child(uid).child("connections").child("matches").child(dataSnapshot.getKey()).child("ChatId").setValue(key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }




}



