package com.korsun.sunrise.engine;

import com.korsun.sunrise.MockSchedulers;
import com.korsun.sunrise.api.RestApi;
import com.korsun.sunrise.api.response.AllCitiesCurrentWeatherResponse;
import com.korsun.sunrise.db.Cities;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.CityDao;
import com.korsun.sunrise.db.DailyWeatherInfoDao;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.db.HourlyWeatherInfoDao;
import com.korsun.sunrise.engine.converter.Converters;
import com.korsun.sunrise.engine.converter.CurrentWeatherAllCitiesInfoConverter;
import com.korsun.sunrise.engine.converter.HoursWeatherInfoConverter;
import com.korsun.sunrise.engine.converter.WeeklyWeatherInfoConverter;
import com.korsun.sunrise.engine.schedulers.RxSchedulers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.*;

/**
 * Created by okorsun on 31.07.16.
 */
public class WeatherManagerImplTest {

    @Mock
    private HourlyWeatherInfoDao hourlyWeatherInfoDao;

    @Mock
    private DailyWeatherInfoDao dailyWeatherInfoDao;

    @Mock
    private CityDao cityDao;

    @Mock
    private RestApi restApi;

    @Mock
    private LastUpdateStorage lastUpdateStorage;

    private RxSchedulers rxSchedulers;

    private Converters converters;

    private WeatherManager weatherManager;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        rxSchedulers = new MockSchedulers();
        converters = new Converters(
                new CurrentWeatherAllCitiesInfoConverter(),
                new WeeklyWeatherInfoConverter(),
                new HoursWeatherInfoConverter());

        weatherManager = new WeatherManagerImpl(
                hourlyWeatherInfoDao,
                dailyWeatherInfoDao,
                cityDao,
                restApi,
                lastUpdateStorage,
                converters,
                rxSchedulers);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetAllCitiesCurrentWeather() throws Exception {
        List<City> cities = Cities.cities(1);
        long timestamp = -1;

        AllCitiesCurrentWeatherResponse response = HourlyRespones.one(cities.get(0));

        doReturn(cities).when(cityDao).getInstalledCities();
        doReturn(rx.Observable.just(timestamp)).when(lastUpdateStorage).getLastAllCitiesWeatherUpdate();
        doReturn(Collections.emptyList()).when(hourlyWeatherInfoDao).getAllCitiesCurrentWeather(cities);
        doReturn(Observable.just(response))
                .when(restApi).getAllCitiesCurrentWeather(String.valueOf(cities.get(0).getId()));

        List<HourlyWeatherInfo> expected =
                converters.getCurrentWeatherAllCitiesInfoConverter().call(cities, response);

        TestSubscriber<List<HourlyWeatherInfo>> subscriber = new TestSubscriber<>();

        weatherManager.getAllCitiesCurrentWeather().subscribe(subscriber);

        subscriber.awaitTerminalEvent();

        subscriber.assertCompleted();
        subscriber.assertNoErrors();

        subscriber.assertValues(Collections.<HourlyWeatherInfo>emptyList(), expected);
    }

}