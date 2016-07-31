package com.korsun.sunrise.engine;

import android.content.SharedPreferences;

import com.korsun.sunrise.common.RxUtils;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by okorsun on 31.07.16.
 */
public class LastUpdateStorageImpl implements LastUpdateStorage {

    public static final String LAST_ALL_CITIES_WEATHER_UPDATE = "las_all_cities_weather_update";
    private final SharedPreferences sharedPreferences;

    @Inject
    public LastUpdateStorageImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Observable<Long> getLastAllCitiesWeatherUpdate() {
        return RxUtils.create(() -> sharedPreferences.getLong(LAST_ALL_CITIES_WEATHER_UPDATE, -1));
    }

    @Override
    public void setLastAllCitiesWeatherUpdate(long timestamp) {
        sharedPreferences.edit()
                .putLong(LAST_ALL_CITIES_WEATHER_UPDATE, timestamp)
                .apply();
    }
}
