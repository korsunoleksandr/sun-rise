package com.korsun.sunrise.ui.citydetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.korsun.sunrise.R;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.di.component.ApplicationComponent;
import com.korsun.sunrise.di.component.DaggerTodayWeatherFragmentComponent;
import com.korsun.sunrise.di.component.UiComponent;
import com.korsun.sunrise.di.module.CityModule;
import com.korsun.sunrise.presentation.base.BaseFragment;
import com.korsun.sunrise.presentation.citydetail.TodayWeatherPresenter;
import com.korsun.sunrise.ui.citydetail.renderer.HourlyTemperatureRenderer;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.korsun.sunrise.presentation.citydetail.TodayWeatherPresenter.*;

/**
 * Created by okorsun on 31.07.16.
 */
public class TodayWeatherFragment extends BaseFragment<TodayWeatherPresenter, TodayWeatherView>
        implements TodayWeatherView {

    @Bind(R.id.weather_icon)
    ImageView weatherIcon;

    @Bind(R.id.current_temp)
    TextView currentTemp;

    @Bind(R.id.temp_rv)
    RecyclerView tempRV;

    @Bind(R.id.wind_rv)
    RecyclerView windRV;

    private RVRendererAdapter<HourlyWeatherInfo> tempAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        HourlyTemperatureRenderer renderer = new HourlyTemperatureRenderer();
        tempAdapter = createRendererAdapter(renderer);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        tempRV.setLayoutManager(layoutManager);
        tempRV.setAdapter(tempAdapter);
    }

    @Override
    public void setData(List<HourlyWeatherInfo> data) {
        tempAdapter.clear();
        tempAdapter.addAll(data);
        tempAdapter.notifyDataSetChanged();

        if (data != null && !data.isEmpty()) {
            Glide.with(getContext())
                    .load("http://openweathermap.org/img/w/" + data.get(0).getIcon() + ".png")
                    .into(weatherIcon);
            currentTemp.setText(Float.toString(data.get(0).getTemp()));
        }
    }

    @Override
    protected TodayWeatherView getPresenterView() {
        return this;
    }

    @Override
    protected UiComponent<TodayWeatherPresenter, TodayWeatherView> createComponent(ApplicationComponent applicationComponent) {
        City city = (City) getArguments().getSerializable(Constants.ARG_CITY);
        return DaggerTodayWeatherFragmentComponent.builder()
                .applicationComponent(applicationComponent)
                .cityModule(new CityModule(city))
                .build();
    }

    private static <T> RVRendererAdapter<T> createRendererAdapter(Renderer<T> renderer) {
        RendererBuilder<T> rendererBuilder = new RendererBuilder<>(renderer);
        return new RVRendererAdapter<>(rendererBuilder, new ListAdapteeCollection<T>());
    }
}
