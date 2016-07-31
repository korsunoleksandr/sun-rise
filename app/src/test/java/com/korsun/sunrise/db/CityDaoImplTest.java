package com.korsun.sunrise.db;

import com.korsun.sunrise.BuildConfig;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by okorsun on 31.07.16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class CityDaoImplTest {

    private CityDao dao;

    @Before
    public void setUp() throws Exception {
        DbHelper dbHelper = new DbHelper(RuntimeEnvironment.application);
        dao = new CityDaoImpl(dbHelper.getConnectionSource(), City.class);
    }

    @After
    public void tearDown() throws Exception {
        Robolectric.reset();
    }

    @Test
    public void testGetInstalledCities() throws Exception {
        City city = Cities.city();
        dao.insertCity(city);

//        List<City> expected = Collections.singletonList(city);
        List<City> actual = dao.getInstalledCities();

        Assert.assertTrue(actual.contains(city));
    }

}