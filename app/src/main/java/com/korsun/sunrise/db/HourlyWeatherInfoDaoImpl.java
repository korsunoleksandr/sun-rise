package com.korsun.sunrise.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import com.korsun.sunrise.common.Utils;
import com.korsun.sunrise.engine.LastUpdateStorage;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by okorsun on 28.07.16.
 */
public final class HourlyWeatherInfoDaoImpl
        extends BaseDaoImpl<HourlyWeatherInfo, Integer>
        implements HourlyWeatherInfoDao {

    public HourlyWeatherInfoDaoImpl(ConnectionSource connectionSource, Class<HourlyWeatherInfo> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public List<HourlyWeatherInfo> getAllCitiesCurrentWeather(long timestamp) {
        try {
            PreparedQuery<HourlyWeatherInfo> query = queryBuilder()
                    .where()
                    .eq(HourlyWeatherInfo.TIMESTAMP, timestamp)
                    .prepare();

            return query(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertAllCitiesCurrentWeather(List<HourlyWeatherInfo> hourlyWeatherInfos) {
        try {
            callBatchTasks(() -> {
                for (HourlyWeatherInfo info : hourlyWeatherInfos) {
                    createOrUpdate(info);
                }

                return null;
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HourlyWeatherInfo> getThreeDaysWeather(City city) {
        try {
            long nextDayStartTimestamp = Utils.getNextDayStartTimestamp(city.getLastHourlyUpdate());
            PreparedQuery<HourlyWeatherInfo> query = queryBuilder()
                    .where()
                    .eq(HourlyWeatherInfo.CITY, city)
                    .and()
                    .ge(HourlyWeatherInfo.TIMESTAMP, nextDayStartTimestamp)
                    .and()
                    .lt(HourlyWeatherInfo.TIMESTAMP, nextDayStartTimestamp + LastUpdateStorage.TREE_DAYS_STAMP)
                    .prepare();

            return query(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HourlyWeatherInfo> getOneDayWeather(City city) {
        try {
            long nextDayStartTimestamp = Utils.getNextDayStartTimestamp(city.getLastHourlyUpdate());
            PreparedQuery<HourlyWeatherInfo> query = queryBuilder()
                    .where()
                    .eq(HourlyWeatherInfo.CITY, city)
                    .and()
                    .ge(HourlyWeatherInfo.TIMESTAMP, city.getLastHourlyUpdate())
                    .and()
                    .lt(HourlyWeatherInfo.TIMESTAMP, city.getLastHourlyUpdate() + LastUpdateStorage.ONE_DAY_STAMP)
                    .prepare();

            return query(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertHourlyWeather(List<HourlyWeatherInfo> hourlyWeatherInfos) {
        try {
            callBatchTasks(() -> {
                for (HourlyWeatherInfo info : hourlyWeatherInfos) {
                    createOrUpdate(info);
                }

                return null;
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
