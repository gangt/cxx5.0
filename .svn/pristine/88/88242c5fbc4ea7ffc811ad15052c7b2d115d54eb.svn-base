package com.xi6666.message.view;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ToolBarBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.common.UserData;

import com.xi6666.databean.MessageBean;
import com.xi6666.message.adapter.MessageAdapter;
import com.xi6666.message.contract.MessageContract;
import com.xi6666.message.di.DaggerMessageComponent;
import com.xi6666.message.di.MessageModule;
import com.xi6666.message.presenter.MessagePresenterImpl;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.SpUtils;
import com.xi6666.view.EmptyLayout;
import com.xi6666.view.refresh.RefreshHeader;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * @author peng
 * @data 创建时间:2016/11/2
 * @desc 小喜展示界面
 */
public class MessageAct extends ToolBarBaseAct implements MessageContract.View,
        PtrHandler, XRecyclerView.LoadingListener, View.OnClickListener {

    @BindView(R.id.xrlv_message)
    XRecyclerView mXrlvMessage;
    @BindView(R.id.elt_mesage)
    EmptyLayout mEltMesage;
    @BindView(R.id.pfl_message)
    PtrFrameLayout mPflMessage;

    private int page = 1;
    private MessageAdapter mAdapter;
    private List<MessageBean.DataBean> mData;
    /*  private MessagePresenterImpl mMessagePresenter;*/
    @Inject
    MessagePresenterImpl mMessagePresenter;

    @Inject
    ApiRest mApiRest;

    private boolean isFristIn = false;
    private String mUserId;
    private String mUserToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mXrlvMessage.setLayoutManager(new LinearLayoutManager(this));

        mUserId = UserData.getUserId();
        mUserToken = UserData.getUserToken();

        mData = new ArrayList<>();
        mAdapter = new MessageAdapter();
        mAdapter.setDatas(mData);
        mXrlvMessage.setVisibility(View.GONE);
        mXrlvMessage.setAdapter(mAdapter);

      /*  mMessagePresenter = new MessagePresenterImpl();*/

        DaggerMessageComponent.builder().apiModule(new ApiModule((BaseApplication) getApplication())).messageModule(new MessageModule()).build().Inject(this);

        mMessagePresenter.attachView(this);
        mMessagePresenter.setApiRest(mApiRest);
        mEltMesage.setErrorButtonClickListener(this);

        RefreshHeader refreshHeader = new RefreshHeader(this);
        mPflMessage.setHeaderView(refreshHeader);
        mPflMessage.addPtrUIHandler(refreshHeader);
        mPflMessage.setPtrHandler(this);
        mXrlvMessage.setLoadingListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !isFristIn) {
            mPflMessage.autoRefresh(true);
        }
        isFristIn = true;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        mXrlvMessage.setNoMore(false);
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                mMessagePresenter.refrashData(page + "", mUserId, mUserToken);
            }
        }, 1000);
    }


    @Override
    public void hideRefresh() {
        mPflMessage.refreshComplete();
    }


    @Override
    public void hideLoadMore() {
        mXrlvMessage.loadMoreComplete();
    }

    @Override
    public void error() {
        mEltMesage.showError();
    }


    @Override
    public void addImages(List<MessageBean.DataBean> list) {
        if (View.GONE == mXrlvMessage.getVisibility()) {
            mXrlvMessage.setVisibility(View.VISIBLE);
        }
        mEltMesage.hide();
        if (page == 1) {
            mData.clear();
        }
        page += 1;
        mData.addAll(list);
        mAdapter.setDatas(mData);
    }

    @Override
    public void hasNodata() {
        mXrlvMessage.setNoMore(true);
    }

    @Override
    public void showEmptyView() {
        mEltMesage.showEmpty();
    }


    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "我的消息";
    }

    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        mMessagePresenter.loadMoreData(page + "", mUserId, mUserToken);
    }

    @Override
    public void onClick(View v) {
        mEltMesage.hide();
        page = 1;
        mMessagePresenter.refrashData(page + "", mUserId, mUserToken);
    }
}
