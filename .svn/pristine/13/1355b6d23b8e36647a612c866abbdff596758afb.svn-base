package com.xi6666.message;

import com.xi6666.network.Repository;
import com.xi6666.network.Usecase;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2016/11/4.
 */

public class MessageUseCase implements Usecase<ResponseBody> {
    private Repository mRepository;
    private String mPage, mUserId,userToken;

    public void setPageAndUserId(String page, String userId,String token) {
        mPage = page;
        mUserId = userId;
        userToken=token;
    }

    public MessageUseCase(Repository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<ResponseBody> execute() {
        return mRepository.getMessage(mPage, mUserId,userToken);
    }
}
