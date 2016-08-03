package com.korsun.sunrise.di.component;

import com.korsun.sunrise.di.UiScope;
import com.korsun.sunrise.di.module.CityModule;
import com.korsun.sunrise.presentation.citydetail.SevenDaysWeatherPresenter;
import com.korsun.sunrise.presentation.citydetail.SevenDaysWeatherPresenter.SevenDaysWeatherView;

import dagger.Component;

/**
 * Created by okorsun on 31.07.16.
 */

@UiScope
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {CityModule.class})
public interface SevenDaysWeatherFragmentComponent extends UiComponent<SevenDaysWeatherPresenter, SevenDaysWeatherView> {
}
