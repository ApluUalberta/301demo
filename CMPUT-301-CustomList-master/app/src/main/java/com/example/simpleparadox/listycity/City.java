package com.example.simpleparadox.listycity;

public class City implements Comparable<City>{
    private String city;
    private String province;
    /**
     * This method constructs a City object to manipulate
     * @param city is the name of the city to manipulate
     * @param province is the name of the province in which the city resides
     * @return initializes the city objects variables
     *
     */
    City(String city, String province){
        this.city = city;
        this.province = province;
    }

    /**
     * This method retreives the object city's name
     * @return returns the given city object's name
     */
    String getCityName(){
        return this.city;
    }
    /**
     * This method retreives the object city's province name
     * @return returns the given city object's province name
     */
    String getProvinceName(){
        return this.province;
    }

    /**
     * This method compares the city name to the objects given name
     * @return returns 0 if the given city's name is equal to the argument cities name (if they refer to the same object)
     */
    @Override
    public int compareTo(City city) {
        return this.city.compareTo(city.getCityName());
    }
}
