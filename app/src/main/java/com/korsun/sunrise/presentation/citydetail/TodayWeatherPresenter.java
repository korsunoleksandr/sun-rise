package com.korsun.sunrise.presentation.citydetail;

import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.di.UiScope;
import com.korsun.sunrise.engine.WeatherManager;
import com.korsun.sunrise.presentation.base.Presenter;
import com.korsun.sunrise.presentation.base.PresenterView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by okorsun on 31.07.16.
 */

@UiScope
public final class TodayWeatherPresenter extends Presenter<TodayWeatherPresenter.TodayWeatherView> {

    public interface TodayWeatherView extends PresenterView {

        void setData(List<HourlyWeatherInfo> data);
    }

    private final WeatherManager weatherManager;

    private final City city;

    @Inject
    public TodayWeatherPresenter(WeatherManager weatherManager, City city) {
        this.weatherManager = weatherManager;
        this.city = city;
    }

    @Override
    protected void onAttach() {
        weatherManager.getTodayWeather(city)
                .compose(latestCache())
                .subscribe(delivery -> {
                    delivery.split(TodayWeatherView::setData);
                });
    }

    public City getCity(){
        return city;
    }
}
