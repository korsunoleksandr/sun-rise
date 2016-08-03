package com.korsun.sunrise.ui.citydetail;

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
import com.korsun.sunrise.common.Utils;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.korsun.sunrise.di.component.ApplicationComponent;
import com.korsun.sunrise.di.component.DaggerThreeDaysWeatherFragmentComponent;
import com.korsun.sunrise.di.component.UiComponent;
import com.korsun.sunrise.di.module.CityModule;
import com.korsun.sunrise.presentation.base.BaseFragment;
import com.korsun.sunrise.presentation.citydetail.ThreeDaysWeatherPresenter;
import com.korsun.sunrise.ui.citydetail.renderer.HourlyTemperatureRenderer;
import com.korsun.sunrise.ui.citydetail.renderer.HourlyWindRenderer;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.korsun.sunrise.presentation.citydetail.ThreeDaysWeatherPresenter.ThreeDaysWeatherView;

/**
 * Created by okorsun on 31.07.16.
 */
public class ThreeDaysWeatherFragment extends BaseFragment<ThreeDaysWeatherPresenter, ThreeDaysWeatherView>
        implements ThreeDaysWeatherView {

    @Bind(R.id.weather_icon)
    ImageView weatherIcon;
    @Bind(R.id.current_temp)
    TextView currentTemp;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.temp_rv)
    RecyclerView tempRV;
    @Bind(R.id.wind_rv)
    RecyclerView windRV;
    @Bind(R.id.pressure)
    TextView pressure;
    @Bind(R.id.humidity)
    TextView humidity;
    @Bind(R.id.description)
    TextView description;

    private RVRendererAdapter<HourlyWeatherInfo> tempAdapter;
    private RVRendererAdapter<HourlyWeatherInfo> windAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three_days_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        tempAdapter = createRendererAdapter(new HourlyTemperatureRenderer());
        tempRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        tempRV.setAdapter(tempAdapter);

        windAdapter = createRendererAdapter(new HourlyWindRenderer());
        windRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        windRV.setAdapter(windAdapter);
    }

    @Override
    public void setData(List<HourlyWeatherInfo> data) {
        tempAdapter.clear();
        tempAdapter.addAll(data);
        tempAdapter.notifyDataSetChanged();

        windAdapter.clear();
        windAdapter.addAll(data);
        windAdapter.notifyDataSetChanged();

        if (data != null && !data.isEmpty()) {
            HourlyWeatherInfo firstItem = data.get(0);
            Glide.with(getContext())
                    .load("http://openweathermap.org/img/w/" + firstItem.getIcon() + ".png")
                    .into(weatherIcon);
            currentTemp.setText(Utils.formatTemp(firstItem.getTemp()));
            date.setText(Utils.getDateString(firstItem.getTimestamp()));
            pressure.setText(Integer.toString(firstItem.getPressure()) + " Па");
            humidity.setText(Integer.toString(firstItem.getHumidity()) + "%");
            description.setText(Utils.capitalise(firstItem.getDescription()));
        }

    }

    @Override
    protected ThreeDaysWeatherView getPresenterView() {
        return this;
    }

    @Override
    protected UiComponent<ThreeDaysWeatherPresenter, ThreeDaysWeatherView> createComponent(ApplicationComponent applicationComponent) {
        City city = (City) getArguments().getSerializable(Constants.ARG_CITY);
        return DaggerThreeDaysWeatherFragmentComponent.builder()
                .applicationComponent(applicationComponent)
                .cityModule(new CityModule(city))
                .build();
    }

    private static <T> RVRendererAdapter<T> createRendererAdapter(Renderer<T> renderer) {
        RendererBuilder<T> rendererBuilder = new RendererBuilder<>(renderer);
        return new RVRendererAdapter<>(rendererBuilder, new ListAdapteeCollection<T>());
    }
}
