package com.capstone.ridewithus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

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
    }
}
