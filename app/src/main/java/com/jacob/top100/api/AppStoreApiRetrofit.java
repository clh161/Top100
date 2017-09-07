package com.jacob.top100.api;

import com.jacob.top100.model.MobileAppFeed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Jacob Ho
 */
public interface AppStoreApiRetrofit {
    @GET("rss/topfreeapplications/limit={limit}/json")
    Observable<MobileAppFeed> getProfile(@Path("limit") int limit);
}
