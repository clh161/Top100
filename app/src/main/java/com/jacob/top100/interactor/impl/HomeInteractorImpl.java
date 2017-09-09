package com.jacob.top100.interactor.impl;

import android.util.Log;

import com.jacob.top100.api.AppStoreApi;
import com.jacob.top100.interactor.HomeInteractor;
import com.jacob.top100.model.HttpResponse;
import com.jacob.top100.model.MobileApp;
import com.jacob.top100.model.MobileAppFeed;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class HomeInteractorImpl implements HomeInteractor {
    private AppStoreApi mAppStoreApi;

    @Inject
    public HomeInteractorImpl(AppStoreApi appStoreApi) {
        mAppStoreApi = appStoreApi;
    }

    @Override
    public void getApps(int topFreeAppLimit, int topGrossAppLimit, HttpResponse<Apps> response) {
        Observable.zip(mAppStoreApi.getTopFreeApps(topFreeAppLimit).map(apps -> {
            List<Integer> ids = new ArrayList<>();
            for (MobileApp app : apps.getEntries())
                ids.add(app.getId());
            return ids;
        }).flatMap(ids -> {
            return mAppStoreApi.getAppsDetails(ids);
        }), mAppStoreApi.getGrossFreeApps(topGrossAppLimit), Apps::new)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response::onSuccess, e -> {
                    String message = e.getMessage();
                    if (message != null)
                        Log.e(getClass().getSimpleName(), message);
                    response.onFailure((Exception) e);
                });
    }

    public class Apps {
        private final MobileAppFeed mFreeApps;
        private final MobileAppFeed mGrossApps;

        public Apps(MobileAppFeed freeApps, MobileAppFeed grossApps) {
            mFreeApps = freeApps;
            mGrossApps = grossApps;
        }

        public MobileAppFeed getFreeApps() {
            return mFreeApps;
        }

        public MobileAppFeed getGrossApps() {
            return mGrossApps;
        }
    }
}