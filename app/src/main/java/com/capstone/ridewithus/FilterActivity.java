package com.capstone.ridewithus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    private Button btnFilter;
    private RadioButton radioButtonHMC, radioButtonDavis, radioButtonTraf;
    private String whichFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        btnFilter = (Button) findViewById(R.id.btnfilter);
        radioButtonDavis = (RadioButton) findViewById(R.id.rdDavis);
        radioButtonHMC = (RadioButton) findViewById(R.id.rdHMC);
        radioButtonTraf = (RadioButton) findViewById(R.id.rdTrafagler);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           // which ever radio button is checked the value will be stored to the whichFeed variable
           if (radioButtonDavis.isChecked())
           {
            whichFeed = "Davis";

           }
           else  if (radioButtonTraf.isChecked())
           {
               whichFeed = "Trafalgar";

           }
           else if (radioButtonHMC.isChecked())
           {
               whichFeed = "HMC";
           }

                // Starting the new display feed activity and will send the correct value to display
                Intent intent = new Intent(FilterActivity.this, side_navigation.class);
                //Create the bundle
                Bundle bundle = new Bundle();
                //Add your data to bundle
                bundle.putString("whichFeed", whichFeed);
                //Add the bundle to the intent
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }
}
