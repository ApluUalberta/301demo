package com.example.ridebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {

    // Activity that is initiated upon pressing add button

    // initialize variables assigned to each atribute of the new Ride Profile
    RideProfile Ride_of_interest;
    EditText date_var;
    EditText time_var;
    EditText speed_var;
    EditText distance_var;
    EditText cadence_var;
    EditText notevar;
    Button add;

    // initialize variables to check if note, date, and tiem are in proper format
    boolean returnDate;
    boolean returnTime;
    boolean notelimit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // assign variables to editText views
        date_var = findViewById(R.id.DateText);
        time_var = findViewById(R.id.TimeText);
        speed_var = findViewById(R.id.averagespeed);
        distance_var = findViewById(R.id.distancetext);
        cadence_var = findViewById(R.id.average_cadence);
        notevar = findViewById(R.id.note);
        add = findViewById(R.id.add_button);

        // add button is set to visible (in case)
        add.setVisibility(View.VISIBLE);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Before parsing input, check if any fields (other than note) are empty. All need to be full
                if (!TextUtils.isEmpty(date_var.getText().toString()) &&
                        !TextUtils.isEmpty(time_var.getText().toString()) &&
                        !TextUtils.isEmpty(speed_var.getText().toString()) &&
                        !TextUtils.isEmpty(distance_var.getText().toString()) &&
                        !TextUtils.isEmpty(cadence_var.getText().toString())) {

                    // Receive the for each attribute in the given fields
                    String date_output = date_var.getText().toString();
                    String time_output = time_var.getText().toString();
                    float speed_output = Float.parseFloat(speed_var.getText().toString());
                    float distance_output = Float.parseFloat(distance_var.getText().toString());
                    int cadence_output = Integer.parseInt(cadence_var.getText().toString());
                    String note_output = notevar.getText().toString();

                    // initialize boolean variables assigned to the return of the checking functions to ensure proper input for date, time, and note
                    returnDate = checkDateInput(date_output);
                    returnTime = checkTimeInput(time_output);
                    notelimit = notelimit(note_output);

                    // if all three are true, the Ride may be appended to the list and displayed
                    if ((returnTime && returnDate && notelimit)) {

                        //create the new Ride Profile object with the inputted fields to be appended to the Ride Array List
                        RideProfile rideProfile = new RideProfile(date_output, time_output, distance_output, speed_output, cadence_output, note_output);
                        // append
                        RideArray.appendRide(rideProfile);
                        //finish and proceed to onresume in main
                        finish();
                    }
                    else {
                        // Date format error handling
                        if (returnDate == false){
                            TextView DateError;
                            DateError = findViewById(R.id.DateError);
                            DateError.setText("Please follow YYYY-MM-DD format. Do not use '/'.");
                        }
                        // Time format error handling
                        if (returnTime == false){
                            TextView TimeError;
                            TimeError = findViewById(R.id.DateError);
                            TimeError.setText("Please input proper HH:MM (time of day) format. 24:00 MAX\n");
                        }
                        // Note format error handling
                        if (notelimit == false){
                            TextView Noteerror;
                            Noteerror = findViewById(R.id.DateError);
                            Noteerror.setText(("Limit note to 20 characters.\n"));
                        }
                    }
                }

                else{
                    // Handling for empty date field
                    if (TextUtils.isEmpty(date_var.getText().toString())){
                        TextView empty_speed = findViewById(R.id.DateError);
                        empty_speed.setText("Please fill in date.\n");
                    }

                    //Handling for empty Time field
                    if (TextUtils.isEmpty(time_var.getText().toString())){
                        TextView empty_speed = findViewById(R.id.DateError);
                        empty_speed.setText("Please fill time.\n");
                    }
                    // Handling for empty speed field
                    if (TextUtils.isEmpty(speed_var.getText().toString())){
                        TextView empty_speed = findViewById(R.id.DateError);
                        empty_speed.setText("Please fill speed variable.\n");
                    }
                    // Handling for empty distance field
                    if (TextUtils.isEmpty(distance_var.getText().toString())){
                        TextView empty_distance = findViewById(R.id.DateError);
                        empty_distance.setText("Please fill distance variable.\n");
                    }
                    // Handling for empty cadence field
                    if (TextUtils.isEmpty(cadence_var.getText().toString())){
                        TextView empty_cadence = findViewById(R.id.DateError);
                        empty_cadence.setText("Please fill cadence variable.\n");
                    }
                }
            }
        });


    }
    // Note limit checker
    public boolean notelimit(String string){
        // note length has to be 20 characters or under
        if (string.length() > 20){
            return false;
        }
        return true;
    }
    // Proper date format checker
    public boolean checkDateInput(String string) {
        // ensures that the user enters proper Date format (YYYY-MM-DD)
        // only evaluates to true if all conditions are satisfied

        int monthone;
        int monthten;
        int monthresult;
        int dayten;
        int dayone;
        int dayresult;
        // date has to be 10 characters long
        if (string.length() == 10) {
            // Date has to have a - after YYYY
            if (string.charAt(4) == '-') {
                // Date has to have a - after YYYY-MM
                if (string.charAt(7) == '-') {
                    // Try statement that only evaluates on int inputs for MM
                    try {
                        // Get int value for the month on string
                        monthone = Character.getNumericValue(string.charAt(5));
                        monthten = Character.getNumericValue(string.charAt(6));
                        monthone = monthone * 10;
                        monthresult = monthone + monthten;
                        // ensure that the month is < 12 for proper input
                        if ((monthresult > 12) || (monthresult < 1)) {
                            return false;
                        }
                    } catch (Exception type) {
                        return false;
                    }
                    // Try statement that only evaluates inputs for DD
                    try {
                        // Get int value for the day on string
                        dayone = Character.getNumericValue(string.charAt(8));
                        dayten = Character.getNumericValue(string.charAt(9));
                        dayone = dayone * 10;
                        dayresult = dayone + dayten;
                        // Check if the day is a proper day
                        if ((dayresult > 31) || (dayresult < 1)) {
                            return false;
                        }
                    } catch (Exception type) {
                        return false;
                    }
                    // check if ANY YYYY, MM, or DD conditions possess any - or / variables
                    if ((string.charAt(0) == '/') || (string.charAt(1) == '/') || (string.charAt(2) == '/') || (string.charAt(3) == '/') ||
                       (string.charAt(0) == '-') || (string.charAt(1) == '-') || (string.charAt(2) == '-') || (string.charAt(3) == '-') ||
                       (string.charAt(5) == '/') || (string.charAt(6) == '/') ||
                       (string.charAt(5) == '-') || (string.charAt(6) == '-') ||
                       (string.charAt(8) == '/') || (string.charAt(9) == '/') ||
                       (string.charAt(8) == '-') || (string.charAt(9) == '-') ){
                        return false;
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkTimeInput(String string) {
        // Checks for proper time input (HH:MM)

        // initialize proper time variables to check
        int TimeNumber;
        int firstdigit;
        int seconddigit;
        int houranswer;
        int thirddigit;
        int fouthdigit;
        int minuteanswer;
        int limitone;
        int limitten;

        // the time of day has to be 5 Characters long
        if (string.length() == 5) {
            // check for : in the correct index
            if (string.charAt(2) == ':') {
                // Try statement to check for proper int input for HH
                try {
                    // convert HH to int
                    firstdigit = Character.getNumericValue(string.charAt(0));
                    seconddigit = Character.getNumericValue(string.charAt(1));
                    firstdigit = firstdigit * 10;
                    houranswer = firstdigit + seconddigit;
                    // HH needs to be lower than 24
                    if (houranswer > 24){
                        return false;
                    }
                    // in the case that HH is = 24, we cannot allow for MM to be anything other than 00
                    if (houranswer == 24){
                        limitone = Character.getNumericValue(string.charAt(3));
                        limitten = Character.getNumericValue(string.charAt(4));
                        if (limitone != 0){
                            return false;
                        }
                        if (limitten != 0){
                            return false;
                        }
                    }

                }catch(Exception type){
                    return false;
                }
                // check for numerical MM input
                try{
                    thirddigit = Character.getNumericValue(string.charAt(3));
                    fouthdigit = Character.getNumericValue(string.charAt(4));
                    thirddigit = thirddigit*10;
                    minuteanswer = fouthdigit + thirddigit;
                    // Minute int has to be 59 and under
                    if (minuteanswer > 59){
                        return false;
                    }
                }catch(Exception type){
                    return false;
                }
                // check if there are : characters despite proper format
                if ((string.charAt(0) == ':') || (string.charAt(1) == ':') || (string.charAt(3) == ':') || (string.charAt(4) == ':') ){
                    return false;
                }
                return true;
            }
        }
        return false;
    }



}
