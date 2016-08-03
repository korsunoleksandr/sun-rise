package com.korsun.sunrise.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by okorsun on 28.07.16.
 */

@DatabaseTable(tableName = City.TABLE_NAME)
public class City implements Serializable {
    public static final String TABLE_NAME = "cities";
    public static final String ID = "id";
    public static final String IS_INSTALLED = "is_installed";

    @DatabaseField(id = true, unique = true, canBeNull = false, columnName = ID)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    public void setInstalled(boolean installed) {
        isInstalled = installed;
    }

    @DatabaseField(canBeNull = false, columnName = IS_INSTALLED)
    private boolean isInstalled;

    @DatabaseField
    private long lastCurrentUpdate;

    @DatabaseField
    private long lastHourlyUpdate;

    @DatabaseField
    private long lastDailyUpdate;

    public boolean isInstalled() {
        return isInstalled;
    }

    public long getLastCurrentUpdate() {
        return lastCurrentUpdate;
    }

    public long getLastHourlyUpdate() {
        return lastHourlyUpdate;
    }

    public long getLastDailyUpdate() {
        return lastDailyUpdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setLastDailyUpdate(long lastDailyUpdate) {
        this.lastDailyUpdate = lastDailyUpdate;
    }

    public void setLastHourlyUpdate(long lastHourlyUpdate) {
        this.lastHourlyUpdate = lastHourlyUpdate;
    }

    public void setLastCurrentUpdate(long lastCurrentUpdate) {
        this.lastCurrentUpdate = lastCurrentUpdate;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastCurrentUpdate=" + lastCurrentUpdate +
                ", lastHourlyUpdate=" + lastHourlyUpdate +
                ", lastDailyUpdate=" + lastDailyUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != city.id) return false;
        if (lastCurrentUpdate != city.lastCurrentUpdate) return false;
        if (lastHourlyUpdate != city.lastHourlyUpdate) return false;
        if (lastDailyUpdate != city.lastDailyUpdate) return false;
        return name.equals(city.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) (lastCurrentUpdate ^ (lastCurrentUpdate >>> 32));
        result = 31 * result + (int) (lastHourlyUpdate ^ (lastHourlyUpdate >>> 32));
        result = 31 * result + (int) (lastDailyUpdate ^ (lastDailyUpdate >>> 32));
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }
}
