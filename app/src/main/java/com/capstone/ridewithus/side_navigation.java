package com.capstone.ridewithus;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class side_navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;

    private ArrayList<String> feedArray = new ArrayList<String>();
    private String whichFeed;
    private TextView navEmail,navName;
    private Button btnJoinTest;
    private TextView feedName;

    private RecyclerView recyclerView;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnJoinTest = (Button) findViewById(R.id.btnJoinTest);
        btnJoinTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(side_navigation.this, RiderInformationActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h = new Intent(side_navigation.this,DriverPostActivity.class);
                startActivity(h);
                finish();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // this displays the users information at the side bar top
        myRef = FirebaseDatabase.getInstance().getReference().child("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mAuth = FirebaseAuth.getInstance();
                String userId = mAuth.getCurrentUser().getUid();
                String email = dataSnapshot.child(userId).child("Email").getValue(String.class);
                String fName = dataSnapshot.child(userId).child("First Name").getValue(String.class);
                String lName = dataSnapshot.child(userId).child("Last Name").getValue(String.class);

               View headerView = navigationView.getHeaderView(0);
                navEmail = (TextView) headerView.findViewById(R.id.navEmailTextView);
                navEmail.setText(email);

                navName = (TextView) headerView.findViewById(R.id.navFullNameTextView);
                navName.setText(fName + " " + lName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // end of displaying the users information

        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data
        whichFeed = bundle.getString("whichFeed");

        feedName = (TextView) findViewById(R.id.feedNameTxt);
        feedName.setText(whichFeed+" Posts");

        myRef = FirebaseDatabase.getInstance().getReference().child("feed").child("/"+ whichFeed);
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
                viewHolder.setDriverName(model.getDriverName());
            }
        };

        recyclerView.setAdapter(adapter);
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
        if (id == R.id.action_filter) {
            Intent f = new Intent(side_navigation.this,FilterActivity.class);
            startActivity(f);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id=item.getItemId();
        switch (id){

            //case R.id.nav_home:
                //Intent h = new Intent(side_navigation.this,side_navigation.class);
                //startActivity(h);
                //break;
            case R.id.nav_chat:
                Intent c = new Intent(side_navigation.this,ChatActivity.class);
                startActivity(c);
                break;
            case R.id.nav_paypal:
                Intent p = new Intent(side_navigation.this,PaypalActivity.class);
                startActivity(p);
                break;
            case R.id.nav_settings:
                Intent s = new Intent(side_navigation.this,SettingsActivity.class);
                startActivity(s);
                break;
            case R.id.nav_help:
                Intent he = new Intent(side_navigation.this,HelpActivity.class);
                startActivity(he);
                break;
            case R.id.nav_signout:
                Intent so = new Intent(side_navigation.this,SignoutActivity.class);
                startActivity(so);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
