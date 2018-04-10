package com.xi6666.cardbag.di.module;

import com.xi6666.cardbag.persenter.WashCardInfoImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/22.
 */
@Module
public class WashCardInfoModule {
    @Provides
    @Singleton
    public WashCardInfoImpl providesWashCardInfoImpl() {
        return new WashCardInfoImpl();
    }


}
