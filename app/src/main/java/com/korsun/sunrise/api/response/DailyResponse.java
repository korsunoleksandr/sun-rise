package com.korsun.sunrise.api.response;

import java.util.Collections;
import java.util.List;

/**
 * Created by okorsun on 28.07.16.
 */
public class DailyResponse {

    public List<Data> list = Collections.emptyList();

    public final class Data {
        public long dt = 0;

        public Temp temp;

        public Weather weather;

        public int pressure;

        public int humidity;

        public int speed;

        public int deg;

    }

    public class Temp {
        public float day;
        public float night;
        public float eve;
        public float morn;
        public float min;
        public float max;
    }
}
