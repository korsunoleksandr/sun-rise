package com.korsun.sunrise.di.component;

import com.korsun.sunrise.di.UiScope;
import com.korsun.sunrise.di.module.CityModule;
import com.korsun.sunrise.presentation.citydetail.CityDetailPresenter;
import com.korsun.sunrise.presentation.citydetail.CityDetailPresenter.CityDetailView;
import com.korsun.sunrise.presentation.citydetail.CityListPresenter;
import com.korsun.sunrise.ui.citydetail.CityListActivity;

import dagger.Component;

/**
 * Created by okorsun on 31.07.16.
 */

@UiScope
@Component(
        dependencies = {ApplicationComponent.class})
public interface CityListActivityComponent extends UiComponent<CityListPresenter, CityListPresenter.CityListView> {
}
