package com.xi6666.home.view;


import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.xi6666.R;
import com.xi6666.addoil.view.AddoOilAct;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.SuperFrgm;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.car.SelectCarTypeAct;
import com.xi6666.car.view.MyCarActivity;
import com.xi6666.carWash.view.CarWashAct;
import com.xi6666.cityaddress.view.AddressAct;
import com.xi6666.classification.view.BrandDetailsAct;
import com.xi6666.common.BuriedPoint;
import com.xi6666.common.CityBean;
import com.xi6666.common.LocationBean;
import com.xi6666.common.UserData;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.databean.HomeHeadBean;
import com.xi6666.databean.HomeHotGoodsBean;
import com.xi6666.databean.HomeSpecialBean;
import com.xi6666.eventbus.AddressEvent;
import com.xi6666.eventbus.ChoiceDefaultCarEvent;
import com.xi6666.eventbus.LoginEvent;
import com.xi6666.home.LocalImageHolderView;
import com.xi6666.home.adapter.HomeFucationAdapter;
import com.xi6666.home.adapter.HomeGoodsAdapter;
import com.xi6666.home.adapter.HomeHeadSpecialAdapter;
import com.xi6666.home.contract.HomeContract;
import com.xi6666.home.di.DaggerHomeComponent;
import com.xi6666.home.di.HomeModule;
import com.xi6666.home.presenter.HomePresenterImpl;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.illegal.Activity.IllegalHomeAct;
import com.xi6666.illegal.Activity.IllegalQueryListActivity;
import com.xi6666.login.view.LoginAct;
import com.xi6666.network.ApiRest;
import com.xi6666.productdetails.view.ProductDetailsAct;
import com.xi6666.producthome.view.ProductHomeAct;
import com.xi6666.utils.DimenUtils;
import com.xi6666.utils.LogUtil;
import com.xi6666.view.CompatToolbar;
import com.xi6666.view.GridItemDivDecoration;
import com.xi6666.view.MesureListView;
import com.xi6666.view.StackTransformer;
import com.xi6666.view.dialog.LocationDialogSwitch;
import com.xi6666.view.dialog.PromotionDialogAct;
import com.xi6666.view.refresh.RefreshHeader;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.functions.Action1;

import static android.media.CamcorderProfile.get;


/**
 * @author peng
 * @data 创建时间:2016/11/24
 * @desc 首页
 */
public class HomeFrgm extends SuperFrgm implements XRecyclerView.LoadingListener,
        HomeContract.View, OnItemClickListener, AdapterView.OnItemClickListener,
        PtrHandler, HomeGoodsAdapter.OnRecyclerViewItemClickListener,
        HomeHeadSpecialAdapter.OnClickListener, View.OnClickListener {
    private static final String TAG = "HomeFrgm";
    @BindView(R.id.txt_homefrgm_address)
    TextView mTxtHomefrgmAddress;
    @BindView(R.id.iv_homefrgm_logo)
    ImageView mIvHomefrgmLogo;
    @BindView(R.id.tb_homefrgm)
    CompatToolbar mTbHomefrgm;
    @BindView(R.id.xrlv_homefrgm)
    XRecyclerView mXrlvHomefrgm;
    @BindView(R.id.pfl_home)
    PtrFrameLayout mPtrFrameLayout;

    @BindView(R.id.iv_homepage_backtop)
    ImageView mIvBackTop;
    @BindView(R.id.tv_homefrgm_state)
    TextView mTvNetStateError;


    private ConvenientBanner mConvenientBanner;
    private ImageView mIvFunctionOne;
    private ImageView mIvFunctionTwo;
    private ImageView mIvFunctionThr;
    private GridView mFuncation;
    private MesureListView mLvSpecial;

    private List<HomeHotGoodsBean.DataBean> mDataBeen = new ArrayList<>();
    private List<HomeSpecialBean.DataBean> mData = new ArrayList<>();
    private int page = 1;

    private List<HomeHeadBean.BannerBean> mBannerBeen = new ArrayList<>();

    private List<HomeHeadBean.IndexIconBean> mModuleBean = new ArrayList<>();

    private List<HomeHeadBean.IndexBlockBean> mIndexBlockBeen = new ArrayList<>();

    private boolean hasCar = false;

    @Inject
    HomePresenterImpl mHomePresenter;
    private HomeGoodsAdapter mHomeGoodsAdapter;
    private HomeHeadSpecialAdapter mHomeHeadSpecialAdapter;
    private HomeFucationAdapter mHomeFucationAdapter;


    //刷新地址
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshAddress(AddressEvent addressEvent) {
        mTxtHomefrgmAddress.setText(CityBean.getCity());
    }

    //选择默认爱车获取logo
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshLogoTwo(ChoiceDefaultCarEvent choiceDefaultCarEvent) {
       /* if (!TextUtils.isEmpty(UserData.getUserId())) {
            mHomePresenter.loadCarLogo();
        } else {
            //   mIvHomefrgmLogo.setImageResource(R.drawable.home_add_car);
        }*/
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        //头标题
        mXrlvHomefrgm.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mXrlvHomefrgm.addItemDecoration(new GridItemDivDecoration(DimenUtils.dip2px(mActivity, 5), 2, true));
        View header = LayoutInflater.from(mActivity).inflate(R.layout.home_head, null, false);
        mXrlvHomefrgm.addHeaderView(header);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        header.setLayoutParams(layoutParams);
        mConvenientBanner = (ConvenientBanner) header.findViewById(R.id.home_convenientBanner);
        mIvFunctionOne = (ImageView) header.findViewById(R.id.iv_homehead_functionone);
        mIvFunctionTwo = (ImageView) header.findViewById(R.id.iv_homehead_functiontwo);
        mIvFunctionThr = (ImageView) header.findViewById(R.id.iv_homehead_functionthr);
        mFuncation = (GridView) header.findViewById(R.id.gv_homedead);
        mLvSpecial = (MesureListView) header.findViewById(R.id.lv_homehead);

        //洗车,加油,商城功能点击事件
        mIvFunctionOne.setOnClickListener(this);
        mIvFunctionTwo.setOnClickListener(this);
        mIvFunctionThr.setOnClickListener(this);

        if (!TextUtils.isEmpty(CityBean.getCity())) {
            //设置地址
            mTxtHomefrgmAddress.setText(CityBean.getCity());
        }
        mConvenientBanner.getViewPager().setPageTransformer(true, new StackTransformer());

        //上拉下拉
        RefreshHeader refreshHeader = new RefreshHeader(mActivity);
        mPtrFrameLayout.setHeaderView(refreshHeader);
        mPtrFrameLayout.addPtrUIHandler(refreshHeader);
        mPtrFrameLayout.setPtrHandler(this);
        mXrlvHomefrgm.setLoadingListener(this);

        //商品流
        mHomeGoodsAdapter = new HomeGoodsAdapter();
        mXrlvHomefrgm.setAdapter(mHomeGoodsAdapter);
        mHomeGoodsAdapter.setDataBeen(mDataBeen);
        mHomeGoodsAdapter.setOnItemClickListener(this);
        mXrlvHomefrgm.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //滚动到头部按钮
                totalDy -= dy;
                if (totalDy < -1000) {
                    mIvBackTop.setVisibility(View.VISIBLE);
                } else {
                    mIvBackTop.setVisibility(View.GONE);
                }
            }
        });

        //模块
        mXrlvHomefrgm.setLoadingListener(this);
        mHomeFucationAdapter = new HomeFucationAdapter();
        mHomeFucationAdapter.setIndexIconBeen(mModuleBean);
        mFuncation.setAdapter(mHomeFucationAdapter);
        mFuncation.setOnItemClickListener(this);

        //专题
        mHomeHeadSpecialAdapter = new HomeHeadSpecialAdapter();
        mHomeHeadSpecialAdapter.setData(mData);
        mLvSpecial.setAdapter(mHomeHeadSpecialAdapter);
        mHomeHeadSpecialAdapter.setOnBannerClickListener(this);
        mLvSpecial.setEnabled(false);

        //注入
        DaggerHomeComponent.builder().apiModule(new
                ApiModule((BaseApplication) mActivity.getApplication()))
                .homeModule(new HomeModule()).build().Inject(this);

        //相关操作
        mHomePresenter.attachView(this);
        mHomePresenter.setContext(mActivity);

        //加载头部数据
        mHomePresenter.loadheadData();

        mHomePresenter.loadMoreData(page + "");

        if (!TextUtils.isEmpty(UserData.getUserId())) {
            mHomePresenter.loadCarLogo();
        }
        //请求权限
        initPermission();

        if (UserData.isLoginIn()) {
            mHomePresenter.loadPopuData(UserData.getUserId(), UserData.getUserToken());
        }
    }

    //获取定位权限
    private void initPermission() {
        RxPermissions.getInstance(mActivity).request(Manifest.permission.ACCESS_FINE_LOCATION).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {

                if (aBoolean) {
                    mHomePresenter.location();
                } else {
                    showLocationSetting("为了您能够正常的使用本app,请授予定位权限!");
                }
            }
        });
    }

    //弹框去位置设置界面
    @Override
    public void showLocationSetting(String context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("提示").setMessage(context);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });
        builder.show();
    }

    @Override
    public void showAddoilPopu(List<AddOilPopuBean.DataBean.CouponListBean> dataBeen) {
        Intent intent = new Intent(mActivity, PromotionDialogAct.class);
        intent.putExtra("list", (Serializable) dataBeen);
        startActivity(intent);
    }

    //设置地址
    @Override
    public void setAddress(String address) {
        mTxtHomefrgmAddress.setText(address);
    }

    @Override
    public void showSwitchAddressDialog(final LocationBean locationBean) {
        //切换到当地数据
        final LocationDialogSwitch locationDialogSwitch = new LocationDialogSwitch(getActivity());
        locationDialogSwitch.showLocationSwitch();
        locationDialogSwitch.setCity(locationBean.getCity());
        locationDialogSwitch.setOnLocationSwichOnclick(new LocationDialogSwitch.OnLocationSwichOnclick() {
            @Override
            public void cancle() {
                locationDialogSwitch.closeDialog();
            }

            @Override
            public void swich() {
                CityBean.setProvince(locationBean.getProvince());
                CityBean.setLat(locationBean.getLatitude());
                CityBean.setLng(locationBean.getLongitude());
                CityBean.setCity(locationBean.getCity().substring(0, locationBean.getCity().length() - 1));
                CityBean.setAddress(locationBean.getAddress());
                CityBean.setRegionId("");
                setAddress(locationBean.getCity().substring(0, locationBean.getCity().length() - 1));
                locationDialogSwitch.closeDialog();
            }
        });
    }


    //设置banner
    @Override
    public void setBanner(List<HomeHeadBean.BannerBean> homeBannerBean) {
        mBannerBeen.clear();
        mBannerBeen.addAll(homeBannerBean);
        mConvenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView(mActivity);
            }
        }, mBannerBeen).setOnItemClickListener(this);
        if (mBannerBeen.size() > 1) {
            mConvenientBanner.setPointViewVisible(true)    //设置指示器是否可见
                    .setPageIndicator(new int[]{R.drawable.enablefalse, R.drawable.enabletrue})   //设置指示器圆点
                    .startTurning(5000);     //设置自动切换（同时设置了切换时间间隔）
        }
    }

    //功能区头图
    @Override
    public void setFuncationImage(List<HomeHeadBean.IndexBlockBean> funcationImage) {
        mIndexBlockBeen.clear();
        mIndexBlockBeen.addAll(funcationImage);
        RequestManager manager = Glide.with(mActivity);
        manager.load(funcationImage.get(0).getIcon_img()).into(mIvFunctionOne);
        manager.load(funcationImage.get(1).getIcon_img()).into(mIvFunctionTwo);
        manager.load(funcationImage.get(2).getIcon_img()).into(mIvFunctionThr);
    }

    @Override
    public void setModuleData(List<HomeHeadBean.IndexIconBean> moduleData) {
        mModuleBean.clear();
        mModuleBean.addAll(moduleData);
        LogUtil.d(TAG, "mModuleBean---->" + mModuleBean.size());
        mHomeFucationAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSpecialData(List<HomeSpecialBean.DataBean> data) {
        mData.clear();
        mData.addAll(data);
        mHomeHeadSpecialAdapter.setData(data);
    }


    //设置商品流数据
    @Override
    public void addGoodsData(List<HomeHotGoodsBean.DataBean> data) {
        if (page == 1) {
            mDataBeen.clear();
        }
        page++;
        mDataBeen.addAll(data);
        Log.d(TAG, "mDataBeen---->" + mDataBeen.size());


        mHomeGoodsAdapter.setDataBeen(mDataBeen);
    }

    //没有数据
    @Override
    public void hasNodata() {
        mXrlvHomefrgm.setNoMore(true);
    }

    @Override
    public void LoadMoreFinish() {
        mXrlvHomefrgm.loadMoreComplete();
    }

    @Override
    public void RefreshFinish() {
        mPtrFrameLayout.refreshComplete();
    }

    @Override
    public void loadCarLogo(String carLogoUrl, boolean hasLogo) {

    }

    @Override
    public void showNetError() {
        mTvNetStateError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNetError() {
        mTvNetStateError.setVisibility(View.GONE);
    }

    //轮播点击事件
    @Override
    public void onItemClick(int position) {
        sendToServer("sy", "banner", "banner" + position);
        HtmlAct.unsealActivity(mActivity, mBannerBeen.get(position).getLink() + "?get_device_type=android" + "&user_id=" + UserData.getUserId()
                + "&user_token=" + UserData.getUserToken());
    }

    //功能区点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        sendToServer("sy", "yy", "yy" + (position + 3));
        //TODO 功能区的按钮的点击事件
        switch (position) {
            //保养
            case 0:
                CarWashAct.openActivity(mActivity, 2, false, mModuleBean.get(position).getIcon_name());
                break;
            //美容
            case 1:
                CarWashAct.openActivity(mActivity, 3, false, mModuleBean.get(position).getIcon_name());
                break;
            //违章查询
            case 2:
                jumpAct(IllegalHomeAct.class);
                break;
            //代驾
            case 3:
                String lat = CityBean.getLat();
                String lng = CityBean.getLng();
                HtmlAct.unsealActivity(mActivity, ApiRest.DAIJIA + "&lat=" + lat + "&lng=" + lng);
                break;
            case 4:
                BrandDetailsAct.openActivity(mActivity, "", mModuleBean.get(position).getGoods_cate_id(),
                        mModuleBean.get(position).getIcon_name());
                break;
            case 5:
                BrandDetailsAct.openActivity(mActivity, "", mModuleBean.get(position).getGoods_cate_id(),
                        mModuleBean.get(position).getIcon_name());
                break;
            case 6:
                BrandDetailsAct.openActivity(mActivity, "", mModuleBean.get(position).getGoods_cate_id(),
                        mModuleBean.get(position).getIcon_name());
                break;
            case 7:
                BrandDetailsAct.openActivity(mActivity, "", mModuleBean.get(position).getGoods_cate_id(),
                        mModuleBean.get(position).getIcon_name());
                break;
            default:
                HtmlAct.unsealActivity(mActivity, mModuleBean.get(position).getIcon_url() +
                        "?get_device_type=android" + "&user_id=" + UserData.getUserId()
                        + "&user_token=" + UserData.getUserToken());
                break;
        }
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    //下拉刷新触发
    @Override
    public void onRefreshBegin(final PtrFrameLayout frame) {
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                mHomePresenter.refreshData(page + "");
                mHomePresenter.loadheadData();
                mXrlvHomefrgm.setNoMore(false);
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {

    }

    //上拉加载
    @Override
    public void onLoadMore() {

        mHomePresenter.loadMoreData(page + "");
    }

    //热销商品的点击事件
    @Override
    public void onRecyclerItemClick(int position) {
        sendToServer("sy", "rm", "rm" + position);
        Intent intent = new Intent(mActivity, ProductDetailsAct.class);
        intent.putExtra("goodId", mDataBeen.get(position).getGoods_id());
        startActivity(intent);
    }

    //专题banner 的点击事件
    @Override
    public void onBannnerClick(int position) {
        sendToServer("sy", "zt", "zt" + position);
        HtmlAct.unsealActivity(mActivity, mData.get(position).getZt_url() + "?get_device_type=android");
    }

    //桌面图标的点击事件
    @OnClick({R.id.txt_homefrgm_address, R.id.iv_homefrgm_logo, R.id.iv_homepage_backtop, R.id.tv_homefrgm_state})
    void onViewClick(View view) {
        switch (view.getId()) {
            //地址
            case R.id.txt_homefrgm_address:
                sendToServer("sy", "dz", "dz");
                jumpAct(AddressAct.class);
                break;
            //在线客服
            case R.id.iv_homefrgm_logo:
                HtmlAct.unsealActivity(mActivity, ApiRest.KEFU);
                break;
            //回顶按钮
            case R.id.iv_homepage_backtop:
                mXrlvHomefrgm.smoothScrollToPosition(0);
                PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.5f, 1.0f);
                PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.5f, 1.0f);
                ValueAnimator valueAnimator = ObjectAnimator.ofPropertyValuesHolder(mIvBackTop, scaleX, scaleY);
                valueAnimator.start();

                break;
            case R.id.tv_homefrgm_state:
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                break;
        }
    }

    //界面跳转
    public void jumpAct(Class mc) {
        startActivity(new Intent(mActivity, mc));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //加油
            case R.id.iv_homehead_functionone:
                sendToServer("sy", "yy", "yy0");
                jumpAct(AddoOilAct.class);
                break;
            //洗车
            case R.id.iv_homehead_functiontwo:
                sendToServer("sy", "yy", "yy1");
                CarWashAct.openActivity(mActivity, 1, true, "特惠洗车");
                break;
            //  商场首页
            case R.id.iv_homehead_functionthr:
                sendToServer("sy", "yy", "yy2");
                jumpAct(ProductHomeAct.class);
                break;
        }
    }

    /**
     * @data 创建时间:2017/1/18
     * @author peng
     * @desc 数据埋点统计
     * @version
     */
    public void sendToServer(String data, String name, String remark) {
        new BuriedPoint().sendToServer(data, name, remark);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
