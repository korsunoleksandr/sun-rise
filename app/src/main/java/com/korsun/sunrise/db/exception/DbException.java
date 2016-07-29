package com.korsun.sunrise.db.exception;

/**
 * Created by okorsun on 28.07.16.
 */
public final class DbException extends Exception {

    public DbException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
