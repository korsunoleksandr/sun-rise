package com.korsun.sunrise.engine;

import com.korsun.sunrise.api.response.AllCitiesCurrentWeatherResponse;
import com.korsun.sunrise.api.response.CurrentWeatherResponse;
import com.korsun.sunrise.api.response.Main;
import com.korsun.sunrise.api.response.Weather;
import com.korsun.sunrise.api.response.Wind;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.MockTextGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by okorsun on 31.07.16.
 */
public class HourlyRespones {

    private static final MockTextGenerator tg = new MockTextGenerator(13);
    private static final Random r = new Random(13);

    private HourlyRespones() {
        // no - ops
    }

    public static AllCitiesCurrentWeatherResponse one(City city){
        Weather weather = new Weather();
        weather.description = tg.nextString(5);
        weather.icon = tg.nextString(3);
        weather.id = r.nextInt();
        weather.main = tg.nextString(3);


        CurrentWeatherResponse currentWeatherResponse =
                new CurrentWeatherResponse();
        currentWeatherResponse.id = city.getId();
        currentWeatherResponse.dt = r.nextInt();
        currentWeatherResponse.main = new Main();
        currentWeatherResponse.main.humidity = r.nextInt();
        currentWeatherResponse.main.pressure = r.nextInt();
        currentWeatherResponse.main.temp = r.nextFloat();
        currentWeatherResponse.main.temp_max = r.nextFloat();
        currentWeatherResponse.main.temp_min = r.nextFloat();
        currentWeatherResponse.name = tg.nextString(5);
        currentWeatherResponse.weather = Collections.singletonList(weather);
        currentWeatherResponse.wind = new Wind();
        currentWeatherResponse.wind.deg = r.nextInt();
        currentWeatherResponse.wind.speed = r.nextInt();

        AllCitiesCurrentWeatherResponse allCitiesCurrentWeatherResponse =
                new AllCitiesCurrentWeatherResponse();

        allCitiesCurrentWeatherResponse.list = new ArrayList<>();
        allCitiesCurrentWeatherResponse.list.add(currentWeatherResponse);
        allCitiesCurrentWeatherResponse.list.add(currentWeatherResponse);

        return allCitiesCurrentWeatherResponse;
    }
}
