package com.capstone.ridewithus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DriverSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_setup);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handles presses on the action bar
        switch(item.getItemId()) {
            case R.id.action_settings:
                //startActivity(new Intent(this, Settings.class));
                return true;
            case R.id.action_profile:
                //startActivity(new Intent(this, UserProfile.class));
                return true;
            case R.id.action_paypal:
                //startActivity(new Intent(this, paypal.class));
                return true;
            case R.id.action_home:
                //startActivity(new Intent(this, SelectRide.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
