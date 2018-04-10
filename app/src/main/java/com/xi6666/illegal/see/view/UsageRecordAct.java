package com.xi6666.illegal.see.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.eventbus.UsageRecordEvent;
import com.xi6666.illegal.see.adapter.UsageRecordAdapter;
import com.xi6666.illegal.see.bean.UsageRecordBean;
import com.xi6666.illegal.see.mvp.UsageRecordContract;
import com.xi6666.illegal.see.mvp.UsageRecordModel;
import com.xi6666.illegal.see.mvp.UsageRecordPresenter;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 创建者 sunsun
 * 时间 2017/2/7 下午3:25.
 * 个人公众号 ardays
 */

public class UsageRecordAct extends BaseToolbarView<UsageRecordPresenter, UsageRecordModel>
        implements UsageRecordContract.View {


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshList(UsageRecordEvent event) {
        showLoading();
        mPage = 1;
        mPersenter.requestIllegalList(mPage, mCardId);
    }

    @Override
    public String title() {
        return "使用记录";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_usage_record;
    }

    XRecyclerView mUsageRecordXrv;  //"使用记录"列表
    UsageRecordAdapter mUsageRecordAdapter; //"使用记录"适配器
    View mUsageRecordNilV;         //没有"使用记录"

    String mCardId; //卡ID
    int mPage = 1;  //页数

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setUp(View view) {
        EventBus.getDefault().register(this);
        initView(view);
        initValue();
        initHttp();
    }


    /**
     * 初始化控件
     */
    private void initView(View view) {
        mUsageRecordXrv = (XRecyclerView) view.findViewById(R.id.usage_record_xrv);
        mUsageRecordNilV = view.findViewById(R.id.usage_record_nil_tv);

        mUsageRecordXrv.setLayoutManager(new LinearLayoutManager(this));
        mUsageRecordAdapter = new UsageRecordAdapter(this);
        mUsageRecordXrv.setAdapter(mUsageRecordAdapter);

        mUsageRecordXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                mPersenter.requestIllegalList(mPage, mCardId);
            }

            @Override
            public void onLoadMore() {
                mPage++;
                mPersenter.requestIllegalList(mPage, mCardId);
            }
        });

        mUsageRecordAdapter.setOnUsageRecordListener((position, data) -> {
            UsageDetailsAct.openActivity(getActivity(), data.log_id);
        });
    }

    private void initValue() {
        mCardId = getIntent().getStringExtra("card_id");
    }

    /**
     * 初始化网络
     */
    private void initHttp() {
        showLoading();
        mPersenter.requestIllegalList(mPage, mCardId);
    }


    @Override
    public void returnIllegalList(UsageRecordBean bean) {
        hiddenErrorView();
        hiddenLoading();
        mUsageRecordXrv.refreshComplete();
        if (bean.success) {
            if (mPage == 1) {
                //如果数据不为空 并且条数大于0就
                if (bean.data != null && bean.data.size() != 0) {
                    mUsageRecordXrv.setVisibility(View.VISIBLE);
                    mUsageRecordNilV.setVisibility(View.GONE);
                    mUsageRecordAdapter.updateData(bean.data);
                } else {
                    mUsageRecordXrv.setVisibility(View.GONE);
                    mUsageRecordNilV.setVisibility(View.VISIBLE);
                }
            } else {
                mUsageRecordAdapter.addData(bean.data);
            }
        } else {
            make(bean.info);
        }
    }

    @Override
    public void illegalListError() {
        make("网络请求超时,请重试");
        hiddenLoading();
        mUsageRecordXrv.refreshComplete();
        if (mPage == 1) {
            showErrorView();
            errorClick(v -> {
                mPersenter.requestIllegalList(mPage, mCardId);
            });
        } else {
            mPage--;
        }
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity,String card_id) {
        Intent intent = new Intent(activity, UsageRecordAct.class);
        intent.putExtra("card_id",card_id);
        activity.startActivity(intent);
    }
}
