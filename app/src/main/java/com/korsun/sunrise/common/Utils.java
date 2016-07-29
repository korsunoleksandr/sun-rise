package com.korsun.sunrise.common;

import java.util.Calendar;
import java.util.TimeZone;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by okorsun on 28.07.16.
 */
public class Utils {

    private Utils() {
        // no instances
    }

    public static long getNextDayNoonTimestamp(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) cal.getTimeInMillis()/1000;
    }

    public static long getNextDayStartTimestamp(long currentTimestamp){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(currentTimestamp * 1000);
        cal.add(Calendar.DAY_OF_WEEK, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) cal.getTimeInMillis()/1000;
    }
}
