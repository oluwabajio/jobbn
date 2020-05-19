package com.example.jobbn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jobbn.bedrifter.BedriftHomeActivity;
import com.example.jobbn.studenter.StudentHomeActivity;
import com.example.jobbn.utils.MyApplication;
import com.example.jobbn.utils.SessionManager;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 1337;
    List<AuthUI.IdpConfig> providers;
    private Button btn_sign_out, btn_student, btn_bedrift;
    SessionManager sessionManager;
    //private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager= new SessionManager();

        //mDatabase = FirebaseDatabase.getInstance().getReference();

        btn_student = (Button) findViewById(R.id.student_knapp);
        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mDatabase.child("Navn").setValue("Ali");
                Intent intent = new Intent(MainActivity.this, StudentHomeActivity.class);
                startActivity(intent);


            }
        });

        btn_bedrift = (Button) findViewById(R.id.bedrift_knapp);
        btn_bedrift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mDatabase.child("Name").setValue("Ikea");
                Intent intent = new Intent(MainActivity.this, BedriftHomeActivity.class);
                startActivity(intent);


            }
        });

        btn_sign_out = (Button) findViewById(R.id.btn_sign_out);
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //logg av
                AuthUI.getInstance()
                        .signOut(MainActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                btn_sign_out.setEnabled(false);
                                showSignInOptions();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,""+e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        //alle providers

        providers= Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(), //Epost builder
                new AuthUI.IdpConfig.GoogleBuilder().build(), //gmail builder
                new AuthUI.IdpConfig.FacebookBuilder().build() //Facebook builder
        );

        showSignInOptions();
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.MyTheme)
                .build(), MY_REQUEST_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== MY_REQUEST_CODE)
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode== RESULT_OK)
            {
                //get user
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                MyApplication.getInstance().getSharedPreferences().edit().putString("UID", user.getUid()).apply();
                Toast.makeText(this,""+user.getEmail() ,Toast.LENGTH_LONG).show();
                sessionManager.setEmail(user.getEmail());
                sessionManager.setDisplayName(user.getDisplayName());
                sessionManager.setUuid(user.getUid());


                if (user != null) {
                    btn_sign_out.setEnabled(true);
                    Intent intent = new Intent(MainActivity.this, BedriftHomeActivity.class);
                    startActivity(intent);


                }else{
                    btn_sign_out.setEnabled(true);
                    btn_student.setEnabled(true);
                    btn_bedrift.setEnabled(true);
                }

            }
            else
                {
                    Toast.makeText(this,"jj"+response.getError().getMessage(), Toast.LENGTH_LONG).show();
                }

        }
    }
}
