package com.example.beefit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GeneralQuestions extends AppCompatActivity {
    String[] level = {"Beginner", "Intermediate", "Gym Rat"};
    AutoCompleteTextView autoCompleteTextView_levels;
    ArrayAdapter<String> adapterItems_levels;

    String[] days = {"1-2 days per week", "3-4 days per week", "5-7 days per week"};
    AutoCompleteTextView autoCompleteTextView_days;
    ArrayAdapter<String> adapterItems_days;
    Button sButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_questions);

        //Levels Dropdown
        autoCompleteTextView_levels = findViewById(R.id.level_completeTextView);
        adapterItems_levels =  new ArrayAdapter<String>(this, R.layout.list_item, level);
        autoCompleteTextView_levels.setAdapter(adapterItems_levels);

        //Days DropDown
        autoCompleteTextView_days = findViewById(R.id.days_completeTextView);
        adapterItems_days =  new ArrayAdapter<String>(this, R.layout.list_item, days);
        autoCompleteTextView_days.setAdapter(adapterItems_days);

        //Save Info in Database
        autoCompleteTextView_levels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid).push();

                String key = databaseReference.getKey(); // Get the current unique key
                databaseReference.getParent().child(key).setValue(null); // Remove the current unique key
                databaseReference = databaseReference.getParent().child("Level"); // Set the new key
                databaseReference.setValue(selectedItem);
            }
        });

        autoCompleteTextView_days.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid).push();

                String key = databaseReference.getKey(); // Get the current unique key
                databaseReference.getParent().child(key).setValue(null); // Remove the current unique key
                databaseReference = databaseReference.getParent().child("Days"); // Set the new key
                databaseReference.setValue(selectedItem);
            }
        });

        //Get start button Onclick
        sButton = findViewById(R.id.StartButton);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.StartButton:
                        startActivity(new Intent(GeneralQuestions.this,MainPage.class));
                        break;
                }
            }
        });
    }


}