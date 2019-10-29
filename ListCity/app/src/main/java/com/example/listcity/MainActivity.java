package com.example.listcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> cityDataList;
    /*Button Addcity;
    Button confirm_city;
    Button removecity;
    String selector;
    String new_city;
    EditText text_id;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);


        String[] cities = {"Edmonton", "Vancouver", "toronto", "Hamilton", "Denver", "Los Angeles"};
        String[] provinces = {"AB", "BC", "ON", "ON", "CO", "CA"};

        cityDataList = new ArrayList<>();

        for (int i = 0; i < cities.length; i++) {
            cityDataList.add((new City(cities[i], provinces[i])));
        }

        cityAdapter = new CustomList(this, cityDataList);

        cityList.setAdapter(cityAdapter);


        final FloatingActionButton addCityButton = findViewById(R.id.add_city_button);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");
            }
        });

    }


    @Override
    public void onOkPressed(City newCity) { cityAdapter.add(newCity);}




        /*dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));*/



        /*cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selector = ((TextView) view.findViewById(R.id.content_view)).getText().toString();
            }
        });

        Addcity = findViewById(R.id.add_button);
        removecity = findViewById(R.id.remove_button);
        confirm_city = findViewById(R.id.confirm_button);

        Addcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //... create a text fill
                text_id = findViewById(R.id.editText);
                text_id.setVisibility(View.VISIBLE);
                confirm_city = findViewById(R.id.confirm_button);
                confirm_city.setVisibility(View.VISIBLE);

                // wait for input?

            }
        });

        confirm_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // the code below does not work, not sure why
                //new_city = ((EditText) view.findViewById(R.id.editText)).getText().toString();
                //dataList.add(new_city);

                EditText tv = findViewById(R.id.editText);
                new_city = tv.getText().toString();
                dataList.add(new_city);

                cityList.setAdapter(cityAdapter);

            }
        });

        removecity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //....
                dataList.remove(selector);
                cityList.setAdapter(cityAdapter);
            }
        });


        cityAdapter = new ArrayAdapter<>(this, R.layout.content,dataList);

        cityList.setAdapter(cityAdapter);*/

}
