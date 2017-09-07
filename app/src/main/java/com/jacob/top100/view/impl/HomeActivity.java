package com.jacob.top100.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jacob.top100.R;
import com.jacob.top100.adapter.MobileAppAdapter;
import com.jacob.top100.injection.AppComponent;
import com.jacob.top100.injection.DaggerHomeViewComponent;
import com.jacob.top100.injection.HomeViewModule;
import com.jacob.top100.model.MobileApp;
import com.jacob.top100.presenter.HomePresenter;
import com.jacob.top100.presenter.loader.PresenterFactory;
import com.jacob.top100.view.HomeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class HomeActivity extends BaseActivity<HomePresenter, HomeView> implements HomeView {
    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;
    @BindView(R.id.list)
    RecyclerView mList;
    private MobileAppAdapter mAdapter = new MobileAppAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);
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

    @Override
    public void setApps(List<MobileApp> apps) {
        mAdapter.setMobileApps(apps);
        mAdapter.notifyDataSetChanged();
    }
}
