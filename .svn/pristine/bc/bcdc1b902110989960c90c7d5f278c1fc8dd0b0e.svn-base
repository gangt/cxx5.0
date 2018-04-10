package com.xi6666.carWash.view.mvp;

/**
 * 创建者 sunsun
 * 时间 16/11/26 上午10:08.
 * 个人公众号 ardays
 */
public class CarWashSearchResultPresenter extends CarWashSearchResultContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestSearchStoreList(String keyword, int page, int order_by) {
        mRxManager.add(mModel.searchStoreList(keyword, page, order_by)
                .subscribe(
                        res -> {
                            mView.returnStoreList(res);
                        }, e -> {
                            mView.returnError();
                        }
                ));
    }
}
