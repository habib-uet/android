package com.example.habib.urrehman.classtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PersonDetails extends AppCompatActivity {
 TextView name,id,gender,status,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        email=findViewById(R.id.email);

        gender=findViewById(R.id.gender);
        status=findViewById(R.id.status);
        name.setText(getIntent().getStringExtra("personName"));
        id.setText("Id:"+getIntent().getIntExtra("personId",-1));
        gender.setText("Gender:"+getIntent().getStringExtra("personGender"));
        status.setText("Status:"+getIntent().getStringExtra("personStatus"));
        email.setText("Email:"+getIntent().getStringExtra("personEmail"));

    }
}