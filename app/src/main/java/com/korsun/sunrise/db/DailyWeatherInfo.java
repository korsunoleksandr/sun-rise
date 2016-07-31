package com.korsun.sunrise.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by okorsun on 28.07.16.
 */

@DatabaseTable(tableName = DailyWeatherInfo.TABLE_NAME)
public class DailyWeatherInfo {
    public static final String TABLE_NAME = "daily_weather";
    public static final String TIMESTAMP = "timestamp";
    public static final String CITY = "city_id";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true, foreignAutoRefresh = true)
    private City city;

    @DatabaseField(canBeNull = false)
    private float tempMin;

    @DatabaseField(canBeNull = false)
    private float tempMax;

    @DatabaseField(canBeNull = false)
    private float tempDay;

    @DatabaseField(canBeNull = false)
    private float tempNight;

    @DatabaseField(canBeNull = false)
    private float tempEvenig;

    @DatabaseField(canBeNull = false)
    private float tempMorning;

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

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public float getTempDay() {
        return tempDay;
    }

    public float getTempNight() {
        return tempNight;
    }

    public float getTempEvenig() {
        return tempEvenig;
    }

    public float getTempMorning() {
        return tempMorning;
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

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public void setTempDay(float tempDay) {
        this.tempDay = tempDay;
    }

    public void setTempNight(float tempNight) {
        this.tempNight = tempNight;
    }

    public void setTempEvenig(float tempEvenig) {
        this.tempEvenig = tempEvenig;
    }

    public void setTempMorning(float tempMorning) {
        this.tempMorning = tempMorning;
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

    @Override
    public String toString() {
        return "DailyWeatherInfo{" +
                "id=" + id +
                ", city=" + city +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", tempDay=" + tempDay +
                ", tempNight=" + tempNight +
                ", tempEvenig=" + tempEvenig +
                ", tempMorning=" + tempMorning +
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

        DailyWeatherInfo that = (DailyWeatherInfo) o;

        if (id != that.id) return false;
        if (Float.compare(that.tempMin, tempMin) != 0) return false;
        if (Float.compare(that.tempMax, tempMax) != 0) return false;
        if (Float.compare(that.tempDay, tempDay) != 0) return false;
        if (Float.compare(that.tempNight, tempNight) != 0) return false;
        if (Float.compare(that.tempEvenig, tempEvenig) != 0) return false;
        if (Float.compare(that.tempMorning, tempMorning) != 0) return false;
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
        int result = id;
        result = 31 * result + city.hashCode();
        result = 31 * result + (tempMin != +0.0f ? Float.floatToIntBits(tempMin) : 0);
        result = 31 * result + (tempMax != +0.0f ? Float.floatToIntBits(tempMax) : 0);
        result = 31 * result + (tempDay != +0.0f ? Float.floatToIntBits(tempDay) : 0);
        result = 31 * result + (tempNight != +0.0f ? Float.floatToIntBits(tempNight) : 0);
        result = 31 * result + (tempEvenig != +0.0f ? Float.floatToIntBits(tempEvenig) : 0);
        result = 31 * result + (tempMorning != +0.0f ? Float.floatToIntBits(tempMorning) : 0);
        result = 31 * result + pressure;
        result = 31 * result + humidity;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + icon.hashCode();
        result = 31 * result + windSpeed;
        result = 31 * result + windDegree;
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}
