package com.jacob.top100.interactor.impl;

import android.util.Log;

import com.jacob.top100.api.AppStoreApi;
import com.jacob.top100.interactor.HomeInteractor;
import com.jacob.top100.model.HttpResponse;
import com.jacob.top100.model.MobileApp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class HomeInteractorImpl implements HomeInteractor {
    private AppStoreApi mAppStoreApi;

    @Inject
    public HomeInteractorImpl(AppStoreApi appStoreApi) {
        mAppStoreApi = appStoreApi;
    }

    @Override
    public void getFreeApps(int limit, HttpResponse<List<MobileApp>> response) {
        mAppStoreApi.getTopFreeApps(limit).map(apps -> {
            List<Integer> ids = new ArrayList<>();
            for (MobileApp app : apps.getEntries())
                ids.add(app.getId());
            return ids;
        }).flatMap(ids -> mAppStoreApi.getAppsDetails(ids))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mobileAppFeed -> response.onSuccess(mobileAppFeed.getEntries()), e -> {
                    String message = e.getMessage();
                    if (message != null)
                        Log.e(getClass().getSimpleName(), message);
                    response.onFailure((Exception) e);
                });
    }

    @Override
    public void getGrossApps(int limit, HttpResponse<List<MobileApp>> response) {
        mAppStoreApi.getGrossApps(limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mobileAppFeed -> response.onSuccess(mobileAppFeed.getEntries()), e -> {
                    String message = e.getMessage();
                    if (message != null)
                        Log.e(getClass().getSimpleName(), message);
                    response.onFailure((Exception) e);
                });
    }

}