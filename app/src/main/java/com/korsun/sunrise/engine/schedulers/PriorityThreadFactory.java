package com.korsun.sunrise.engine.schedulers;

import android.os.Process;
import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by okorsun on 29.07.16.
 */
public final class PriorityThreadFactory implements ThreadFactory {

    private final AtomicInteger atomicInteger = new AtomicInteger();
    private final String name;
    private final int threadPriority;

    public PriorityThreadFactory(String name, int threadPriority) {
        this.name = name;
        this.threadPriority = threadPriority;
    }

    @Override
    public Thread newThread(@NonNull Runnable r) {
        return new Thread(r, name + "-" + atomicInteger.getAndIncrement()){
            @Override
            public void run() {
                Process.setThreadPriority(threadPriority);
                super.run();
            }
        };
    }
}
