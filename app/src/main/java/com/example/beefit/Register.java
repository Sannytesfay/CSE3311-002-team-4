package com.example.beefit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText name,email,password,age;
    Button Register;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        name = findViewById(R.id.addName);
        email = findViewById(R.id.addEmail);
        password = findViewById(R.id.addPass);
        age = findViewById(R.id.addAge);
        Register = findViewById(R.id.registerbttn);

        ref = FirebaseDatabase.getInstance().getReference().child("Customers");


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomers();
                Toast.makeText(Register.this,"Successfully added user",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addCustomers(){

        String getname = name.getText().toString();
        String getemail = email.getText().toString();
        String getPass = password.getText().toString();
        String getage = age.getText().toString();

        Customers customers = new Customers(getname,getemail,getPass,getage);

        ref.push().setValue(customers);

    }


}