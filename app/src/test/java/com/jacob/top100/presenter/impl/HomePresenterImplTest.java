package com.jacob.top100.presenter.impl;

import com.jacob.top100.interactor.HomeInteractor;
import com.jacob.top100.view.HomeView;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author Jacob Ho
 */
public class HomePresenterImplTest {
    @Mock
    HomeInteractor mInteractor;
    @Mock
    HomeView mView;
    private HomePresenterImpl mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new HomePresenterImpl(mInteractor);
        mPresenter.onViewAttached(mView);
        mPresenter.onStart(true);

    }
}