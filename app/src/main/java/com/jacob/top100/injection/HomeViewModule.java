package com.jacob.top100.injection;

import android.support.annotation.NonNull;

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
    public HomeInteractor provideInteractor() {
        return new HomeInteractorImpl();
    }

    @Provides
    public PresenterFactory<HomePresenter> providePresenterFactory(@NonNull final HomeInteractor interactor) {
        return new PresenterFactory<HomePresenter>() {
            @NonNull
            @Override
            public HomePresenter create() {
                return new HomePresenterImpl(interactor);
            }
        };
    }
}
