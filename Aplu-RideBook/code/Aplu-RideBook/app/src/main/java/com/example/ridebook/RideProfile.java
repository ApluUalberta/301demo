package com.example.ridebook;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.util.Date;

public class RideProfile implements Parcelable {
    String date;
    String time;
    float distance;
    float average_speed;
    int average_cadence;
    String note;

    // constructor for rideprofile
    public RideProfile(String date, String time, float distance,float average_speed, int average_cadence, String note){
        this.date = date;
        this.time = time;
        this.distance = distance;
        this.average_speed = average_speed;
        this.average_cadence = average_cadence;
        this.note = note;


    }

    // for parcelling
    public RideProfile(Parcel parcel) {
        date = parcel.readString();
        time = parcel.readString();
        distance = parcel.readFloat();
        average_speed = parcel.readFloat();
        average_cadence = parcel.readInt();
        note = parcel.readString();
    }

    //getters and setters for each attribute in the Ride Profile object

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getAverage_speed() {
        return average_speed;
    }

    public void setAverage_speed(float average_speed) {
        this.average_speed = average_speed;
    }

    public int getAverage_cadence() {
        return average_cadence;
    }

    public void setAverage_cadence(int average_cadence) {
        this.average_cadence = average_cadence;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    // parcelling

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
        dest.writeFloat(distance);
        dest.writeFloat(average_speed);
        dest.writeFloat(average_cadence);
        dest.writeString(note);
    }



    public static final Creator<RideProfile> CREATOR = new Creator<RideProfile>() {
        @Override
        public RideProfile[] newArray(int size) {
            return new RideProfile[size];
        }

        @Override
        public RideProfile createFromParcel(Parcel source) {
            return new RideProfile(source);
        }
    };
}
