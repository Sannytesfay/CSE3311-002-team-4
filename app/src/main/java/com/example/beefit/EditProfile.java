package com.example.beefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {
    EditText Editfname, Editusername, Editemail;
    String[] levelsarr = {"Beginner", "Intermediate", "Gym Rat"};
    AutoCompleteTextView Editlevel;
    ArrayAdapter<String> adapterItems_levels;

    Button saveButton;
    String fullname, username, email, level;

    DatabaseReference rref;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user  = mAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        rref = FirebaseDatabase.getInstance().getReference("Users");

        Editfname = findViewById(R.id.Eprof_name);
        Editusername = findViewById(R.id.Eprof_username);
        Editemail = findViewById(R.id.Eprof_email);
        Editlevel = findViewById(R.id.Eprof_level);
        saveButton = findViewById(R.id.SaveButton);

        //DropDown
        adapterItems_levels =  new ArrayAdapter<String>(this, R.layout.list_item, levelsarr);
        Editlevel.setAdapter(adapterItems_levels);

        showData();
       saveButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(isNameChanged()||isUserNameChanged()||isLevelChanged()||isEmailChanged()){
                   Toast.makeText(EditProfile.this, "Saved", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(EditProfile.this,Profile.class);
                   startActivity(intent);
               }
               else {
                   Toast.makeText(EditProfile.this, "No changes found", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(EditProfile.this,Profile.class);
                   startActivity(intent);
               }
           }
       });
    }

    //Checking if data changed
    public boolean isNameChanged() {
        if (user != null) {
            String uid = user.getUid();
            if (!fullname.equals(Editfname.getText().toString())) {
                rref.child(uid).child("fullName").setValue(Editfname.getText().toString());
                fullname = Editfname.getText().toString();

                return true;
            } else {
                return false;
            }
        }
        else{
            Toast.makeText(EditProfile.this,"User not logged in",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean isUserNameChanged() {
        if (user != null) {
            String uid = user.getUid();
            if (!username.equals(Editusername.getText().toString())) {
                rref.child(uid).child("userName").setValue(Editusername.getText().toString());
                username = Editusername.getText().toString();

                return true;
            } else {
                return false;
            }
        }
        else{
            Toast.makeText(EditProfile.this,"User not logged in",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean isLevelChanged() {
        if (user != null) {
            String uid = user.getUid();
            if (!level.equals(Editlevel.getText().toString())) {
                rref.child(uid).child("Level").setValue(Editlevel.getText().toString());
                fullname = Editfname.getText().toString();

                return true;
            } else {
                return false;
            }
        }
        else{
            Toast.makeText(EditProfile.this,"User not logged in",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean isEmailChanged(){
        if (user != null) {
            String uid = user.getUid();
            if (!email.equals(Editemail.getText().toString())) {
                rref.child(uid).child("email").setValue(Editemail.getText().toString());
                email = Editemail.getText().toString();

                return true;
            } else {
                return false;
            }
        }
        else{
            Toast.makeText(EditProfile.this,"User not logged in",Toast.LENGTH_LONG).show();
            return false;
        }
    }
    //Show Data in Edit Text Fields
    public void showData(){
        Intent intent = getIntent();

        fullname = intent.getStringExtra("name");
        username = intent.getStringExtra("userName");
        level = intent.getStringExtra("Level");
        email = intent.getStringExtra("email");

        Editfname.setText(fullname);
        Editusername.setText(username);
        Editlevel.setText(level);
        Editemail.setText(email);
    }
}