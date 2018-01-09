package com.capstone.ridewithus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RiderInformationActivity extends AppCompatActivity {

    private Button btnJoinRide;
    private TextView addressText, cityText;
    FirebaseDatabase wDatabase = FirebaseDatabase.getInstance();
    DatabaseReference wmyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_information);

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // moving to the chat page
                Intent intent = new Intent(RiderInformationActivity.this, side_navigation.class);
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

        // getting the text from the user
        addressText = (TextView) findViewById(R.id.addressEditText);
        cityText = (TextView) findViewById(R.id.cityEditText);

        // setting the on clink for the buttoin when presses
        btnJoinRide = (Button) findViewById(R.id.btnJoinRide2);
        btnJoinRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // checking if the user has entered nothing if it did then display message ig there is text then move forward
                if ((addressText.getText().toString().equalsIgnoreCase("")) || (cityText.getText().toString().equalsIgnoreCase("")))
                {
                    Toast.makeText(RiderInformationActivity.this,"Please Fill in the address field", Toast.LENGTH_LONG).show();
                }
                else
                {
                    // setting up firebase connection with the correct path to chat
                    DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("chat");
                    // saving the value got from the user to firebase
                    currentUser.child(addressText.getText().toString()).setValue("Hey I Would like to join your ride -Rider");

                    // setting up firebase connection with the correct path to rider Adddress
                    currentUser = FirebaseDatabase.getInstance().getReference().child("riderAddress");
                    // saving the value got from the user to firebase
                    currentUser.child("address").setValue(addressText.getText().toString() + " " + cityText.getText().toString() + " ON Canada");

                    Toast.makeText(RiderInformationActivity.this,"You have joined this ride, your driver will contact you soon. Thank You", Toast.LENGTH_LONG).show();

                    // moving to the chat page
                    Intent intent = new Intent(RiderInformationActivity.this, side_navigation.class);
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

            }
        });
    }
}
