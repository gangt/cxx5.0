package com.xi6666.cardbag.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xi6666.R;
import com.xi6666.app.ToolBarBaseAct;
import com.xi6666.eventbus.PointerChangeEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author peng
 * @data 创建时间:2016/11/12
 * @desc 卡券包
 */
public class CardBagAct extends ToolBarBaseAct {
    private FragmentManager mSupportFragmentManager;
    private FragmentTransaction mFragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardbag);
        mSupportFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mSupportFragmentManager.beginTransaction();
        int type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case 0:
                loadCarbag("加油卡", new AddOilCardFrgm());
                break;
            case 1:
                loadCarbag("洗车卡", new CarWashCardFrgm());
                break;
            case 2:
                loadCarbag("优惠券", new CouponFrgm());
                break;
            case 3:
                loadCarbag("违章卡", new IllageBagFrgm());
                break;
        }
    }

    private void loadCarbag(String title, Fragment fragment) {
        mTxtTiltle.setText(title);
        mFragmentTransaction.replace(R.id.fl_carbag, fragment);
        mFragmentTransaction.commit();
    }

    @Override
    public void setToolbarIconDo() {
        EventBus.getDefault().post(new PointerChangeEvent("success"));
        finish();
    }
    @Override
    public String setToolbarTitle() {
        return "卡券包";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new PointerChangeEvent("success"));
    }
}
