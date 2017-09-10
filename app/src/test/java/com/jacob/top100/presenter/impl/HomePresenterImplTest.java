package com.jacob.top100.presenter.impl;

import com.jacob.top100.interactor.HomeInteractor;
import com.jacob.top100.model.HttpResponse;
import com.jacob.top100.model.MobileApp;
import com.jacob.top100.view.HomeView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.jacob.top100.presenter.impl.HomePresenterImpl.LIST_LOAD_MORE_THRESHOLD;
import static com.jacob.top100.presenter.impl.HomePresenterImpl.TOP_FREE_APP_LIMIT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Jacob Ho
 */
public class HomePresenterImplTest {
    @Mock
    HomeInteractor mInteractor;
    @Mock
    HomeView mView;
    private HomePresenterImpl mPresenter;
    private List<MobileApp> mFreeApps = new ArrayList<>();
    private List<MobileApp> mGrossApps = new ArrayList<>();
    private String[] mCategories = new String[]{"Games", "Photo & Video", "Offical App", "Health & Fitness"};

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mGrossApps.clear();
        for (int i = 0; i < LIST_LOAD_MORE_THRESHOLD; i++) {

            MobileApp grossApp = new MobileApp(i + 1, "Gross App No." + i, "http://www.website.com/gross-app-icon-" + i, mCategories[i % mCategories.length], null, null);
            mGrossApps.add(grossApp);
        }
        doAnswer(invocation -> {
            mFreeApps.clear();
            int limit = invocation.getArgument(0);
            HttpResponse<List<MobileApp>> response = invocation.getArgument(1);
            for (int i = 0; i < limit; i++) {
                MobileApp app = new MobileApp(i + 1, "Free App No." + i, "http://www.website.com/free-app-icon-" + i, mCategories[i % mCategories.length], (float) ((i % 5) + 1), i + 1);
                mFreeApps.add(app);
            }
            response.onSuccess(mFreeApps);

            return null;
        }).when(mInteractor).getFreeApps(anyInt(), any(HttpResponse.class));

        doAnswer(invocation -> {
            mGrossApps.clear();
            int limit = invocation.getArgument(0);
            HttpResponse<List<MobileApp>> response = invocation.getArgument(1);
            for (int i = 0; i < limit; i++) {
                MobileApp app = new MobileApp(i + 1, "Gross App No." + i, "http://www.website.com/free-app-icon-" + i, mCategories[i % mCategories.length], null, null);
                mGrossApps.add(app);
            }
            response.onSuccess(mGrossApps);
            return null;
        }).when(mInteractor).getGrossApps(anyInt(), any(HttpResponse.class));
        mPresenter = spy(new HomePresenterImpl(mInteractor));
        mPresenter.onViewAttached(mView);
        mPresenter.onStart(true);
    }

    @Test
    public void fetchFreeAppsOnInit() throws Exception {
        verify(mInteractor).getFreeApps(eq(LIST_LOAD_MORE_THRESHOLD), any(HttpResponse.class));
        verify(mView).setTopFreeApps(eq(mFreeApps));
    }

    @Test
    public void fetchGrossAppsOnInit() throws Exception {
        verify(mInteractor).getGrossApps(eq(LIST_LOAD_MORE_THRESHOLD), any(HttpResponse.class));
        verify(mView).setTopGrossApps(eq(mGrossApps));
    }

    @Test
    public void loadingTest() throws Exception {
        verify(mView, times(2)).setLoading(eq(true));
        verify(mView, times(3)).setLoading(eq(false));
    }

    @Test
    public void doNotFetchMoreFreeApps() throws Exception {
        reset(mView);
        mPresenter.onListScroll(0, mFreeApps.size(), mFreeApps.size() - 5);
        verify(mView, never()).setLoading(anyBoolean());

    }

    @Test
    public void fetchMoreFreeApps() throws Exception {
        reset(mView);
        int freeAppSize = mFreeApps.size();
        for (int i = freeAppSize; i < freeAppSize + LIST_LOAD_MORE_THRESHOLD; i++) {
            MobileApp freeApp = new MobileApp(i + 1, "Free App No." + i, "http://www.website.com/free-app-icon-" + i, mCategories[i % mCategories.length], (float) ((i % 5) + 1), i + 1);
            mFreeApps.add(freeApp);
        }
        mPresenter.onListScroll(0, mFreeApps.size(), mFreeApps.size() - 1);
        verify(mView).setTopFreeApps(eq(mFreeApps));
    }

    @Test
    public void scrollListsToTopOnSearch() throws Exception {
        reset(mView);
        mPresenter.onQueryTextChange("SOME_QUERY");
        verify(mView).scrollFreeAppList(0);
        verify(mView).scrollGrossAppList(0);
    }

    /**
     * Query may shorten the apps list, if the list size is too small, fetch more apps
     */
    @Test
    public void loadMoreAppsOnQuery() throws Exception {
        mPresenter.onQueryTextChange("NO_RESULTS_FOR_THIS_STRING");
        for (int i = 0; i < TOP_FREE_APP_LIMIT / LIST_LOAD_MORE_THRESHOLD; i++) {
            verify(mPresenter).getFreeApps((i + 1) * LIST_LOAD_MORE_THRESHOLD);
        }
        verify(mPresenter, times(TOP_FREE_APP_LIMIT / LIST_LOAD_MORE_THRESHOLD)).getFreeApps(anyInt());
    }
}