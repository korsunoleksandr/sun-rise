package com.korsun.sunrise.engine;

import com.korsun.sunrise.BuildConfig;
import com.korsun.sunrise.core.AndroidApplication;
import com.korsun.sunrise.db.Cities;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import rx.observers.TestSubscriber;

/**
 * Created by okorsun on 31.07.16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class, application = AndroidApplication.class)
public class IntegrationWeatherManagerTest {

    private WeatherManager weatherManager;

    @Before
    public void setUp() throws Exception {
        AndroidApplication application = (AndroidApplication) RuntimeEnvironment.application;

        weatherManager = application.getApplicationComponent().weatherManager();
    }

    @After
    public void tearDown() throws Exception {
        Robolectric.reset();
    }

    @Test
    public void test() {
        TestSubscriber<List<HourlyWeatherInfo>> subscriber = new TestSubscriber<>();

        weatherManager
                .getAllCitiesCurrentWeather()
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
        subscriber.assertValueCount(2);
    }

}
