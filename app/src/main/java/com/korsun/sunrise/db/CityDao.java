package com.korsun.sunrise.db;

import java.util.List;

/**
 * Created by okorsun on 28.07.16.
 */
public interface CityDao {

    List<City> getInstalledCities();

    List<City> getUninstalledCities();

    void insertCity(City city);
}
