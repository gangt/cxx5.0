package com.xi6666.classification.view.mvp;

/**
 * 创建者 sunsun
 * 时间 2016/11/30 下午9:48.
 * 个人公众号 ardays
 */
public class ShopSearchPresenter extends ShopSearchContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestHotSearch() {
        mRxManager.add(
                mModel.getHotSearch()
                        .subscribe(
                                res -> {
                                    mView.returnHotSearch(res);
                                }, e -> {
                                    e.printStackTrace();
                                }
                        ));
    }

    @Override
    public void requestSearchList(int page, int sort, String keyword) {
        mRxManager.add(
                mModel.getSearchList(page, sort, keyword)
                        .subscribe(
                                res -> {
                                    mView.returnSearchList(res);
                                }, e -> {
                                    e.printStackTrace();
                                }
                        )
        );
    }
}
