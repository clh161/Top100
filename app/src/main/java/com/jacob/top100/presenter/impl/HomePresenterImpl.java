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
    private String mQuery;

    @Inject
    public HomePresenterImpl(@NonNull HomeInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        assert mView != null;
        setViewLoading(mIsLoading);
        mView.setTopFreeApps(filter(mQuery, mTopFreeApps));
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
                    mView.setTopFreeApps(filter(mQuery, apps));
                    mTopFreeApps = apps;
                    if (mTopFreeApps.size() < mTopFreeAppLimit && filter(mQuery, apps).size() < mListLoadMoreThreshold)
                        getFreeApps(mTopFreeApps.size() + mListLoadMoreThreshold);
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

    private List<MobileApp> filter(String query, List<MobileApp> apps) {
        if (query == null || query.isEmpty())
            return apps;
        else {
            List<MobileApp> filteredApps = new ArrayList<>();
            filteredApps.addAll(apps);
            for (String q : query.split(" ")) {
                for (int i = 0; i < filteredApps.size(); i++) {
                    MobileApp app = filteredApps.get(i);
                    if (app.getName() != null && !app.getName().toLowerCase().contains(q.toLowerCase())) {
                        filteredApps.remove(i);
                        i--;
                    }
                }
            }
            return filteredApps;
        }
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
                getFreeApps(mTopFreeApps.size() + mListLoadMoreThreshold);
            }
    }

    @Override
    public void onQueryTextChange(String query) {
        assert mView != null;
        mQuery = query;
        if (query.isEmpty()) {
            mView.showQueryText(false);
            mView.setTopFreeApps(mTopFreeApps);
        } else {
            mView.showQueryText(true);
            mView.setQueryText(query);
            List<MobileApp> filteredApps = filter(query, mTopFreeApps);
            mView.setTopFreeApps(filteredApps);
            if (!mIsLoading && mTopFreeApps.size() < mTopFreeAppLimit && filteredApps.size() < mListLoadMoreThreshold)
                getFreeApps(mTopFreeApps.size() + mListLoadMoreThreshold);
        }
    }
}