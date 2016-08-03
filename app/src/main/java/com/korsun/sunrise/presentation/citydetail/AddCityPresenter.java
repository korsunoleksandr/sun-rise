package com.korsun.sunrise.presentation.citydetail;

import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.CityDao;
import com.korsun.sunrise.di.UiScope;
import com.korsun.sunrise.presentation.base.Presenter;
import com.korsun.sunrise.presentation.base.PresenterView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by okorsun on 28.07.16.
 */

@UiScope
public final class AddCityPresenter extends Presenter<AddCityPresenter.AddCityView> {
    public interface AddCityView extends PresenterView {
    }

    private final CityDao cityDao;

    @Inject
    public AddCityPresenter(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public List<City> getUninstaledCities(){
        return cityDao.getUninstalledCities();
    }

    public void installCity(City city){
        cityDao.insertCity(city);
    }
}
