package com.example.beefit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    ImageView weeklyWorkout, menuButton;
    ImageButton stopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        weeklyWorkout = (ImageView) findViewById(R.id.orangebutton);
        weeklyWorkout.setOnClickListener(this);
        menuButton = findViewById(R.id.menubutton);
    //Menu Button
       menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Profile.class);
                startActivity(intent);
            }
        });

        stopwatch = (ImageButton) findViewById(R.id.Stopwatch_button);
        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Stopwatch.class);
                startActivity(intent);
            }
        });


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orangebutton:
                startActivity(new Intent(this,weeklyWorkout.class));
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}