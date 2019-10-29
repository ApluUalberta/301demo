package com.example.simpleparadox.listycity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CityListTest {
    private CityList mockCityList(){
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }
    private City mockCity(){
        return new City("Edmonton","Alberta");
    }

    @Test
    void testAdd(){
        CityList cityList = mockCityList();

        assertEquals(1,cityList.getCities().size());

        City city = new City("Regina","Saskatchewan");

        cityList.add(city);
        assertEquals(2,cityList.getCities().size());
    }

    @Test
    void testAddException(){
        CityList cityList = mockCityList();

        City city = new City("YellowKnife","Northwest Territories");
        cityList.add(city);

        assertThrows(IllegalArgumentException.class, ()->{cityList.add(city);});
    }

    @Test
    void testGetCities() {
        CityList cityList = mockCityList();

        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));

        City city = new City("Charlottetown", "Prince Edward Island");

        cityList.add(city);

        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
    }

    @Test
    void testHasCity(){
        CityList cityList = mockCityList();

        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);

        assertEquals(true, cityList.hasCity(city));
    }
    @Test
    void testDelete(){
        CityList cityList = mockCityList();

        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);

        assertEquals(2, cityList.getCities().size());
        cityList.delete(city);

        assertEquals(1,cityList.getCities().size());
        assertThrows(IllegalArgumentException.class, ()->{cityList.delete(city);});
    }
    @Test
    void testcount(){
        CityList cityList = mockCityList();
        assertEquals(1,cityList.count());
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);
        assertEquals(2,cityList.count());
    }
}
