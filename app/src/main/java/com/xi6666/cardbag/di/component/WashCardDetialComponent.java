package com.xi6666.cardbag.di.component;

import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.cardbag.di.module.WashCardDetialModule;
import com.xi6666.cardbag.view.WashCardDetialFrgm;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/11/22.
 */
@Component(modules = {ApiModule.class, WashCardDetialModule.class})
@Singleton
public interface WashCardDetialComponent {
    void Inject(WashCardDetialFrgm washCardDetialFrgm);
}
