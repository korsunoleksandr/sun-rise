package com.korsun.sunrise.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by okorsun on 29.07.16.
 */
public class DailyWeatherInfoDaoImpl
        extends BaseDaoImpl<DailyWeatherInfo, Integer>
        implements DailyWeatherInfoDao {

    public DailyWeatherInfoDaoImpl(ConnectionSource connectionSource, Class<DailyWeatherInfo> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public List<DailyWeatherInfo> getSevenDaysWeatherInfo(City city) {
        try {
            PreparedQuery<DailyWeatherInfo> query = queryBuilder()
                    .where()
                    .eq(DailyWeatherInfo.CITY, city)
                    .and()
                    .ge(HourlyWeatherInfo.TIMESTAMP, city.getLastDailyUpdate())
                    .prepare();

            return query(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertSevenDaysWeatherInfo(List<DailyWeatherInfo> dailyWeatherInfos) {
        try {
            callBatchTasks(() -> {
                for (DailyWeatherInfo info : dailyWeatherInfos) {
                    createOrUpdate(info);
                }

                return null;
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
