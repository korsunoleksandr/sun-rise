package com.korsun.sunrise.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by okorsun on 28.07.16.
 */
public class ApiKeyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalUrl = original.url();

        HttpUrl url = originalUrl.newBuilder()
                .addQueryParameter("appid", Config.API_KEY)
                .addQueryParameter("lang", Config.LANGUAGE)
                .addQueryParameter("units", Config.UTINTS)
                .build();

        Request request = original.newBuilder()
                .url(url)
                .build();

        return chain.proceed(request);
    }
}
