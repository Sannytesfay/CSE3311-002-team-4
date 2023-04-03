package com.example.beefit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class workoutDemo extends AppCompatActivity {

    private VideoView workoutVideo;
    private TextView currentWorkoutTitle, currentWorkoutDescription;

    private DatabaseReference workoutRef;
    private StorageReference videoRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_demo);

        MediaController mediaController = new MediaController(this);

        workoutVideo = findViewById(R.id.workout_video);
        workoutVideo.setMediaController(mediaController);
        mediaController.setAnchorView(workoutVideo);

        currentWorkoutTitle = findViewById(R.id.current_workout_title);
        currentWorkoutDescription = findViewById(R.id.current_workout_description);

        workoutRef = FirebaseDatabase.getInstance().getReference("Workouts/Push/Workout_1");
        workoutRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String title = snapshot.child("Title").getValue(String.class);
                    String description = snapshot.child("Description").getValue(String.class);
                    String videoUrl = snapshot.child("Video").getValue(String.class);
                    //System.out.println(videoUrl);

                    // Display workout title and description on screen
                    currentWorkoutTitle.setText(title);
                    currentWorkoutDescription.setText(description);

                    Uri uri = Uri.parse(videoUrl);
                    workoutVideo.setVideoURI(uri);
                    workoutVideo.start();


                     //Get workout video from Firebase storage and display on screen
/*
                    videoRef = FirebaseStorage.getInstance().getReferenceFromUrl(videoUrl);

                    videoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //workoutVideo.setVideoURI(uri);
                            workoutVideo.setVideoPath("https://firebasestorage.googleapis.com/v0/b/beefit-database-93831.appspot.com/o/FC197F8E-2F6C-4B8E-B342-95EC0344DA04_2_0_a.mov?alt=media&token=1eea8df3-01cf-40f8-9757-611a3079ce9e");
                            workoutVideo.start();
                        }
                    });
*/



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(workoutDemo.this,"Failed to load video",Toast.LENGTH_LONG).show();
            }

        });
    }

}