package com.korsun.sunrise.engine.converter;

import com.korsun.sunrise.api.response.DailyResponse;
import com.korsun.sunrise.api.response.Weather;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.DailyWeatherInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by okorsun on 28.07.16.
 */
public final class WeeklyWeatherInfoConverter {

    @Inject
    public WeeklyWeatherInfoConverter() {
        // no-op
    }

    public List<DailyWeatherInfo> call(City city, DailyResponse dailyResponse) {
        List<DailyWeatherInfo> result = new ArrayList<>();
        for (DailyResponse.Data dayWeather : dailyResponse.list) {
            result.add(transform(city, dayWeather));
        }
        city.setLastDailyUpdate(dailyResponse.list.get(1).dt);
        return result;
    }


    private static DailyWeatherInfo transform(City city, DailyResponse.Data dayWeather) {
        DailyWeatherInfo info = new DailyWeatherInfo();
        Weather weather = dayWeather.weather.get(0);
        DailyResponse.Temp temp = dayWeather.temp;

        info.setId(city.getId() ^ dayWeather.dt);
        info.setTempMax(temp.max);
        info.setTempMin(temp.min);
        info.setTempEvenig(temp.eve);
        info.setTempNight(temp.night);
        info.setTempMorning(temp.morn);
        info.setTempDay(temp.day);
        info.setPressure((int) dayWeather.pressure);
        info.setHumidity((int) dayWeather.humidity);
        info.setDescription(weather.description);
        info.setIcon(weather.icon);
        info.setWindSpeed((int) dayWeather.speed);
        info.setWindDegree((int) dayWeather.deg);
        info.setTimestamp(dayWeather.dt);

        info.setCity(city);

        return info;
    }

}
