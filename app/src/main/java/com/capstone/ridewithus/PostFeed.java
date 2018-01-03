package com.capstone.ridewithus;

/**
 * Created by Umayr Mian on 12/29/2017.
 */

public class PostFeed {

    private String time, seat, charge, driverName;

    public PostFeed() {
    }

    public PostFeed(String time, String seat, String charge, String driverName) {
        this.time = time;
        this.seat = seat;
        this.charge = charge;
        this.driverName = driverName;
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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
