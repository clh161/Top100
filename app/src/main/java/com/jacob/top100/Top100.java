package com.jacob.top100;

import android.app.Application;
import android.support.annotation.NonNull;

import com.jacob.top100.injection.AppComponent;
import com.jacob.top100.injection.AppModule;
import com.jacob.top100.injection.DaggerAppComponent;

public final class Top100 extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}