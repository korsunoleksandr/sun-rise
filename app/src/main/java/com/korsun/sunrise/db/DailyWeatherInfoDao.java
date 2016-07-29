package com.korsun.sunrise.db;

import java.util.List;

/**
 * Created by okorsun on 28.07.16.
 */
public interface DailyWeatherInfoDao {

    List<DailyWeatherInfo> getSevenDaysWeatherInfo(City city);

    void insertSevenDaysWeatherInfo(List<DailyWeatherInfo> dailyWeatherInfos);

}
