package com.korsun.sunrise.common;

import rx.functions.Func1;

/**
 * Created by okorsun on 28.07.16.
 */
public interface Converter<T, R> extends Func1<T, R> {

}
