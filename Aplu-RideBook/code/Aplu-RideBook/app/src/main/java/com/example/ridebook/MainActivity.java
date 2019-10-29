package com.example.ridebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // initialize the variables pointing to buttons
    ListView RideList;
    RideAdapter RideAdapter;
    ArrayList<RideProfile> dataList;
    Button AddRide;
    Button DeleteRide;
    Button EditRide;
    int position;
    TextView total_distance;
    float distance_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize the first page view
        setContentView(R.layout.activity_main);
        // assign Ridelist Variable to the Listview that shows the rides
        RideList = findViewById(R.id.RideList);

        // Construct the data source
        dataList = RideArray.getRideList();
        // Create the adapter to convert the array to views
        RideAdapter = new RideAdapter(this, dataList);
        // Attach the adapter to a ListView
        RideList.setAdapter(RideAdapter);

        // assign variables to buttons to press
        AddRide = findViewById(R.id.addbutton);
        DeleteRide = findViewById(R.id.deletebutton);
        EditRide = findViewById(R.id.Edit);

        //get the total distance by indexing RideProfile objects and display in the bottom right corner
        total_distance = findViewById(R.id.total_distance);
        distance_result = getTotalDistance(dataList);
        total_distance.setText("Total distance: " +Float.toString(distance_result)+ " Km");

        //If clicking on an item in the list, selector gets assigned to the String that is selected. This is key for selecting an item to delete
        RideList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // when an item in the list is selected, we display our Edit and Delete Buttons for the option to delete or edit the existing profile

                // assign global position variable to grab index in case of editting
                position = i;

                // make relevant buttons visible and usable
                EditRide.setVisibility(View.VISIBLE);
                DeleteRide.setVisibility(View.VISIBLE);
            }
        });

        // Case handling clicking on the edit button
        EditRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Start the Editting of profile activity
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                // Pass the position of the rideprofile object to edit
                intent.putExtra("ride_position", position);
                startActivity(intent);

            }
        });
        // Case handling of the click of a delete button
        DeleteRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete the object in the position
                RideArray.deleteRide(position);
                // Edit the total distance of the rides in the list
                total_distance = findViewById(R.id.total_distance);
                distance_result = getTotalDistance(dataList);
                total_distance.setText("Total distance: " +Float.toString(distance_result)+ " Km");

                //Upon deletion, make the edit and delete buttons invisible
                DeleteRide.setVisibility(View.INVISIBLE);
                EditRide.setVisibility(View.INVISIBLE);

                // Update the list
                dataList = RideArray.getRideList();
                RideAdapter = new RideAdapter(MainActivity.this, dataList);
                RideList.setAdapter(RideAdapter);
            }
        });
        // case handling for pressing the Add button
        AddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a view that makes a field to create the ride information

                //Create intent to go to adding profile activity
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);

                //import the list to the adding activity
                intent.putExtra("Ride",dataList);

                // start the activity
                startActivity(intent);
            }
        });


    }
    // algorithm to Retrieve the total distance for main display
    public float getTotalDistance(ArrayList<RideProfile> rides){
        // initialize size and keeper of total distance variable
        int ridesize = rides.size();
        float distance;
        RideProfile ride;
        distance = 0.0f;
        // iterate and grab ride distance attributes to add to the total distance
        for (int i = 0; i < ridesize; i++){
            ride = rides.get(i);
            distance = distance + ride.getDistance();
        }
        //return
        return distance;

    }

    @Override
    protected void onResume(){
        super.onResume();
        // Create the adapter to convert the array to view

        // update distance after operation is done
        total_distance = findViewById(R.id.total_distance);
        distance_result = getTotalDistance(dataList);
        total_distance.setText("Total distance: " +Float.toString(distance_result)+ " Km");

        // upon reactivation, make the edit and delete buttons invisible
        DeleteRide.setVisibility(View.INVISIBLE);
        EditRide.setVisibility(View.INVISIBLE);

        // update data list
        dataList = RideArray.getRideList();
        RideAdapter = new RideAdapter(this, dataList);
        RideList.setAdapter(RideAdapter);

}
}
