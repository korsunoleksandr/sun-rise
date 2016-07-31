package com.korsun.sunrise.ui.citydetail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.korsun.sunrise.R;
import com.korsun.sunrise.di.component.ApplicationComponent;
import com.korsun.sunrise.di.component.UiComponent;
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
        getSupportActionBar().setTitle("Tilte");    // TODO: set real

        pagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        int selectedColor = Color.WHITE;
        int normalColor = ContextCompat.getColor(this, R.color.grey);
        tabLayout.setTabTextColors(normalColor, selectedColor);
    }

    @Override
    protected CityDetailView getPresenterView() {
        return this;
    }

    @Override
    protected UiComponent<CityDetailPresenter, CityDetailView> createComponent(ApplicationComponent applicationComponent) {
        return null;
    }
}
