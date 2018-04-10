package com.xi6666.carWash.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.carWash.adapter.CarWashImageHolderView;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.view.CxxErrorView;
import com.xi6666.carWash.base.view.CxxNotView;
import com.xi6666.carWash.view.custom.SortTabView;
import com.xi6666.carWash.view.fragment.CarWashNearFarFragment;
import com.xi6666.carWash.view.mvp.CarWashActContract;
import com.xi6666.carWash.view.mvp.CarWashActModel;
import com.xi6666.carWash.view.mvp.CarWashActPresenter;
import com.xi6666.common.UserData;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.libray.widget.HeaderViewPager;
import com.xi6666.store.bean.StoreBannerBean;
import com.xi6666.store.mvp.bean.StoreServiceBean;
import com.xi6666.view.superrecyclerview.XRecyclerView;

/**
 * 创建者 sunsun
 * 时间 16/10/31 下午3:41.
 * 个人公众号 ardays
 * <p>
 * 特惠洗车 - 首页
 */

public class CarWashAct extends BaseToolbarView<CarWashActPresenter, CarWashActModel>
        implements CarWashActContract.View, SortTabView.OnSortTabListener {
    @Override
    public String title() {
        return "";
    }


    @Override
    public int mainResId() {
        return R.layout.activity_carwash;
    }

    View mView;

    SortTabView mSelectedSortTabView;    //选中的SortTab
    HeaderViewPager mHeaderViewPager;   //滚动布局
    ConvenientBanner mBannerIv;    //Banner图
    CxxErrorView mErrorView;    //加载失败的页面
    CxxNotView mNotView;    //没有可服务门店
    View mFrgmView; //fragmentView

    CarWashNearFarFragment mNearFarFg;         //从近到远的筛选条件


    private int mType = 1;      //分类
    private int PAGE = 1;       //页码
    private int mSort = 5;      //排序方式


    @Override
    public void setUp(View view) {
        initView(view);
        initToolbar();
        initType();
        initClick();
        showLoading();
        mPersenter.requestCarWashList(mType, mSort, PAGE);
    }

    /**
     * 初始化View
     */
    private void initView(View view) {
        this.mView = view;
        mSelectedSortTabView = (SortTabView) view.findViewById(R.id.carWash_tab_one);
        mHeaderViewPager = (HeaderViewPager) view.findViewById(R.id.store_hvp);
        mBannerIv = (ConvenientBanner) view.findViewById(R.id.carWash_banner_cb);
        mErrorView = (CxxErrorView) view.findViewById(R.id.carWash_error_view);
        mFrgmView = view.findViewById(R.id.carWash_fl);
        mNotView = (CxxNotView) view.findViewById(R.id.carWash_not_view);

        mNearFarFg = CarWashNearFarFragment.newInstance();
        //设置滚动
        mHeaderViewPager.setCurrentScrollableContainer(mNearFarFg);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.carWash_fl, mNearFarFg);
        transaction.commit();

        //网络加载错误
        mErrorView.setOnErrorClickListener(v -> {
            showLoading();
            mPersenter.requestCarWashList(mType, mSort, PAGE);
        });

        //点击失败
        mErrorView.setOnErrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPersenter.requestCarWashList(mType, mSort, PAGE);
            }
        });

        //设置上拉下拉刷新
        mNearFarFg.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE = 1;
                mPersenter.requestCarWashList(mType, mSort, PAGE);
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                mPersenter.requestCarWashList(mType, mSort, PAGE);
            }
        });

    }


    /**
     * 初始化标题栏
     */
    public void initToolbar() {
        //设置标题栏右边搜索按钮
        setToolbarRightIcon(R.mipmap.ic_query_gray);
    }

    /**
     * 判断分类
     */
    private void initType() {
        mType = getIntent().getIntExtra(TYPE, 0);
        String title = getIntent().getStringExtra(TITLE);
        boolean bol = getIntent().getBooleanExtra(SHOW_BANNER, false);

        if (bol) {
            mPersenter.requestBanner();
        } else {
            mBannerIv.setVisibility(View.GONE);
            mHeaderViewPager.setScrolled(false);
        }
//        switch (mType) {
//            case 0:
//                //特惠洗车
//                title = "特惠洗车";
//                mPersenter.requestBanner();
//                break;
//            case 2:
//                //美容:
//                title = "美容";
//                mBannerIv.setVisibility(View.GONE);
//                mHeaderViewPager.setScrolled(false);
//                break;
//            case 1:
//                //保养;
//                mBannerIv.setVisibility(View.GONE);
//                mHeaderViewPager.setScrolled(false);
//                title = "保养";
//                break;
//            case 3:
//                mBannerIv.setVisibility(View.GONE);
//                mHeaderViewPager.setScrolled(false);
//                title = "违章处理";
//                break;
//        }
//        mType += 1;
        setToolbarTitle(title);
    }


    @Override
    public void onToolbarRightClick(View view) {
        NewCarWashSearchAct.openActivity(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //当前窗口获取焦点时，才能正真拿到titlebar的高度，此时将需要固定的偏移量设置给scrollableLayout即可
        //默认是拿当前布局第一个控件的高度
        mHeaderViewPager.setTopOffset(0);
    }

    /**
     * 点击时间
     */
    private void initClick() {
        ((SortTabView) (this.mView.findViewById(R.id.carWash_tab_one))).setOnSortTabListener(this);
        ((SortTabView) (this.mView.findViewById(R.id.carWash_tab_two))).setOnSortTabListener(this);
        ((SortTabView) (this.mView.findViewById(R.id.carWash_tab_three))).setOnSortTabListener(this);
    }


    /***
     * 选中的颜色
     */
    @Override
    public void onSortTabSwitch(View view, boolean bol) {

        switch (view.getId()) {
            case R.id.carWash_tab_one:
                mSort = 5;
                break;
            case R.id.carWash_tab_two:
                if (bol) {
                    //价格从高到低
                    mSort = 1;
                } else {
                    //价格从低到高
                    mSort = 2;
                }
                break;
            case R.id.carWash_tab_three:
                mSort = 3;
            default:
                break;
        }
        PAGE = 1;
        showLoading();
        mPersenter.requestCarWashList(mType, mSort, PAGE);
    }

    /**
     * 第一次选中Tab回调
     */
    @Override
    public void onSelectedSortTab(View view, boolean bol) {
        //判断是否此次点击Tab是不是选中的Tab，如果是就让当前的Tab取消选中.在末尾赋值当前的Tab
        if (mSelectedSortTabView != null && mSelectedSortTabView.getId() != view.getId()) {
            mSelectedSortTabView.unSelectTab();
        }
        onSortTabSwitch(view, bol);
        mSelectedSortTabView = (SortTabView) view;
    }


    //                          @数据返回
    @Override
    public void returnCarWashList(StoreServiceBean bean, int position) {
        hiddenLoading();
        mErrorView.setVisibility(View.GONE);
        mFrgmView.setVisibility(View.VISIBLE);
        if (bean.success) {
            if (PAGE == 1) {
                if (bean.data.size() == 0) {
                    mNotView.setVisibility(View.VISIBLE);
                    mFrgmView.setVisibility(View.GONE);
                    mHeaderViewPager.setScrolled(false);
                } else {
                    mNotView.setVisibility(View.GONE);
                    mFrgmView.setVisibility(View.VISIBLE);
                    mNearFarFg.update(bean.data);
                }
            } else {
                mNearFarFg.addList(bean.data);
            }
        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnBannerList(StoreBannerBean bean) {
        if (!bean.success) {
            make(bean.info);
        }
        mBannerIv.setPages(new CBViewHolderCreator<CarWashImageHolderView>() {
            @Override
            public CarWashImageHolderView createHolder() {
                return new CarWashImageHolderView();
            }
        }, bean.data).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HtmlAct.unsealActivity(getActivity(), bean.data.get(position).link + "?get_device_type=android&user_id=" + UserData.getUserId() + "&user_token=" + UserData.getUserToken());
            }
        });
        mBannerIv.setCanLoop(false);


        //设置显示器
        if (bean.data.size() > 1) {
            mBannerIv.setCanLoop(true);
            mBannerIv.setPointViewVisible(true)    //设置指示器是否可见
                    .setPageIndicator(new int[]{R.drawable.point, R.drawable.point_pre})   //设置指示器圆点
                    .startTurning(5000);     //设置自动切换（同时设置了切换时间间隔）
        }
    }

    @Override
    public void loadingError() {
        make("网络错误");
        hiddenLoading();
        mNearFarFg.refreshComplete();
        if (PAGE == 1) {
            mErrorView.setVisibility(View.VISIBLE);
            mFrgmView.setVisibility(View.GONE);
        }
    }


    /**
     * BY:记得注册Activity
     * type : 0 = 洗车
     * 1 = 美容
     * 2 = 保养
     */
    public static final void openActivity(Activity activity, int type) {
        Intent intent = new Intent(activity, CarWashAct.class);
        intent.putExtra(TYPE, type);
        activity.startActivity(intent);
    }

    public static final String TYPE = "CarWashAct.type";
    public static final String SHOW_BANNER = "CarWashAct.show_banner";
    public static final String TITLE = "CarWashAct.title";


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, int type, boolean showBanner, String title) {
        Intent intent = new Intent(activity, CarWashAct.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(TITLE, title);
        intent.putExtra(SHOW_BANNER, showBanner);
        activity.startActivity(intent);
    }

}
