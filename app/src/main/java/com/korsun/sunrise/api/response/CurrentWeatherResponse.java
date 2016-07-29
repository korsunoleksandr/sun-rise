package com.korsun.sunrise.api.response;

import java.util.Collections;
import java.util.List;

/**
 * Created by okorsun on 28.07.16.
 */
public class CurrentWeatherResponse {

    public Coord coord = null;

    public List<Weather> weather = Collections.emptyList();

    public Main main = null;

    public Wind wind = null;

    public Clouds clouds = null;

    public long dt = 0;

    public Sys sys = null;

    public int id = 0;

    public String name = null;


    public final class Coord {
        public float lon = 0;
        public float lat = 0;
    }

    public final class Sys {
        public int type = 0;
        public int id = 0;
        public float message = 0;
        public String country = null;
        public long sunrise = 0;
        public long sunset = 0;
    }

}
