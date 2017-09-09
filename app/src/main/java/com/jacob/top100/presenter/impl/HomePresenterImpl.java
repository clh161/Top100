package com.jacob.top100.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jacob.top100.interactor.HomeInteractor;
import com.jacob.top100.interactor.impl.HomeInteractorImpl;
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
    private final int mTopFreeAppsMax = 100;
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
        getApps();
    }

    private void getApps() {
        setViewLoading(true);
        mInteractor.getApps(mTopFreeAppLimit, mTopGrossAppLimit, new HttpResponse<HomeInteractorImpl.Apps>() {
            @Override
            public void onSuccess(HomeInteractorImpl.Apps apps) {
                if (mView != null) {
                    setViewLoading(false);
                    mTopFreeApps = apps.getFreeApps().getEntries();
                    mTopGrossApps = apps.getGrossApps().getEntries();
                    mView.setTopFreeApps(mTopFreeApps);
                    mView.setTopGrossApps(mTopGrossApps);
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
        if (mTopFreeApps.size() < mTopFreeAppsMax)
            if (!mIsLoading && totalItemCount == lastVisibleItem) {
                setViewLoading(true);
                Log.d(getClass().getSimpleName(), "Get More");
            }
    }
}