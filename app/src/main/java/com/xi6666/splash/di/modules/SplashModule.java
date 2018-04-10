package com.xi6666.splash.di.modules;

import com.xi6666.app.di.scope.ActScope;
import com.xi6666.splash.presenter.SplashPersenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/10/28.
 */
@Module
public class SplashModule {
    @Provides
    @ActScope
    public SplashPersenter providesSplashPersenter() {
        return new SplashPersenter();
    }
}
