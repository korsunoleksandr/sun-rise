package com.korsun.sunrise.ui.citydetail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.korsun.sunrise.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by okorsun on 28.07.16.
 */
public class WeatherPagerAdapter extends FragmentPagerAdapter {

    private static List<Class<? extends Fragment>> fragmentsToShow = new ArrayList<>();

    static {
        fragmentsToShow.add(null);
        fragmentsToShow.add(null);
        fragmentsToShow.add(null);
    }

    Context context;

    public WeatherPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return fragmentsToShow.get(position).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("fragment should have empty default public constructor", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("fragment  should have empty default public constructor", e);
        }
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
