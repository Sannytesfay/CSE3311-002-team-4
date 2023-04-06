package com.example.beefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends AppCompatActivity {
    TextView nameText, usernameText, emailText, levelText;
    ImageView backButton;
    private Button logout, edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameText = findViewById(R.id.prof_fullname);
        usernameText = findViewById(R.id.prof_username);
        emailText = findViewById(R.id.prof_email);
        levelText = findViewById(R.id.prof_level);
        logout = findViewById(R.id.LogOutButton);
        edit = findViewById(R.id.EditButton);
        backButton = findViewById(R.id.backbutton);

        isUser();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainPage.class);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passUserData();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void isUser() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();

            //FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        String name = dataSnapshot.child(uid).child("fullName").getValue(String.class);
                        String username = dataSnapshot.child(uid).child("userName").getValue(String.class);
                        String gymLevel = dataSnapshot.child(uid).child("Level").getValue(String.class);
                        String email = dataSnapshot.child(uid).child("email").getValue(String.class);
                        // Display the name, email, username, and gym level in your user profile screen

                        nameText.setText(name);
                        usernameText.setText(username);
                        levelText.setText(gymLevel);
                        emailText.setText(email);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle the error
                }
            });
        }
        else{
            Toast.makeText(Profile.this,"User not logged in",Toast.LENGTH_LONG).show();
        }
    }

    public void passUserData() {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String name = dataSnapshot.child(uid).child("fullName").getValue(String.class);
                        String username = dataSnapshot.child(uid).child("userName").getValue(String.class);
                        String gymLevel = dataSnapshot.child(uid).child("Level").getValue(String.class);
                        String email = dataSnapshot.child(uid).child("email").getValue(String.class);

                        Intent intent = new Intent(Profile.this, EditProfile.class);
                        intent.putExtra("name", name);
                        intent.putExtra("userName", username);
                        intent.putExtra("Level", gymLevel);
                        intent.putExtra("email", email);

                        startActivity(intent);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        else{
            Toast.makeText(Profile.this,"User not logged in",Toast.LENGTH_LONG).show();
        }
    }
}