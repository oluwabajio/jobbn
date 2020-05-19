package com.example.jobbn.bedrifter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.jobbn.ArrayAdapterClass;
import com.example.jobbn.utils.MyApplication;
import com.example.jobbn.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.ArrayList;

public class BedriftMatchResultatActivity extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private ArrayList<cards> nameList;
    private ArrayList<String> keyList = new ArrayList<>();
    private ArrayAdapterClass arrayAdapter;
    private int i = 0;
    private DatabaseReference mDatabase;
    private String uid;
    SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedrift_match_resultat);


        initData();
        getIntentExtras();
        initViews();
        initListeners();
        flingContainer.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

    }

    private void initListeners() {
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));


        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                Log.e("EEE", "eee" + count);
                if (i < keyList.size())
                    mDatabase.child("Studenter").child(keyList.get(i)).child("connections").child(uid).setValue(false);
                i++;
            }
        });

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                nameList.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {

                cards obj = (cards) dataObject;
                String userId = obj.getUserId();
                mDatabase.child("Studenter").child(userId).child("connections").child(uid).setValue(false);
                Toast.makeText(BedriftMatchResultatActivity.this, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                cards obj = (cards) dataObject;
                String userId = obj.getUserId();
                mDatabase.child("Studenter").child(userId).child("connections").child(uid).setValue(true);
                mDatabase.child("Studenter").child(userId).child("connections").child("yeps").child(uid).setValue(true);
                Toast.makeText(BedriftMatchResultatActivity.this, "Right", Toast.LENGTH_SHORT).show();

                isConnectionMatch(userId);
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(BedriftMatchResultatActivity.this, "Item Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initViews() {
        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        mSwipeView = (SwipePlaceHolderView) findViewById(R.id.swipeView);
    }

    private void initData() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        uid = MyApplication.getInstance().getSharedPreferences().getString("UID", "");
        mContext = getApplicationContext();
    }

    private void getIntentExtras() {
        Intent intent = getIntent();
        if (intent != null) {
            nameList = (ArrayList<cards>) intent.getSerializableExtra("keyValue");

        }
        arrayAdapter = new ArrayAdapterClass(this, R.layout.tinder_card_view, nameList);

    }

    private void isConnectionMatch(String userId) {
        DatabaseReference currentUserConnectionsDb = mDatabase.child("Bedrifter").child(uid).child("connections").child("yeps").child(userId);
        Log.e("TAGG", "usersid = " + userId + " currentuid " + uid);
        currentUserConnectionsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(BedriftMatchResultatActivity.this, "new Connection", Toast.LENGTH_LONG).show();

                    String key = FirebaseDatabase.getInstance().getReference().child("Chat").push().getKey();

                    mDatabase.child("Studenter").child(dataSnapshot.getKey()).child("connections").child("matches").child(uid).child("ChatId").setValue(key);
                    mDatabase.child("Bedrifter").child(uid).child("connections").child("matches").child(dataSnapshot.getKey()).child("ChatId").setValue(key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
