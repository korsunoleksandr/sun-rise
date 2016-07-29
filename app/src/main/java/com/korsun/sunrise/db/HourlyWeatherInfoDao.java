package com.korsun.sunrise.db;

import java.util.List;

/**
 * Created by okorsun on 28.07.16.
 */
public interface HourlyWeatherInfoDao {

    List<HourlyWeatherInfo> getAllCitiesCurrentWeather(long timestamp);

    List<HourlyWeatherInfo> getThreeDaysWeather(City city);

    List<HourlyWeatherInfo> getOneDayWeather(City city);

    void insertAllCitiesCurrentWeather(List<HourlyWeatherInfo> hourlyWeatherInfos);

    void insertHourlyWeather(List<HourlyWeatherInfo> hourlyWeatherInfos);

}
