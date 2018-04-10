package com.xi6666.cardbag.view;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.xi6666.R;
import com.xi6666.app.baset.BaseTFrgm;
import com.xi6666.cardbag.adapter.IllageAdapter;
import com.xi6666.cardbag.constract.IllegaBagContract;
import com.xi6666.cardbag.model.IllegaBagModel;
import com.xi6666.cardbag.persenter.IllegaBagPresenter;
import com.xi6666.common.UserData;
import com.xi6666.databean.IllegaBagListBean;
import com.xi6666.eventbus.IllegaCardChangeEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.illegal.Activity.IllegalFindActivity;
import com.xi6666.illegal.see.view.RedeemCodeAct;
import com.xi6666.network.ApiRest;
import com.xi6666.view.EmptyLayout;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mr_yag on 2017/2/7.
 * 违章卡
 */

public class IllageBagFrgm extends BaseTFrgm<IllegaBagPresenter,
        IllegaBagModel> implements IllegaBagContract.View,
        XRecyclerView.LoadingListener {
    @BindView(R.id.xrec_illagecardbag)
    XRecyclerView mXrecIllagecardbag;
    @BindView(R.id.empt_illagecardbag)
    EmptyLayout mEmptIllagecardbag;
    @BindView(R.id.btn_illagecardbag_redeemcode)
    Button mBtnIllagecardbagRedeemcode;
    @BindView(R.id.btn_illagecardbag_buy)
    Button mBtnIllagecardbagBuy;
    private List<IllegaBagListBean.DataBean> mData = new ArrayList<>();
    private IllageAdapter mIllageAdapter;

    private int mPage = 1;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void cardChange(IllegaCardChangeEvent IllegaCardChangeEvent) {
        mPage = 1;
        mPresent.loadData(UserData.getUserId(), UserData.getUserToken(), "" + mPage);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_cardbag_illage;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        //错误按钮点击事件
        mEmptIllagecardbag.setErrorButtonClickListener(v -> {
            mPresent.loadData(UserData.getUserId(), UserData.getUserToken(), "" + mPage);
        });
        //列表
        mXrecIllagecardbag.setLoadingListener(this);
        mXrecIllagecardbag.setLayoutManager(new LinearLayoutManager(mActivity));
        mIllageAdapter = new IllageAdapter();
        mIllageAdapter.setDataBeen(mData);
        mXrecIllagecardbag.setAdapter(mIllageAdapter);
        mPresent.loadData(UserData.getUserId(), UserData.getUserToken(), "" + mPage);
    }

    @OnClick({R.id.btn_illagecardbag_redeemcode, R.id.btn_illagecardbag_buy})
    public void viewOnclick(View view) {
        switch (view.getId()) {
            //兑换码
            case R.id.btn_illagecardbag_redeemcode:
                RedeemCodeAct.openActivity(mActivity);
                break;
            //立即买
            case R.id.btn_illagecardbag_buy:
                HtmlAct.unsealActivity(mActivity, ApiRest.ILLEGA + "?get_device_type=android" + "&user_id=" + UserData.getUserId()
                        + "&user_token=" + UserData.getUserToken());
                break;
        }
    }

    @Override
    public void showLoading() {
        mEmptIllagecardbag.showLoading();
    }

    @Override
    public void hideEmptyLayout() {
        mEmptIllagecardbag.hide();
    }

    @Override
    public void showError() {
        mEmptIllagecardbag.showError();
    }

    @Override
    public void setListData(List<IllegaBagListBean.DataBean> data) {
        if (mPage == 1) {
            mData.clear();
        }
        mData.addAll(data);
        mIllageAdapter.setDataBeen(mData);
    }

    @Override
    public void refreshFinish() {
        mXrecIllagecardbag.refreshComplete();
    }

    @Override
    public void loadMoreFinish() {
        mXrecIllagecardbag.loadMoreComplete();
    }

    @Override
    public void hasNoData(boolean has) {
        mXrecIllagecardbag.setNoMore(has);
    }

    @Override
    public void showEmpty() {
        mEmptIllagecardbag.showEmpty();
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        mPresent.loadData(UserData.getUserId(), UserData.getUserToken(), "" + mPage);
    }

    @Override
    public void onLoadMore() {
        mPage++;
        mPresent.loadData(UserData.getUserId(), UserData.getUserToken(), "" + mPage);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}