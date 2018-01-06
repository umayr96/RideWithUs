package com.capstone.ridewithus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RiderInformationActivity extends AppCompatActivity {

    private Button btnJoinRide;
    private TextView addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_information);

        addressText = (TextView) findViewById(R.id.addressEditText);

        btnJoinRide = (Button) findViewById(R.id.btnJoinRide2);
        btnJoinRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("chat");
                currentUser.setValue(true);
                currentUser.child(addressText.getText().toString()).setValue(addressText.getText().toString());

                currentUser = FirebaseDatabase.getInstance().getReference().child("riderAddress");
                currentUser.setValue(true);
                currentUser.child("address").setValue(addressText.getText().toString());

















                Intent intent = new Intent(RiderInformationActivity.this, ChatActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
