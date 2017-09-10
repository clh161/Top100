package com.jacob.top100.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

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
    @BindView(R.id.search_view)
    SearchView mSearchView;
    @BindView(R.id.header_no_results)
    TextView mNoResult;
    @BindView(R.id.top_gross_list_container)
    LinearLayout mTopGrossListContainer;
    private MobileAppAdapter mTopFreeAdapter = new MobileAppAdapter(R.layout.item_mobile_app_row);
    private MobileAppAdapter mTopGrossAdapter = new MobileAppAdapter(R.layout.item_mobile_app_column);
    private LinearLayoutManager mTopFreeListLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        assert mPresenter != null;
        mTopFreeListLayoutManager = new LinearLayoutManager(this);
        mTopFreeList.setLayoutManager(mTopFreeListLayoutManager);
        mTopFreeList.setAdapter(mTopFreeAdapter);
        mTopGrossList.setAdapter(mTopGrossAdapter);
        mTopGrossAdapter.setNaughtyLayout(false);
        mSearchView.setOnClickListener(searchBar -> ((SearchView) searchBar).setIconified(false));
        mTopFreeList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = mTopFreeListLayoutManager.getItemCount();
                int lastVisibleItem = mTopFreeListLayoutManager.findLastVisibleItemPosition();
                mPresenter.onListScroll(dy, totalItemCount, lastVisibleItem);
            }
        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, mTopFreeListLayoutManager.getOrientation());
        mTopFreeList.addItemDecoration(dividerItemDecoration);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mPresenter.onQueryTextChange(query);
                return false;
            }
        });
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
        if (apps.isEmpty())
            mNoResult.setVisibility(View.VISIBLE);
        else
            mNoResult.setVisibility(View.GONE);
    }

    @Override
    public void setTopGrossApps(List<MobileApp> apps) {
        mTopGrossAdapter.setMobileApps(apps);
        mTopGrossAdapter.notifyDataSetChanged();
    }

    @Override
    public void setLoading(boolean isLoading) {
        mNoResult.setVisibility(View.GONE);
        mProgressBar.setVisibility(isLoading ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setTopGrossListContainerTopMargin(int margin) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mTopGrossListContainer.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, margin, layoutParams.rightMargin, layoutParams.bottomMargin);
        mTopGrossListContainer.requestLayout();
    }

    @Override
    public float getTopGrossListContainerHeight() {
        return mTopGrossListContainer.getHeight();
    }

    @Override
    public void scrollGrossAppList(int index) {
        mTopGrossList.scrollToPosition(index);
    }

    @Override
    public void scrollFreeAppList(int index) {
        mTopFreeList.scrollToPosition(index);
    }
}
