package com.korsun.sunrise.engine.converter;

import javax.inject.Inject;

/**
 * Created by okorsun on 29.07.16.
 */
public final class Converters {

    private final CurrentWeatherAllCitiesInfoConverter currentWeatherAllCitiesInfoConverter;
    private final WeeklyWeatherInfoConverter weeklyWeatherInfoConverter;
    private final HoursWeatherInfoConverter hoursWeatherInfoConverter;

    @Inject
    public Converters(CurrentWeatherAllCitiesInfoConverter currentWeatherAllCitiesInfoConverter,
                      WeeklyWeatherInfoConverter weeklyWeatherInfoConverter,
                      HoursWeatherInfoConverter hoursWeatherInfoConverter) {
        this.currentWeatherAllCitiesInfoConverter = currentWeatherAllCitiesInfoConverter;
        this.weeklyWeatherInfoConverter = weeklyWeatherInfoConverter;
        this.hoursWeatherInfoConverter = hoursWeatherInfoConverter;
    }

    public CurrentWeatherAllCitiesInfoConverter getCurrentWeatherAllCitiesInfoConverter() {
        return currentWeatherAllCitiesInfoConverter;
    }

    public WeeklyWeatherInfoConverter getWeeklyWeatherInfoConverter() {
        return weeklyWeatherInfoConverter;
    }

    public HoursWeatherInfoConverter getHoursWeatherInfoConverter() {
        return hoursWeatherInfoConverter;
    }
}
