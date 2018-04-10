package com.xi6666.app;

import android.os.Bundle;

import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;

/**
 * Created by Mr_yang on 2016/11/11.
 */

public abstract class ComponetBaseAct extends ToolBarBaseAct {

    protected AppComponets mAppComponets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppComponets = DaggerAppComponets.builder().apiModule(new ApiModule((BaseApplication) getApplication())).
                appModule(new AppModule((BaseApplication) getApplication())).build();

    }
}
