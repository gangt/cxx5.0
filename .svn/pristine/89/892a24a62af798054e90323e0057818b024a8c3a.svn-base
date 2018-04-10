package com.xi6666.message.di;

import com.xi6666.message.MessageUseCase;
import com.xi6666.message.presenter.MessagePresenterImpl;
import com.xi6666.network.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/4.
 */
@Module
public class MessageModule {
    @Provides
    public MessageUseCase providesMessageUseCase(Repository repository) {
        return new MessageUseCase(repository);
    }

    @Provides
    public MessagePresenterImpl providesMessagePresenterImpl(MessageUseCase messageUseCase) {
        return new MessagePresenterImpl(messageUseCase);
    }


}
