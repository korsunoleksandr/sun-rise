package com.korsun.sunrise.core;

import android.app.Application;

import com.korsun.sunrise.di.component.ApplicationComponent;
import com.korsun.sunrise.presentation.base.ComponentProvider;

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

    }

    public ComponentProvider getComponentProvider() {
        return componentProvider;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
