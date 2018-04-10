package com.xi6666.classification.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.xi6666.R;
import com.xi6666.carWash.adapter.CarWashSearchAdapter;
import com.xi6666.carWash.view.custom.BaseSearchToolbarView;
import com.xi6666.carWash.view.custom.SortTabView;
import com.xi6666.classification.view.adapter.BrandDetailsAdapter;
import com.xi6666.classification.view.adapter.MallHomeAdapter;
import com.xi6666.classification.view.adapter.TypeDetailsAdapter;
import com.xi6666.classification.view.custom.BaseListView;
import com.xi6666.classification.view.custom.TagCloudView;
import com.xi6666.classification.view.mvp.BrandDetailsContract;
import com.xi6666.classification.view.mvp.ShopSearchContract;
import com.xi6666.classification.view.mvp.ShopSearchModel;
import com.xi6666.classification.view.mvp.ShopSearchPresenter;
import com.xi6666.classification.view.mvp.bean.BrandDetailsBean;
import com.xi6666.classification.view.mvp.bean.ShopSearchHotBean;
import com.xi6666.productdetails.view.ProductDetailsAct;
import com.xi6666.utils.DimenUtils;
import com.xi6666.utils.RecyclearViewUtils;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2016/11/30 下午9:38.
 * 个人公众号 ardays
 */

public class ShopSearchAct extends BaseSearchToolbarView<ShopSearchPresenter, ShopSearchModel>
        implements ShopSearchContract.View, SortTabView.OnSortTabListener {


    @Override
    public String searchTitle() {
        return "";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_shop_search;
    }

    TagCloudView mHotSearchTcv; //热门搜索
    BaseListView mHistoryBlv;   //历史列表
    TextView mClearBtn;   //清除历史记录
    View mNotSearchHintTv;  //没有搜索记录提示
    View mDefaultView;  //历史记录页面
    View mSearchView;   //搜索结果
    View mSearchNotView;  //搜索结果为空
    XRecyclerView mSearchResultXrv; //历史记录搜索结果页面
    SortTabView mTabOneStv;
    SortTabView mTabTwoStv;
    SortTabView mTabThreeStv;
    SortTabView mDefaultStv;    //


    CarWashSearchAdapter mHistoryAdapter;   //历史记录适配器
    TypeDetailsAdapter mMallHomeAdapter;   //商品详情页
    int PAGE = 1;   //分页
    int SORT = 0;   //排序
    String mKeyWord = ""; //关键字


    @Override
    public void setUp(View view) {
        initView(view);
        initToolbar();
        initHistory();
        initSearchLv();
        showLoading();
        mPersenter.requestHotSearch();  //初始化热门搜索
    }

    /**
     * 初始化绑定控件
     */
    private void initView(View view) {
        mHotSearchTcv = (TagCloudView) view.findViewById(R.id.home_hot_search_tcv);
        mHistoryBlv = (BaseListView) view.findViewById(R.id.home_history_search_blv);
        mSearchNotView = view.findViewById(R.id.brand_details_not_view);
        mClearBtn = (TextView) view.findViewById(R.id.home_clear_history_btn);
        mDefaultView = view.findViewById(R.id.home_search_default_sl);
        mSearchView = view.findViewById(R.id.home_search_result_ll);
        mSearchResultXrv = (XRecyclerView) view.findViewById(R.id.home_search_result_xrv);
        mNotSearchHintTv = view.findViewById(R.id.home_no_search_data_tv);

        mTabOneStv = (SortTabView) view.findViewById(R.id.brand_details_tab_one);
        mTabTwoStv = (SortTabView) view.findViewById(R.id.brand_details_tab_two);
        mTabThreeStv = (SortTabView) view.findViewById(R.id.brand_details_tab_three);
        this.mDefaultStv = mTabOneStv;


        //设置tab切换事件
        mTabOneStv.setOnSortTabListener(this);
        mTabTwoStv.setOnSortTabListener(this);
        mTabThreeStv.setOnSortTabListener(this);
    }


    /**
     * 初始化标题栏
     */
    private void initToolbar() {
    }

    private void initHistory() {
        mHistoryAdapter = new CarWashSearchAdapter(this);
        mHistoryBlv.setAdapter(mHistoryAdapter);

        //清除历史记录
        mClearBtn.setOnClickListener(v -> {
            clearHistory();
        });

        setSearchHint("请输入商品名");

        //点击历史记录
        mHistoryAdapter.setOnCarWashItemHistoryListener(new CarWashSearchAdapter.OnCarWashItemHistoryListener() {
            @Override
            public void onHistoryItemClick(String keyWord) {
                clickSearch(keyWord);
            }
        });
    }

    /**
     * 初始化搜索列表
     */
    private void initSearchLv() {
        RequestManager glide = Glide.with(this);
        mMallHomeAdapter = new TypeDetailsAdapter(this, glide);
        mSearchResultXrv.setLayoutManager(new GridLayoutManager(this, 2));
        mSearchResultXrv.setAdapter(mMallHomeAdapter);
        //添加分割线
        mSearchResultXrv.addItemDecoration(new RecyclearViewUtils.RecyclearViewDivider(this, DimenUtils.dip2px(this, 7)));

        mMallHomeAdapter.setOnTypeDetailsAdapterListener(new TypeDetailsAdapter.OnTypeDetailsAdapterListener() {
            @Override
            public void onItemClick(BrandDetailsBean.DataBean.ListBean bean) {
                Intent intent = new Intent(getActivity(), ProductDetailsAct.class);
                intent.putExtra("goodId", bean.goods_id);
                startActivity(intent);
            }
        });

        mSearchResultXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE = 1;
                mPersenter.requestSearchList(PAGE, SORT, mKeyWord);
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                mPersenter.requestSearchList(PAGE, SORT, mKeyWord);
            }
        });
    }


    /*                  @历史记录的逻辑条件              */
    @Override
    public void historyData(List<String> historyDatas) {
        super.historyData(historyDatas);
        Log.d("historyData", "historyData:--> " + historyDatas.toString());
        if (historyDatas.size() == 0) {
            //没有历史记录
//            mHistoryLl.setVisibility(View.GONE);
//            mHistoryNullLl.setVisibility(View.VISIBLE);
            mNotSearchHintTv.setVisibility(View.VISIBLE);
            mClearBtn.setVisibility(View.GONE);
        } else {
            mClearBtn.setVisibility(View.VISIBLE);
            mNotSearchHintTv.setVisibility(View.GONE);
        }

        //更新
        mHistoryAdapter.update(historyDatas);
    }

    @Override
    public void clickSearch(String keyWord) {
        super.clickSearch(keyWord);
        mKeyWord = keyWord;
        if (TextUtils.isEmpty(mKeyWord)) {
            mDefaultView.setVisibility(View.VISIBLE);
            mSearchView.setVisibility(View.GONE);
        } else {
            mDefaultView.setVisibility(View.GONE);
            mSearchView.setVisibility(View.VISIBLE);
            PAGE = 1;
            mPersenter.requestSearchList(PAGE, SORT, mKeyWord);
            mNotSearchHintTv.setVisibility(View.GONE);
            mClearBtn.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onSortTabSwitch(View view, boolean bol) {
        switch (view.getId()) {
            case R.id.brand_details_tab_one:    //默认
                SORT = 0;
                break;
            case R.id.brand_details_tab_two:    //销量
                SORT = 1;
                break;
            case R.id.brand_details_tab_three:  //价格
                if (bol) {
                    SORT = 5;
                } else {
                    SORT = 4;
                }
                break;
        }

        PAGE = 1;
        showLoading();
        mPersenter.requestSearchList(PAGE, SORT, mKeyWord);
    }

    @Override
    public void onSelectedSortTab(View view, boolean bol) {
        if (mDefaultStv != null && view.getId() != mDefaultStv.getId()) {
            mDefaultStv.unSelectTab();
        }
        onSortTabSwitch(view, bol);
        mDefaultStv = (SortTabView) view;
    }


    @Override
    public void returnHotSearch(ShopSearchHotBean bean) {
        hiddenLoading();
        if (bean.success) {
            mHotSearchTcv.setTags(bean.data.list);
            //点击热门搜索
            mHotSearchTcv.setOnTagClickListener(i -> {
                clickSearch(bean.data.list.get(i));
            });
        }else{
            make(bean.info);
        }
    }

    @Override
    public void returnSearchList(BrandDetailsBean data) {
        hiddenLoading();
        mSearchResultXrv.refreshComplete();
        mSearchResultXrv.requestLayout();
        if (data.success) {
            if (PAGE == 1) {
                if(data.data.list.size() == 0){
                    mSearchNotView.setVisibility(View.VISIBLE);
                    mSearchResultXrv.setVisibility(View.GONE);
                }else {
                    mSearchNotView.setVisibility(View.GONE);
                    mSearchResultXrv.setVisibility(View.VISIBLE);
                    mMallHomeAdapter.update(data.data.list);
                }
            } else {
                mMallHomeAdapter.addData(data.data.list);
            }
        } else {
            make(data.info);
            mMallHomeAdapter.clear();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (!TextUtils.isEmpty(mKeyWord)) {
                    clickSearch("");
                    mHistoryBlv.requestLayout();
                    mHistoryAdapter.notifyDataSetChanged();
                    searchKeyWord("");
                    return true;
                }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity) {
        Intent intent = new Intent(activity, ShopSearchAct.class);
        activity.startActivity(intent);
    }
}
