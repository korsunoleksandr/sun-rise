package com.korsun.sunrise.engine;

import com.korsun.sunrise.api.RestApi;
import com.korsun.sunrise.common.RxUtils;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.CityDao;
import com.korsun.sunrise.db.DailyWeatherInfo;
import com.korsun.sunrise.db.DailyWeatherInfoDao;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.db.HourlyWeatherInfoDao;
import com.korsun.sunrise.engine.converter.Converters;
import com.korsun.sunrise.engine.converter.CurrentWeatherAllCitiesInfoConverter;
import com.korsun.sunrise.engine.schedulers.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by okorsun on 28.07.16.
 */
public final class WeatherManagerImpl implements WeatherManager {

    public static final int ONE_DAY_PERIOD_COUNT = 8;
    public static final int FOUR_DAYS_PERIOD_COUNT = 32;
    public static final int SEVEN_DAYS_PERIOD_COUNT = 7;
    private final HourlyWeatherInfoDao hourlyWeatherInfoDao;
    private final DailyWeatherInfoDao dailyWeatherInfoDao;
    private final CityDao cityDao;
    private final RestApi api;
    private final LastUpdateStorage lastUpdateStorage;
    private final Converters converters;
    private final RxSchedulers rxSchedulers;

    @Inject
    public WeatherManagerImpl(HourlyWeatherInfoDao hourlyWeatherInfoDao,
                              DailyWeatherInfoDao dailyWeatherInfoDao,
                              CityDao cityDao,
                              RestApi api,
                              LastUpdateStorage lastUpdateStorage,
                              Converters converters, RxSchedulers rxSchedulers) {
        this.hourlyWeatherInfoDao = hourlyWeatherInfoDao;
        this.dailyWeatherInfoDao = dailyWeatherInfoDao;
        this.cityDao = cityDao;
        this.api = api;
        this.lastUpdateStorage = lastUpdateStorage;
        this.converters = converters;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public Observable<List<HourlyWeatherInfo>> getAllCitiesCurrentWeather() {
        return Observable.mergeDelayError(getAllCitiesCurrentWeatherFromDB(), fetchAllCitiesCurrentWeather())
                .observeOn(rxSchedulers.getMain());
    }

    @Override
    public Observable<List<HourlyWeatherInfo>> getTodayWeather(City city) {
        return Observable.mergeDelayError(getTodayWeatherFromDB(city), fetchTodayWeather(city))
                .observeOn(rxSchedulers.getMain());
    }

    @Override
    public Observable<List<HourlyWeatherInfo>> getThreeDaysWeather(City city) {
        return Observable.mergeDelayError(getThreeDaysWeatherFromDB(city), fetchThreeDaysWeather(city))
                .observeOn(rxSchedulers.getMain());
    }

    @Override
    public Observable<List<DailyWeatherInfo>> getWeekWeather(City city) {
        return Observable.mergeDelayError(getWeekWeatherFromDB(city), fetchWeekWeather(city))
                .observeOn(rxSchedulers.getMain());
    }


    private Observable<List<HourlyWeatherInfo>> getTodayWeatherFromDB(City city) {
        return RxUtils.create(() -> hourlyWeatherInfoDao.getOneDayWeather(city))
                .subscribeOn(rxSchedulers.getDB());
    }

    private Observable<List<HourlyWeatherInfo>> fetchTodayWeather(City city) {
        return api.getThreeHoursForecast(city.getId(), ONE_DAY_PERIOD_COUNT)
                .map(response -> converters.getHoursWeatherInfoConverter().call(city, response))
                .subscribeOn(rxSchedulers.getNetwork())
                .observeOn(rxSchedulers.getDB())
                .doOnNext(hourlyWeatherInfos -> cityDao.insertCity(city))
                .doOnNext(hourlyWeatherInfoDao::insertHourlyWeather);
    }

    private Observable<List<HourlyWeatherInfo>> getThreeDaysWeatherFromDB(City city) {
        return RxUtils.create(() -> hourlyWeatherInfoDao.getThreeDaysWeather(city))
                .subscribeOn(rxSchedulers.getDB());
    }

    private Observable<List<HourlyWeatherInfo>> fetchThreeDaysWeather(City city) {
        return api.getThreeHoursForecast(city.getId(), FOUR_DAYS_PERIOD_COUNT)
                .map(response -> converters.getHoursWeatherInfoConverter().call(city, response))
                .subscribeOn(rxSchedulers.getNetwork())
                .observeOn(rxSchedulers.getDB())
                .doOnNext(hourlyWeatherInfos -> cityDao.insertCity(city))
                .doOnNext(hourlyWeatherInfoDao::insertHourlyWeather);
    }

    private Observable<List<DailyWeatherInfo>> getWeekWeatherFromDB(City city) {
        return RxUtils.create(() -> dailyWeatherInfoDao.getSevenDaysWeatherInfo(city))
                .subscribeOn(rxSchedulers.getDB());
    }

    private Observable<List<DailyWeatherInfo>> fetchWeekWeather(City city) {
        return api.getDaylyForecast(city.getId(), SEVEN_DAYS_PERIOD_COUNT)
                .map(response -> converters.getWeeklyWeatherInfoConverter().call(city, response))
                .subscribeOn(rxSchedulers.getNetwork())
                .observeOn(rxSchedulers.getDB())
                .doOnNext( dailyWeatherInfos -> cityDao.insertCity(city))
                .doOnNext(dailyWeatherInfoDao::insertSevenDaysWeatherInfo);
    }


    private Observable<List<HourlyWeatherInfo>> getAllCitiesCurrentWeatherFromDB() {
        return lastUpdateStorage.getLastAllCitiesWeatherUpdate()
                .switchMap(timestamp -> RxUtils.create(() -> hourlyWeatherInfoDao.getAllCitiesCurrentWeather(timestamp))
                        .subscribeOn(rxSchedulers.getDB()));
    }

    private Observable<List<HourlyWeatherInfo>> fetchAllCitiesCurrentWeather() {
        return RxUtils.create(cityDao::getInstalledCities)
                .switchMap(cities -> {
                    List<Integer> ids = new ArrayList<>();
                    for (City city : cities) {
                        ids.add(city.getId());
                    }

                    return api.getAllCitiesCurrentWeather(ids)
                            .map(response -> converters.getCurrentWeatherAllCitiesInfoConverter().call(cities, response));
                })
                .subscribeOn(rxSchedulers.getNetwork())
                .observeOn(rxSchedulers.getDB())
                .doOnNext(this::insertCitiesToDb)
                .doOnNext(hourlyWeatherInfoDao::insertAllCitiesCurrentWeather);
    }

    private void insertCitiesToDb(List<HourlyWeatherInfo> hourlyWeatherInfos) {
        for (HourlyWeatherInfo hourlyWeatherInfo : hourlyWeatherInfos){
            cityDao.insertCity(hourlyWeatherInfo.getCity());
        }
    }

}