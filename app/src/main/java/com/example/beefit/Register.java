package com.example.beefit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText name,email,password,age,user;
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
        user = findViewById(R.id.Username);

        ref = FirebaseDatabase.getInstance().getReference().child("Customers");


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // changing the TextEdit values to a string
                String getname = name.getText().toString();
                String getemail = email.getText().toString();
                String getPass = password.getText().toString();
                String getage = age.getText().toString();
                String username = user.getText().toString();

                //Checking if any of the fields required are empty
                if(TextUtils.isEmpty(getname) || TextUtils.isEmpty(getemail) || TextUtils.isEmpty(getPass) || TextUtils.isEmpty(getage) || TextUtils.isEmpty(username))
                {
                    Toast.makeText(Register.this, "Error! All fields required.", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    ref.child("Customers").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //checks if the user doesn't already exist (email)
                            if(snapshot.hasChild(username)) {
                                Toast.makeText(Register.this,"Error! User already exists.",Toast.LENGTH_SHORT).show();
                            }
                            else {

                                ref.child("Customers").child(username).child("email").setValue(getemail);
                                ref.child("Customers").child(username).child("name").setValue(getname);
                                ref.child("Customers").child(username).child("password").setValue(getPass);
                                ref.child("Customers").child(username).child("age").setValue(getage);



                                Toast.makeText(Register.this,"Successfully added user",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this,MainActivity.class);
                                startActivity(intent);

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });




                   /* ref.child("Customers").child(getemail).child("name").setValue(getname);
                    ref.child("Customers").child(getemail).child("password").setValue(getPass);
                    ref.child("Customers").child(getemail).child("age").setValue(getage);


                    Toast.makeText(Register.this,"Successfully added user",Toast.LENGTH_SHORT).show();*/
                }

            }
        });
    }




}