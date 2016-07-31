package com.korsun.sunrise.core;

import android.app.Application;

import com.korsun.sunrise.BuildConfig;
import com.korsun.sunrise.di.component.ApplicationComponent;
import com.korsun.sunrise.di.component.DaggerApplicationComponent;
import com.korsun.sunrise.di.module.ApplicationModule;
import com.korsun.sunrise.di.module.DbModule;
import com.korsun.sunrise.log.LogcatTree;
import com.korsun.sunrise.presentation.base.ComponentProvider;

import timber.log.Timber;

/**
 * Created by okorsun on 28.07.16.
 */
public class AndroidApplication extends Application {

    private ComponentProvider componentProvider;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        componentProvider = new ComponentProvider();

        applicationComponent =
                DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .dbModule(new DbModule())
                        .build();

        initLogging();
        logDeviceInfo();
    }

    public ComponentProvider getComponentProvider() {
        return componentProvider;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void logDeviceInfo() {
        Timber.i("version: ${BuildConfig.VERSION_NAME}");
        Timber.i("build type: ${BuildConfig.BUILD_TYPE}");

        Timber.i("SystemInfo: Board: ${Build.BOARD}");
        Timber.i("SystemInfo: Brand: ${Build.BRAND}");
        Timber.i("SystemInfo: Device: ${Build.DEVICE}");
        Timber.i("SystemInfo: Model: ${Build.MODEL}");
        Timber.i("SystemInfo: Product: ${Build.PRODUCT}");
        Timber.i("SystemInfo: Display: ${Build.DISPLAY}");
        Timber.i("SystemInfo: SDK: ${Build.VERSION.SDK_INT}");
    }

    private void initLogging() {
        Timber.plant(new LogcatTree(BuildConfig.LOG_LEVEL));
    }

}
