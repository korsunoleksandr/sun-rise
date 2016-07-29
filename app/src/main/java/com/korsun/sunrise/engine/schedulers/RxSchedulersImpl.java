package com.korsun.sunrise.engine.schedulers;

import android.os.Process;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by okorsun on 29.07.16.
 */
public final class RxSchedulersImpl implements RxSchedulers {

    private static final int NETWORK_CORE_POOL_SIZE = 2;
    private static final int NETWORK_MAX_POOL_SIZE = 2;
    private static final long NET_THREAD_KEEP_ALIVE = 10L;
    private static final int DB_CORE_POOL_SIZE = 1;
    private static final int DB_MAX_POOL_SIZE = 1;
    private static final long DB_THREAD_KEEP_ALIVE = 10L;

    private final Scheduler network;
    private final Scheduler db;

    @Inject
    public RxSchedulersImpl() {
        ThreadPoolExecutor networkExecutor = new ThreadPoolExecutor(
                NETWORK_CORE_POOL_SIZE, NETWORK_MAX_POOL_SIZE, NET_THREAD_KEEP_ALIVE, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new PriorityThreadFactory("net-thread-pool", Process.THREAD_PRIORITY_BACKGROUND));
        networkExecutor.allowCoreThreadTimeOut(true);
        network = Schedulers.from(networkExecutor);

        ThreadPoolExecutor dbExecutor = new ThreadPoolExecutor(
                DB_CORE_POOL_SIZE, DB_MAX_POOL_SIZE, DB_THREAD_KEEP_ALIVE, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new PriorityThreadFactory("db-thread-pool", Process.THREAD_PRIORITY_BACKGROUND));
        networkExecutor.allowCoreThreadTimeOut(true);
        db = Schedulers.from(dbExecutor);

    }

    @Override
    public Scheduler getNetwork() {
        return network;
    }

    @Override
    public Scheduler getDB() {
        return db;
    }

    @Override
    public Scheduler getMain() {
        return AndroidSchedulers.mainThread();
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
