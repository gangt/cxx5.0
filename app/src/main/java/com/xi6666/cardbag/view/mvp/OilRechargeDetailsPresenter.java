package com.xi6666.cardbag.view.mvp;


import com.xi6666.common.UserData;
import com.xi6666.databean.RechargeDetialBean;
import com.xi6666.databean.RechargeListBean;

import rx.Subscriber;

/**
 * 创建者 sunsun
 * 时间 16/11/23 下午4:24.
 * 个人公众号 ardays
 */

public class OilRechargeDetailsPresenter extends OilRechargeDetailsContract.Presenter {

    private static final String TAG = "OilRecharge";

    @Override
    public void onAttached() {

    }


    @Override
    public void getRechargeDetial(String userId, String cardId, String userToken) {
        mRxManager.add(mModel.getRechargeDetial(userId, cardId, userToken).subscribe(new Subscriber<RechargeDetialBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RechargeDetialBean rechargeDetialBean) {
                if (rechargeDetialBean.isSuccess()) {
                    mView.setData(rechargeDetialBean);
                    mView.setMoney("¥" + rechargeDetialBean.getData().getSoon_money(), "¥" + rechargeDetialBean.getData().getLeiji_money());
                } else {
                    mView.showToast(rechargeDetialBean.getInfo());
                }
            }
        }));
    }

    @Override
    public void getRechargeList(String userId, String card_id, int page, String userToken) {
        mRxManager.add(mModel.getRechargeList(userId, card_id, page, userToken).subscribe(new Subscriber<RechargeListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RechargeListBean rechargeListBean) {
                if (rechargeListBean.isSuccess()) {
                    mView.setRechargeList(rechargeListBean.getData().getData());
                } else {
                    mView.showToast(rechargeListBean.getInfo());
                }
            }
        }));
    }

    @Override
    public void requestDefaultOilCard(String cardId) {
        mRxManager.add(
                mModel.defaultOilCardDefualt(cardId, UserData.getUserId(), UserData.getUserToken()).subscribe(
                        res -> {
                            mView.resultDefaultOiLCardStatus(res);
                        }, e -> {
                            mView.showToast("网络异常，请稍后重试");
                        }
                )
        );
    }
}
