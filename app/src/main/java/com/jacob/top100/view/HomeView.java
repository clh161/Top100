package com.jacob.top100.view;

import android.support.annotation.UiThread;

import com.jacob.top100.model.MobileApp;

import java.util.List;

@UiThread
public interface HomeView extends BaseView {

    void setTopFreeApps(List<MobileApp> apps);

    void setTopGrossApps(List<MobileApp> apps);

    void setLoading(boolean isLoading);

    void setTopGrossListContainerTopMargin(int margin);

    float getTopGrossListContainerHeight();

    void scrollGrossAppList(int index);

    void scrollFreeAppList(int index);
}