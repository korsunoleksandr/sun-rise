package com.korsun.sunrise.common;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

        return cal.getTimeInMillis()/1000;
    }

    public static long getNextDayStartTimestamp(long currentTimestamp){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(currentTimestamp * 1000);
        cal.add(Calendar.DAY_OF_WEEK, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis() /1000;
    }

    public static String getTimeString(long timestamp){
        DateFormat sdf = new SimpleDateFormat("HH:mm");
        Date netDate = (new Date(timestamp * 1000));

        return sdf.format(netDate);
    }

    public static String getDateString(long timestamp){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date netDate = (new Date(timestamp * 1000));

        return sdf.format(netDate);
    }

    public static String getDateTmpString(long timestamp){
        DateFormat sdf = new SimpleDateFormat("MM-dd");
        Date netDate = (new Date(timestamp * 1000));

        return sdf.format(netDate);
    }

    public static String formatFloat(Float f){
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(f);
    }

    public static String formatTemp(Float f){
        return formatFloat(f) + "°C";
    }

    public static String formatWind(Float f){
        return formatFloat(f) + " м/с";
    }

    public static String capitalise(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
