package com.jacob.top100.presenter.impl;

import android.support.annotation.NonNull;

import com.jacob.top100.interactor.HomeInteractor;
import com.jacob.top100.interactor.impl.HomeInteractorImpl;
import com.jacob.top100.model.HttpResponse;
import com.jacob.top100.presenter.HomePresenter;
import com.jacob.top100.view.HomeView;

import javax.inject.Inject;

public final class HomePresenterImpl extends BasePresenterImpl<HomeView> implements HomePresenter {
    @NonNull
    private final HomeInteractor mInteractor;
    private final int mTopFreeAppLimit = 100;
    private final int mTopGrossAppLimit = 10;

    @Inject
    public HomePresenterImpl(@NonNull HomeInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        mInteractor.getApps(mTopFreeAppLimit, mTopGrossAppLimit, new HttpResponse<HomeInteractorImpl.Apps>() {
            @Override
            public void onSuccess(HomeInteractorImpl.Apps apps) {
                if (mView != null) {
                    mView.setTopFreeApps(apps.getFreeApps().getEntries());
                    mView.setTopGrossApps(apps.getGrossApps().getEntries());
                }
            }

            @Override
            public void onFailure(Exception e) {
                String message = e.getMessage();
                if (message != null && mView != null)
                    mView.showToast(message);
            }
        });
    }

}