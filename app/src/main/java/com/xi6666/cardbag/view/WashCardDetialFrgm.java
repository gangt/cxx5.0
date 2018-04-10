package com.xi6666.cardbag.view;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.BaseFrgm;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.cardbag.adapter.WashCarDetialAdapter;
import com.xi6666.cardbag.constract.WashCardDetialConstract;
import com.xi6666.cardbag.di.component.DaggerWashCardDetialComponent;
import com.xi6666.cardbag.di.module.WashCardDetialModule;
import com.xi6666.cardbag.persenter.WashCardDetialPresenterImpl;
import com.xi6666.common.UserData;
import com.xi6666.databean.WashCardDetialBean;
import com.xi6666.network.ApiRest;
import com.xi6666.view.EmptyLayout;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/14.
 */

public class WashCardDetialFrgm extends BaseFrgm implements WashCardDetialConstract.View,
        RadioGroup.OnCheckedChangeListener, View.OnClickListener, XRecyclerView.LoadingListener {
    private static final String TAG = "WashCardDetialFrgm";
    @BindView(R.id.txt_washcarddetial_all)
    RadioButton mTxtWashcarddetialAll;
    @BindView(R.id.txt_washcarddetial_get)
    RadioButton mTxtWashcarddetialGet;
    @BindView(R.id.txt_washcarddetial_set)
    RadioButton mTxtWashcarddetialSet;
    @BindView(R.id.xrel_washcarddetial)
    XRecyclerView mXrelWashcarddetial;
    @BindView(R.id.el_washcarddetial)
    EmptyLayout mElWashcarddetial;

    @Inject
    ApiRest mApiRest;
    @Inject
    WashCardDetialPresenterImpl mWashCardDetialPresenter;
    @BindView(R.id.rg_washcarddetial)
    RadioGroup mRgWashcarddetial;

    private List<WashCardDetialBean.DataBean> mDataBeen = new ArrayList<>();
    private WashCarDetialAdapter mWashCarDetialAdapter;


    private int page = 1;
    private String option = "1";

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_washcardetial;
    }

    @Override
    protected void init() {
        mElWashcarddetial.setErrorButtonClickListener(this);
        mElWashcarddetial.setEmptyButtonClickListener(this);

        mXrelWashcarddetial.setLayoutManager(new LinearLayoutManager(mActivity));
        mWashCarDetialAdapter = new WashCarDetialAdapter();
        mWashCarDetialAdapter.setDataBeen(mDataBeen);
        mXrelWashcarddetial.setAdapter(mWashCarDetialAdapter);
        mXrelWashcarddetial.setLoadingListener(this);


        DaggerWashCardDetialComponent.builder().apiModule(new ApiModule((BaseApplication)
                mActivity.getApplication())).washCardDetialModule(new WashCardDetialModule()).build().Inject(this);
        mWashCardDetialPresenter.attachView(this);
        mWashCardDetialPresenter.setApiRest(mApiRest);

        mRgWashcarddetial.setOnCheckedChangeListener(this);
        mTxtWashcarddetialAll.setChecked(true);
    }

    @Override
    public void showLoading() {
        mElWashcarddetial.showLoading();
    }

    @Override
    public void hideStateLyout() {
        mElWashcarddetial.hide();
    }

    @Override
    public void showError() {
        mElWashcarddetial.showError();
    }

    @Override
    public void showEmpty() {
        mElWashcarddetial.showEmpty();
    }

    @Override
    public void hasNodata() {
       // mXrelWashcarddetial.setNoMore(true);
    }

    @Override
    public void loadMoreFinish() {
        mXrelWashcarddetial.loadMoreComplete();
    }

    @Override
    public void refreshFinish() {
        mXrelWashcarddetial.refreshComplete();
    }

    @Override
    public void setData(List<WashCardDetialBean.DataBean> dataBeen) {
        Log.d(TAG, "dataBeen.size--->" + dataBeen.size());
        if (page == 1) {
            mDataBeen.clear();
        }
        mDataBeen.addAll(dataBeen);
        mWashCarDetialAdapter.setDataBeen(mDataBeen);

        if (page == 1 && dataBeen.size() == 0) {
            showEmpty();
        }
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(mActivity, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.txt_washcarddetial_all:
                option = "1";
                initLoadMoreData();
                break;
            case R.id.txt_washcarddetial_get:
                option = "2";
                initLoadMoreData();
                break;
            case R.id.txt_washcarddetial_set:
                option = "3";
                initLoadMoreData();
                break;
        }
    }

    private void initLoadMoreData() {
        mXrelWashcarddetial.setNoMore(false);
        page = 1;
        mDataBeen.clear();
        String userId = UserData.getUserId();
        String userToken = UserData.getUserToken();
        mWashCardDetialPresenter.loadMoreData(option + "", page + "", userId, userToken);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //错误界面
            case R.id.buttonError:
                page = 1;
                String userId = UserData.getUserId();
                String userToken = UserData.getUserToken();
                mWashCardDetialPresenter.loadMoreData(option + "", page + "", userId, userToken);
                break;
            //空界面
            case R.id.buttonEmpty:
                page = 1;
                String userIdTwo = UserData.getUserId();
                String userTokenTwo = UserData.getUserToken();
                mWashCardDetialPresenter.loadMoreData(option + "", page + "", userIdTwo, userTokenTwo);
                break;
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        mXrelWashcarddetial.setNoMore(false);
        String userId = UserData.getUserId();
        String userToken = UserData.getUserToken();
        mWashCardDetialPresenter.refreshData(option + "", page + "", userId, userToken);

    }

    @Override
    public void onLoadMore() {
        page++;
        String userId = UserData.getUserId();
        String userToken = UserData.getUserToken();
        mWashCardDetialPresenter.loadMoreData(option + "", page + "", userId, userToken);
    }
}
