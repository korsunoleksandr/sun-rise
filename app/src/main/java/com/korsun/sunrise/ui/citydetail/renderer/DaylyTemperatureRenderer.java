package com.korsun.sunrise.ui.citydetail.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.korsun.sunrise.R;
import com.korsun.sunrise.common.Utils;
import com.korsun.sunrise.db.DailyWeatherInfo;
import com.korsun.sunrise.db.HourlyWeatherInfo;
import com.pedrogomez.renderers.Renderer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by okorsun on 31.07.16.
 */
public final class DaylyTemperatureRenderer extends Renderer<DailyWeatherInfo> {
    @Bind(R.id.weather_icon)
    ImageView weatherIcon;

    @Bind(R.id.temperature)
    TextView temperature;

    @Bind(R.id.time)
    TextView time;

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.hourly_temp_rv_item, parent, false);
    }

    @Override
    public void render() {
        DailyWeatherInfo content = getContent();
        Glide.with(getContext())
                .load("http://openweathermap.org/img/w/" + content.getIcon() + ".png")
                .into(weatherIcon);

        time.setText(Utils.getDateTmpString(content.getTimestamp()));
        temperature.setText(Utils.formatTemp(content.getTempDay()) + "\n"+
        Utils.formatTemp(content.getTempMax()) + "↑\n" +
        Utils.formatTemp(content.getTempMin()) + "↓");
    }
}
