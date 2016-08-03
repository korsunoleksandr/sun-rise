package com.korsun.sunrise.presentation.citydetail;

import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.di.UiScope;
import com.korsun.sunrise.engine.WeatherManager;
import com.korsun.sunrise.presentation.base.Presenter;
import com.korsun.sunrise.presentation.base.PresenterView;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by okorsun on 28.07.16.
 */

@UiScope
public final class CityDetailPresenter extends Presenter<CityDetailPresenter.CityDetailView> {
    public interface CityDetailView extends PresenterView {
        void shareWeather(HourlyWeatherInfo weatherInfo);
    }

    private final WeatherManager weatherManager;
    private final City city;

    @Inject
    public CityDetailPresenter(WeatherManager weatherManager, City city) {
        this.weatherManager = weatherManager;
        this.city = city;
    }

    public City getCurrentCity() {
        return city;
    }

    public void shareCurrentWeather() {
        weatherManager.getTodayWeather(city)
                .take(1)
                .map(list -> list.get(0))
                .subscribe((weatherInfo) -> {
                    getView().shareWeather(weatherInfo);
                });
    }
}
