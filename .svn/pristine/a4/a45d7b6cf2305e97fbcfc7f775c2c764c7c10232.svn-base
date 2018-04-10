package com.xi6666.illegal.see.mvp;

/**
 * 创建者 sunsun
 * 时间 2017/2/8 下午3:49.
 * 个人公众号 ardays
 */
public class IllegalRedeemCodePresenter extends IllegalRedeemCodeContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestIllegalCardStatus(String card_id) {
        mRxManager.add(
                mModel.getIllegalCardStatus(card_id)
                        .subscribe(
                                data -> {
                                    mView.returnIllegalCardStatus(data);
                                }, e -> {
                                    e.printStackTrace();
                                    mView.returnError();
                                }
                        )
        );
    }

    @Override
    public void requestRedeemCode(String card_id) {
        mRxManager.add(
                mModel.getRedeemCode(card_id)
                        .subscribe(
                                bean -> {
                                    mView.returnRedeemCode(bean);
                                }, e -> {
                                    e.printStackTrace();
                                    mView.returnRedeemCodeError();
                                }
                        )
        );
    }
}
