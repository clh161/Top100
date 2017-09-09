package com.jacob.top100.api;

import com.jacob.top100.model.MobileAppFeed;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Jacob Ho
 */

public interface AppStoreApi {
    Observable<MobileAppFeed> getTopFreeApps(int limit);

    Observable<MobileAppFeed> getGrossFreeApps(int limit);

    Observable<MobileAppFeed> getAppsDetails(List<Integer> ids);
}
