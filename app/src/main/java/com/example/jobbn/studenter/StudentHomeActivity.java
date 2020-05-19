package com.example.jobbn.studenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jobbn.R;
import com.example.jobbn.chat.MatchesListActivity;
import com.example.jobbn.studenter.StudentHomeActivity;

public class StudentHomeActivity extends AppCompatActivity {

    private CardView hjem_student,profil_student, match_student, cdMatchesStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        initViews();
        initListeners();

    }

    private void initListeners() {
        hjem_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomeActivity.this, StudentHomeActivity.class);
                startActivity(intent);
            }
        });


        profil_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomeActivity.this, StudentProfilActivity.class);
                startActivity(intent);
            }
        });

        match_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, StudentMatchActivity.class));
            }
        });

        cdMatchesStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomeActivity.this, MatchesListActivity.class);
                intent.putExtra("coming_from", "Student");
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        hjem_student = (CardView) findViewById(R.id.studenthjem);
        profil_student = (CardView) findViewById(R.id.profil_student);
        match_student = findViewById(R.id.match_student);
cdMatchesStudent = findViewById(R.id.cd_matches_student);

    }
}
