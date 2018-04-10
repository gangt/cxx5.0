package com.xi6666.cardbag.persenter;

import android.text.TextUtils;

import com.xi6666.cardbag.constract.IllegaBagContract;
import com.xi6666.databean.IllegaBagListBean;

import rx.Subscriber;

/**
 * Created by Mr_yang on 2017/2/8.
 */

public class IllegaBagPresenter extends IllegaBagContract.Presenter {
    private boolean isFirst = true;


    @Override
    public void onAttached() {

    }


    @Override
    public void loadData(String userId, String userToken, String page) {
        if (TextUtils.equals("1", page) && isFirst) {
            mView.showLoading();
            isFirst = false;
        }
        if (TextUtils.equals("1", page)) {
            mView.hasNoData(false);
        }
        mRxManager.add(mModel.loadData(userId, userToken, page).subscribe(new Subscriber<IllegaBagListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mView.showError();
                if (TextUtils.equals("1", page)) {
                    mView.refreshFinish();
                }
                mView.loadMoreFinish();
            }

            @Override
            public void onNext(IllegaBagListBean illegaBagListBean) {
                if (illegaBagListBean.isSuccess()) {
                    //第一页刷新
                    if (TextUtils.equals("1", page)) {
                        mView.refreshFinish();
                    }
                    mView.hideEmptyLayout();
                    mView.setListData(illegaBagListBean.getData());
                    if (TextUtils.equals("1", page) && illegaBagListBean.getData().size() == 0) {
                        mView.showEmpty();
                    }
                    mView.loadMoreFinish();
                    if (illegaBagListBean.getData().size() < 15 && !TextUtils.equals("1", page)) {
                        mView.hasNoData(true);
                    }
                } else {
                    mView.showError();
                }
            }
        }));
    }
}
