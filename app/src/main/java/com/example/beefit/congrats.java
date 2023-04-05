package com.example.beefit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.droidsonroids.gif.GifImageView;

public class congrats extends AppCompatActivity implements View.OnClickListener{


    AppCompatButton home;
    GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        home = findViewById(R.id.gohome);
        home.setOnClickListener(this);

        gifImageView = findViewById(R.id.workout_gif);
        gifImageView.setImageResource(R.drawable.donegif);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.gohome:
                startActivity(new Intent(this,MainPage.class));
                break;
        }

    }
}