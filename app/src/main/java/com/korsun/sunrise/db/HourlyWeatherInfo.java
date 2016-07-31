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
    public static final String CITY = "city_id";

    @DatabaseField(id = true)
    private long id;

    @DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true, foreignAutoRefresh = true)
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
    private float windSpeed;

    @DatabaseField(canBeNull = false)
    private float windDegree;

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

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getWindDegree() {
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

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDegree(float windDegree) {
        this.windDegree = windDegree;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "HourlyWeatherInfo{" +
                "id=" + id +
                ", city=" + city +
                ", temp=" + temp +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", windSpeed=" + windSpeed +
                ", windDegree=" + windDegree +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HourlyWeatherInfo that = (HourlyWeatherInfo) o;

        if (id != that.id) return false;
        if (Float.compare(that.temp, temp) != 0) return false;
        if (Float.compare(that.tempMin, tempMin) != 0) return false;
        if (Float.compare(that.tempMax, tempMax) != 0) return false;
        if (pressure != that.pressure) return false;
        if (humidity != that.humidity) return false;
        if (windSpeed != that.windSpeed) return false;
        if (windDegree != that.windDegree) return false;
        if (timestamp != that.timestamp) return false;
        if (!city.equals(that.city)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return icon.equals(that.icon);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (temp != +0.0f ? Float.floatToIntBits(temp) : 0);
        result = 31 * result + (tempMin != +0.0f ? Float.floatToIntBits(tempMin) : 0);
        result = 31 * result + (tempMax != +0.0f ? Float.floatToIntBits(tempMax) : 0);
        result = 31 * result + pressure;
        result = 31 * result + humidity;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (windSpeed != +0.0f ? Float.floatToIntBits(windSpeed) : 0);
        result = 31 * result + (windDegree != +0.0f ? Float.floatToIntBits(windDegree) : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    public void setId(long id) {
        this.id = id;
    }
}
