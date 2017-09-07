package com.jacob.top100.api;

import com.jacob.top100.model.MobileAppFeed;

import io.reactivex.Observable;

/**
 * @author Jacob Ho
 */

public interface AppStoreApi {
    Observable<MobileAppFeed> getTopFreeApps(int topFreeAppLimit);
}
