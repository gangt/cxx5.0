package com.xi6666.producthome.di;

import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.producthome.view.ProductHomeAct;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/11/28.
 */
@Singleton
@Component(modules = {ProductModule.class, ApiModule.class})
public interface ProductHomeComponent {
    void Inject(ProductHomeAct productHomeAct);


}
