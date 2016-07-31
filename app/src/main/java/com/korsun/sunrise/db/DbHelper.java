package com.korsun.sunrise.db;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.korsun.sunrise.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by okorsun on 31.07.16.
 */
@Singleton
public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "weather.db";
    private static final int DB_VERSION = 1;

    private static List<Class<?>> DB_ITEMS =
            Arrays.asList(City.class,
                    DailyWeatherInfo.class,
                    HourlyWeatherInfo.class);

    private final Context context;

    @Inject
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        for (Class<?> item : DB_ITEMS) {
            try {
                TableUtils.createTable(connectionSource, item);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            List<String> statements = readFromResources(context.getResources(), R.raw.default_db_values);
            for (String statement : statements) {
                database.execSQL(statement);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> readFromResources(Resources resources, int resId) throws IOException {
        InputStream is = resources.openRawResource(resId);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        List<String> result = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            result.add(line);
        }

        reader.close();

        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
