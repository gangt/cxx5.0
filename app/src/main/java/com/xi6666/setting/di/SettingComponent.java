package com.xi6666.setting.di;



import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.scope.ActScope;
import com.xi6666.setting.view.SettingAct;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/11/19.
 */
@ActScope
@Component(modules = {SettingModule.class, ApiModule.class})
public interface SettingComponent {
    void Inject(SettingAct settingAct);
}
