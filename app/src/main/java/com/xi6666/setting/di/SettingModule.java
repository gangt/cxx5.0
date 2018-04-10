package com.xi6666.setting.di;

import com.xi6666.app.di.scope.ActScope;
import com.xi6666.network.ApiRest;
import com.xi6666.setting.SettingPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/19.
 */
@Module
public class SettingModule {
    @Provides
    @ActScope
    public SettingPresenterImpl providesSettingPresenterImpl() {
        return new SettingPresenterImpl();
    }
}
