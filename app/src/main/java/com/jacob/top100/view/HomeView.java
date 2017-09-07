package com.jacob.top100.view;

import android.support.annotation.UiThread;

import com.jacob.top100.model.MobileApp;

import java.util.List;

@UiThread
public interface HomeView extends BaseView {

    void setApps(List<MobileApp> apps);


}