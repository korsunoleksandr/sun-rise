package com.korsun.sunrise.engine.converter;

import com.korsun.sunrise.api.response.AllCitiesCurrentWeatherResponse;
import com.korsun.sunrise.api.response.CurrentWeatherResponse;
import com.korsun.sunrise.api.response.Weather;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by okorsun on 28.07.16.
 */
public final class CurrentWeatherAllCitiesInfoConverter {

    @Inject
    public CurrentWeatherAllCitiesInfoConverter() {
        // no-op
    }

    public List<HourlyWeatherInfo> call(List<City> cities, AllCitiesCurrentWeatherResponse currentWeatherResponse) {
        List<HourlyWeatherInfo> result = new ArrayList<>();
        for (CurrentWeatherResponse currentWeather : currentWeatherResponse.list) {
            result.add(transform(cities, currentWeather));
        }

        return result;
    }


    private static HourlyWeatherInfo transform(List<City> cities, CurrentWeatherResponse response) {
        HourlyWeatherInfo info = new HourlyWeatherInfo();
        Weather weather = response.weather.get(0);
        info.setTemp(response.main.temp);
        info.setTempMax(response.main.temp_max);
        info.setTempMin(response.main.temp_min);
        info.setPressure((int)response.main.pressure);
        info.setHumidity((int)response.main.humidity);
        info.setDescription(weather.description);
        info.setIcon(weather.icon);
        info.setWindSpeed(response.wind.speed);
        info.setWindDegree(response.wind.deg);
        info.setTimestamp(response.dt);

        for (City city : cities) {
            if (city.getId() == response.id) {
                city.setLastCurrentUpdate(response.dt);
                info.setCity(city);
                info.setId(city.getId() ^ info.getTimestamp());
                break;
            }
        }

        return info;
    }

}
