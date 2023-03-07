package com.example.beefit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

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