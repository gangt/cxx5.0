package com.xi6666.feedback.di;

import com.xi6666.feedback.presenter.FeedBackPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/29.
 */
@Module
@Singleton
public class FeedBackModule {
    @Provides
    public FeedBackPresenterImpl providesFeedBackPresenterImpl() {
        return new FeedBackPresenterImpl();
    }
}
