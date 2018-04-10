package com.xi6666.productdetails.di;

import com.xi6666.productdetails.presenter.ProductPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr_yang on 2016/11/25.
 */
@Module
@Singleton
public class ProductDetialModule {
    @Provides
    public ProductPresenterImpl providesProductPresenterImpl() {
        return new ProductPresenterImpl();
    }
}
