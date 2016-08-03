package com.korsun.sunrise.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
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
            PreparedQuery<City> query = queryBuilder()
                    .where()
                    .eq(City.IS_INSTALLED, true)
                    .prepare();

            return query(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<City> getUninstalledCities() {
        try {
            PreparedQuery<City> query = queryBuilder()
                    .where()
                    .eq(City.IS_INSTALLED, false)
                    .prepare();

            return query(query);
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
