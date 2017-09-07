package com.jacob.top100.view;

import android.support.annotation.UiThread;

import com.jacob.top100.model.MobileAppFeed;

@UiThread
public interface HomeView extends BaseView {

    void setApps(MobileAppFeed apps);


}