package com.korsun.sunrise.common;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by okorsun on 28.07.16.
 */
public class RxUtils {

    private RxUtils() {
        // no instances
    }

    public static <T> Observable<T> create(Func0<T> f) {
        return Observable.create(subscriber -> {
            try {
                T data = f.call();
                subscriber.onNext(data);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

}
