package com.korsun.sunrise.ui.citydetail.renderer;

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
import com.pedrogomez.renderers.Renderer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by okorsun on 01.08.16.
 */
public class AddCityItemRenderer extends Renderer<City> {

    public interface CityAction{
        void call(City city);
    }

    private final CityAction action;

    public AddCityItemRenderer(CityAction action){
        this.action = action;
    }

    @Bind(R.id.city_name)
    TextView cityName;

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {
        rootView.setOnClickListener(v -> action.call(getContent()));
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.city_list_item, parent, false);
    }

    @Override
    public void render() {
        City content = getContent();
        cityName.setText(content.getName());
    }
}
