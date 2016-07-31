package com.korsun.sunrise.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by okorsun on 31.07.16.
 */
public class Cities {
    private static final MockTextGenerator tg = new MockTextGenerator(13);
    private static final Random r = new Random(13);

    private Cities() {
        // no-instances
    }

    public static City kyiv() {
        City city = new City();
        city.setId(703448);
        city.setName("Kiev");

        return city;
    }

    public static City city() {
        City city = new City();
        city.setId(r.nextInt());
        city.setName(tg.nextString(5));

        return city;
    }

    public static List<City> cities(int count){
        List<City> cities = new ArrayList<>(count);
        for(int i =0; i < count; i++){
            cities.add(city());
        }
        return cities;
    }

}
