package com.example.beefit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button SignUp, Login;
    EditText Email, Pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //hides the ugly action bar that appears on top of the app.
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignUp = (Button) findViewById(R.id.signupbutton);
        Login = (Button) findViewById(R.id.LoginButton);
        Email = (EditText) findViewById(R.id.enterEmail);
        Pass = (EditText) findViewById(R.id.getPassword);


    }
}