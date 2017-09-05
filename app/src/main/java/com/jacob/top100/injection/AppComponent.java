package com.jacob.top100.injection;

import android.content.Context;

import com.jacob.top100.Top100;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    Top100 getApp();
}