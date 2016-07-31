package com.korsun.sunrise.presentation.citydetail;

import com.korsun.sunrise.db.City;
import com.korsun.sunrise.di.UiScope;
import com.korsun.sunrise.engine.WeatherManager;
import com.korsun.sunrise.presentation.base.Presenter;
import com.korsun.sunrise.presentation.base.PresenterView;

import javax.inject.Inject;

/**
 * Created by okorsun on 28.07.16.
 */

@UiScope
public final class CityDetailPresenter extends Presenter<CityDetailPresenter.CityDetailView> {
    public interface CityDetailView extends PresenterView {
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
}
