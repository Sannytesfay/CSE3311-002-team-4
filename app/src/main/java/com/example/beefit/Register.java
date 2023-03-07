package com.example.beefit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText name,email,password,age,username;
    //Button register;
    AppCompatButton register;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        name = (EditText) findViewById(R.id.addName);
        email = (EditText) findViewById(R.id.addEmail);
        password = (EditText) findViewById(R.id.addPass);
        age = (EditText) findViewById(R.id.addAge);

        register = (AppCompatButton) findViewById(R.id.registerbttn);
        register.setOnClickListener(this);

        username = (EditText) findViewById(R.id.Username);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerbttn:
                registerUser();
                break;
        }
    }

    private void registerUser() {

        String getname = name.getText().toString().trim();
        String getemail = email.getText().toString().trim();
        String getPass = password.getText().toString().trim();
        String getage = age.getText().toString().trim();
        String getUsername = username.getText().toString().trim();

        if (getUsername.isEmpty()) {
            username.setError("Username required!");
            username.requestFocus();
            return;
        }

        if (getname.isEmpty()) {
            name.setError("Full name is required!");
            name.requestFocus();
            return;
        }


        if (getemail.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(getemail).matches()) {
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }

        if (getPass.isEmpty()) {
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if (getPass.length() < 6) {
            password.setError("Minimum password length should be 6");
            password.requestFocus();
            return;
        }

        if (getage.isEmpty()) {
            age.setError("Age is required!");
            age.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(getemail,getPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(getname,getUsername,getage,getemail);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this,"You have successfully created an account!", Toast.LENGTH_LONG);
                                        startActivity(new Intent(Register.this,GeneralQuestions.class));

                                    }
                                    else {
                                        Toast.makeText(Register.this,"Failed to register! please try again!", Toast.LENGTH_LONG);
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(Register.this,"Failed to register! Please try again!", Toast.LENGTH_LONG);

                }
            }
        });



    }
}