package com.capstone.ridewithus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
                Intent intent = new Intent(DrivingOrRidingActivity.this, DriverLoginActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrivingOrRidingActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });
    }
}