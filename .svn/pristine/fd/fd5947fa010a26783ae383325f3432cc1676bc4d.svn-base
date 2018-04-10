package com.xi6666.car.mp;

/**
 * 创建者 sunsun
 * 时间 2017/1/16 上午10:59.
 * 个人公众号 ardays
 */
public class SettingCarTypePresenter extends SettingCarTypeContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestCarBrand() {
        mRxManager.add(
                mModel.getCarType()
                        .subscribe(res -> {
                            mView.resultCarType(res);
                        }, e -> {
                            e.printStackTrace();
                            mView.brandNetError();
                        })
        );
    }

    @Override
    public void requestCarBrandType(String brand_number) {
        mRxManager.add(mModel.getCarBrandType(brand_number)
                .subscribe(
                        res -> {
                            mView.resultCarBrandType(res);
                        }, e -> {
                            e.printStackTrace();
                        }
                ));
    }

    @Override
    public void reqeustCarYear(String cartype_number) {
        mRxManager.add(mModel.getCarYear(cartype_number)
                .subscribe(
                        res -> {
                            mView.resultCarYear(res);
                        }, e -> {
                            e.printStackTrace();
                        }
                ));
    }

    @Override
    public void requestCarEngine(String brand_number, String cartype_number) {
        mRxManager.add(mModel.getCarEngine(brand_number, cartype_number).subscribe(
                res -> {
                    mView.resultCarEngine(res);
                }, e -> {
                    e.printStackTrace();
                }
        ));
    }
}
