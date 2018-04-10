package com.xi6666.home.di;

import com.xi6666.home.presenter.HomePresenterImpl;
import com.xi6666.network.ApiRest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/10.
 */
@Module
public class HomeModule {
    @Provides
    public HomePresenterImpl providesHomePresenterImpl(ApiRest maApiRest) {
        return new HomePresenterImpl(maApiRest);
    }
}
