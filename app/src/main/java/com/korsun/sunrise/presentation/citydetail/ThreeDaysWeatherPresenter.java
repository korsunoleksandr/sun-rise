package com.korsun.sunrise.presentation.citydetail;

import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.di.UiScope;
import com.korsun.sunrise.engine.WeatherManager;
import com.korsun.sunrise.presentation.base.Presenter;
import com.korsun.sunrise.presentation.base.PresenterView;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by okorsun on 31.07.16.
 */

@UiScope
public final class ThreeDaysWeatherPresenter extends Presenter<ThreeDaysWeatherPresenter.ThreeDaysWeatherView> {

    public interface ThreeDaysWeatherView extends PresenterView {

        void setData(List<HourlyWeatherInfo> data);
    }

    private final WeatherManager weatherManager;

    private final City city;

    @Inject
    public ThreeDaysWeatherPresenter(WeatherManager weatherManager, City city) {
        this.weatherManager = weatherManager;
        this.city = city;
    }

    @Override
    protected void onAttach() {
        weatherManager.getThreeDaysWeather(city)
                .compose(latestCache())
                .subscribe(delivery ->
                        delivery.split(ThreeDaysWeatherView::setData,
                                (view, t) -> {
                                    Timber.e("getToday weather error, %s", t.getMessage());
                                }));
    }

    public City getCity() {
        return city;
    }
}
