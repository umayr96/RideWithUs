package com.capstone.ridewithus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerLoginActivity extends AppCompatActivity {


    private EditText mEditTextEmail, mEditTextPassword;
    private Button btnLogin, btnRegister;

    // creating Authentication Variable
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener fireBaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        // setting up the database connection
        // getting current state of database
        mAuth = FirebaseAuth.getInstance();
        // creating a listener
        fireBaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //getting the cuurent login user
                if (user!=null)// if the user is not null and exist in the database will move to the next activity
                {
                    Intent intent = new Intent(CustomerLoginActivity.this, CustomerMapActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };



        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);

        btnLogin = (Button) findViewById (R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        // this is for the registration Proccess
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting the information from the user for email and passowrd
                final String email = mEditTextEmail.getText().toString();
                final String password = mEditTextPassword.getText().toString();
                // sending the information to the dataabse
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CustomerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // if the task is unsuccessful then a meesage will be displayed to the user
                        if (!task.isSuccessful()) {
                            Toast.makeText(CustomerLoginActivity.this,"Error While Trying To Sign Up", Toast.LENGTH_SHORT).show();
                        }
                        // if it is succesful then it will add the user to the database with special key
                        else{
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("users").child("customers").child(userId);
                            currentUser.setValue(true);
                        }
                    }
                });
            }
        });

        // this is for the Login Proccess
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEditTextEmail.getText().toString();
                final String password = mEditTextPassword.getText().toString();
                // gettign the email and passowrd and sending it to the database to check if the user exists
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(CustomerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(CustomerLoginActivity.this,"Sorry Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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