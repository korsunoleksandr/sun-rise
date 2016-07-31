package com.korsun.sunrise.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by okorsun on 30.07.16.
 */
public final class CityDaoImpl extends BaseDaoImpl<City, Integer> implements CityDao {

    public CityDaoImpl(ConnectionSource connectionSource, Class<City> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public List<City> getInstalledCities() {
        try {
            return queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertCity(City city) {
        try {
            createOrUpdate(city);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
