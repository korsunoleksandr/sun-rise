package com.korsun.sunrise.presentation.citydetail;

import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.di.UiScope;
import com.korsun.sunrise.engine.WeatherManager;
import com.korsun.sunrise.presentation.base.Presenter;
import com.korsun.sunrise.presentation.base.PresenterView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.Subscriptions;
import timber.log.Timber;

/**
 * Created by okorsun on 01.08.16.
 */

@UiScope
public final class CityListPresenter extends Presenter<CityListPresenter.CityListView> {

    private Subscription subscription = Subscriptions.empty();

    public interface CityListView extends PresenterView {
        void setData(List<HourlyWeatherInfo> data);
    }

    private final WeatherManager weatherManager;

    @Inject
    public CityListPresenter(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;

    }

    @Override
    protected void onAttach() {
        subscription = weatherManager.getAllCitiesCurrentWeather()
                .subscribe(hourlyWeatherInfos -> {
                    getView().setData(hourlyWeatherInfos);
                },
                        throwable -> {
                            Timber.e("getAllCitiesCurrentWeather error: %s", throwable.getMessage());

                        });
    }

    @Override
    protected void onDetach() {
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}
