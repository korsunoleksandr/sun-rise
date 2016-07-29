package com.korsun.sunrise.api.response;

import java.util.List;

/**
 * Created by okorsun on 28.07.16.
 */
public class ThreeHoursResponse {

    public List<Data> list = null;

    public final class Data {
        public long dt = 0;

        public Main main;

        public Weather weather;

        public Clouds clouds;

        public Wind wind;

        public String dt_txt;

    }

}