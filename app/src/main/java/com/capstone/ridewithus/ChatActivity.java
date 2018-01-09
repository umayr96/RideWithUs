package com.capstone.ridewithus;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView listViewChat;
    private ArrayList<String> chatArray = new ArrayList<String>();
    private Button btnSensMessage, btnStartRide;
    private EditText chatEditTextView;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    String riderAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // moving to the chat page
                Intent intent = new Intent(ChatActivity.this, side_navigation.class);
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



        btnStartRide = (Button) findViewById(R.id.btnStartRideDriver);
        btnStartRide.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View view) {
               //Double latitude = 43.656121;
               //Double longitude = -79.739020;
               // getting the rider address and sending to the google maps app with intent
               myRef = FirebaseDatabase.getInstance().getReference().child("riderAddress");
               myRef.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       mAuth = FirebaseAuth.getInstance();
                       riderAddress = dataSnapshot.child("address").getValue(String.class);
                       String uri = String.format(Locale.ENGLISH, "geo:0,0?q="+riderAddress);

                       Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                       startActivity(intent);
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
            }
        });

        // getting the value from the user for the chat message
        chatEditTextView = (EditText) findViewById(R.id.chatEditTextView);
        btnSensMessage = (Button) findViewById(R.id.btnSendMessage);
        // When the send button is pressed it will send the message and save it to the database
        btnSensMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // checking if the user has entered nothing if it did then display message ig there is text then move forward
                if (chatEditTextView.getText().toString().equalsIgnoreCase(""))
                {
                    // do nothing
                }
                else
                {
                    // setting up firebase connection with the correct path to chat
                    DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("chat");
                    // saving the value got from the user to firebase
                    currentUser.child(chatEditTextView.getText().toString()).setValue(chatEditTextView.getText().toString() + " -Driver");
                    chatEditTextView.setText("");
                }

            }
        });

        // Read From Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("chat");
        listViewChat = (ListView) findViewById(R.id.listViewChat);

        // setting up the array adapter for the list view
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, chatArray);
        listViewChat.setAdapter(arrayAdapter);


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // getting all the information from firebase and saving it to the array
                String value = dataSnapshot.getValue(String.class);
                chatArray.add(value);
                //Collections.reverse(chatArray); // this to reverse the array so the lastest post will be on top
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
