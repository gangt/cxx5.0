package com.xi6666.illegal.see.mvp;

/**
 * 创建者 sunsun
 * 时间 2017/2/9 上午10:36.
 * 个人公众号 ardays
 */
public class UsageDetailsPresenter extends UsageDetailsContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestUsageDetails(String log_id) {
        mRxManager.add(
                mModel.getUsageDetails(log_id)
                        .subscribe(
                                bean -> {
                                    mView.returnUsageDetails(bean);
                                }, e -> {
                                    e.printStackTrace();
                                    mView.returnUsageDetailsError();
                                }
                        )
        );
    }

    @Override
    public void requestCancelOrder(String log_id) {
        mRxManager.add(
                mModel.getCancelOrder(log_id)
                        .subscribe(
                                bean -> {
                                    mView.returnCancelOrder(bean);
                                }, e -> {
                                    mView.returnCancelOrderError();
                                    e.printStackTrace();
                                }
                        )
        );
    }
}
