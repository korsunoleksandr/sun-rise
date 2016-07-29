package com.korsun.sunrise.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by okorsun on 28.07.16.
 */

@DatabaseTable(tableName = HourlyWeatherInfo.TABLE_NAME)
public class HourlyWeatherInfo {
    public static final String TABLE_NAME = "hourly_weather";
    public static final String TIMESTAMP = "timestamp";
    public static final String CITY = "city";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true, foreignColumnName = CITY)
    private City city;

    @DatabaseField(canBeNull = false)
    private float temp;

    @DatabaseField(canBeNull = false)
    private float tempMin;

    @DatabaseField(canBeNull = false)
    private float tempMax;

    @DatabaseField(canBeNull = false)
    private int pressure;

    @DatabaseField(canBeNull = false)
    private int humidity;

    @DatabaseField(canBeNull = false)
    private String description;

    @DatabaseField(canBeNull = false)
    private String icon;

    @DatabaseField(canBeNull = false)
    private int windSpeed;

    @DatabaseField(canBeNull = false)
    private int windDegree;

    @DatabaseField(canBeNull = false, columnName = TIMESTAMP, uniqueCombo = true)
    private long timestamp;

    public City getCity() {
        return city;
    }

    public float getTemp() {
        return temp;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getWindDegree() {
        return windDegree;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDegree(int windDegree) {
        this.windDegree = windDegree;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
