package com.korsun.sunrise.engine;

import com.korsun.sunrise.db.City;

import rx.Observable;

/**
 * Created by okorsun on 28.07.16.
 */
public interface LastUpdateStorage {

    long ONE_DAY_STAMP = 1 * 24 * 60 * 60;
    long TREE_DAYS_STAMP = 3 * 24 * 60 * 60;
    long ONE_WEEK_STAMP = 7 * 24 * 60 * 60;


    Observable<Long> getLastAllCitiesWeatherUpdate();

}
