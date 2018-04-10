package com.xi6666.classification.view.fragment.mvp;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午2:14.
 * 个人公众号 ardays
 */
public class ServiceClassificationBrandPresenter extends ServiceClassificationBrandContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestBrandList() {
        mRxManager.add(
                mModel.getBrandList()
                        .subscribe(
                                res -> {
                                    mView.returnBrandList(res);
                                }, e -> {
                                    e.printStackTrace();
                                    mView.error();
                                }
                        ));
    }
}
