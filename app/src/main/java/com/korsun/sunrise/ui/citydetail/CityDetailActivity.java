package com.korsun.sunrise.ui.citydetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.korsun.sunrise.R;
import com.korsun.sunrise.common.Utils;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.di.component.ApplicationComponent;
import com.korsun.sunrise.di.component.DaggerCityDetailActivityComponent;
import com.korsun.sunrise.di.component.UiComponent;
import com.korsun.sunrise.di.module.CityModule;
import com.korsun.sunrise.presentation.base.BaseActivity;
import com.korsun.sunrise.presentation.citydetail.CityDetailPresenter;
import com.korsun.sunrise.presentation.citydetail.CityDetailPresenter.CityDetailView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by okorsun on 28.07.16.
 */
public class CityDetailActivity extends BaseActivity<CityDetailPresenter, CityDetailView>
        implements CityDetailView {

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    private WeatherPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getPresenter().getCurrentCity().getName());

        pagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager(), this, getPresenter().getCurrentCity());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        int selectedColor = Color.WHITE;
        int normalColor = ContextCompat.getColor(this, R.color.grey);
        tabLayout.setTabTextColors(normalColor, selectedColor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share){
            getPresenter().shareCurrentWeather();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void shareWeather(HourlyWeatherInfo wi){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Зараз " + wi.getCity().getName() + ": температура " + Utils.formatTemp(wi.getTemp()));
        startActivity(intent);
    }

    @Override
    protected CityDetailView getPresenterView() {
        return this;
    }

    @Override
    protected UiComponent<CityDetailPresenter, CityDetailView> createComponent(ApplicationComponent applicationComponent) {
        City city = (City) getIntent().getSerializableExtra(Constants.ARG_CITY);
        return DaggerCityDetailActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .cityModule(new CityModule(city))
                .build();
    }
}
