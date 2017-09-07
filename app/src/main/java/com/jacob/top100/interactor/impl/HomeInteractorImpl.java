package com.jacob.top100.interactor.impl;

import com.jacob.top100.api.AppStoreApi;
import com.jacob.top100.interactor.HomeInteractor;
import com.jacob.top100.model.HttpResponse;
import com.jacob.top100.model.MobileApp;

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
    public void getTopFreeApps(int topFreeAppLimit, HttpResponse<List<MobileApp>> response) {
        mAppStoreApi.getTopFreeApps(topFreeAppLimit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    response.onSuccess(result.getEntries());
                }, e -> response.onFailure((Exception) e));
    }
}