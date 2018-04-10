package com.xi6666.cityaddress.di;

import com.xi6666.cityaddress.presenter.AddressPresenterCityImpl;
import com.xi6666.cityaddress.presenter.AddressPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/23.
 */

@Module
public class AddressModule {
    @Provides
    @Singleton
    AddressPresenterImpl providesAddressPresenterImpl() {
        return new AddressPresenterImpl();
    }

    @Provides
    @Singleton
    AddressPresenterCityImpl providesAddressPresenterCityImpl() {
        return new AddressPresenterCityImpl();
    }
}