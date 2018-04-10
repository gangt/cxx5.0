package com.xi6666.message.di;

import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.message.view.MessageAct;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/11/4.
 */
@Component(modules = {MessageModule.class, ApiModule.class})
@Singleton
public interface MessageComponent {
    void Inject(MessageAct messageAct);
}
