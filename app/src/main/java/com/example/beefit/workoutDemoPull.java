package com.example.beefit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class workoutDemoPull extends AppCompatActivity implements View.OnClickListener {

    //push day demo
    private static final String TAG = "workoutDemo";
    private VideoView workoutVideo;
    private TextView currentWorkoutTitle, currentWorkoutDescription,nextWorkoutTitle, nextWorkoutDescription;

    private DatabaseReference workoutRef;
    private DatabaseReference nextWorkout;




    //buttons
    AppCompatButton next,done;
    int workoutCounter;
    String levels;

    int workoutLev;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_demo_pull);
        workoutCounter = 1;


        getlevel();


        //setting up buttons
        done = (AppCompatButton) findViewById(R.id.doneButton);
        done.setOnClickListener(this);

        next = (AppCompatButton) findViewById(R.id.nextButton);
        next.setOnClickListener(this);

        //for current workout
        MediaController mediaController = new MediaController(this);

        workoutVideo = findViewById(R.id.workout_video);
        workoutVideo.setMediaController(mediaController);
        mediaController.setAnchorView(workoutVideo);

        currentWorkoutTitle = findViewById(R.id.current_workout_title);
        currentWorkoutDescription = findViewById(R.id.current_workout_description);

        nextWorkoutTitle = findViewById(R.id.next_workout_title);
        nextWorkoutDescription = findViewById(R.id.next_workout_description);

        workoutRef = FirebaseDatabase.getInstance().getReference("Workouts/Pull/Workout_1");
        workoutRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String title = snapshot.child("Title").getValue(String.class);
                    String description = snapshot.child("Description").getValue(String.class);
                    String videoUrl = snapshot.child("Video").getValue(String.class);

                    // Display workout title and description on screen
                    currentWorkoutTitle.setText(title);
                    currentWorkoutDescription.setText(description);

                    Uri uri = Uri.parse(videoUrl);
                    workoutVideo.setVideoURI(uri);
                    workoutVideo.start();

                    //next workout
                    workoutCounter++;
                    String workout = "Workouts/Pull/Workout_" + workoutCounter;
                    workoutCounter--;

                    workoutRef = FirebaseDatabase.getInstance().getReference(workout);
                    workoutRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                String nextTitle = snapshot.child("Title").getValue(String.class);
                                String nextDescription = snapshot.child("Description").getValue(String.class);

                                // Display workout title and description on screen
                                nextWorkoutTitle.setText(nextTitle);
                                nextWorkoutDescription.setText(nextDescription);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(workoutDemoPull.this,"Failed to load video",Toast.LENGTH_LONG).show();
                        }

                    });
                    //next workout

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(workoutDemoPull.this,"Failed to load video",Toast.LENGTH_LONG).show();
            }

        });
    }

    private void getlevel() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String workoutLevel = snapshot.child(uid).child("Level").getValue(String.class);
                        levels = (String) workoutLevel;
                        Log.d("workout level",levels);
                        if (levels.equals("Beginner")) {
                            workoutLev = 4;
                        }
                        else if(levels.equals("Intermediate")){
                            workoutLev = 5;
                        }
                        else if (levels.equals("Gym Rat")){
                            workoutLev = 7;
                        }
                        else{
                            workoutLev = 4;
                        }
                        Log.d("workout level","value = " + workoutLev);


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.nextButton:
                if (workoutCounter < workoutLev) {
                    workoutCounter++;
                    String workout = "Workouts/Pull/Workout_" + workoutCounter;
                    workoutRef = FirebaseDatabase.getInstance().getReference(workout);
                    workoutRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                String title = snapshot.child("Title").getValue(String.class);
                                String description = snapshot.child("Description").getValue(String.class);
                                String videoUrl = snapshot.child("Video").getValue(String.class);

                                // Display workout title and description on screen
                                currentWorkoutTitle.setText(title);
                                currentWorkoutDescription.setText(description);

                                Uri uri = Uri.parse(videoUrl);
                                workoutVideo.setVideoURI(uri);
                                workoutVideo.start();

                                //next workout
                                if (workoutCounter < workoutLev) {
                                    workoutCounter++;
                                    String workout = "Workouts/Pull/Workout_" + workoutCounter;
                                    workoutCounter--;
                                    workoutRef = FirebaseDatabase.getInstance().getReference(workout);
                                    workoutRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                String nextTitle = snapshot.child("Title").getValue(String.class);
                                                String nextDescription = snapshot.child("Description").getValue(String.class);

                                                // Display workout title and description on screen
                                                nextWorkoutTitle.setText(nextTitle);
                                                nextWorkoutDescription.setText(nextDescription);

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(workoutDemoPull.this,"Failed to load video",Toast.LENGTH_LONG).show();
                                        }

                                    });
                                }
                                else {
                                    nextWorkoutTitle.setText("");
                                    nextWorkoutDescription.setText("");
                                }
                                //next workout
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(workoutDemoPull.this,"Failed to load video",Toast.LENGTH_LONG).show();
                        }

                    });

                }else {
                    Toast.makeText(workoutDemoPull.this,"This ii the last workout for this program",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.doneButton:
                if(workoutCounter < workoutLev) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("You are not done with your workout. Do you wish to still end it?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(workoutDemoPull.this,congrats.class));
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Do nothing and dismiss the dialog
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
                else{
                    startActivity(new Intent(workoutDemoPull.this,congrats.class));
                }
        }

    }
}