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

public class MainActivity extends AppCompatActivity {

    Button SignUp, Login,Reset;
    EditText Email, Pass;

    DatabaseReference ref;


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
        Reset = (Button) findViewById(R.id.resetpass);

        ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://beefit-database-93831-default-rtdb.firebaseio.com/Customers");


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailDb = Email.getText().toString();
                String passDb = Pass.getText().toString();

                if(TextUtils.isEmpty(emailDb) || TextUtils.isEmpty(passDb)) {

                    Toast.makeText(MainActivity.this, "Error! All fields required.", Toast.LENGTH_SHORT).show();
                }
                else {

                    ref.child("Customers").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //checks if username exists within the database
                            if(snapshot.hasChild(emailDb)) {

                                String getpass = snapshot.child(emailDb).child("password").getValue(String.class);


                                        if(getpass.equals(passDb)) {

                                            Toast.makeText(MainActivity.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(MainActivity.this, MainPage.class);
                                            startActivity(intent);

                                        }
                                        else
                                        {
                                            Toast.makeText(MainActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                        }
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ResetPass.class);
                startActivity(intent);
            }
        });


    }


}
