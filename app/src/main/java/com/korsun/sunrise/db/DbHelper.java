package com.korsun.sunrise.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by okorsun on 31.07.16.
 */
public class DbHelper
        extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "weather.db";
    private static final int DB_VERSION = 1;

    private static List<Class<?>> DB_ITEMS =
            Arrays.asList(City.class,
                    DailyWeatherInfo.class,
                    HourlyWeatherInfo.class);

    static {
        DB_ITEMS.add(City.class);
        DB_ITEMS.add(DailyWeatherInfo.class);
    }

    @Singleton
    @Inject
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        for (Class<?> item : DB_ITEMS){
            try {
                TableUtils.createTable(connectionSource, item);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
