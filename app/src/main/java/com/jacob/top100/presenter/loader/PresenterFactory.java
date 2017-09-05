package com.jacob.top100.presenter.loader;

import android.support.annotation.NonNull;

import com.jacob.top100.presenter.BasePresenter;

/**
 * Factory to implement to create a presenter
 */
public interface PresenterFactory<T extends BasePresenter> {
    @NonNull
    T create();
}
