package com.korsun.sunrise.engine.converter;

import com.korsun.sunrise.api.response.DailyResponse;
import com.korsun.sunrise.api.response.Weather;
import com.korsun.sunrise.common.Utils;
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
        city.setLastDaylyUpdate(dailyResponse.list.get(1).dt);
        return result;
    }


    private static DailyWeatherInfo transform(City city, DailyResponse.Data dayWeather) {
        DailyWeatherInfo info = new DailyWeatherInfo();
        Weather weather = dayWeather.weather;
        DailyResponse.Temp temp = dayWeather.temp;

        info.setTempMax(temp.max);
        info.setTempMin(temp.min);
        info.setTempEvenig(temp.eve);
        info.setTempNight(temp.night);
        info.setTempMorning(temp.morn);
        info.setTempDay(temp.day);
        info.setPressure(dayWeather.pressure);
        info.setHumidity(dayWeather.humidity);
        info.setDescription(weather.description);
        info.setIcon(weather.icon);
        info.setWindSpeed(dayWeather.speed);
        info.setWindDegree(dayWeather.deg);
        info.setTimestamp(dayWeather.dt);

        info.setCity(city);

        return info;
    }

}
