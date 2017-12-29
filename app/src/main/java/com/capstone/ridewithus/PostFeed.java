package com.capstone.ridewithus;

/**
 * Created by Umayr Mian on 12/29/2017.
 */

public class PostFeed {

    private String time, seat, charge;

    public PostFeed() {
    }

    public PostFeed(String time, String seat, String charge) {
        this.time = time;
        this.seat = seat;
        this.charge = charge;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }
}
