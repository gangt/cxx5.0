package com.xi6666.store;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.common.CityBean;
import com.xi6666.store.utils.DrivingRouteOverlay;
import com.xi6666.store.utils.OverlayManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建人 孙孙啊i
 * 时间 2016/6/17 0017.
 * 功能 导航地图功能
 */
public class NaviActivity extends BaseToolbarView implements
        BaiduMap.OnMapClickListener, OnGetRoutePlanResultListener, BaiduMap.OnMapLoadedCallback {

    MapView mMapView;
    Button mNaviBtn;
    TextView mAddressTv;

    public static final String NAVI_LNG = "com.xi666.NAVI_LNG";
    public static final String NAVI_LAT = "com.xi666.NAVI_LAT";
    public static final String ADDRESS = "com.xi666.ADDRESS";


    private BaiduMap mBaiduMap;
    private RoutePlanSearch mRoutePlanSearch;
    private RouteLine route = null;
    private OverlayManager routeOverlay = null;
    private double startLa;
    private double startLn;
    private double endLa;
    private double endLn;
    private String address;
    private LatLng mLatLng;
    private LatLng mLatend;

    @Override
    public int mainResId() {
        return 0;
    }

    @Override
    public int backgroundResId() {
        return R.layout.activity_navi;
    }

    @Override
    public void uiCreate() {
        SDKInitializer.initialize(getApplicationContext());
        super.uiCreate();
    }

    @Override
    public void setUp(View view) {
        mMapView = (MapView) view.findViewById(R.id.mapview_navi);
        mNaviBtn = (Button) view.findViewById(R.id.navi_btn);
        mAddressTv = (TextView) view.findViewById(R.id.navi_address_tv);

        mNaviBtn.setOnClickListener(v -> {
            check(v);
        });
        initi();
    }

    @Override
    public String title() {
        return null;
    }

    /**
     * 在onCreate之前
     */


    void initi() {
        //更改右边图标
        setToolbarLeftIcon(R.mipmap.ic_black_close);
        setToolbarColorTransparent(0);

        mBaiduMap = mMapView.getMap();
        //规划路径的坐标值
        if (!TextUtils.isEmpty(CityBean.getLat())) {
            startLa = Double.parseDouble(CityBean.getLat());
            startLn = Double.parseDouble(CityBean.getLng());
        }
        endLa = Double.parseDouble(getIntent().getStringExtra(NAVI_LAT));
        endLn = Double.parseDouble(getIntent().getStringExtra(NAVI_LNG));
        address = getIntent().getStringExtra(ADDRESS);
        mAddressTv.setText(address);
        mBaiduMap.setOnMapClickListener(this);
        mBaiduMap.setOnMapLoadedCallback(this);
    }

    /**
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    /**
     * 线路规划
     */
    private void planLine() {
        // 重置浏览节点的路线数据
        route = null;
        mBaiduMap.clear();
        // 设置起终点信息，对于tranist search 来说，
        mLatLng = new LatLng(startLa, startLn);
        mLatend = new LatLng(endLa, endLn);

        PlanNode planNode = PlanNode.withLocation(mLatLng);
        PlanNode planNode2 = PlanNode.withLocation(mLatend);

        mRoutePlanSearch = RoutePlanSearch.newInstance();
        mRoutePlanSearch.drivingSearch((new DrivingRoutePlanOption()).from(planNode).to(planNode2));
        mRoutePlanSearch.setOnGetRoutePlanResultListener(this);
    }

    public void check(View view) {
        if (isAvilible(this, "com.baidu.BaiduMap")) {//传入指定应用包名
            startBaiduNavi();
        } else {
            startAppShop();

        }
    }

    /**
     * 打开百度导航
     */
    private void startBaiduNavi() {
        // 构建 导航参数// 构建 导航参数
        NaviParaOption para = new NaviParaOption();
        para.startPoint(mLatLng);
        para.endPoint(mLatend);
        //打开百度导航
        BaiduMapNavigation.openBaiduMapNavi(para, this);
    }

    /**
     * 打开应用商城去下载app
     */
    private void startAppShop() {
        //显示手机上所有的market商店
        Toast.makeText(NaviActivity.this, "您还未安装百度地图!", Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    /**
     * 打开当前activity,记得注册哦！！！
     */
    public static void openActivity(Context mActivity, String lat, String lng,String address) {
        Intent intent = new Intent(mActivity, NaviActivity.class);
        intent.putExtra(NAVI_LAT, lat);
        intent.putExtra(NAVI_LNG, lng);
        intent.putExtra(ADDRESS, address);
        mActivity.startActivity(intent);
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            make("抱歉，未找到结果");
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            // result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            route = result.getRouteLines().get(0);
            DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
            routeOverlay = overlay;
            mBaiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }
    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    /**
     * 判断是否安装某一包名
     *
     * @param context
     * @param packageName
     * @return
     */
    private boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    @Override
    public void onMapLoaded() {
        planLine();
    }
}
