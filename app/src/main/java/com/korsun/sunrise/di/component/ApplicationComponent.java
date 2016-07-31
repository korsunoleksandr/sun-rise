package com.korsun.sunrise.di.component;

import android.content.Context;

import com.korsun.sunrise.api.RestApi;
import com.korsun.sunrise.db.CityDao;
import com.korsun.sunrise.db.DailyWeatherInfoDao;
import com.korsun.sunrise.db.HourlyWeatherInfoDao;
import com.korsun.sunrise.di.module.ApplicationModule;
import com.korsun.sunrise.di.module.DbModule;
import com.korsun.sunrise.engine.WeatherManager;
import com.korsun.sunrise.engine.schedulers.RxSchedulers;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by okorsun on 28.07.16.
 */
@Component(modules = {ApplicationModule.class, DbModule.class})
@Singleton
public interface ApplicationComponent {

    Context context();

    RxSchedulers rxSchedulers();

    WeatherManager weatherManager();
}
