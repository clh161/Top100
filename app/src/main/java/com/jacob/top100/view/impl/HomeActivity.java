package com.jacob.top100.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jacob.top100.R;
import com.jacob.top100.injection.AppComponent;
import com.jacob.top100.injection.DaggerHomeViewComponent;
import com.jacob.top100.injection.HomeViewModule;
import com.jacob.top100.presenter.HomePresenter;
import com.jacob.top100.presenter.loader.PresenterFactory;
import com.jacob.top100.view.HomeView;

import javax.inject.Inject;

public final class HomeActivity extends BaseActivity<HomePresenter, HomeView> implements HomeView {
    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart or onPostCreate.
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerHomeViewComponent.builder()
                .appComponent(parentComponent)
                .homeViewModule(new HomeViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
