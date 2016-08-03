package com.korsun.sunrise.ui.citydetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.korsun.sunrise.R;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.di.component.ApplicationComponent;
import com.korsun.sunrise.di.component.DaggerAddCityActivityComponent;
import com.korsun.sunrise.di.component.UiComponent;
import com.korsun.sunrise.presentation.base.BaseActivity;
import com.korsun.sunrise.presentation.citydetail.AddCityPresenter;
import com.korsun.sunrise.presentation.citydetail.AddCityPresenter.AddCityView;
import com.korsun.sunrise.ui.citydetail.renderer.AddCityItemRenderer;
import com.korsun.sunrise.ui.citydetail.renderer.CityItemRenderer;
import com.korsun.sunrise.ui.widget.DividerItemDecoration;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by okorsun on 01.08.16.
 */
public class AddCityActivity extends BaseActivity<AddCityPresenter, AddCityView>
        implements AddCityView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.cities_rv)
    RecyclerView citiesRV;

    private RVRendererAdapter<City> cityAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AddCityItemRenderer renderer = new AddCityItemRenderer(cityAction);
        cityAdapter = createRendererAdapter(renderer);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        citiesRV.addItemDecoration(new DividerItemDecoration(this, R.drawable.list_divider));
        citiesRV.setLayoutManager(layoutManager);
        citiesRV.setAdapter(cityAdapter);
        setData(getPresenter().getUninstaledCities());
    }

    private AddCityItemRenderer.CityAction cityAction =
            city -> {
                city.setInstalled(true);
                getPresenter().installCity(city);
                setData(getPresenter().getUninstaledCities());
            };



    private void setData(List<City> data) {
        cityAdapter.clear();
        cityAdapter.addAll(data);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected AddCityView getPresenterView() {
        return this;
    }

    @Override
    protected UiComponent<AddCityPresenter, AddCityView> createComponent(ApplicationComponent applicationComponent) {
        return DaggerAddCityActivityComponent.builder()
                .applicationComponent(applicationComponent)

                .build();
    }

    private static <T> RVRendererAdapter<T> createRendererAdapter(Renderer<T> renderer) {
        RendererBuilder<T> rendererBuilder = new RendererBuilder<>(renderer);
        return new RVRendererAdapter<>(rendererBuilder, new ListAdapteeCollection<T>());
    }
}
