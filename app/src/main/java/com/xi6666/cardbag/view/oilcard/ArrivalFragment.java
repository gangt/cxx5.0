package com.xi6666.cardbag.view.oilcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.baset.BaseTFrgm;
import com.xi6666.cardbag.view.mvp.OilRechargeDetailsContract;
import com.xi6666.cardbag.view.mvp.OilRechargeDetailsModel;
import com.xi6666.cardbag.view.mvp.OilRechargeDetailsPresenter;
import com.xi6666.cardbag.view.mvp.bean.OilCardDeleteBean;
import com.xi6666.cardbag.view.oilcard.adapter.OilRechargeDetialAdapter;
import com.xi6666.common.UserData;
import com.xi6666.databean.RechargeDetialBean;
import com.xi6666.databean.RechargeListBean;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.R.attr.x;

/**
 * Created by Mr_yang on 2017/3/15.
 * 充值记录
 */

public class ArrivalFragment extends BaseTFrgm<OilRechargeDetailsPresenter, OilRechargeDetailsModel> implements OilRechargeDetailsContract.View {
    private static final String TAG = "ArrivalFragment";
    @BindView(R.id.elv_chargeDetial)
    ExpandableListView mElvChargeDetial;
    private OilRechargeDetialAdapter mOilRechargeDetialAdapter;
    private List<RechargeDetialBean.DataBeanX.ShouldDataBean> mShouldDataBeen = new ArrayList<>();
    private List<RechargeDetialBean.DataBeanX.BackListBean> mDataBeen = new ArrayList<>();
    private String mCarId;

    @Override
    protected void initData() {
        if (getArguments() != null) {
            mCarId = getArguments().getString(TAG);
        }
        mElvChargeDetial.setGroupIndicator(null);
        mOilRechargeDetialAdapter = new OilRechargeDetialAdapter(mActivity);
        mOilRechargeDetialAdapter.setDataBeen(mDataBeen, mShouldDataBeen);
        mElvChargeDetial.setAdapter(mOilRechargeDetialAdapter);
        mPresent.getRechargeDetial(UserData.getUserId(), mCarId, UserData.getUserToken());
    }

    public static Fragment newInstance(String age) {
        ArrivalFragment arrivalFragment = new ArrivalFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAG, age);
        arrivalFragment.setArguments(bundle);
        return arrivalFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_arrival;


    }

    @Override
    public void setData(RechargeDetialBean rechargeDetialBean) {
        mOilRechargeDetialAdapter.setDataBeen(rechargeDetialBean.getData().getBack_list(), rechargeDetialBean.getData().getShould_data());
        for (int x = 1; x < rechargeDetialBean.getData().getBack_list().size(); x++) {
            //   Toast.makeText(mActivity, rechargeDetialBean.getData().getBack_list().get(x - 1).getIs_show() + "", Toast.LENGTH_SHORT).show();
            if (rechargeDetialBean.getData().getBack_list().get(x - 1).getIs_show() == 1) {
                mElvChargeDetial.expandGroup(x);
            }
        }
        if (rechargeDetialBean.getData().getBack_list().size() == 1) {
            if (rechargeDetialBean.getData().getBack_list().get(0).getIs_show() == 1) {
                mElvChargeDetial.expandGroup(1);
            }
        }
    }

    @Override
    public void setMoney(String arrival, String total) {

    }

    @Override
    public void setRechargeList(List<RechargeListBean.DataBeanX.DataBean> rechargeList) {

    }

    @Override
    public void resultDefaultOiLCardStatus(OilCardDeleteBean bean) {

    }
}
