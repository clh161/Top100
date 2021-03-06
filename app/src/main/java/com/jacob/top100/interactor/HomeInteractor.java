package com.jacob.top100.interactor;

import com.jacob.top100.model.HttpResponse;
import com.jacob.top100.model.MobileApp;

import java.util.List;

public interface HomeInteractor extends BaseInteractor {

    void getFreeApps(int limit, HttpResponse<List<MobileApp>> httpResponse);

    void getGrossApps(int limit, HttpResponse<List<MobileApp>> httpResponse);
}