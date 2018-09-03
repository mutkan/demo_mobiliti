package com.mobiliti.demo.rest;

import com.mobiliti.demo.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BuildConfig.APP_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit sRetrofit;

    public static <S> S createService(Class<S> serviceClass) {
        httpClient = new OkHttpClient.Builder();
        OkHttpClient client;

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = httpClient
                    .addInterceptor(logging)
                    .build();
        } else {
            client = httpClient
                    .build();
        }
        sRetrofit = builder.client(client).build();
        return sRetrofit.create(serviceClass);
    }
}
