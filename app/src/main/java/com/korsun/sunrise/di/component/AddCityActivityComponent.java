package com.korsun.sunrise.di.component;

import com.korsun.sunrise.di.UiScope;
import com.korsun.sunrise.presentation.citydetail.AddCityPresenter;
import com.korsun.sunrise.presentation.citydetail.CityListPresenter;

import dagger.Component;

/**
 * Created by okorsun on 31.07.16.
 */

@UiScope
@Component(
        dependencies = {ApplicationComponent.class})
public interface AddCityActivityComponent extends UiComponent<AddCityPresenter, AddCityPresenter.AddCityView> {
}
