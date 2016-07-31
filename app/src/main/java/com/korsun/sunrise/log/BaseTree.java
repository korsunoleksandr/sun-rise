package com.korsun.sunrise.log;

import timber.log.Timber;

/**
 * Created by okorsun on 31.07.16.
 */
public abstract class BaseTree extends Timber.DebugTree {
    private final int logLevel;

    public BaseTree(int logLevel) {
        this.logLevel = logLevel;
    }

    protected final boolean isLoggable(int priority){
        return priority >= logLevel;
    }
}
