package com.xi6666.classification.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.view.CxxErrorView;
import com.xi6666.carWash.base.view.CxxNotView;
import com.xi6666.carWash.view.custom.SortTabView;
import com.xi6666.classification.view.adapter.BrandDetailsAdapter;
import com.xi6666.classification.view.adapter.MallHomeAdapter;
import com.xi6666.classification.view.adapter.TypeDetailsAdapter;
import com.xi6666.classification.view.fragment.mvp.bean.ShoppingNumberBean;
import com.xi6666.classification.view.mvp.BrandDetailsContract;
import com.xi6666.classification.view.mvp.BrandDetailsModel;
import com.xi6666.classification.view.mvp.BrandDetailsPresenter;
import com.xi6666.classification.view.mvp.bean.BrandDetailsBean;
import com.xi6666.order.activity.MyShoppingCartActivity;
import com.xi6666.productdetails.view.ProductDetailsAct;
import com.xi6666.utils.DimenUtils;
import com.xi6666.utils.RecyclearViewUtils;
import com.xi6666.view.superrecyclerview.XRecyclerView;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午6:30.
 * 个人公众号 ardays
 */

public class BrandDetailsAct extends BaseToolbarView<BrandDetailsPresenter, BrandDetailsModel>
        implements BrandDetailsContract.View, SortTabView.OnSortTabListener {


    @Override
    public String title() {
        return "品牌分类";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_brand_details;
    }

    public XRecyclerView mBrandDetailsXrv;
    SortTabView mTabOneStv;
    SortTabView mTabTwoStv;
    SortTabView mTabThreeStv;
    SortTabView mDefaultStv;
    CxxNotView mNotView;
    CxxErrorView mErrorView;

    int PAGE = 1;    //页数
    String mGoodsId;    //商品ID
    String mBrandId;    //品牌ID
    BrandDetailsContract.Sort mSort = BrandDetailsContract.Sort.Default; //排序

    public TypeDetailsAdapter mBrandDetailsAdapter;  //适配器


    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initToolbar();
        initList();
        showLoading();
        mErrorView.setOnErrorClickListener(v -> {
            mPersenter.requestBrandGoodsList(mBrandId, mGoodsId, PAGE, mSort);
        });
        mPersenter.requestBrandGoodsList(mBrandId, mGoodsId, PAGE, mSort);
        mPersenter.requestShoppingNumber();
    }

    /**
     * @param view
     */
    private void initView(View view) {
        mBrandDetailsXrv = (XRecyclerView) view.findViewById(R.id.brand_details_xrv);
        mTabOneStv = (SortTabView) view.findViewById(R.id.brand_details_tab_one);
        mTabTwoStv = (SortTabView) view.findViewById(R.id.brand_details_tab_two);
        mTabThreeStv = (SortTabView) view.findViewById(R.id.brand_details_tab_three);
        mNotView = (CxxNotView) view.findViewById(R.id.brand_details_not_view);
        mErrorView = (CxxErrorView) view.findViewById(R.id.brand_details_error_view);

        this.mDefaultStv = mTabOneStv;

        //设置tab切换事件
        mTabOneStv.setOnSortTabListener(this);
        mTabTwoStv.setOnSortTabListener(this);
        mTabThreeStv.setOnSortTabListener(this);

        mBrandDetailsXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE = 1;
                mPersenter.requestBrandGoodsList(mBrandId, mGoodsId, PAGE, mSort);
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                mPersenter.requestBrandGoodsList(mBrandId, mGoodsId, PAGE, mSort);
            }
        });
        //添加分割线
        mBrandDetailsXrv.addItemDecoration(new RecyclearViewUtils.RecyclearViewDivider(this, DimenUtils.dip2px(this, 7)));
    }

    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        //设置标题
        String title = getIntent().getStringExtra(TITLE);
        setToolbarTitle(title);

        //
        setToolbarRightNumberTitle("0");
        setToolbarRightIcon(R.drawable.ic_product_shoppingcar);
    }


    @Override
    public void onToolbarRightClick(View view) {
        super.onToolbarRightClick(view);
        if (isLogin()) startActivity(new Intent(this, MyShoppingCartActivity.class));
    }

    /**
     * 初始化值
     */
    private void initValue() {
        mGoodsId = getIntent().getStringExtra(GOODS_ID);
        mBrandId = getIntent().getStringExtra(BRAND_ID);
    }

    /**
     * 初始化列表
     */
    private void initList() {
        //设置适配器
        RequestManager glide = Glide.with(this);
        mBrandDetailsAdapter = new TypeDetailsAdapter(getActivity(), glide);
        //创建下拉刷新
        mBrandDetailsXrv.setLayoutManager(new GridLayoutManager(this, 2));
        mBrandDetailsXrv.setAdapter(mBrandDetailsAdapter);

        mBrandDetailsAdapter.setOnTypeDetailsAdapterListener(new TypeDetailsAdapter.OnTypeDetailsAdapterListener() {
            @Override
            public void onItemClick(BrandDetailsBean.DataBean.ListBean bean) {
                Intent intent = new Intent(getActivity(), ProductDetailsAct.class);
                intent.putExtra("goodId", bean.goods_id);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onSortTabSwitch(View view, boolean bol) {
        switch (view.getId()) {
            case R.id.brand_details_tab_one:    //默认
                mSort = BrandDetailsContract.Sort.Default;
                break;
            case R.id.brand_details_tab_two:    //销量
//                if (bol) {
                    mSort = BrandDetailsContract.Sort.SalesMany;
//                } else {
//                    mSort = BrandDetailsContract.Sort.SalesMany;
////                    mSort = BrandDetailsContract.Sort.SalesLess;
//                }
                break;
            case R.id.brand_details_tab_three:  //价格
                if (bol) {
                    mSort = BrandDetailsContract.Sort.MoneyMany;
                } else {
                    mSort = BrandDetailsContract.Sort.MeneyLess;
                }
                break;
        }

        PAGE = 1;
        showLoading();
        mPersenter.requestBrandGoodsList(mBrandId, mGoodsId, PAGE, mSort);
    }

    @Override
    public void onSelectedSortTab(View view, boolean bol) {
        if (mDefaultStv != null && view.getId() != mDefaultStv.getId()) {
            mDefaultStv.unSelectTab();
        }
        onSortTabSwitch(view, bol);
        mDefaultStv = (SortTabView) view;
    }


//                                      @网络请求

    @Override
    public void returnBrandGoodsData(BrandDetailsBean bean) {
        hiddenLoading();
        mBrandDetailsXrv.refreshComplete();
        if (bean.success) {
            if (PAGE == 1) {
                if (bean.data.list.size() == 0) {
                    mNotView.setVisibility(View.VISIBLE);
                    mBrandDetailsXrv.setVisibility(View.GONE);
                } else {
                    mNotView.setVisibility(View.GONE);
                    mBrandDetailsXrv.setVisibility(View.VISIBLE);
                    mBrandDetailsAdapter.update(bean.data.list);
                }
            } else {
                mBrandDetailsAdapter.addData(bean.data.list);
            }
        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnShoppingNumber(ShoppingNumberBean bean) {
        if (bean.success) {
            setToolbarRightNumberTitle(bean.data);
        }
    }

    @Override
    public void returnBrandError() {
        hiddenLoading();
        mErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, String brand_id, String goods_id, String title) {
        Intent intent = new Intent(activity, BrandDetailsAct.class);
        intent.putExtra(BRAND_ID, brand_id);
        intent.putExtra(GOODS_ID, goods_id);
        intent.putExtra(TITLE, title);
        activity.startActivity(intent);
    }

    private static final String BRAND_ID = "com.xi6666.brand_id";   //品牌ID
    private static final String GOODS_ID = "com.xi6666.goods_id";   //商品ID
    private static final String TITLE = "com.xi6666.title";   //标题

}
