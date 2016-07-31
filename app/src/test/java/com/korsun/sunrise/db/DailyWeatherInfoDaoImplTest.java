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

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by okorsun on 31.07.16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class DailyWeatherInfoDaoImplTest {

    private CityDao cityDao;
    private DailyWeatherInfoDao dao;

    @Before
    public void setUp() throws Exception {
        DbHelper dbHelper = new DbHelper(RuntimeEnvironment.application);
        cityDao = new CityDaoImpl(dbHelper.getConnectionSource(), City.class);
        dao = new DailyWeatherInfoDaoImpl(dbHelper.getConnectionSource(), DailyWeatherInfo.class);
    }

    @After
    public void tearDown() throws Exception {
        Robolectric.reset();
    }

    @Test
    public void testGetSevenDaysWeatherInfo() throws Exception {
        City city = Cities.city();

        cityDao.insertCity(city);

        List<DailyWeatherInfo> infos = DailyWeatherInfos.dailyInfos(city, 1);
        dao.insertSevenDaysWeatherInfo(infos);

        List<DailyWeatherInfo> actual = dao.getSevenDaysWeatherInfo(city);

        Assert.assertEquals(infos, actual);

    }
}