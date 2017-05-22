package com.wipro.weather.services;

import com.wipro.weather.conf.Constants;
import com.wipro.weather.interfaces.RestWeatherInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Manage connections with weather webserver using retrofit
 */
public class RestWeatherManager {

    /**
     * Initialize retrofit
     * @return retrofit interface
     */
    public static RestWeatherInterface init() {
        //Create an http logging interceptor
        HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor();
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Create http client
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(mHttpLoggingInterceptor).build();

        //Set retrofit object
        Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URL_WEATHER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        return mRetrofit.create(RestWeatherInterface.class);
    }
}
