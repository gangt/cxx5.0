package com.xi6666.classification.view.mvp;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午4:22.
 * 个人公众号 ardays
 */
public class AllBrandPresenter extends AllBrandContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestAllBrand() {
        mRxManager.add(mModel.getAllBrand().subscribe(
                res -> {
                    mView.returnAllBrand(res);
                }, e -> {
                    e.printStackTrace();
                }
        ));
    }
}
