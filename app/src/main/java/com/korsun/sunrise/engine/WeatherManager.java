package com.korsun.sunrise.engine;

import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.CityDao;
import com.korsun.sunrise.db.DailyWeatherInfo;
import com.korsun.sunrise.db.HourlyWeatherInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by okorsun on 28.07.16.
 */
public interface WeatherManager {

    Observable<List<HourlyWeatherInfo>> getAllCitiesCurrentWeather();

    Observable<List<HourlyWeatherInfo>> getTodayWeather(City city);

    Observable<List<HourlyWeatherInfo>> getThreeDaysWeather(City city);

    Observable<List<DailyWeatherInfo>> getWeekWeather(City city);

}
