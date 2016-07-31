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
 * Created by okorsun on 01.08.16.
 */

@UiScope
public class CityListPresenter extends Presenter<CityListPresenter.CityListView> {
    public interface CityListView extends PresenterView {
        void setData(List<HourlyWeatherInfo> data);
    }

    private final WeatherManager weatherManager;

    @Inject
    public CityListPresenter(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;

        weatherManager.getAllCitiesCurrentWeather()
                .compose(latestCache())
                .subscribe(delivery ->
                        delivery.split((view, data) -> view.setData(data),
                                (view, t) -> {
                                    throw new RuntimeException(t);
                                }));
    }

}
