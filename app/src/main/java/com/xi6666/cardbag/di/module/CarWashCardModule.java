package com.xi6666.cardbag.di.module;

import com.xi6666.cardbag.persenter.CarWashCardPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/24.
 */
@Module
public class CarWashCardModule {
    @Provides
    @Singleton
    public CarWashCardPresenterImpl providesCarWashCardPresenterImpl() {
        return new CarWashCardPresenterImpl();
    }
}
