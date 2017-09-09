package com.jacob.top100.api;

import com.jacob.top100.model.MobileApp;
import com.jacob.top100.model.MobileAppFeed;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Jacob Ho
 */

public class AppStoreApiImpl implements AppStoreApi {
    private AppStoreApiRetrofit mRetrofit;

    public AppStoreApiImpl(AppStoreApiRetrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Observable<MobileAppFeed> getTopFreeApps(int limit) {
        return mRetrofit.getTopFreeApps(limit);
    }

    @Override
    public Observable<MobileAppFeed> getGrossFreeApps(int limit) {
        return mRetrofit.getTopGrossApps(limit);
    }

    @Override
    public Observable<MobileAppFeed> getAppsDetails(List<Integer> ids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            if (i != 0)
                stringBuilder.append(",");
            stringBuilder.append(ids.get(i));
        }
        String idsString = stringBuilder.toString();
        return mRetrofit.getAppsDetails(idsString).flatMap(apps -> {
            List<MobileApp> entries = apps.getEntries();
            MobileAppFeed mobileAppFeed = new MobileAppFeed();
            mobileAppFeed.setEntries(entries);
            return Observable.just(mobileAppFeed);
        });
    }

}
