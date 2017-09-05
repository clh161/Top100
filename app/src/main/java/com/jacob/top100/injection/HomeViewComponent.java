package com.jacob.top100.injection;

import com.jacob.top100.view.impl.HomeActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = HomeViewModule.class)
public interface HomeViewComponent {
    void inject(HomeActivity activity);
}