package com.xi6666.app.di.modules;

import android.content.Context;

import com.xi6666.app.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/10/15.
 */
@Module
public class AppModule {
    private BaseApplication mBaseApplication;

    public AppModule(BaseApplication baseApplication) {
        mBaseApplication = baseApplication;
    }

    @Provides
    @Singleton
    BaseApplication providesApplication() {
        return mBaseApplication;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mBaseApplication.getApplicationContext();
    }
}
