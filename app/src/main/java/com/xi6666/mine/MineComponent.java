package com.xi6666.mine;

import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.scope.FrgmScope;
import com.xi6666.mine.view.MineFrgm;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by Mr_yang on 2016/11/12.
 */
@FrgmScope
@Component(dependencies = AppComponets.class, modules = MineModule.class)
public interface MineComponent {
    void Inject(MineFrgm mineFrgm);
}
