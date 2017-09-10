package com.jacob.top100.presenter;

import com.jacob.top100.view.HomeView;

public interface HomePresenter extends BasePresenter<HomeView> {

    void onListScroll(int dy, int totalItemCount, int lastVisibleItem);

    void onQueryTextChange(String query);
}