package com.korsun.sunrise.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by okorsun on 28.07.16.
 */

@DatabaseTable(tableName = City.TABLE_NAME)
public class City {
    public static final String TABLE_NAME = "cities";
    public static final String ID = "id";
    public static final String IS_INSTALLED = "is_installed";

    @DatabaseField(id = true, unique = true, canBeNull = false, columnName = ID)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false, columnName = IS_INSTALLED)
    private boolean isInstalled;

    @DatabaseField
    private long lastCurrentUpdate;

    @DatabaseField
    private long lastHourlyUpdate;

    @DatabaseField
    private long lastDaylyUpdate;

    public boolean isInstalled() {
        return isInstalled;
    }

    public long getLastCurrentUpdate() {
        return lastCurrentUpdate;
    }

    public long getLastHourlyUpdate() {
        return lastHourlyUpdate;
    }

    public long getLastDaylyUpdate() {
        return lastDaylyUpdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setLastDaylyUpdate(long lastDaylyUpdate) {
        this.lastDaylyUpdate = lastDaylyUpdate;
    }

    public void setLastHourlyUpdate(long lastHourlyUpdate) {
        this.lastHourlyUpdate = lastHourlyUpdate;
    }

    public void setLastCurrentUpdate(long lastCurrentUpdate) {
        this.lastCurrentUpdate = lastCurrentUpdate;
    }
}
