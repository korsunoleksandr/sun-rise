package com.korsun.sunrise.ui.citydetail.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
public final class DaylyWindRenderer extends Renderer<DailyWeatherInfo> {
    @Bind(R.id.wind_icon)
    ImageView windIcon;

    @Bind(R.id.wind_speed)
    TextView windSpeed;

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
        return inflater.inflate(R.layout.hourly_wind_rv_item, parent, false);
    }

    @Override
    public void render() {
        DailyWeatherInfo content = getContent();
        windSpeed.setText(Utils.formatWind(content.getWindSpeed()));
        time.setText(Utils.getDateTmpString(content.getTimestamp()));
        windIcon.setRotation(content.getWindDegree());
//        windIcon.setColorFilter(Color.WHITE);
    }
}
