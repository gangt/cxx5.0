package com.xi6666.login.di;
import com.xi6666.login.presenter.LoginPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/5.
 */
@Module
public class PhoneLoginModule {
    @Provides
    @Singleton
    public LoginPresenterImpl providesLoginPresenterImpl() {
        return new LoginPresenterImpl();
    }
}
