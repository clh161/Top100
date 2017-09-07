package com.jacob.top100.injection;

import android.support.annotation.NonNull;

import com.jacob.top100.api.AppStoreApi;
import com.jacob.top100.interactor.HomeInteractor;
import com.jacob.top100.interactor.impl.HomeInteractorImpl;
import com.jacob.top100.presenter.HomePresenter;
import com.jacob.top100.presenter.impl.HomePresenterImpl;
import com.jacob.top100.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class HomeViewModule {
    @Provides
    public HomeInteractor provideInteractor(AppStoreApi appStoreApi) {
        return new HomeInteractorImpl(appStoreApi);
    }

    @Provides
    public PresenterFactory<HomePresenter> providePresenterFactory(@NonNull final HomeInteractor interactor) {
        return () -> new HomePresenterImpl(interactor);
    }
}
