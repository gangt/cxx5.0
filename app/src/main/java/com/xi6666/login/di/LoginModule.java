package com.xi6666.login.di;



import com.xi6666.login.presenter.LoginActPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/7.
 */
@Module
public class LoginModule {
    @Provides
    @Singleton
    public LoginActPresenterImpl providesLoginPresenterImpl() {
        return new LoginActPresenterImpl();
    }
}
