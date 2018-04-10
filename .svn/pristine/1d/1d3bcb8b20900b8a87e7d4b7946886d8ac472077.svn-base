package com.xi6666.classification.view.fragment.mvp;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午12:03.
 * 个人公众号 ardays
 */
public class ServiceClassificationPresenter extends ServiceClassificationContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestGoodsList() {
        mRxManager.add(mModel.getGoosList().subscribe(
                res -> {
                    mView.returnGoodsList(res);
                }, e -> {
                    e.printStackTrace();
                }
        ));
    }
}
