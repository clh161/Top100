package com.jacob.top100.interactor;

import com.jacob.top100.model.HttpResponse;
import com.jacob.top100.model.MobileAppFeed;

public interface HomeInteractor extends BaseInteractor {

    void getTopFreeApps(int topFreeAppLimit, HttpResponse<MobileAppFeed> response);
}