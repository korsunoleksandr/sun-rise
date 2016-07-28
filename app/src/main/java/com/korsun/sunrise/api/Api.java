package com.korsun.sunrise.api;

import com.korsun.sunrise.api.response.ThreeHoursResponse;
import com.korsun.sunrise.api.response.CurrentWeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by okorsun on 28.07.16.
 */
public interface Api {

    @GET("weather")
    Observable<CurrentWeatherResponse> getCurrentWeather(@Query("id") int id);

    @GET("forecast")
    Observable<ThreeHoursResponse> getThreeHoursForecast(@Query("id") int id, @Query("cnt") int cnt);

    @GET("forecast/daily")
    Observable<ThreeHoursResponse> getDaylyForecast(@Query("id") int id, @Query("cnt") int days);

}
