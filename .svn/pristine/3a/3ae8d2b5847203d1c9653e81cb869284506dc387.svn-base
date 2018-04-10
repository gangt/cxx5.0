package com.xi6666.cardbag.view.oilcard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.app.baset.BaseTFrgm;
import com.xi6666.cardbag.view.mvp.OilRechargeDetailsContract;
import com.xi6666.cardbag.view.mvp.OilRechargeDetailsModel;
import com.xi6666.cardbag.view.mvp.OilRechargeDetailsPresenter;
import com.xi6666.cardbag.view.mvp.bean.OilCardDeleteBean;
import com.xi6666.cardbag.view.oilcard.adapter.RechargeRecordAdapter;
import com.xi6666.common.UserData;
import com.xi6666.databean.RechargeDetialBean;
import com.xi6666.databean.RechargeListBean;
import com.xi6666.view.MesureListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Mr_yang on 2017/3/15.
 * 到账记录
 */

public class OilRecordFragment extends BaseTFrgm<OilRechargeDetailsPresenter, OilRechargeDetailsModel> implements OilRechargeDetailsContract.View {
    private static final String TAG = "OilRecordFragment";
    @BindView(R.id.elv_chargeDetial)
    ExpandableListView mElvChargeDetial;
    private List<RechargeListBean.DataBeanX.DataBean> mRechargeList = new ArrayList<>();
    private RechargeRecordAdapter mRechargeRecordAdapter;
    private String mCardId;
    private int mPage = 1;
    private List<RechargeDetialBean.DataBeanX.ShouldDataBean> mShould_data;

    @Override
    protected void initData() {
        if (getArguments() != null) {
            mCardId = getArguments().getString(TAG);
        }


        mElvChargeDetial.setGroupIndicator(null);
        mRechargeRecordAdapter = new RechargeRecordAdapter(mActivity);
        mRechargeRecordAdapter.setDataBeen(mRechargeList);
        mElvChargeDetial.setAdapter(mRechargeRecordAdapter);
        mElvChargeDetial.expandGroup(0);
        mPresent.getRechargeList(UserData.getUserId(), mCardId, 1, UserData.getUserToken());
        mPresent.getRechargeDetial(UserData.getUserId(), mCardId, UserData.getUserToken());
        // 监听listview滚到最底部
        mElvChargeDetial.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                            mPage++;
                            mPresent.getRechargeList(UserData.getUserId(), mCardId + "", mPage, UserData.getUserToken());
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });


    }

    public static Fragment newIntance(String arg) {
        OilRecordFragment oilRecordFragment = new OilRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAG, arg);
        oilRecordFragment.setArguments(bundle);
        return oilRecordFragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_arrival;
    }


    @Override
    public void setData(RechargeDetialBean rechargeDetialBean) {
        mShould_data = rechargeDetialBean.getData().getShould_data();
        MesureListView mesureListView = new MesureListView(mActivity);
        TabAdapter tabAdapter = new TabAdapter();
        tabAdapter.setShouldDataBeen(mShould_data);
        mesureListView.setAdapter(tabAdapter);
        mElvChargeDetial.addHeaderView(mesureListView);
    }

    @Override
    public void setMoney(String arrival, String total) {

    }

    @Override
    public void setRechargeList(List<RechargeListBean.DataBeanX.DataBean> rechargeList) {
        if (rechargeList.size() > 0) {
            mRechargeList.addAll(rechargeList);
            mRechargeRecordAdapter.setDataBeen(mRechargeList);
        }
    }

    @Override
    public void resultDefaultOiLCardStatus(OilCardDeleteBean bean) {

    }

    public class TabAdapter extends BaseAdapter {
        private List<RechargeDetialBean.DataBeanX.ShouldDataBean> mShouldDataBeen;

        public void setShouldDataBeen(List<RechargeDetialBean.DataBeanX.ShouldDataBean> shouldDataBeen) {
            mShouldDataBeen = shouldDataBeen;
        }

        @Override
        public int getCount() {
            return mShouldDataBeen.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rootView = LayoutInflater.from(mActivity).inflate(R.layout.item_recharge_package, null);
            TextView tvOne = (TextView) rootView.findViewById(R.id.txt_package_one);
            TextView tvTwo = (TextView) rootView.findViewById(R.id.txt_package_two);
            TextView tvThr = (TextView) rootView.findViewById(R.id.txt_package_thr);
            tvOne.setText(mShouldDataBeen.get(position).getBack_money());
            tvTwo.setText(mShouldDataBeen.get(position).getBack_time());
            if (TextUtils.equals(mShouldDataBeen.get(position).getBack_status(), "已到账")) {
                tvThr.setTextColor(mActivity.getResources().getColor(R.color.themeColor));
            }
            if (TextUtils.equals(mShouldDataBeen.get(position).getBack_status(), "未到账")) {
                tvThr.setTextColor(mActivity.getResources().getColor(R.color.txthomeGoodsOld));
            }
            if (TextUtils.equals(mShouldDataBeen.get(position).getBack_status(), "到账中")) {
                tvThr.setTextColor(mActivity.getResources().getColor(R.color.orange));
            }
            tvThr.setText(mShouldDataBeen.get(position).getBack_status());
            return rootView;
        }
    }
}
