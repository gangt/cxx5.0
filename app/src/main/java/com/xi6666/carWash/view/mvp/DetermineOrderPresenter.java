package com.xi6666.carWash.view.mvp;

/**
 * 创建者 sunsun
 * 时间 2016/12/1 上午10:15.
 * 个人公众号 ardays
 */
public class DetermineOrderPresenter extends DetermineOrderContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestGenerateOrder(String service_id) {
        mRxManager.add(
                mModel.generateOrder(service_id)
                        .subscribe(
                                res -> {
                                    mView.returnGenerateOrder(res);
                                }, e -> {
                                    mView.returnNetError();
                                }
                        )
        );
    }
}
