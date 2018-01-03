//NOT USING THIS ANYMORE

package com.capstone.ridewithus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayFeedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_feed);

        myRef = FirebaseDatabase.getInstance().getReference().child("feed").child("/Davis");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerAdapter<PostFeed,PostFeedViewHolder> adapter = new FirebaseRecyclerAdapter<PostFeed, PostFeedViewHolder>(
                PostFeed.class,
                R.layout.individual_row,
                PostFeedViewHolder.class,
                myRef
        ) {

            @Override
            protected void populateViewHolder(PostFeedViewHolder viewHolder, PostFeed model, int position) {

                viewHolder.setTime(model.getTime());
                viewHolder.setSeat(model.getSeat());
                viewHolder.setCharge(model.getCharge());
                viewHolder.setDriverName(model.getDriverName());
                
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public static class PostFeedViewHolder extends RecyclerView.ViewHolder{
        TextView timeText, seatText,chargeText,driverNameText;
        public PostFeedViewHolder(View itemView) {
            super(itemView);
            timeText = (TextView)itemView.findViewById(R.id.timeDisplay);
            seatText = (TextView)itemView.findViewById(R.id.seatDisplay);
            chargeText = (TextView)itemView.findViewById(R.id.chargeDisplay);
            driverNameText = (TextView)itemView.findViewById(R.id.driverNameDisplay);
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

        public void setDriverName(String driverName) {
            driverNameText.setText(driverName);
        }
    }

}
