package com.xi6666.cardbag.view.mvp;

/**
 * 创建者 sunsun
 * 时间 16/11/22 下午6:35.
 * 个人公众号 ardays
 */

public class AddOilCardPresenter extends AddOilCardContract.Presenter {

    @Override
    public void requestOilCardName(String oilCardNumber) {
         mRxManager.add(mModel.getOilCardName(oilCardNumber).subscribe(
                res ->{
                    mView.returnOilCardName(res);
                },e ->{
//                     mView.toastError("网络异常");
                }
        ));
    }

    @Override
    public void requestAddOilCard(String card_name, String card_number, String user_mobile) {
        mRxManager.add(mModel.addOilCard(card_name, card_number, user_mobile).subscribe(
                res ->{
                    mView.returnAddOilCardResult(res);
                },e ->{
                    e.printStackTrace();
                }
        ));
    }

    @Override
    protected void onStart() {

    }
}
