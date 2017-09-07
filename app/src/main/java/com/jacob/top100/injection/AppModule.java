package com.jacob.top100.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jacob.top100.Top100;
import com.jacob.top100.api.AppStoreApi;
import com.jacob.top100.api.AppStoreApiImpl;
import com.jacob.top100.api.AppStoreApiRetrofit;
import com.jacob.top100.deserializer.MobileAppDeserializer;
import com.jacob.top100.model.MobileAppFeed;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class AppModule {
    @NonNull
    private final Top100 mApp;

    public AppModule(@NonNull Top100 app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public Top100 provideApp() {
        return mApp;
    }

    @Provides
    public AppStoreApi provideAppStoreApi() {
        AppStoreApiRetrofit retrofit = getRetrofitService("https://itunes.apple.com/hk/", AppStoreApiRetrofit.class);
        return new AppStoreApiImpl(retrofit);
    }

    private <T> T getRetrofitService(String baseUrl, Class<T> tClass) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder().registerTypeAdapter(MobileAppFeed.class, new MobileAppDeserializer()).create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(tClass);
    }
}
