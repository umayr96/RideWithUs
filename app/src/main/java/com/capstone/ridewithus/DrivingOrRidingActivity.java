package com.capstone.ridewithus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import com.capstone.ridewithus.CustomerLoginActivity;
//import com.capstone.ridewithus.DriverLoginActivity;
//

public class DrivingOrRidingActivity extends AppCompatActivity {

    private Button btnDriver,btnCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_or_riding);

        btnDriver = (Button) findViewById(R.id.btnDriver);
        btnCustomer = (Button) findViewById(R.id.btnCustomer);

        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                //DatabaseReference driverRef =  FirebaseDatabase.getInstance().getReference().child("users").child("drivers").child(currentUser);
                Intent intent = new Intent(DrivingOrRidingActivity.this, DriverMapActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                //DatabaseReference driverRef =  FirebaseDatabase.getInstance().getReference().child("users").child("customers").child(currentUser);

                Intent intent = new Intent(DrivingOrRidingActivity.this, FilterActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });
    }
}
