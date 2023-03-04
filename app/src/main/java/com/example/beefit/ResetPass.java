package com.example.beefit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ResetPass extends AppCompatActivity {

    EditText resetemail;
    Button resetbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        resetemail = (EditText) findViewById(R.id.resetEmail);
        resetbutton = (Button) findViewById(R.id.resetButton);

    }
}