package com.xi6666.app.di.componets;

import com.xi6666.MainActivity;
import com.xi6666.app.di.scope.ActScope;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/10/15.
 */
@ActScope
@Component(dependencies = AppComponets.class)
public interface ActComponet {
    void inject(MainActivity mainActivity);
}
