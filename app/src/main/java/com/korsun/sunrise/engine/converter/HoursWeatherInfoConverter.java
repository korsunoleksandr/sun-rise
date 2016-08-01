package com.korsun.sunrise.engine.converter;

import com.korsun.sunrise.api.response.ThreeHoursResponse;
import com.korsun.sunrise.api.response.Weather;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by okorsun on 28.07.16.
 */
public final class HoursWeatherInfoConverter {

    @Inject
    public HoursWeatherInfoConverter() {
        // no-op
    }

    public List<HourlyWeatherInfo> call(City city, ThreeHoursResponse threeHoursResponse) {
        List<HourlyWeatherInfo> result = new ArrayList<>();
        for (ThreeHoursResponse.Data hourlyData : threeHoursResponse.list) {
            result.add(transform(city, hourlyData));
        }
        city.setLastHourlyUpdate(threeHoursResponse.list.get(0).dt);

        return result;
    }


    private static HourlyWeatherInfo transform(City city, ThreeHoursResponse.Data hourlyData) {
        HourlyWeatherInfo info = new HourlyWeatherInfo();
        Weather weather = hourlyData.weather.get(0);
        info.setId((city.getId() ^ hourlyData.dt));
        info.setTemp(hourlyData.main.temp);
        info.setTempMax(hourlyData.main.temp_max);
        info.setTempMin(hourlyData.main.temp_min);
        info.setPressure((int)hourlyData.main.pressure);
        info.setHumidity((int)hourlyData.main.humidity);
        info.setDescription(weather.description);
        info.setIcon(weather.icon);
        info.setWindSpeed(hourlyData.wind.speed);
        info.setWindDegree(hourlyData.wind.deg);
        info.setTimestamp(hourlyData.dt);

        info.setCity(city);

        return info;
    }

}
