package com.xi6666.producthome.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ComponetBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.classification.view.AllClassificationAct;
import com.xi6666.classification.view.ShopSearchAct;
import com.xi6666.common.UserData;
import com.xi6666.databean.HomeHotGoodsBean;
import com.xi6666.databean.HomeSpecialBean;
import com.xi6666.databean.ProductHomeCateBean;
import com.xi6666.home.adapter.HomeGoodsAdapter;
import com.xi6666.home.adapter.HomeHeadSpecialAdapter;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.login.view.LoginAct;
import com.xi6666.network.ApiRest;
import com.xi6666.order.activity.MyShoppingCartActivity;
import com.xi6666.productdetails.view.ProductDetailsAct;
import com.xi6666.producthome.ProductHeadcClassifyAdapter;
import com.xi6666.producthome.ProductHomeHolderView;
import com.xi6666.producthome.contract.ProductHomeContract;
import com.xi6666.producthome.di.DaggerProductHomeComponent;
import com.xi6666.producthome.di.ProductModule;
import com.xi6666.producthome.presenter.ProductHomePresenterImpl;
import com.xi6666.utils.DimenUtils;
import com.xi6666.view.EmptyLayout;
import com.xi6666.view.GridItemDivDecoration;
import com.xi6666.view.MesureGrideView;
import com.xi6666.view.MesureListView;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductHomeAct extends ComponetBaseAct implements View.OnClickListener,
        ProductHomeContract.View,
        HomeGoodsAdapter.OnRecyclerViewItemClickListener, XRecyclerView.LoadingListener,
        OnItemClickListener,
        AdapterView.OnItemClickListener, HomeHeadSpecialAdapter.OnClickListener {

    private static final String TAG = "ProductHomeAct";
    @BindView(R.id.xrec_producthome)
    XRecyclerView mXrecProducthome;

    @BindView(R.id.iv_producthome_backtop)
    ImageView mIcBackTop;

    @BindView(R.id.iv_producthome_shopcar)
    ImageView mShopCar;
    @BindView(R.id.el_address_producthome)
    EmptyLayout mElAddressProducthome;

    private HomeGoodsAdapter mHomeGoodsAdapter;
    private ConvenientBanner mBanner;
    private MesureGrideView mGvClassify;
    private RelativeLayout mRlSales;
    private MesureListView mMlSales;
    private RelativeLayout mRlHot;
    private HomeHeadSpecialAdapter mHomeHeadSpecialAdapter;

    @Inject
    ApiRest mApiRest;
    @Inject
    ProductHomePresenterImpl mProductHomePresenter;
    private ProductHeadcClassifyAdapter mProductHeadcClassifyAdapter;
    private List<ProductHomeCateBean.DataBean.ListBean> mCateDataBeen = new ArrayList<>();
    private List<HomeHotGoodsBean.DataBean> mHotDataBeen = new ArrayList<>();
    private List<HomeSpecialBean.DataBean> mSpecialData = new ArrayList<>();
    private List<String> mBannerBeen = new ArrayList<>();

    private int page = 1;
    private RelativeLayout mRlHeadAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_home);
        ButterKnife.bind(this);
        setRightTxt("");
        mTxtRight.setBackgroundResource(R.drawable.ic_product_seach);
        mTxtRight.setOnClickListener(this);
        mXrecProducthome.setLayoutManager(new GridLayoutManager(this, 2));
        mElAddressProducthome.setErrorButtonClickListener(this);


        DaggerProductHomeComponent.builder().apiModule(new ApiModule((BaseApplication) getApplication())).
                productModule(new ProductModule()).build().Inject(this);

        View headView = LayoutInflater.from(this).inflate(R.layout.product_head, null);
        mBanner = (ConvenientBanner) headView.findViewById(R.id.cb_product_head);

        mGvClassify = (MesureGrideView) headView.findViewById(R.id.gv_producthead_classify);
        mRlSales = (RelativeLayout) headView.findViewById(R.id.rl_producthead_sales);
        mMlSales = (MesureListView) headView.findViewById(R.id.ml_producthead_sales);
        mRlHot = (RelativeLayout) headView.findViewById(R.id.rl_producthead_hot);
        mRlHeadAll = (RelativeLayout) headView.findViewById(R.id.rl_producthomehead_all);


        mMlSales.setEnabled(false);

        mProductHomePresenter.attachView(this);
        mProductHomePresenter.setApiRest(mApiRest);
        mProductHomePresenter.loadCateGories();
        mProductHeadcClassifyAdapter = new ProductHeadcClassifyAdapter();
        mProductHeadcClassifyAdapter.setDataBeen(mCateDataBeen);
        mGvClassify.setAdapter(mProductHeadcClassifyAdapter);
        mGvClassify.setOnItemClickListener(this);

        mRlSales.setOnClickListener(this);
        mRlHot.setOnClickListener(this);
        mShopCar.setOnClickListener(this);
        mRlHeadAll.setOnClickListener(this);

        //专题数据
        mHomeHeadSpecialAdapter = new HomeHeadSpecialAdapter();
        mHomeHeadSpecialAdapter.setProductHome(true);
        mHomeHeadSpecialAdapter.setData(mSpecialData);
        mHomeHeadSpecialAdapter.setOnBannerClickListener(this);
        mMlSales.setAdapter(mHomeHeadSpecialAdapter);
        mIcBackTop.setOnClickListener(this);

        //商品流
        mXrecProducthome.addHeaderView(headView);
        mXrecProducthome.addItemDecoration(new GridItemDivDecoration(DimenUtils.dip2px(this, 5), 2, true));
        mHomeGoodsAdapter = new HomeGoodsAdapter();
        mHomeGoodsAdapter.setDataBeen(mHotDataBeen);
        mHomeGoodsAdapter.setOnItemClickListener(this);
        mXrecProducthome.setAdapter(mHomeGoodsAdapter);
        mXrecProducthome.setLoadingListener(this);
        mXrecProducthome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //滚动到头部按钮
                totalDy -= dy;
                if (totalDy < -1000) {
                    mIcBackTop.setVisibility(View.VISIBLE);
                } else {
                    mIcBackTop.setVisibility(View.GONE);

                }
            }
        });
        //数据加载
        mProductHomePresenter.loadCateGories();
        mProductHomePresenter.loadSpeCiaData();
        mProductHomePresenter.loadHotProduct(page);

    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "车品商城";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //商品搜索
            case R.id.txt_basetoolbar_right:
                startActivity(new Intent(this, ShopSearchAct.class));
                break;
            //商品分类
            case R.id.rl_producthomehead_all:
                AllClassificationAct.openActivity(this, 0);
                break;
            //返回顶部按钮
            case R.id.iv_producthome_backtop:
                mXrecProducthome.smoothScrollToPosition(0);
                break;
            case R.id.iv_producthome_shopcar:
                if (TextUtils.isEmpty(UserData.getUserId())) {
                    startActivity(new Intent(this, LoginAct.class));
                } else {
                    startActivity(new Intent(this, MyShoppingCartActivity.class));
                }
                break;
            case R.id.buttonError:
                page = 1;
                mProductHomePresenter.loadCateGories();
                mProductHomePresenter.loadHotProduct(page);
                mProductHomePresenter.loadSpeCiaData();
                break;
        }
    }


    @Override
    public void addCateData(List<ProductHomeCateBean.DataBean.ListBean> productHomeCateBean) {
        mCateDataBeen.clear();
        mCateDataBeen.addAll(productHomeCateBean);
        mProductHeadcClassifyAdapter.setDataBeen(mCateDataBeen);
    }


    @Override
    public void addSpecialData(List<HomeSpecialBean.DataBean> listBeen) {
        mSpecialData.clear();
        mSpecialData.addAll(listBeen);
        mHomeHeadSpecialAdapter.setData(mSpecialData);
    }

    @Override
    public void addBannerData(List<String> bannerBeen) {
        mBannerBeen.clear();
        mBannerBeen.addAll(bannerBeen);
        mBanner.setPages(new CBViewHolderCreator<ProductHomeHolderView>() {
            @Override
            public ProductHomeHolderView createHolder() {
                return new ProductHomeHolderView(ProductHomeAct.this);
            }
        }, bannerBeen).setOnItemClickListener(this);
        if (mBannerBeen.size() > 1) {
            mBanner.setPointViewVisible(true)    //设置指示器是否可见
                    .setPageIndicator(new int[]{R.drawable.point, R.drawable.point_pre})   //设置指示器圆点
                    .startTurning(5000);     //设置自动切换（同时设置了切换时间间隔）
        }
    }

    @Override
    public void finishiRefresh() {
        mXrecProducthome.refreshComplete();
    }

    @Override
    public void finishiLoadMore() {
        mXrecProducthome.loadMoreComplete();
    }

    @Override
    public void hasNoData(boolean isMore) {
        mXrecProducthome.setNoMore(isMore);
    }

    @Override
    public void showLoaind() {
        mElAddressProducthome.showLoading();
    }

    @Override
    public void showError() {
        mElAddressProducthome.showError();
    }

    @Override
    public void hideState() {
        mElAddressProducthome.hide();
    }

    @Override
    public void onRecyclerItemClick(int position) {
        Intent intent = new Intent(this, ProductDetailsAct.class);
        intent.putExtra("goodId", mHotDataBeen.get(position).getGoods_id());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        page = 1;
        hasNoData(false);
        mProductHomePresenter.loadHotProduct(page);

    }

    @Override
    public void onLoadMore() {
        page++;
        mProductHomePresenter.loadHotProduct(page);
    }

    @Override
    public void addHotProduct(List<HomeHotGoodsBean.DataBean> dataBeen) {
        if (page == 1) {
            mHotDataBeen.clear();
        }
        mHotDataBeen.addAll(dataBeen);
        mHomeGoodsAdapter.setDataBeen(mHotDataBeen);

    }


    //bannner图的点击事件
    @Override
    public void onItemClick(int position) {
        //TODO 轮播图的点击事件

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AllClassificationAct.openActivity(this, 0, position);
    }

    @Override
    public void onBannnerClick(int position) {
        HtmlAct.unsealActivity(this, mSpecialData.get(position).getZt_url() + "?get_device_type=android");
    }
}
