package com.korsun.sunrise.ui.citydetail.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.korsun.sunrise.R;
import com.korsun.sunrise.db.City;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.pedrogomez.renderers.Renderer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by okorsun on 01.08.16.
 */
public class CityItemRenderer extends Renderer<HourlyWeatherInfo> {

    public interface CityAction{
        void call(City city);
    }

    private final CityAction action;

    public CityItemRenderer(CityAction action){
        this.action = action;
    }

    @Bind(R.id.city_name)
    TextView cityName;

    @Bind(R.id.temperature)
    TextView temperature;

    @Bind(R.id.weather_icon)
    ImageView weatherIcon;

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {
        rootView.setOnClickListener(v -> action.call(getContent().getCity()));
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.city_list_item, parent, false);
    }

    @Override
    public void render() {
        HourlyWeatherInfo content = getContent();
        Glide.with(getContext())
                .load("http://openweathermap.org/img/w/" + content.getIcon() + ".png")
                .centerCrop()
                .into(weatherIcon);

        temperature.setText(Float.toString(content.getTemp()));
        cityName.setText(content.getCity().getName());
    }
}
