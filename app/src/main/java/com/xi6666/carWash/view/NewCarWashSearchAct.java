package com.xi6666.carWash.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.adapter.CarWashSearchAdapter;
import com.xi6666.carWash.adapter.CarWashSearchResultAdapter;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.view.CxxErrorView;
import com.xi6666.carWash.view.custom.BaseSearchToolbarView;
import com.xi6666.carWash.view.custom.SortTabView;
import com.xi6666.carWash.view.mvp.CarWashSearchModel;
import com.xi6666.carWash.view.mvp.CarWashSearchPresenter;
import com.xi6666.carWash.view.mvp.CarWashSearchResultContract;
import com.xi6666.carWash.view.mvp.CarWashSearchResultModel;
import com.xi6666.carWash.view.mvp.CarWashSearchResultPresenter;
import com.xi6666.carWash.view.mvp.NewCarWashSearchContract;
import com.xi6666.carWash.view.mvp.NewCarWashSearchModel;
import com.xi6666.carWash.view.mvp.NewCarWashSearchPresenter;
import com.xi6666.classification.view.custom.BaseListView;
import com.xi6666.store.mvp.bean.StoreServiceBean;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.List;

import retrofit2.http.POST;

/**
 * 创建者 sunsun
 * 时间 2016/12/10 下午5:28.
 * 个人公众号 ardays
 */

public class NewCarWashSearchAct extends BaseSearchToolbarView<CarWashSearchResultPresenter, CarWashSearchResultModel>
        implements CarWashSearchResultContract.View, SortTabView.OnSortTabListener {

    @Override
    public String searchTitle() {
        return "";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_new_carwash_search;
    }


    View mHistoryLl;   //有历史记录的布局
    View mHistoryNullLl;//历史记录为空的布局
    BaseListView mHistoryItemLv;//历史记录的item
    TextView mHistoryClearBtn; //清除历史记录
    View mHistoryView;  //历史记录搜索
    View mDataResultView;  //搜索记录
    CxxErrorView mErrorView;    //错误页面

    CarWashSearchAdapter mHistoryAdapter;   //历史记录适配器


    @Override
    public void setUp(View view) {
        //设置左边的按钮
        setLeftIcon(R.mipmap.ic_query_gray);
        //写入
        setSearchHint("请输入商家名称");
        //设置搜索能点击
        setSearchClicked(true);
        initView(view);
        initTab();
        initHistoryView();
        initClick();
    }


    /**
     * 初始化View
     */
    private void initView(View view) {
        mHistoryLl = view.findViewById(R.id.carWash_history_ll);
        mHistoryNullLl = view.findViewById(R.id.carWash_history_nil_ll);
        mHistoryItemLv = (BaseListView) view.findViewById(R.id.carWash_history_lv);
        mHistoryClearBtn = (TextView) view.findViewById(R.id.carWash_clear_history_btn);
        mCarWashRv = (XRecyclerView) view.findViewById(R.id.carWash_search_xrv);
        mDefaultStv = (SortTabView) view.findViewById(R.id.carWash_search_default_stv);
        mEvaluateStv = (SortTabView) view.findViewById(R.id.carWash_search_evaluate_stv);
        mSearchNotTv = view.findViewById(R.id.carWash_search_null_tv);
        mErrorView = (CxxErrorView) view.findViewById(R.id.carWash_search_error_view);

        mHistoryView = view.findViewById(R.id.new_carwash_history_ll);
        mDataResultView = view.findViewById(R.id.new_carwash_soso_result_ll);


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

        mErrorView.setOnErrorClickListener(v -> {
            showLoading();
            mPersenter.requestSearchStoreList(mKeyWord, PAGE, mSort);
        });
    }

    /**
     * 初始化历史记录
     */
    private void initHistoryView() {
        mHistoryAdapter = new CarWashSearchAdapter(this);
        mHistoryItemLv.setAdapter(mHistoryAdapter);
    }


    /**
     * 初始化点击事件
     */
    private void initClick() {
        //清除历史记录点击事件
        mHistoryClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearHistory();
            }
        });

        //历史记录点击事件
        mHistoryAdapter.setOnCarWashItemHistoryListener(new CarWashSearchAdapter.OnCarWashItemHistoryListener() {
            @Override
            public void onHistoryItemClick(String keyWord) {
                clickSearch(keyWord);
            }
        });
    }


    @Override
    public void clickSearch(String keyWord) {
        super.clickSearch(keyWord);
        if (TextUtils.isEmpty(keyWord)) {
            make("请输入商家名称");
            return;
        }
        showLoading();
        mKeyWord = keyWord;
        PAGE = 1;
        mHistoryView.setVisibility(View.GONE);
        mDataResultView.setVisibility(View.VISIBLE);
        mPersenter.requestSearchStoreList(mKeyWord, PAGE, mSort);
    }


    /*                  @历史记录的逻辑条件              */
    @Override
    public void historyData(List<String> historyDatas) {
        super.historyData(historyDatas);
        Log.d("historyData", "historyData:--> " + historyDatas.toString());
        if (historyDatas.size() == 0) {
            //没有历史记录
            mHistoryLl.setVisibility(View.GONE);
            mHistoryNullLl.setVisibility(View.VISIBLE);
        } else {
            mHistoryLl.setVisibility(View.VISIBLE);
            mHistoryNullLl.setVisibility(View.GONE);
        }

        mHistoryItemLv.requestLayout();
        //更新
        mHistoryAdapter.update(historyDatas);
    }


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
        mErrorView.setVisibility(View.GONE);

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
        hiddenLoading();
        mErrorView.setVisibility(View.VISIBLE);
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


//    @Override
//    public void finish() {
//        if (mHistoryView.getVisibility() == View.GONE) {
//            mHistoryView.setVisibility(View.VISIBLE);
//            return;
//        }
//        super.finish();
//    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity) {
        Intent intent = new Intent(activity, NewCarWashSearchAct.class);
        activity.startActivity(intent);
    }

}
