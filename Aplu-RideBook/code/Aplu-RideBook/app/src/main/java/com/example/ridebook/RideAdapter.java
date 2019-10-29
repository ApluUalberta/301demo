package com.example.ridebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RideAdapter extends ArrayAdapter<RideProfile> {
    public RideAdapter(@NonNull Context context, ArrayList<RideProfile> resource) {
        super(context, 0,resource);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RideProfile rideProfile = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false);
        }
        // Lookup view for data population
        TextView contentview = (TextView) convertView.findViewById(R.id.content_view);
        TextView timeview = (TextView) convertView.findViewById(R.id.time_text);
        TextView Distanceview = (TextView) convertView.findViewById(R.id.distance_text);

        // Populate the data into the template view using the data object
        // can set multiple

        contentview.setText(rideProfile.date);
        timeview.setText(rideProfile.time);
        Distanceview.setText(Float.toString(rideProfile.distance)+ "km");

        // Return the completed view to render on screen
        return convertView;
    }
}
