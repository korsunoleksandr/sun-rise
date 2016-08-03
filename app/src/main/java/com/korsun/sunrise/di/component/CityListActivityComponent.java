package com.korsun.sunrise.di.component;

import com.korsun.sunrise.di.UiScope;
import com.korsun.sunrise.presentation.citydetail.CityListPresenter;

import dagger.Component;

/**
 * Created by okorsun on 31.07.16.
 */

@UiScope
@Component(
        dependencies = {ApplicationComponent.class})
public interface CityListActivityComponent extends UiComponent<CityListPresenter, CityListPresenter.CityListView> {
}
