package com.capstone.ridewithus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DisplayFeedActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView ListViewFeed;
    private ArrayList<String> feedArray = new ArrayList<String>();
    private String whichFeed;
    private TextView heading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_feed);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data
         whichFeed = bundle.getString("whichFeed");

         // setting the heading of the page
        heading = (TextView) findViewById(R.id.textViewHeading);
        heading.setText(whichFeed);

        // Read From Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("feed").child(whichFeed);
        ListViewFeed = (ListView) findViewById(R.id.ListViewFeed);
        // setting the array adapter with the list view
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, feedArray);
        ListViewFeed.setAdapter(arrayAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);

                feedArray.add(value);

                //Collections.reverse(feedArray); // this to reverse the array so the lastest post will be on top
                arrayAdapter.notifyDataSetChanged();
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
