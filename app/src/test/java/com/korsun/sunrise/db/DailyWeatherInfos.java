package com.korsun.sunrise.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by okorsun on 31.07.16.
 */
public class DailyWeatherInfos {
    private static final MockTextGenerator tg = new MockTextGenerator(13);
    private static final Random r = new Random(13);

    private DailyWeatherInfos() {
        // no - opps
    }

    static DailyWeatherInfo dailyInfo(City city) {
        DailyWeatherInfo dailyWeatherInfo = new DailyWeatherInfo();
        dailyWeatherInfo.setDescription(tg.nextString(6));
        dailyWeatherInfo.setHumidity(r.nextInt());
        dailyWeatherInfo.setPressure(r.nextInt());
        dailyWeatherInfo.setTempDay(r.nextFloat());
        dailyWeatherInfo.setTempEvenig(r.nextFloat());
        dailyWeatherInfo.setTempMorning(r.nextFloat());
        dailyWeatherInfo.setTempNight(r.nextFloat());
        dailyWeatherInfo.setTempMin(r.nextFloat());
        dailyWeatherInfo.setTempMax(r.nextFloat());
        dailyWeatherInfo.setWindDegree(r.nextInt());
        dailyWeatherInfo.setWindSpeed(r.nextInt());
        dailyWeatherInfo.setTimestamp(r.nextInt());
        dailyWeatherInfo.setIcon(tg.nextString(4));
        dailyWeatherInfo.setCity(city);

        return dailyWeatherInfo;
    }

    static List<DailyWeatherInfo> dailyInfos(City city, int count) {
        List<DailyWeatherInfo> infos = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            infos.add(dailyInfo(city));
        }
        return infos;
    }
}
