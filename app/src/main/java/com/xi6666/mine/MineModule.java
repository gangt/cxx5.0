package com.xi6666.mine;

import com.xi6666.app.di.scope.FragmScope;
import com.xi6666.mine.presenter.MinePresenterImpl;
import com.xi6666.network.ApiRest;
import com.xi6666.setting.SettingPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/21.
 */
@Module
@FragmScope
public class MineModule {
    @Provides
    public MinePresenterImpl providesSettingPresenterImpl(ApiRest apiRest) {
        return new MinePresenterImpl(apiRest);
    }
}
