package com.example.ridebook;

import java.util.ArrayList;

public class RideArray extends ArrayList{

    // Custom array that holds RideProfile Objects

    private static ArrayList<RideProfile> rides = new ArrayList<>();

    // Adding and deleting a Ride
    public static void appendRide(RideProfile ride){
        rides.add(ride);
    }
    public static void deleteRide(RideProfile ride){
        rides.remove(ride);
    }

    // deleting a ride at a given index
    public static void deleteRide(int i){
        rides.remove(i);
    }
    // retrieving a ride profile at a gievn index
    public static RideProfile getRideProfile(int i) {
        return rides.get(i);
    }
    // Retrieving an entire ride list
    public static ArrayList<RideProfile> getRideList(){
        return rides;
    }
    // get the size of the array list
    public static int getRideArraySize() {
        return rides.size();
    }
}
