package com.korsun.sunrise.ui.citydetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.korsun.sunrise.R;
import com.korsun.sunrise.db.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by okorsun on 28.07.16.
 */
public class WeatherPagerAdapter extends FragmentPagerAdapter {

    private static List<Class<? extends Fragment>> fragmentsToShow = new ArrayList<>();

    static {
        fragmentsToShow.add(TodayWeatherFragment.class);
        fragmentsToShow.add(ThreeDaysWeatherFragment.class);
        fragmentsToShow.add(SevenDaysWeatherFragment.class);
    }

    private final Context context;

    private final City city;

    public WeatherPagerAdapter(FragmentManager fm, Context context, City city) {
        super(fm);
        this.context = context;
        this.city = city;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.ARG_CITY, city);
        return instantiateFragment(args, fragmentsToShow.get(position));
    }

    private Fragment instantiateFragment(Bundle args, Class<? extends Fragment> cls) {
        return Fragment.instantiate(context, cls.getName(), args);
    }

    @Override
    public int getCount() {
        return fragmentsToShow.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.pager_today);
            case 1:
                return context.getString(R.string.pager_three_days);
            case 2:
                return context.getString(R.string.pager_five_days);
        }
        return null;
    }
}
