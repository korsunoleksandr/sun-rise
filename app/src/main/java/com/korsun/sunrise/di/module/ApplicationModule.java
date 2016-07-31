package com.korsun.sunrise.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.korsun.sunrise.api.ApiKeyInterceptor;
import com.korsun.sunrise.api.Config;
import com.korsun.sunrise.api.RestApi;
import com.korsun.sunrise.core.AndroidApplication;
import com.korsun.sunrise.engine.LastUpdateStorage;
import com.korsun.sunrise.engine.LastUpdateStorageImpl;
import com.korsun.sunrise.engine.WeatherManager;
import com.korsun.sunrise.engine.WeatherManagerImpl;
import com.korsun.sunrise.engine.schedulers.RxSchedulers;
import com.korsun.sunrise.engine.schedulers.RxSchedulersImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by okorsun on 30.07.16.
 */

@Module
public final class ApplicationModule {
    private final AndroidApplication androidApplication;

    public ApplicationModule(AndroidApplication androidApplication) {
        this.androidApplication = androidApplication;
    }

    @Provides
    public Context provideContext() {
        return androidApplication.getApplicationContext();
    }

    @Provides
    public RestApi provideRestApi() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> {
                    Timber.d(message);
                });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        ApiKeyInterceptor apiKeyInterceptor = new ApiKeyInterceptor();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(RestApi.class);
    }

    @Provides
    @Singleton
    public RxSchedulers provideRxSchedulers(RxSchedulersImpl rxSchedulers) {
        return rxSchedulers;
    }

    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    public LastUpdateStorage provideLastUpdateStorage(LastUpdateStorageImpl lastUpdateStorage) {
        return lastUpdateStorage;
    }

    @Provides
    @Singleton
    public WeatherManager provideWeatherManager(WeatherManagerImpl weatherManager) {
        return weatherManager;
    }
}
