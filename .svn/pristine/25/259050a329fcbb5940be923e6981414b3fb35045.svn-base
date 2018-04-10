package com.xi6666.carWash.view.mvp;

/**
 * 创建者 sunsun
 * 时间 2016/12/2 下午11:22.
 * 个人公众号 ardays
 */
public class CashierPresenter extends CashierContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestCashierDetails(String order_id) {
        mRxManager.add(mModel.getCashierDetails(order_id)
                .subscribe(
                        res -> {
                            mView.returnCashierDetails(res);
                        }, e -> {
                            mView.returnNetError();
                        }
                ));
    }

    @Override
    public void requestSelectDiscount(String service_order_sn, String use_status, int position) {
        mRxManager.add(mModel.selectServiceDiscount(service_order_sn, use_status)
                .subscribe(
                        res -> {
                            //正确返回
                            mView.returnDiscountResult(res, position);
                        }, e -> {
                            e.printStackTrace();
                        }
                ));
    }

    @Override
    public void requestFullDeduction(String service_order_sn) {
        mRxManager.add(mModel.fullDeduction(service_order_sn)
                .subscribe(
                        res -> {
                            mView.returnFullDeductionResult(res);
                        }, e -> {
                            mView.returnFullDeductionError();
                        }
                ));
    }
}
