package com.capstone.ridewithus;

import android.app.TabActivity;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    private Button btnPassword, btnNewFirstName, btnNewLastName;
    private EditText newFirstName, newLastName, newPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // moving to the chat page
                Intent intent = new Intent(SettingsActivity.this, side_navigation.class);
                //Create the bundle
                Bundle bundle = new Bundle();
                //Add your data to bundle
                bundle.putString("whichFeed", "Davis");
                //Add the bundle to the intent
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                return;
            }
        });

        btnNewFirstName = (Button) findViewById(R.id.btnEnterFirstName);
        btnNewFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newFirstName = (EditText) findViewById(R.id.newFirstName);

                if (newFirstName.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(SettingsActivity.this,"Please enter first name to be updated", Toast.LENGTH_LONG).show();
                }
                else
                {
                    // getting the unique user id and assigning it this account
                    mAuth = FirebaseAuth.getInstance();
                    String userId = mAuth.getCurrentUser().getUid();
                    DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
                    // updating the first name
                    currentUser.child("First Name").setValue(newFirstName.getText().toString());
                    Toast.makeText(SettingsActivity.this,"First Name has been updated", Toast.LENGTH_LONG).show();
                    newFirstName.setText("");
                }
            }
        });

        btnNewLastName = (Button) findViewById(R.id.btnEnterLastName);
        btnNewLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newLastName = (EditText) findViewById(R.id.newLastName);

                if (newLastName.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(SettingsActivity.this,"Please enter last name to be updated", Toast.LENGTH_LONG).show();
                }
                else
                {
                    // getting the unique user id and assigning it this account
                    mAuth = FirebaseAuth.getInstance();
                    String userId = mAuth.getCurrentUser().getUid();
                    DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
                    // updating the last name
                    currentUser.child("Last Name").setValue(newLastName.getText().toString());
                    Toast.makeText(SettingsActivity.this,"Last Name has been updated", Toast.LENGTH_LONG).show();
                    newLastName.setText("");

                }
            }
        });


        btnPassword = (Button) findViewById(R.id.btnEnterPassword);
        newPassword = (EditText) findViewById(R.id.newPassword);
        // this is for the reset password
        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = newPassword.getText().toString() + "@sheridancollege.ca"; // getting the username from the user
                // if there is no username entered then display message
                if (newPassword.getText().toString().equalsIgnoreCase(""))

                {Toast.makeText(SettingsActivity.this,"Please enter the username associated with the email account", Toast.LENGTH_LONG).show();

                }
                // if there is a username then send the email
                else
                {
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Log.d(TAG, "Email sent.");
                                    }
                                }
                            });
                    Toast.makeText(SettingsActivity.this,"Email has been sent please check email and follow instructions to reset the password", Toast.LENGTH_LONG).show();
                }

            }
        });
        // end of password reset


    }
}
