package com.xi6666.userdata.di;

import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.scope.ActScope;
import com.xi6666.userdata.view.UserDataAct;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/11/21.
 */
@Component(dependencies = AppComponets.class, modules = UserDataModule.class)
@ActScope
public interface UserDataComponent {
    void Inject(UserDataAct userDataAct);
}
