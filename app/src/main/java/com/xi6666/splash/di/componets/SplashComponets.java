package com.xi6666.splash.di.componets;

import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.scope.ActScope;
import com.xi6666.splash.di.modules.SplashModule;
import com.xi6666.splash.view.SplashAct;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/10/28.
 */
@Component(modules = SplashModule.class)
@ActScope
public interface SplashComponets {
    void inject(SplashAct splashAct);
}
