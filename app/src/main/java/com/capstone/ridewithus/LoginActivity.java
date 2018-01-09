package com.capstone.ridewithus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextEmail, mEditTextPassword;
    private Button btnEnter, btnRegister, btnPasswordReset;

    // creating Authentication Variables to use in later on
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener fireBaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);

        btnEnter = (Button) findViewById (R.id.btnEnter);
        btnRegister = (Button) findViewById(R.id.btnRegister);

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
                    Intent intent = new Intent(LoginActivity.this, DrivingOrRidingActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        // this is for the Login Proccess
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEditTextEmail.getText().toString();
                final String password = mEditTextPassword.getText().toString();
                if((email.equalsIgnoreCase("")) || (password.equalsIgnoreCase("")))
                {
                    Toast.makeText(LoginActivity.this,"Please Fill out all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // gettign the email and passowrd and sending it to the database to check if the user exists
                    mAuth.signInWithEmailAndPassword(email + "@sheridancollege.ca",password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this,"Sorry Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        // this is for the reset password
        btnPasswordReset = (Button) (findViewById(R.id.btnPasswordReset));
        btnPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = mEditTextEmail.getText().toString() + "@sheridancollege.ca"; // getting the username from the user
                // if there is no username entered then display message
                if (mEditTextEmail.getText().toString().equalsIgnoreCase(""))

                    {Toast.makeText(LoginActivity.this,"Please enter the username associated with the email account", Toast.LENGTH_LONG).show();

                }
                // if there is a username then send the email
                else
                {
                    mAuth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Log.d(TAG, "Email sent.");
                                    }
                                }
                            });
                    Toast.makeText(LoginActivity.this,"Email has been sent please check email and follow instructions to reset the password", Toast.LENGTH_LONG).show();
                }

            }
        });
    // end of password reset

        // this is when the user clicks the register button it will move to the register activity
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
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

