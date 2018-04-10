package com.xi6666.message.presenter;


import android.util.Log;

import com.google.gson.Gson;

import com.xi6666.databean.MessageBean;
import com.xi6666.message.MessageUseCase;
import com.xi6666.message.contract.MessageContract;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Mr_yang on 2016/11/2.
 */

public class MessagePresenterImpl implements MessageContract.Presenter {

    private static final String TAG = "MessagePresenterImpl";
    private MessageUseCase mMessageUseCase;
    private MessageContract.View mView;
    private ApiRest mApiRest;

    public MessagePresenterImpl(MessageUseCase messageUseCase) {
        mMessageUseCase = messageUseCase;
    }

    public void setApiRest(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void attachView(MessageContract.View view) {
        mView = view;
    }

    @Override
    public void loadMoreData(final String page, String userId, String userToekn) {

       /* mMessageUseCase.setPageAndUserId(page, userId, userToekn);
        mMessageUseCase.execute()*/
        mApiRest.getMessage(page, userId, userToekn).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(TAG, "loadMoreDataerror--->" + e);
                        mView.error();
                        mView.hideLoadMore();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            LogUtil.d(TAG, "string==" + string);
                            Gson gson = new Gson();
                            MessageBean messageBean = gson.fromJson(string, MessageBean.class);
                            List<MessageBean.DataBean> data = messageBean.getData();
                            mView.addImages(data);
                            mView.hideLoadMore();
                            if (data.size() < 5) {
                                mView.hasNodata();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void refrashData(final String page, String userId, String userToeken) {
        LogUtil.d(TAG, "page--->" + page);
        LogUtil.d(TAG, "userId--->" + userId);
        LogUtil.d(TAG, "userToeken--->" + userToeken);
        // mMessageUseCase.setPageAndUserId(page, userId, userToeken);
        mApiRest.getMessage(page, userId, userToeken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(TAG, "refrashDataerror--->" + e);
                        mView.error();
                        mView.hideLoadMore();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            LogUtil.d(TAG, "string==" + string);
                            Gson gson = new Gson();
                            MessageBean messageBean = gson.fromJson(string, MessageBean.class);
                            List<MessageBean.DataBean> data = messageBean.getData();
                            mView.addImages(data);
                            if (data.size() < 1) {
                                mView.showEmptyView();
                            }
                            mView.hideRefresh();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
