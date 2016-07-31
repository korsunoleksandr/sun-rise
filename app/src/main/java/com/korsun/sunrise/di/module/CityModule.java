package com.korsun.sunrise.di.module;

import com.korsun.sunrise.db.City;

import dagger.Module;
import dagger.Provides;

/**
 * Created by okorsun on 31.07.16.
 */

@Module
public final class CityModule {

    private final City city;

    public CityModule(City city) {
        this.city = city;
    }

    @Provides
    public City provideCity() {
        return city;
    }
}
