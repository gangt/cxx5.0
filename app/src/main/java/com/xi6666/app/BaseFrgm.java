package com.xi6666.app;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;

/**
 * Created by Mr_yang on 2016/11/12.
 */

public abstract class BaseFrgm extends SuperFrgm {

    protected AppComponets mAppComponets;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppComponets = DaggerAppComponets.builder().appModule(new AppModule((BaseApplication) mActivity.getApplication()
        )).apiModule(new ApiModule((BaseApplication) mActivity.getApplication())).build();
    }

    public AppComponets getAppComponets() {

        return mAppComponets;
    }
}
