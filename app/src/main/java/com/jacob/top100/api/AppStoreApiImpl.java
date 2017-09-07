package com.jacob.top100.api;

import com.jacob.top100.model.MobileAppFeed;

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
    public Observable<MobileAppFeed> getTopFreeApps(int topFreeAppLimit) {
        return mRetrofit.getProfile(100);
    }
}
