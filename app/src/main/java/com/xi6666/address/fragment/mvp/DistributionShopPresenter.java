package com.xi6666.address.fragment.mvp;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午5:41.
 * 个人公众号 ardays
 */
public class DistributionShopPresenter extends DistributionShopContract.Presenter {


    @Override
    protected void onStart() {

    }


    @Override
    public void requestDistributionShop(int page) {
        mRxManager.add(mModel.getDistributionShop(page).subscribe(
                res->{
                    mView.returnDistributionShop(res);
                },e ->{
                    e.printStackTrace();
                    mView.returnError("网络异常");
                }
        ));
    }
}
