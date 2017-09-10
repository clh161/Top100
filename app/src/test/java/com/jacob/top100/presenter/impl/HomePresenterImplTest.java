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
import static com.jacob.top100.presenter.impl.HomePresenterImpl.TOP_GROSS_APP_LIMIT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        String[] categories = new String[]{"Games", "Photo & Video", "Offical App", "Health & Fitness"};
        mFreeApps.clear();
        mGrossApps.clear();
        for (int i = 0; i < 10; i++) {
            MobileApp freeApp = new MobileApp(i + 1, "Free App No." + i, "http://www.website.com/free-app-icon-" + i, categories[i % categories.length], (float) ((i % 5) + 1), i + 1);
            mFreeApps.add(freeApp);
            MobileApp grossApp = new MobileApp(i + 1, "Gross App No." + i, "http://www.website.com/gross-app-icon-" + i, categories[i % categories.length], null, null);
            mGrossApps.add(grossApp);
        }
        doAnswer(invocation -> {
            ((HttpResponse<List<MobileApp>>) invocation.getArgument(1)).onSuccess(mFreeApps);
            return null;
        }).when(mInteractor).getFreeApps(anyInt(), any(HttpResponse.class));

        doAnswer(invocation -> {
            ((HttpResponse<List<MobileApp>>) invocation.getArgument(1)).onSuccess(mGrossApps);
            return null;
        }).when(mInteractor).getGrossApps(anyInt(), any(HttpResponse.class));
        mPresenter = new HomePresenterImpl(mInteractor);
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
        verify(mInteractor).getGrossApps(eq(TOP_GROSS_APP_LIMIT), any(HttpResponse.class));
        verify(mView).setTopGrossApps(eq(mGrossApps));
    }

    @Test
    public void loadingTest() throws Exception {
        verify(mView, times(2)).setLoading(eq(true));
        verify(mView, times(3)).setLoading(eq(false));
    }

}