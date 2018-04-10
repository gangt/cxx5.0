package com.xi6666.userdata.di;

import com.xi6666.app.di.scope.ActScope;
import com.xi6666.network.ApiRest;
import com.xi6666.userdata.UserDataPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/21.
 */
@Module
@ActScope
public class UserDataModule {
    @Provides
    @ActScope
    public UserDataPresenterImpl providesUserDataPresenterImpl(ApiRest apiRest) {
        return new UserDataPresenterImpl(apiRest);
    }
}
