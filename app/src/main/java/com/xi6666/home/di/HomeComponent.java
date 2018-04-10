package com.xi6666.home.di;

import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.home.view.HomeFrgm;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/11/10.
 */
@Component(modules = {ApiModule.class, HomeModule.class})
@Singleton
public interface HomeComponent {
    void Inject(HomeFrgm homeFrgm);
}
