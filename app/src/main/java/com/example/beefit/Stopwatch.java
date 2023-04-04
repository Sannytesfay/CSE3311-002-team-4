package com.example.beefit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class Stopwatch extends AppCompatActivity {

    private Chronometer chronometer;
    private long Pause;
    private boolean countdown_active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);


        chronometer = findViewById(R.id.chronometer);

    }

    public void startChronometer(View v) {
        if (!countdown_active) {
            chronometer.setBase(SystemClock.elapsedRealtime() - Pause);
            chronometer.start();
            countdown_active = true;
            Toast.makeText(this, "Started Stopwatch!", Toast.LENGTH_SHORT).show();
        }
    }

    public void pauseChronometer(View v) {
        if (countdown_active) {
            chronometer.stop();
            Pause = SystemClock.elapsedRealtime() - chronometer.getBase();
            countdown_active = false;
            Toast.makeText(this, "Paused Stopwatch!", Toast.LENGTH_SHORT).show();

        }
    }

    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());     //resets back to 0
        Pause = 0;
        Toast.makeText(this, "Reset Stopwatch!", Toast.LENGTH_SHORT).show();
    }

}