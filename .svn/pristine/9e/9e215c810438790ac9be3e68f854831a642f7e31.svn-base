package com.xi6666.carWash.view.mvp;

/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午5:57.
 * 个人公众号 ardays
 */
public class CarWashActPresenter extends CarWashActContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestCarWashList(int type, int sort, int page) {
        mRxManager.add(mModel.getCarWashList(type, sort, page)
                .subscribe(
                        res -> {
                            mView.returnCarWashList(res, page);
                        }, e -> {
                            mView.loadingError();
                            e.printStackTrace();
                        }
                ));
    }

    @Override
    public void requestBanner() {
        mRxManager.add(mModel.getBannerList()
                .subscribe(
                        res -> {
                            mView.returnBannerList(res);
                        }, e -> {
                            e.printStackTrace();
                            mView.loadingError();
                        }
                ));
    }
}
