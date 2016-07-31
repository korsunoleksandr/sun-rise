package com.korsun.sunrise.api;

import com.korsun.sunrise.api.response.AllCitiesCurrentWeatherResponse;
import com.korsun.sunrise.api.response.DailyResponse;
import com.korsun.sunrise.api.response.ThreeHoursResponse;
import com.korsun.sunrise.api.response.CurrentWeatherResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by okorsun on 28.07.16.
 */
public interface RestApi {

    @GET("weather")
    Observable<CurrentWeatherResponse> getCurrentWeather(@Query("id") int id);

    @GET("group")
    Observable<AllCitiesCurrentWeatherResponse> getAllCitiesCurrentWeather(@Query("id") String ids);

    @GET("forecast")
    Observable<ThreeHoursResponse> getThreeHoursForecast(@Query("id") int id, @Query("cnt") int cnt);

    @GET("forecast/daily")
    Observable<DailyResponse> getDaylyForecast(@Query("id") int id, @Query("cnt") int days);

}
