package com.xi6666.car.mp;

/**
 * 创建者 sunsun
 * 时间 16/11/26 上午11:38.
 * 个人公众号 ardays
 */
public class MyCarPresenter extends MyCarContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestMyCarList() {
        mRxManager.add(mModel.getMyCarList().subscribe(
                res -> {
                    mView.returnCarList(res);
                }, e -> {
                    mView.returnListNetError();
                }
        ));

    }

    @Override
    public void requestSetDefaultCar(String car_id, String car_brand_id, String car_chexing_id, String car_pailiang_id, String car_nianfen_id, int position) {
        mRxManager.add(mModel
                .setDefaultCar(car_id, car_brand_id, car_chexing_id, car_pailiang_id, car_nianfen_id).subscribe(
                        res -> {
                            mView.returnCarResult(res, position);
                        }, e -> {
                            mView.returnError("网络异常");
                        }
                ));
    }

    @Override
    public void requestDelCar(String car_id, int position) {
        mRxManager.add(mModel
                .delCar(car_id)
                .subscribe(
                        res -> {
                            mView.returnDelResult(res, position);
                        }, e -> {
                            e.printStackTrace();
                        }
                )
        );
    }

}
