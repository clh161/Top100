package com.jacob.top100.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

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
    @BindView(R.id.top_gross_list)
    RecyclerView mTopGrossList;
    @BindView(R.id.top_free_list)
    RecyclerView mTopFreeList;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    private MobileAppAdapter mTopFreeAdapter = new MobileAppAdapter(R.layout.item_mobile_app_row);
    private MobileAppAdapter mTopGrossAdapter = new MobileAppAdapter(R.layout.item_mobile_app_column);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mTopFreeList.setAdapter(mTopFreeAdapter);
        mTopGrossList.setAdapter(mTopGrossAdapter);
        mTopGrossAdapter.setNaughtyLayout(false);
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
    public void setTopFreeApps(List<MobileApp> apps) {
        mTopFreeAdapter.setMobileApps(apps);
        mTopFreeAdapter.notifyDataSetChanged();
    }

    @Override
    public void setTopGrossApps(List<MobileApp> apps) {
        mTopGrossAdapter.setMobileApps(apps);
        mTopGrossAdapter.notifyDataSetChanged();
    }

    @Override
    public void setLoading(boolean isLoading) {
        mProgressBar.setVisibility(isLoading ? View.VISIBLE : View.INVISIBLE);
    }
}
