package com.jacob.top100.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jacob.top100.Top100;

import dagger.Module;
import dagger.Provides;

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
}
