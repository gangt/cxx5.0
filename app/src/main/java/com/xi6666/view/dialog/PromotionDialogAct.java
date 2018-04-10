package com.xi6666.view.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.SuperAct;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.eventbus.CouponSuccessEvent;
import com.xi6666.network.ApiRest;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;



public class PromotionDialogAct extends SuperAct {
    private List<AddOilPopuBean.DataBean.CouponListBean> mCouponListBeen;
    @BindView(R.id.fl_coupon_popu)
    FrameLayout mFlCouponPopu;
    private FragmentManager mSupportFragmentManager;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void couPonSuccess(CouponSuccessEvent couponSuccessEvent) {
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        ReceiveSuccessFrg receiveSuccessFrg = ReceiveSuccessFrg.newInstance(couponSuccessEvent.getInfo());
        fragmentTransaction.replace(R.id.fl_coupon_popu, receiveSuccessFrg);
        fragmentTransaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_coupon_popu);
        ButterKnife.bind(this);

        mCouponListBeen = (List<AddOilPopuBean.DataBean.CouponListBean>) getIntent().getSerializableExtra("list");
        mSupportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        PromotionDialogFrg promotionDialogFrg = PromotionDialogFrg.newInstance(mCouponListBeen);
        fragmentTransaction.replace(R.id.fl_coupon_popu, promotionDialogFrg);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
