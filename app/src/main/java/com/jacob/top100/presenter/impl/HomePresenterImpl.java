package com.jacob.top100.presenter.impl;

import android.support.annotation.NonNull;

import com.jacob.top100.interactor.HomeInteractor;
import com.jacob.top100.model.HttpResponse;
import com.jacob.top100.model.MobileApp;
import com.jacob.top100.presenter.HomePresenter;
import com.jacob.top100.view.HomeView;

import java.util.List;

import javax.inject.Inject;

public final class HomePresenterImpl extends BasePresenterImpl<HomeView> implements HomePresenter {
    @NonNull
    private final HomeInteractor mInteractor;
    private final int mTopFreeAppLimit = 100;

    @Inject
    public HomePresenterImpl(@NonNull HomeInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        mInteractor.getTopFreeApps(mTopFreeAppLimit, new HttpResponse<List<MobileApp>>() {
            @Override
            public void onSuccess(List<MobileApp> apps) {
                mView.setApps(apps);
            }

            @Override
            public void onFailure(Exception e) {
                String message = e.getMessage();
                if (message != null)
                    mView.showToast(message);
            }
        });
    }

}