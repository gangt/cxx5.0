package com.xi6666.cardbag.view;


import android.support.v7.widget.LinearLayoutManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.BaseFrgm;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.carWash.view.CarWashAct;
import com.xi6666.cardbag.adapter.WashCardInfoAdapter;
import com.xi6666.cardbag.constract.WashCardInfoConstract;
import com.xi6666.cardbag.di.component.DaggerWashCardInfoComponent;
import com.xi6666.cardbag.di.module.WashCardInfoModule;
import com.xi6666.cardbag.persenter.WashCardInfoImpl;
import com.xi6666.common.UserData;
import com.xi6666.databean.WashCardInfoBean;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.DimenUtils;
import com.xi6666.view.EmptyLayout;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr_yang on 2016/11/14.
 */

public class WashCardInfoFrgm extends BaseFrgm implements WashCardInfoConstract.View, View.OnClickListener {
    @BindView(R.id.xrec_washcardinfo)
    XRecyclerView mXrecWashcardinfo;
    @BindView(R.id.el_washcardinfo)
    EmptyLayout mElWashcardinfo;
    @BindView(R.id.txt_washcardinfo_cardnum)
    TextView mTxtWashcardinfoCardnum;
    @BindView(R.id.txt_washcardinfo_balance)
    TextView mTxtWashcardinfoBalance;
    @BindView(R.id.txt_washcardinfo_data)
    TextView mWashcardinfoDate;
    @BindView(R.id.rl_washcardinfo)
    RelativeLayout mRelativeLayout;

    private List<WashCardInfoBean.DataBean> mData = new ArrayList<>();
    private WashCardInfoAdapter mWashCardInfoAdapter;

    @Inject
    ApiRest mApiRest;

    @Inject
    WashCardInfoImpl mWashCardInfo;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_washcarinfo;
    }

    @Override
    protected void init() {
        mElWashcardinfo.setEmptyButtonClickListener(this);
        mElWashcardinfo.setErrorButtonClickListener(this);

        mXrecWashcardinfo.setLayoutManager(new LinearLayoutManager(mActivity));
        mWashCardInfoAdapter = new WashCardInfoAdapter();
        mWashCardInfoAdapter.setData(mData);
        mXrecWashcardinfo.setAdapter(mWashCardInfoAdapter);

        DaggerWashCardInfoComponent.builder().apiModule(new ApiModule((BaseApplication) mActivity.
                getApplication())).washCardInfoModule(new WashCardInfoModule()).build().Inject(this);
        mWashCardInfo.attachView(this);
        mWashCardInfo.setApiRest(mApiRest);
        String userId = UserData.getUserId();
        String userToken = UserData.getUserToken();
        mWashCardInfo.loadData(userId, userToken);
    }

    @Override
    public void showLoading() {
        mElWashcardinfo.showLoading();
    }

    @Override
    public void hideStateLyout() {
        mElWashcardinfo.hide();
    }

    @Override
    public void showError() {
        mElWashcardinfo.showError();
    }

    @Override
    public void showEmpty() {
        mElWashcardinfo.showEmpty();
    }

    @Override
    public void hasNodata() {
        mXrecWashcardinfo.setNoMore(true);
    }

    @Override
    public void loadMoreFinish() {
        mXrecWashcardinfo.loadMoreComplete();
    }

    @Override
    public void refreshFinish() {
        mXrecWashcardinfo.refreshComplete();
    }

    @Override
    public void setCardInfo(String cradNum, String balance, String data) {
        mTxtWashcardinfoCardnum.setText("卡号: " + cradNum);
        mTxtWashcardinfoBalance.setText("¥ " + balance);
        mWashcardinfoDate.setText(data);
        Spannable span = new SpannableString(mTxtWashcardinfoBalance.getText());
        span.setSpan(new AbsoluteSizeSpan(DimenUtils.sp2px(mActivity, 12)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTxtWashcardinfoBalance.setText(span);
    }

    @Override
    public void changeBackground() {
        mRelativeLayout.setBackgroundResource(R.drawable.bg_washcard_info);
    }

    @Override
    public void showState(boolean flag) {
        if (flag) {
            mWashcardinfoDate.setVisibility(View.VISIBLE);
        } else {
            mWashcardinfoDate.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.btn_washcardinfo_experience})
    public void viewOnclik(View view) {
        switch (view.getId()) {
            case R.id.btn_washcardinfo_experience:
                CarWashAct.openActivity(mActivity, 1, true, "特惠洗车");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonError:

                break;
            case R.id.buttonEmpty:

                break;
        }
    }

}
