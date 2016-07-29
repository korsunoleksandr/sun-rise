package com.korsun.sunrise.engine.schedulers;

import rx.Scheduler;

/**
 * Created by okorsun on 29.07.16.
 */
public interface RxSchedulers {

    Scheduler getNetwork();

    Scheduler getDB();

    Scheduler getMain();

    Scheduler getComputation();

    Scheduler getIO();
}