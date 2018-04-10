package com.xi6666.illegal.see.mvp;

/**
 * 创建者 sunsun
 * 时间 2017/2/8 下午5:34.
 * 个人公众号 ardays
 */
public class RedeemCodePresenter extends RedeemCodeContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestGenerateRedeemCode(String redeem_code) {
        mRxManager.add(
                mModel.getGenerateRedeemCode(redeem_code)
                        .subscribe(
                                bean -> {
                                    mView.returnGenerateRedeemCode(bean);
                                }, e -> {
                                    mView.generateError();
                                    e.printStackTrace();
                                }
                        )
        );
    }
}
