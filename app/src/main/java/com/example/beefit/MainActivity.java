package com.example.beefit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                if(emailDb.isEmpty() && passDb.isEmpty())
                {

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
