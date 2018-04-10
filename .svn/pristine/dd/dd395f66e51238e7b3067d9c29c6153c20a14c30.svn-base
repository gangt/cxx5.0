package com.xi6666.store.mvp;

/**
 * 创建者 sunsun
 * 时间 16/11/25 上午11:32.
 * 个人公众号 ardays
 */
public class StorePresenter extends StoreContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestServiceType() {
        mRxManager.add(mModel.getServiceType()
                .subscribe(
                        res -> {
                            mView.returnServiceType(res);
                        }, e -> {
                            mView.netError();
                        }
                ));
    }
}
