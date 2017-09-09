package com.jacob.top100.interactor;

import com.jacob.top100.interactor.impl.HomeInteractorImpl;
import com.jacob.top100.model.HttpResponse;

public interface HomeInteractor extends BaseInteractor {

    void getApps(int topFreeAppLimit, int topGrossAppLimit, HttpResponse<HomeInteractorImpl.Apps> response);
}