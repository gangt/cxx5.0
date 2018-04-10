package com.xi6666.login.di;

import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.login.view.LoginAct;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/11/7.
 */
@Component(modules = {LoginModule.class, ApiModule.class})
@Singleton
public interface LoginComponent {
    void Inject(LoginAct loginAct);
}
