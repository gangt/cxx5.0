package com.xi6666.home.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.xi6666.common.CityBean;
import com.xi6666.common.LocationBean;
import com.xi6666.common.LocationService;
import com.xi6666.common.UserData;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.databean.HomeHeadBean;
import com.xi6666.databean.HomeHotGoodsBean;
import com.xi6666.databean.HomeSpecialBean;
import com.xi6666.eventbus.LocationEvent;
import com.xi6666.home.contract.HomeContract;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.xi6666.R.id.view;

/**
 * Created by Mr_yang on 2016/11/7.
 */

public class HomePresenterImpl implements HomeContract.Persenter {

    private static final String TAG = "HomePresenterImpl";
    private ApiRest mApiRest;
    private HomeContract.View mView;
    private Context mContext;

    public HomePresenterImpl(ApiRest apiRest) {
        mApiRest = apiRest;
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.mView = view;
    }

    @Override
    public void setContext(Context context) {
        this.mContext = context;
    }

    //获取汽车logo
    @Override
    public void loadCarLogo() {
        String userId = UserData.getUserId();
        mApiRest.getCarLogoIcon(userId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("logo", "e--->" + e);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            LogUtil.d("logo", "carlog--->" + string);
                            JSONObject jsonObject = new JSONObject(string);
                            if (jsonObject.getBoolean("success")) {
                                mView.loadCarLogo(jsonObject.getJSONObject("data").getString("user_car"), jsonObject.getBoolean("success"));
                            } else {
                                mView.loadCarLogo("", jsonObject.getBoolean("success"));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    @Override
    public void loadPopuData(String userId, String userToken) {
        mApiRest.addOilPopu(userId, userToken).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<AddOilPopuBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "e->" + e);
                    }

                    @Override
                    public void onNext(AddOilPopuBean addOilPopuBean) {
                        if (addOilPopuBean.isSuccess()) {
                            if (addOilPopuBean.getData().getIs_receive() == 1) {
                                mView.showAddoilPopu(addOilPopuBean.getData().getCoupon_list());
                            }
                        } else {
                            // view.showToast(addOilPopuBean.getInfo());
                        }
                    }
                });
    }


    @Override
    public void loadheadData() {
        //判断是否有缓存
        if (TextUtils.isEmpty(SpUtils.getString(mContext, "homeData"))) {
            loadHomeHeadNet();
        } else {
            loadHomeHeadCache();
            loadHomeHeadNet();
        }
        LoadSpecialData();
    }

    //获取专题数据
    private void LoadSpecialData() {
        mApiRest.getSpecial().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<HomeSpecialBean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeSpecialBean homeSpecialBean) {
                        mView.setSpecialData(homeSpecialBean.getData());

                    }
                });
    }

    //头部网络数据
    private void loadHomeHeadNet() {
        mApiRest.getHomeHeadData("android").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showNetError();
                        mView.RefreshFinish();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Log.d(TAG, "homeHeadData--->" + string);
                            SpUtils.setString(mContext, "homeData", string);
                            HomeHeadBean homeHeadBean = new Gson().fromJson(string, HomeHeadBean.class);
                            List<HomeHeadBean.IndexIconBean> index_icon = homeHeadBean.getIndex_icon();

                            if (homeHeadBean.getBanner() != null && homeHeadBean.getBanner().size() > 0) {
                                mView.setBanner(homeHeadBean.getBanner());
                            }

                            mView.setFuncationImage(homeHeadBean.getIndex_block());
                            mView.setModuleData(index_icon);
                            mView.hideNetError();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //头部缓存
    private void loadHomeHeadCache() {
        String homeData = SpUtils.getString(mContext, "homeData");
        HomeHeadBean homeHeadBean = new Gson().fromJson(homeData, HomeHeadBean.class);

        List<HomeHeadBean.IndexIconBean> index_icon = homeHeadBean.getIndex_icon();
        if (homeHeadBean.getBanner() != null && homeHeadBean.getBanner().size() > 0) {
            mView.setBanner(homeHeadBean.getBanner());
        }
        mView.setFuncationImage(homeHeadBean.getIndex_block());
        mView.setModuleData(index_icon);
    }

    //定位数据
    @Override
    public void location() {
        final LocationService locationService = new LocationService(mContext);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                switch (what) {
                    case LocationService.DATA:
                        //如果定位开启,就将定位关闭
                        if (locationService.isStarted()) {
                            locationService.stop();
                        }
                        String location = (String) msg.obj;
                        Log.d(TAG, "location--->" + location);
                        LocationBean locationBean = new Gson().fromJson(location, LocationBean.class);
                        Log.d(TAG, "CityBean.getCity()--->" + CityBean.getCity());
                        Log.d(TAG, "locationBean.getCity()--->" + locationBean.getCity());
                        //判断保存的数据是否相同的
                        if (TextUtils.equals(CityBean.getCity(), locationBean.getCity().substring(0, locationBean.getCity().length() - 1))
                                || TextUtils.isEmpty(CityBean.getCity())) {
                            //设置city数据
                            CityBean.setProvince(locationBean.getProvince());
                            CityBean.setLat(locationBean.getLatitude());
                            CityBean.setLng(locationBean.getLongitude());
                            CityBean.setCity(locationBean.getCity().substring(0, locationBean.getCity().length() - 1));
                            CityBean.setAddress(locationBean.getAddress());

                            mView.setAddress(locationBean.getCity().substring(0, locationBean.getCity().length() - 1));
                        } else {
                            mView.showSwitchAddressDialog(locationBean);
                        }
                        EventBus.getDefault().post(new LocationEvent("success"));
                        break;
                    case LocationService.NODATA:
                        //如果定位开启,就将定位关闭
                        if (locationService.isStarted()) {
                            locationService.stop();
                        }
                        Log.d(TAG, "nodata");
                        mView.showLocationSetting("无法获取您的位置信息,请打开网络和位置功能!");
                        EventBus.getDefault().post(new LocationEvent("error"));
                        break;
                }
                super.handleMessage(msg);
            }
        };
        //开启定位
        locationService.start(handler);
    }

    //加载更多数据
    @Override
    public void loadMoreData(String page) {
        mApiRest.getHomeHotGoods(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.LoadMoreFinish();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Log.d(TAG, "loadmore--->" + string);
                            HomeHotGoodsBean homeHotGoodsBean = new Gson().fromJson(string, HomeHotGoodsBean.class);


                            Log.d(TAG, "集合长度--->" + homeHotGoodsBean.getData().size());
                            mView.addGoodsData(homeHotGoodsBean.getData());
                            if (homeHotGoodsBean.getData().size() < 6) {
                                mView.hasNodata();
                            }
                            mView.LoadMoreFinish();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //刷新数据
    @Override
    public void refreshData(final String page) {
        mApiRest.getHomeHotGoods(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Log.d(TAG, "refresh--->" + string);
                            HomeHotGoodsBean homeHotGoodsBean = new Gson().fromJson(string, HomeHotGoodsBean.class);
                            mView.addGoodsData(homeHotGoodsBean.getData());
                            mView.RefreshFinish();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
