package com.capstone.ridewithus;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class side_navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference mDatabase;
    private ListView ListViewFeed;
    private ArrayList<String> feedArray = new ArrayList<String>();
    private String whichFeed;
    private TextView heading;

    private RecyclerView recyclerView;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        myRef = FirebaseDatabase.getInstance().getReference().child("feed").child("/Davis");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerAdapter<PostFeed,DisplayFeedActivity.PostFeedViewHolder> adapter = new FirebaseRecyclerAdapter<PostFeed, DisplayFeedActivity.PostFeedViewHolder>(
                PostFeed.class,
                R.layout.individual_row,
                DisplayFeedActivity.PostFeedViewHolder.class,
                myRef
        ) {

            @Override
            protected void populateViewHolder(DisplayFeedActivity.PostFeedViewHolder viewHolder, PostFeed model, int position) {

                viewHolder.setTime(model.getTime());
                viewHolder.setSeat(model.getSeat());
                viewHolder.setCharge(model.getCharge());

            }
        };

        recyclerView.setAdapter(adapter);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_chat) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ChatActivity()).commit();
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_paypal) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_signout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class PostFeedViewHolder extends RecyclerView.ViewHolder{
        TextView timeText, seatText,chargeText;
        public PostFeedViewHolder(View itemView) {
            super(itemView);
            timeText = (TextView)itemView.findViewById(R.id.timeDisplay);
            seatText = (TextView)itemView.findViewById(R.id.seatDisplay);
            chargeText = (TextView)itemView.findViewById(R.id.chargeDisplay);
        }

        public void setTime(String time) {
            timeText.setText(time);
        }

        public void setSeat(String seat) {
            seatText.setText(seat);
        }

        public void setCharge(String charge) {
            chargeText.setText(charge);
        }
    }
}
