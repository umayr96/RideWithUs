package com.capstone.ridewithus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverPostActivity extends AppCompatActivity {

    private Button btnSkip;
    private RadioButton rdDavis,rdHMC,rdTrafalgar;
    private EditText textClock;
    private SeekBar sliderSeat;
    private EditText chargeText;
    private Button btnPost;
    private String whichFeed;

    private FirebaseAuth mAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth.AuthStateListener fireBaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_post);

        // setting up the database connection
        // getting current state of database
        mAuth = FirebaseAuth.getInstance();

        btnSkip = (Button) findViewById(R.id.btnskip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverPostActivity.this, FilterActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


        btnPost = (Button) findViewById(R.id.btnpost);
        rdDavis = (RadioButton) findViewById(R.id.postRadioDavis);
        rdHMC = (RadioButton) findViewById(R.id.postRadioHMC);
        rdTrafalgar = (RadioButton) findViewById(R.id.postRadioTrafalgar);
        textClock = (EditText) findViewById(R.id.textClock);
        sliderSeat = (SeekBar) findViewById(R.id.sliderSeat);
        chargeText = (EditText) findViewById(R.id.chargeText);



        // this is for the driver post Proccess
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting the information from the user

                if (rdDavis.isChecked())
                {
                    whichFeed = "Davis";

                }
                else  if (rdTrafalgar.isChecked())
                {
                    whichFeed = "Trafalgar";

                }
                else if (rdHMC.isChecked())
                {
                    whichFeed = "HMC";
                }

                final String time = textClock.getText().toString();
                final String seats = String.valueOf(sliderSeat.getProgress());
                final String charge = chargeText.getText().toString();

                // checking if any of the fields are blank  if it is then will show the message
                if (time.equalsIgnoreCase("") || (seats.equalsIgnoreCase("")) || (charge.equalsIgnoreCase("")))
                {
                    Toast.makeText(DriverPostActivity.this,"All Fields Required . Please Try Again", Toast.LENGTH_LONG).show();
                }
                else
                {
                    // sending the information to the database
                    // if it is succesful then it will add the user to the database with special key
                    // getting the unique user id and assigning it this account
                    String userId = mAuth.getCurrentUser().getUid();
                    DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("feed").child(whichFeed).child(userId);
                    currentUser.setValue(true);
                    // adding all the information to the database under this user
                    currentUser.child("time").setValue(time);
                    currentUser.child("seat").setValue(seats);
                    currentUser.child("charge").setValue(charge);

                    Toast.makeText(DriverPostActivity.this,"Post Has Been Created", Toast.LENGTH_LONG).show();
                    // move to the login page and once the user has been verified they can login
                    Intent intent = new Intent(DriverPostActivity.this, FilterActivity.class);
                    startActivity(intent);
                    finish();
                    return;

                }

            }
        });

    }
}
