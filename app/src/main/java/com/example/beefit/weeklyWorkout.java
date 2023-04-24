package com.example.beefit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class weeklyWorkout extends AppCompatActivity implements View.OnClickListener {

    TextView day_1, day_3,day_2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_workout);



        day_1 = (TextView) findViewById(R.id.day1);
        day_1.setOnClickListener(this);
        day_3 = (TextView) findViewById(R.id.day3);
        day_3.setOnClickListener(this);
        day_2 = (TextView) findViewById(R.id.day2);
        day_2.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.day1:
                startActivity(new Intent(this,workoutDemo.class));
                break;

            case R.id.day3:
                startActivity(new Intent(this,workoutDemoPull.class));
                break;
            case R.id.day2:
                startActivity(new Intent(this,workoutDemoLeg.class));
                break;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}