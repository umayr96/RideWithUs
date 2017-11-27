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
import java.util.Collections;

public class FilterActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView listViewFeed;
    private Button btnFilter;
    private RadioButton radioButtonHMC, radioButtonDavis, radioButtonTraf;

    private ArrayList<String> feedArrayDavis = new ArrayList<String>();
    private ArrayList<String> feedArrayTrafalgar = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        btnFilter = (Button) findViewById(R.id.btnfilter);
        radioButtonDavis = (RadioButton) findViewById(R.id.radioButtonDavis);
        radioButtonHMC = (RadioButton) findViewById(R.id.radioButtonHMC);
        radioButtonTraf = (RadioButton) findViewById(R.id.radioButtonTrafalgar);

        // Read From Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("feed").child("Davis");
        listViewFeed = (ListView) findViewById(R.id.ListViewFeed);

        final ArrayAdapter<String> arrayAdapterDavis = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, feedArrayDavis);
        final ArrayAdapter<String> arrayAdapterTrafalgar = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, feedArrayTrafalgar);
        listViewFeed.setAdapter(arrayAdapterDavis);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           if (radioButtonDavis.isChecked())
           {
               Toast.makeText(FilterActivity.this,"Works", Toast.LENGTH_LONG).show();
               mDatabase = FirebaseDatabase.getInstance().getReference().child("feed").child("Davis");

           }
           else  if (radioButtonTraf.isChecked())
           {
               Toast.makeText(FilterActivity.this,"Works", Toast.LENGTH_LONG).show();
               mDatabase = FirebaseDatabase.getInstance().getReference().child("feed").child("Trafalgar");

           }

            }
        });



        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);

                    feedArrayDavis.add(value);


                //Collections.reverse(feedArray); // this to reverse the array so the lastest post will be on top
                arrayAdapterDavis.notifyDataSetChanged();
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
