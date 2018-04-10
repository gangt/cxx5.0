package com.xi6666.app.di.componets;

import android.content.Context;

import com.baidu.platform.comapi.map.E;
import com.google.gson.Gson;
import com.xi6666.MainActivity;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ComponetBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.network.ApiRest;
import com.xi6666.network.Repository;
import com.xi6666.network.RetrofitCallBackUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/10/15.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponets {
    BaseApplication BASE_APPLICATION();

    Context CONTEXT();

    ApiRest API_REST();

    Gson GSON();

    RetrofitCallBackUtil<E> RETROFITCALLBACKUTIL();

    Repository REPOSITORY();
}
