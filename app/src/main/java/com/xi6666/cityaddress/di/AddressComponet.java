package com.xi6666.cityaddress.di;

import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.cityaddress.view.AddressAct;
import com.xi6666.cityaddress.view.CityAddressAct;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/11/11.
 */
@Component(modules = {ApiModule.class, AddressModule.class})
@Singleton
public interface AddressComponet {

    void Inject(AddressAct maAddressAct);

    void Inject(CityAddressAct cityAddressAct);

}
