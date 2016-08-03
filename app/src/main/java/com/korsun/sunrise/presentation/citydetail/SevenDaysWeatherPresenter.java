package com.korsun.sunrise.presentation.citydetail;

import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.DailyWeatherInfo;
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
public final class SevenDaysWeatherPresenter extends Presenter<SevenDaysWeatherPresenter.SevenDaysWeatherView> {

    public interface SevenDaysWeatherView extends PresenterView {

        void setData(List<DailyWeatherInfo> data);
    }

    private final WeatherManager weatherManager;

    private final City city;

    @Inject
    public SevenDaysWeatherPresenter(WeatherManager weatherManager, City city) {
        this.weatherManager = weatherManager;
        this.city = city;
    }

    @Override
    protected void onAttach() {
        weatherManager.getWeekWeather(city)
                .compose(latestCache())
                .subscribe(delivery ->
                        delivery.split(SevenDaysWeatherView::setData,
                                (view, t) -> {
                                    throw new RuntimeException(t);
//                                    Timber.e("getToday weather error, %s", t.getMessage());
                                }));
    }

    public City getCity() {
        return city;
    }
}
