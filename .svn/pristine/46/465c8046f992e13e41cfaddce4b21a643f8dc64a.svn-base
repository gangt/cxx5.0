package com.xi6666.address.fragment.mvp;

/**
 * 创建者 sunsun
 * 时间 16/11/24 下午2:34.
 * 个人公众号 ardays
 */
public class PersonalAddressPresenter extends PersonalAddressContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestAddressList() {
        mRxManager.add(mModel.getAddressList()
            .subscribe(
                res -> {
                    mView.returnAddressList(res);
                },e ->{
                        //错误逻辑
                        e.printStackTrace();
                }
        ));
    }

    @Override
    public void requestSetDefaultAddress(String address_id, int position) {
        mRxManager.add(mModel.setDefaultAddress(address_id)
        .subscribe(
                res -> {
                    mView.returnSetDefaultResult(res, position);
                },e -> {
                    e.printStackTrace();
                }
        ));
    }
}
