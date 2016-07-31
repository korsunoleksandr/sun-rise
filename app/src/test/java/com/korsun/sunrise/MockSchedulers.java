package com.korsun.sunrise;

import com.korsun.sunrise.engine.schedulers.RxSchedulers;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by okorsun on 31.07.16.
 */
public final class MockSchedulers implements RxSchedulers {

    @Override
    public Scheduler getNetwork() {
        return Schedulers.newThread();
    }

    @Override
    public Scheduler getDB() {
        return Schedulers.newThread();
    }

    @Override
    public Scheduler getMain() {
        return Schedulers.newThread();
    }

    @Override
    public Scheduler getComputation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler getIO() {
        return Schedulers.io();
    }
}
