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

public class RegisterActivity extends AppCompatActivity {

    private Button btnEnter;
    private EditText textFirstName, textLastName, textEmail, textPassword, textConfirmPassword;

    private FirebaseAuth mAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth.AuthStateListener fireBaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // setting up the database connectionmAuth = FirebaseAuth.getInstance();
        // getting current state of database


        // seeting the varaibles
        btnEnter = (Button) findViewById(R.id.btnEnter);
        textFirstName = (EditText) findViewById(R.id.editTextFirstName);
        textLastName = (EditText) findViewById(R.id.editTextLastName);
        textEmail = (EditText) findViewById(R.id.editTextEmail);
        textPassword = (EditText) findViewById(R.id.editTextPassword);
        textConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);


        // this is for the registration Proccess
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting the information from the user
                  final String firstName = textFirstName.getText().toString();
                  final String lastName = textLastName.getText().toString();
                  final String email = textEmail.getText().toString();
                  final String password = textPassword.getText().toString();
                  final String confirmPassword = textConfirmPassword.getText().toString();

                  if (!password.equals(confirmPassword)) // if the passwords do not match then show the messaeg
                  {
                      Toast.makeText(RegisterActivity.this,"Passwords Do Not Match. Please Try Again", Toast.LENGTH_LONG).show();
                  }
                  // checking if any of the fields are blank  if it is then will show the message
                  else if (firstName.equalsIgnoreCase("") || (lastName.equalsIgnoreCase("")) || (email.equalsIgnoreCase(""))
                          || (password.equalsIgnoreCase("")) || (confirmPassword.equalsIgnoreCase("")))
                  {
                      Toast.makeText(RegisterActivity.this,"All Fields Required . Please Try Again", Toast.LENGTH_LONG).show();
                  }
                  else
                  {
                      // sending the information to the database
                      mAuth.createUserWithEmailAndPassword(email + "@sheridancollege.ca", password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              // if the task is unsuccessful then a meesage will be displayed to the user
                              if (!task.isSuccessful()) {
                                  Toast.makeText(RegisterActivity.this,"Error While Trying To Sign Up. Please Try Again", Toast.LENGTH_LONG).show();
                              }
                              // if it is succesful then it will add the user to the database with special key
                              else{
                                  // getting the unique user id and assigning it this account
                                  String userId = mAuth.getCurrentUser().getUid();
                                  DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
                                  currentUser.setValue(true);
                                  // adding all the information to the database under this user
                                  currentUser.child("First Name").setValue(firstName);
                                  currentUser.child("Last Name").setValue(lastName);
                                  currentUser.child("Email").setValue(email + "@sheridancollege.ca");
                                  // firebase finds the current user and sends and email verification to the user
                                  mFirebaseUser = mAuth.getCurrentUser();
                                  mFirebaseUser.sendEmailVerification();
                                  Toast.makeText(RegisterActivity.this,"Account Has Been Created, confirm account by clicking link in verification email", Toast.LENGTH_LONG).show();

                                  // move to the login page and once the user has been verified they can login
                                  Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                  startActivity(intent);
                                  finish();
                                  return;
                              }
                          }
                      });
                  }

            }
        });

    }
}
