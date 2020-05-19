package com.example.jobbn.studenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobbn.R;
import com.example.jobbn.models.Student;
import com.example.jobbn.studenter.StudentProfilActivity;
import com.example.jobbn.utils.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentProfilActivity extends AppCompatActivity {

    private EditText mStudentNavn;
    private EditText mStudentEpost;
    private EditText mStudentAge;
    private EditText mStudentDescription;
    private EditText mStudentEmail;
    private Button mLagreStudent;
    private DatabaseReference mDatabase;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profil);
        sessionManager = new SessionManager();

        initViews();
        initListeners();
        getStudentDetails();
        populateEdittexts();

    }

    private void getStudentDetails() {
        String userId = sessionManager.getUuid();
        mDatabase .child("Studenter").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Student user = dataSnapshot.getValue(Student.class);

                if (user != null) {
                    sessionManager.setDisplayName(user.getNavn());
                    sessionManager.setAge(user.getAge());
                    sessionManager.setDescription(user.getKompetanse());
                    sessionManager.setEmail(user.getEmail());
                    Log.e("ggg", user.getEmail() + user.getKompetanse());
                    populateEdittexts();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void initListeners() {

        mLagreStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String navn = mStudentNavn.getText().toString();
                String email = mStudentEmail.getText().toString().trim();
                String age = mStudentAge.getText().toString();
                String descrtiption = mStudentDescription.getText().toString();

                addStudentToDb(navn, email, age, descrtiption);
            }
        });
    }

    private void initViews() {
        mStudentNavn = findViewById(R.id.student_navn);
        mStudentEmail = findViewById(R.id.student_email);
        mStudentAge = findViewById(R.id.student_age);
        mStudentDescription = findViewById(R.id.student_description);

        mLagreStudent = findViewById(R.id.lagre_student_knapp);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    private void addStudentToDb(String name, String email, String age, String description) {
        String userId = sessionManager.getUuid();
        Student user = new Student(name, email, age, description);

        mDatabase.child("Studenter")
                .child(userId).setValue(user);

    }

    private void populateEdittexts() {
        mStudentNavn.setText(sessionManager.getDisplayName());
        mStudentEmail.setText(sessionManager.getEmail());
        mStudentDescription.setText(sessionManager.getDescription());
        mStudentAge.setText(sessionManager.getAge());

    }
}
