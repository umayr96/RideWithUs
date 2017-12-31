package com.capstone.ridewithus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SignoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signout);
        FirebaseAuth.getInstance().signOut(); // signing out the currnet user
        Intent intent = new Intent(SignoutActivity.this, MainActivity.class);// going back to the main activity
        startActivity(intent);
        finish();
        return;
    }
}
