package com.xi6666.address.fragment.mvp;

import com.xi6666.address.fragment.mvp.bean.ErrorBean;
import com.xi6666.carWash.base.network.BaseBean;

/**
 * 创建者 sunsun
 * 时间 16/11/25 上午10:02.
 * 个人公众号 ardays
 */
public class EditAddressPresenter extends EditAddressContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestAddAddress(String address, String city, String consignee, String district, String is_default, String mobile, String province) {
        mRxManager.add(mModel
                .addAddress(address, city, consignee, district, is_default, mobile, province)
                .subscribe(
                        res -> {
                            mView.returnResult(res);
                        }, e -> {
                            mView.returnResult(ErrorBean.getError());
                        }
                )
        );
    }

    @Override
    public void requestUpdateAddress(String address_id, String address, String city, String consignee, String district, String is_default, String mobile, String province) {
        mRxManager.add(mModel
                .updateAddress(address_id, address, city, consignee, district, is_default, mobile, province)
                .subscribe(
                        res -> {
                            mView.returnResult(res);
                        }, e -> {
                            e.printStackTrace();
                            mView.returnResult(ErrorBean.getError());
                        }
                )
        );
    }

    @Override
    public void requestDeleteAddress(String address_id) {
        mRxManager.add(mModel
                .deleteAddress(address_id)
                .subscribe(
                        res -> {
                            mView.returnResult(res);
                        }, e -> {

                        }
                ));
    }
}
