package com.jacob.top100.presenter.impl;

import android.support.annotation.NonNull;

import com.jacob.top100.interactor.HomeInteractor;
import com.jacob.top100.model.HttpResponse;
import com.jacob.top100.model.MobileApp;
import com.jacob.top100.presenter.HomePresenter;
import com.jacob.top100.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class HomePresenterImpl extends BasePresenterImpl<HomeView> implements HomePresenter {
    @NonNull
    private final HomeInteractor mInteractor;
    private final int mTopFreeAppLimit = 100;
    private final int mTopGrossAppLimit = 10;
    private final int mListLoadMoreThreshold = 10;
    private boolean mIsLoading = false;
    private List<MobileApp> mTopFreeApps = new ArrayList<>();
    private List<MobileApp> mTopGrossApps = new ArrayList<>();

    @Inject
    public HomePresenterImpl(@NonNull HomeInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        assert mView != null;
        setViewLoading(mIsLoading);
        mView.setTopFreeApps(mTopFreeApps);
        mView.setTopGrossApps(mTopGrossApps);
        getFreeApps(mListLoadMoreThreshold);
        getGrossApps(mTopGrossAppLimit);
    }

    private void getFreeApps(int limit) {
        setViewLoading(true);
        mInteractor.getFreeApps(limit, new HttpResponse<List<MobileApp>>() {
            @Override
            public void onSuccess(List<MobileApp> apps) {
                if (mView != null) {
                    setViewLoading(false);
                    mTopFreeApps = apps;
                    mView.setTopFreeApps(apps);
                }
            }

            @Override
            public void onFailure(Exception e) {
                setViewLoading(false);
                String message = e.getMessage();
                if (message != null && mView != null)
                    mView.showToast(message);
            }
        });
    }

    private void getGrossApps(int limit) {
        setViewLoading(true);
        mInteractor.getGrossApps(limit, new HttpResponse<List<MobileApp>>() {
            @Override
            public void onSuccess(List<MobileApp> apps) {
                if (mView != null) {
                    setViewLoading(false);
                    mTopGrossApps = apps;
                    mView.setTopGrossApps(apps);
                }
            }

            @Override
            public void onFailure(Exception e) {
                setViewLoading(false);
                String message = e.getMessage();
                if (message != null && mView != null)
                    mView.showToast(message);
            }
        });
    }


    private void setViewLoading(boolean isLoading) {
        mIsLoading = isLoading;
        if (mView != null)
            mView.setLoading(isLoading);
    }

    @Override
    public void onListScroll(int totalItemCount, int lastVisibleItem) {
        if (mTopFreeApps.size() < mTopFreeAppLimit)
            if (!mIsLoading && totalItemCount - 1 == lastVisibleItem) {
                setViewLoading(true);
                getFreeApps(mTopFreeApps.size() + mListLoadMoreThreshold);
            }
    }
}