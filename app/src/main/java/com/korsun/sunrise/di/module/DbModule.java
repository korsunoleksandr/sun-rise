package com.korsun.sunrise.di.module;

import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.CityDao;
import com.korsun.sunrise.db.CityDaoImpl;
import com.korsun.sunrise.db.DailyWeatherInfo;
import com.korsun.sunrise.db.DailyWeatherInfoDao;
import com.korsun.sunrise.db.DailyWeatherInfoDaoImpl;
import com.korsun.sunrise.db.DbHelper;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.db.HourlyWeatherInfoDao;
import com.korsun.sunrise.db.HourlyWeatherInfoDaoImpl;

import java.sql.SQLException;

import dagger.Module;
import dagger.Provides;

/**
 * Created by okorsun on 31.07.16.
 */

@Module
public class DbModule {

    @Provides
    public CityDao provideCityDao(DbHelper dbHelper) {
        try {
            return new CityDaoImpl(dbHelper.getConnectionSource(), City.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    public HourlyWeatherInfoDao provideHourlyDao(DbHelper dbHelper, CityDao cityDao) {
        try {
            return new HourlyWeatherInfoDaoImpl(dbHelper.getConnectionSource(), HourlyWeatherInfo.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    public DailyWeatherInfoDao provideDailyDao(DbHelper dbHelper, CityDao cityDao) {
        try {
            return new DailyWeatherInfoDaoImpl(dbHelper.getConnectionSource(), DailyWeatherInfo.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
