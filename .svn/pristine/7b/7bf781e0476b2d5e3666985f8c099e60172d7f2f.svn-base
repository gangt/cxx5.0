package com.xi6666.carWash.mvp;

/**
 * 创建者 sunsun
 * 时间 16/11/21 下午5:10.
 * 个人公众号 ardays
 */

public class CarWashCardPresenter extends CarWashCardContract.Presenter {

    @Override
    protected void onStart() {

    }

    @Override
    public void getWashCardList(){
        mRxManager.add(mModel.requestWashCardList().
                subscribe(
                        carWash -> {
                            mView.resultWashCardList(carWash);
                        },e ->{
                        }
                ));
    }
}
