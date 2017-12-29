package com.capstone.ridewithus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnLogin,btnSignUp;
    private FirebaseAuth.AuthStateListener fireBaseAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting up the database connection
        // getting current state of database
        mAuth = FirebaseAuth.getInstance();
        // creating a listener
        fireBaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //getting the cuurent login user
                if ((user!=null) && (user.isEmailVerified())) // if the user is not null and exist in the database will move to the next activity
                {
                    Intent intent = new Intent(MainActivity.this, DrivingOrRidingActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };


        // this is button is cliked then it will go to the login page
        btnLogin = (Button) findViewById (R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnSignUp = (Button) findViewById(R.id.btnSignup);
        // this is when the user clicks the register button it will move to the register activity
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

    // this method is to start the authentication listener
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(fireBaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(fireBaseAuthListener);
    }

}
