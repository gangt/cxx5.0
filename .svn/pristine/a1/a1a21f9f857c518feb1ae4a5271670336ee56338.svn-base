package com.xi6666.login;

import com.xi6666.databean.TokenBean;
import com.xi6666.network.Repository;
import com.xi6666.network.Usecase;

import rx.Observable;

/**
 * Created by Mr_yang on 2016/11/5.
 */

public class PhoneLoginUseCase implements Usecase<TokenBean> {
    private Repository mRepository;

    public PhoneLoginUseCase(Repository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<TokenBean> execute() {
        return mRepository.getToken();
    }
}
