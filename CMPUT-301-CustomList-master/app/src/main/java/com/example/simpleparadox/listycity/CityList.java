package com.example.simpleparadox.listycity;
/*
* This is a class that keeps track of our city objects
 */

import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityList {
    private List<City> cities = new ArrayList<>();

    /**
     * This method will add cities to the list if the city does not exist
     * @param city This is a candidate city add
     *
     */
    public void add(City city){
        if(cities.contains(city)){
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }
    /**
     * This method returns a sorted list of cities
     * @return Return the sorted list
     *
     */
    public List<City> getCities(){
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }
    /**
     * This method returns a boolean if the argument city is in the list
     * @param city This is a candidate city to determine if existing in the list
     * @return Return true/false if the city does/doesn't exist.
     *
     */
    public boolean hasCity(City city){
        for (int i = 0; i < getCities().size(); i++){
            if (city == cities.get(i)){
                return true;
            }
        }
        return false;
    }
    /**
     * This method deletes the argument city
     * @param city This is a candidate city to delete
     * If the city to delete does not exist, throw an exception
     * @return Return back to caller

     */
    public void delete(City city){
        for (int i = 0; i < getCities().size(); i++){
            if (city == cities.get(i)){
                cities.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException();
    }
    /**
     * This method returns the number of elements in the city List
     * @return Return the number of cities (int) of the list.
     *
     */
    public int count(){
        return getCities().size();
    }
}
