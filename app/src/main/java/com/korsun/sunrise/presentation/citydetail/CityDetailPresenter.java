package com.korsun.sunrise.presentation.citydetail;

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

    @Inject
    public CityDetailPresenter(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
    }
}
