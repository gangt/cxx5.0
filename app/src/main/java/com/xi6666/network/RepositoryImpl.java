package com.xi6666.network;

import com.xi6666.databean.TokenBean;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2016/11/4.
 */

public class RepositoryImpl implements Repository {
    private ApiRest mApiRest;

    public RepositoryImpl(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public Observable<ResponseBody> getMessage(String page, String userId,String token) {
        return mApiRest.getMessage(page, userId,token);
    }

    @Override
    public Observable<TokenBean> getToken() {
        return null;
    }
}
