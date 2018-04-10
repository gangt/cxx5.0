package com.xi6666.cardbag.view.mvp;

/**
 * 创建者 sunsun
 * 时间 16/11/24 上午10:34.
 * 个人公众号 ardays
 */
public class OilCardNotAlreadyPresenter extends OilCardNotAlreadyContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestNotAlready(String card_id) {
        mRxManager.add(mModel.getNotAlready(card_id)
        .subscribe(
                res -> {
                    mView.returnNotAlready(res);
                },e ->{

                }
        ));
    }
}
