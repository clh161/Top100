package com.jacob.top100.api;

import com.jacob.top100.model.MobileAppDetailFeed;
import com.jacob.top100.model.MobileAppFeed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Jacob Ho
 */
public interface AppStoreApiRetrofit {
    @GET("rss/topfreeapplications/limit={limit}/json")
    Observable<MobileAppFeed> getTopFreeApps(@Path("limit") int limit);

    @GET("rss/topgrossingapplications/limit={limit}/json")
    Observable<MobileAppFeed> getTopGrossApps(@Path("limit") int limit);

    @GET("lookup")
    Observable<MobileAppDetailFeed> getAppsDetails(@Query("id") String ids);
}
