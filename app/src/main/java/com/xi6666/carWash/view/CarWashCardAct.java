package com.xi6666.carWash.view;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.mvp.bean.CarWashListBean;
import com.xi6666.carWash.mvp.CarWashCardContract;
import com.xi6666.carWash.mvp.CarWashCardModel;
import com.xi6666.carWash.mvp.CarWashCardPresenter;

/**
 * 创建者 sunsun
 * 时间 16/11/21 下午4:54.
 * 个人公众号 ardays
 *
 * 这是洗车卡页面
 */

public class CarWashCardAct extends BaseToolbarView<CarWashCardPresenter, CarWashCardModel> implements CarWashCardContract.View {

    @Override
    public String title() {
        return "洗车卡充值";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_carwash_card;
    }

    @Override
    public void setUp(View view) {
        initWeb();
    }


    /**
     * 初始化网络
     */
    private void initWeb() {
        mPersenter.getWashCardList();
    }

    /**
     * 洗车卡数据返回
     */
    @Override
    public void resultWashCardList(CarWashListBean carWash) {
        Log.e("TAG","carwash-->" + carWash);
    }



    /**
     *  BY:记得注册Activity
     */
    public static final void openActivity(Activity activity){
        Intent intent = new Intent(activity,CarWashCardAct.class);
        activity.startActivity(intent);
    }

}
