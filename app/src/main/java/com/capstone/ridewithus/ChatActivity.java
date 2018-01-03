package com.capstone.ridewithus;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

//        Button back = (Button) findViewById(R.id.btnBack);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent h = new Intent(ChatActivity.this,side_navigation.class);
//                startActivity(h);
//                finish();
//            }
//        });

                /*
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
        */


    }
}
