package com.xi6666.happybeans.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ToolBarBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.common.UserData;
import com.xi6666.databean.HappyBeansBean;
import com.xi6666.happybeans.adapter.HappyBeansAdapter;
import com.xi6666.happybeans.contract.HappyBeansConstract;
import com.xi6666.happybeans.di.DaggerHappyBeanComponent;
import com.xi6666.happybeans.di.HappyBeanModule;
import com.xi6666.happybeans.presenter.HappyBeansPersenterImpl;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.network.ApiRest;
import com.xi6666.view.EmptyLayout;
import com.xi6666.view.RandomTextView;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author peng
 * @data 创建时间:2016/11/3
 * @desc 喜豆界面
 */
public class HappyBeansAct extends ToolBarBaseAct implements
        HappyBeansConstract.View, RadioGroup.OnCheckedChangeListener,
        XRecyclerView.LoadingListener, View.OnClickListener {

    private static final String TAG = "HappyBeansAct";
    @BindView(R.id.txt_happybeans_num)
    RandomTextView mTxtHappybeansNum;
    @BindView(R.id.txt_happybeans_persion)
    RandomTextView mTxtHappybeansPersion;
    @BindView(R.id.txt_happybeans_all)
    RadioButton mTxtHappybeansAll;
    @BindView(R.id.txt_happybeans_get)
    RadioButton mTxtHappybeansGet;
    @BindView(R.id.txt_happybeans_set)
    RadioButton mTxtHappybeansSet;
    @BindView(R.id.rg_happybeans)
    RadioGroup mRgHappybeans;
    @BindView(R.id.xrv_happybeans)
    XRecyclerView mXrvHappybeans;
    @BindView(R.id.el_happybeans)
    EmptyLayout mElHappybeans;
    @BindView(R.id.activity_happy_beans)
    LinearLayout mActivityHappyBeans;

    @Inject
    HappyBeansPersenterImpl mHappyBeansPersenter;

    @Inject
    ApiRest mApiRest;
    private List<HappyBeansBean.DataBean.XidouListBean> mDataBeen = new ArrayList<>();
    private HappyBeansAdapter mHappyBeansAdapter;

    private String option = "1";
    private int page = 1;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_beans);
        ButterKnife.bind(this);
        mRgHappybeans.setOnCheckedChangeListener(this);

        //注入
        DaggerHappyBeanComponent.builder().apiModule(new ApiModule((BaseApplication) getApplication()))
                .happyBeanModule(new HappyBeanModule()).build().Inject(this);
        mHappyBeansPersenter.attachView(this);
        mHappyBeansPersenter.setApiRest(mApiRest);
        //recycler设置
        mXrvHappybeans.setLayoutManager(new LinearLayoutManager(this));
        mHappyBeansAdapter = new HappyBeansAdapter();
        mHappyBeansAdapter.setDataBeen(mDataBeen);
        mXrvHappybeans.setAdapter(mHappyBeansAdapter);
        mXrvHappybeans.setLoadingListener(this);
        mXrvHappybeans.setPullRefreshEnabled(false);

        //界面状态展示设置
        mElHappybeans.setErrorButtonClickListener(this);

        setRightTxt("赚喜豆");
        mTxtRight.setOnClickListener(this);


        mTxtHappybeansAll.setChecked(true);
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "喜豆";
    }

    @Override
    public void setHappyBeanNum(String num) {
        if (flag) {
            mTxtHappybeansNum.setText(num);
            mTxtHappybeansNum.setPianyilian(RandomTextView.ALL);
            mTxtHappybeansNum.start();
        }

    }

    @Override
    public void setPersionNum(String num) {
        if (flag) {
            mTxtHappybeansPersion.setText(num);
            mTxtHappybeansPersion.setPianyilian(RandomTextView.ALL);
            mTxtHappybeansPersion.start();
        }
        flag = false;
    }

    @Override
    public void showLoading() {
        mElHappybeans.showLoading();
    }

    @Override
    public void showError() {
        mElHappybeans.showError();
    }

    @Override
    public void hideEmptyLayout() {
        mElHappybeans.hide();
    }

    @Override
    public void loadData(List<HappyBeansBean.DataBean.XidouListBean> dataBeen) {
        mDataBeen.addAll(dataBeen);
        mHappyBeansAdapter.setDataBeen(mDataBeen);
    }

    @Override
    public void refreshFinish() {
        mXrvHappybeans.refreshComplete();
    }

    @Override
    public void hasNodata() {
        //  mXrvHappybeans.setNoMore(true);
    }

    @Override
    public void showEmpty() {
        mElHappybeans.showEmpty();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int checkedRadioButtonId = group.getCheckedRadioButtonId();
        switch (checkedRadioButtonId) {
            case R.id.txt_happybeans_all:
                option = "1";
                initRefreshData();
                break;
            case R.id.txt_happybeans_get:
                option = "2";
                initRefreshData();

                break;
            case R.id.txt_happybeans_set:
                option = "3";
                initRefreshData();
                break;
        }
    }

    private void initRefreshData() {
        mXrvHappybeans.setNoMore(false);
        mDataBeen.clear();
        page = 1;
        mHappyBeansPersenter.loadData(option, page + "");
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        page++;
        mHappyBeansPersenter.loadData(option, page + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //错误界面按钮
            case R.id.buttonError:
                page = 1;
                mHappyBeansPersenter.loadData(option, page + "");
                break;
            //右侧赚喜豆按钮的点击事件
            case R.id.txt_basetoolbar_right:
                HtmlAct.unsealActivity(this, ApiRest.baseUrl + ApiRest.MAKEHAPPYBEAN + "&user_id=" + UserData.getUserId()
                        + "&user_token=" + UserData.getUserToken());
                break;
        }
    }
}
