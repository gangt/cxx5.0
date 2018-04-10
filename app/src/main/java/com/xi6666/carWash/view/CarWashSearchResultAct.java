package com.xi6666.carWash.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.xi6666.R;
import com.xi6666.carWash.adapter.CarWashSearchResultAdapter;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.carWash.view.custom.BaseSearchToolbarView;
import com.xi6666.carWash.view.custom.SortTabView;
import com.xi6666.carWash.view.mvp.CarWashSearchResultContract;
import com.xi6666.carWash.view.mvp.CarWashSearchResultModel;
import com.xi6666.carWash.view.mvp.CarWashSearchResultPresenter;
import com.xi6666.store.mvp.bean.StoreServiceBean;
import com.xi6666.view.superrecyclerview.XRecyclerView;

/**
 * 创建者 sunsun
 * 时间 16/11/2 上午10:32.
 * 个人公众号 ardays
 */

public class CarWashSearchResultAct
        extends BaseSearchToolbarView<CarWashSearchResultPresenter, CarWashSearchResultModel>
        implements CarWashSearchResultContract.View, SortTabView.OnSortTabListener {

    private static final String TAG = "CarWashSearchResultAct";

    //当前页数
    private int PAGE = 1;

    XRecyclerView mCarWashRv;
    SortTabView mDefaultStv;  //默认排序
    SortTabView mEvaluateStv;   //评分排序
    View mSearchNotTv;  //搜索为空的文本
    private SortTabView mSelectedSortTabView;   //选中的


    private String mKeyWord;    //关坚持
    private int mSort = 5;      //排序方式
    CarWashSearchResultAdapter mCarWashSearchResultAdapter;


    @Override
    public String searchTitle() {
        return "";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_carwash_search_result;
    }

    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initTab();
    }

    @Override
    public void clickSearchsBox() {
        finish();
    }

    /**
     * 初始化View
     */
    private void initView(View view) {
        mCarWashRv = (XRecyclerView) view.findViewById(R.id.carWash_search_xrv);
        mDefaultStv = (SortTabView) view.findViewById(R.id.carWash_search_default_stv);
        mEvaluateStv = (SortTabView) view.findViewById(R.id.carWash_search_evaluate_stv);
        mSearchNotTv = view.findViewById(R.id.carWash_search_null_tv);

        //列表
        mCarWashRv.setLayoutManager(new LinearLayoutManager(this));
        mCarWashSearchResultAdapter = new CarWashSearchResultAdapter(this);
        mCarWashRv.setAdapter(mCarWashSearchResultAdapter);

        mDefaultStv.setOnSortTabListener(this);
        mEvaluateStv.setOnSortTabListener(this);
        this.mSelectedSortTabView = mDefaultStv;

        //监听下拉上拉刷新
        mCarWashRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE = 1;
                mPersenter.requestSearchStoreList(mKeyWord, PAGE, mSort);
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                mPersenter.requestSearchStoreList(mKeyWord, PAGE, mSort);
            }
        });

        //设置不能搜索
        setSearchClicked(false);
    }


    /**
     * 初始化传值
     */
    private void initValue() {
        //获取上个页面的搜索关键词
        mKeyWord = getIntent().getStringExtra(KEY_WORD);
        searchKeyWord(mKeyWord);

        mPersenter.requestSearchStoreList(mKeyWord, PAGE, 1);
    }

    /**
     * tab切换
     */
    private void initTab() {
    }


//                              @网络数据

    @Override
    public void returnStoreList(StoreServiceBean bean) {
        mCarWashRv.refreshComplete();
        hiddenLoading();

        if (bean.success) {
            if (PAGE == 1) {
                if (bean.data == null || bean.data.size() == 0) {
                    mCarWashRv.setVisibility(View.GONE);
                    mSearchNotTv.setVisibility(View.VISIBLE);
                } else {
                    mCarWashRv.setVisibility(View.VISIBLE);
                    mSearchNotTv.setVisibility(View.GONE);
                    mCarWashSearchResultAdapter.update(bean.data);
                }
            } else {
                mCarWashSearchResultAdapter.addAll(bean.data);
            }
        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnError() {

    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, String keyWord) {
        Intent intent = new Intent(activity, CarWashSearchResultAct.class);
        intent.putExtra(KEY_WORD, keyWord);
        activity.startActivity(intent);
    }

    //搜索关键词的key
    private static final String KEY_WORD = TAG + ".keyword";

    @Override
    public void onSortTabSwitch(View view, boolean bol) {
        switch (view.getId()) {
            case R.id.carWash_search_default_stv:
                mSort = 5;
                break;
            case R.id.carWash_search_evaluate_stv:
//                if (bol) {
                //评分从高到低
//                    make("评分从高到低");
                mSort = 3;
//                } else {
//                    //评分从低到高
//                    mSort = 4;
//                }
                break;
            default:
                break;
        }
        PAGE = 1;
        showLoading();
        mPersenter.requestSearchStoreList(mKeyWord, PAGE, mSort);
    }

    @Override
    public void onSelectedSortTab(View view, boolean bol) {
        //判断是否此次点击Tab是不是选中的Tab，如果是就让当前的Tab取消选中.在末尾赋值当前的Tab
        if (mSelectedSortTabView != null && mSelectedSortTabView.getId() != view.getId()) {
            mSelectedSortTabView.unSelectTab();
        }
        mSelectedSortTabView = (SortTabView) view;
        onSortTabSwitch(view, bol);
    }
}
