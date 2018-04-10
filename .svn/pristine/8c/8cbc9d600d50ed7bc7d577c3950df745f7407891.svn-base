package com.xi6666;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.platform.comapi.map.E;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xi6666.alipay.PayDemoActivity;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ToolBarBaseAct;
import com.xi6666.common.CircleTransform;
import com.xi6666.network.ApiRest;
import com.xi6666.network.RetrofitCallBackUtil;
import com.xi6666.utils.LoacationUtils;
import com.xi6666.utils.LogUtil;
import com.xi6666.view.EmptyLayout;
import com.xi6666.view.RandomTextView;
import com.xi6666.view.dialog.BaseDialog;
import com.xi6666.view.dialog.LoadingDialog;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends ToolBarBaseAct implements OnGetGeoCoderResultListener {
    private static final int LOCATION_REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";
    @BindView(R.id.btn_main_net)
    Button mBtnMainNet;
    @BindView(R.id.txt_main_test)
    RandomTextView mTxtMainTest;

    @BindView(R.id.iv_main_test)
    ImageView mImageView;
    @BindView(R.id.xrlv_main)
    XRecyclerView mXRecyclerView;

    @BindView(R.id.eml_main)
    EmptyLayout mEmptyLayout;

    /*@BindView(R.id.tl_pass)
    TextInputLayout TextInputLayout;*/
    @Inject
    BaseApplication mBaseApplication;
    @Inject
    Context mAppContext;
    @Inject
    ApiRest mApiRest;
    @Inject
    RetrofitCallBackUtil<E> mRetrofitCallBackUtil;
    @Inject
    Gson mGson;
    private int mCount;
    @BindView(R.id.ll_main)
    RelativeLayout mLayout;
    private IWXAPI mWxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mXRecyclerView.setAdapter(new Myada());
        mEmptyLayout.setErrorButtonClickListener(v -> {
            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
        });
        mEmptyLayout.setEmptyButtonClickListener(v -> {
            mEmptyLayout.hide();
        });

        mEmptyLayout.showError();
        mWxapi = WXAPIFactory.createWXAPI(this, "wx98d17f727ecaa444", true);
        mWxapi.registerApp("wx98d17f727ecaa444");
    }

    @Override
    public void setToolbarIconDo() {

    }

    private Toast mToast;

    public void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public void onBackPressed() {
        cancelToast();
        super.onBackPressed();
    }


    public GeoPoint getGeoPointBystr(String str) {
        GeoPoint gpGeoPoint = null;
        if (str != null) {
            Geocoder gc = new Geocoder(this, Locale.CHINA);
            List<Address> addressList = null;
            try {

                addressList = gc.getFromLocationName(str, 1);
                if (!addressList.isEmpty()) {
                    Address address_temp = addressList.get(0);
                    //计算经纬度
                    double Latitude = address_temp.getLatitude() * 1E6;
                    double Longitude = address_temp.getLongitude() * 1E6;

                    LogUtil.d(TAG, "lat------>" + Latitude);
                    LogUtil.d(TAG, "lng------>" + Longitude);


                    System.out.println("经度：" + Latitude);
                    System.out.println("纬度：" + Longitude);
                    //生产GeoPoint
                    gpGeoPoint = new GeoPoint((int) Latitude, (int) Longitude);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gpGeoPoint;
    }

    @Override
    public String setToolbarTitle() {
        return null;
    }

    @OnClick({R.id.btn_main_net, R.id.btn_main_alipay, R.id.btn_mian_wechat, R.id.btn_mian_location, R.id.btn_main_error,
            R.id.btn_main_isdebug, R.id.btn_mian_gotonext, R.id.btn_main_screen, R.id.btn_main_pic})
    void getNet(View view) {
        switch (view.getId()) {
            case R.id.btn_main_net:

                BaseDialog baseDialog = new BaseDialog(this);
                baseDialog.setTitle("测试代码");
                baseDialog.setLeftAndRight("再看看", "不看了");
                baseDialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
                    @Override
                    public void onLeftOnclick() {
                        Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                        baseDialog.dismiss();
                    }

                    @Override
                    public void onRightOncklick() {
                        Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                        baseDialog.dismiss();
                    }
                });
                baseDialog.show();
                break;
            case R.id.btn_main_alipay:
                new LoadingDialog().LoadingDialog(this).show();
                break;
            case R.id.btn_mian_wechat:

                getGeoPointBystr("上海");
                break;
            case R.id.btn_mian_location:
                requestLocation();
                break;
            case R.id.btn_main_error:
                mGson.excluder();
                break;
            case R.id.btn_main_isdebug:


                break;
            case R.id.btn_mian_gotonext:
                startActivity(new Intent(this, SecondAct.class));
                break;
            case R.id.btn_main_screen:
                LogUtil.d("main", BaseApplication.DIMEN_DPI + "\n" + BaseApplication.SCREEN_HEIGHT + "\n" + BaseApplication.SCREEN_WIDTH);
                mTxtMainTest.setText(BaseApplication.DIMEN_DPI + "\n" + BaseApplication.SCREEN_HEIGHT + "\n" + BaseApplication.SCREEN_WIDTH);
                break;
            case R.id.btn_main_pic:
                Toast.makeText(this, "加载图片", Toast.LENGTH_SHORT).show();
                String internetUrl = "http://i.imgur.com/DvpvklR.png";
                Glide.with(this)
                        .load(internetUrl).placeholder(R.mipmap.ic_launcher).centerCrop()
                        .transform(new CircleTransform(this))
                        .into(mImageView);
                break;
        }
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        Toast.makeText(this, "onGetGeoCodeResult", Toast.LENGTH_SHORT).show();

        if (geoCodeResult == null
                || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            // 没有检测到结果
            Toast.makeText(MainActivity.this, "抱歉，未能找到结果" + geoCodeResult.error,
                    Toast.LENGTH_LONG).show();
            return;
        }
        double latitude = geoCodeResult.getLocation().latitude;

        Toast.makeText(this, "latitude--->" + latitude, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        Toast.makeText(this, "onGetReverseGeoCodeResult", Toast.LENGTH_SHORT).show();
        if (reverseGeoCodeResult == null
                || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            // 没有检测到结果
            Toast.makeText(MainActivity.this, "抱歉，未能找到结果",
                    Toast.LENGTH_LONG).show();
            return;
        }
        ArrayList<PoiInfo> poiList = (ArrayList<PoiInfo>) reverseGeoCodeResult.getPoiList();
        double latitude = poiList.get(0).location.latitude;
        double latitude1 = poiList.get(0).location.longitude;
        Toast.makeText(this, "latitude--->" + latitude, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "latitude-->" + latitude);
        Log.d(TAG, "latitude1-->" + latitude1);

    }

    private void location() {
        final LoacationUtils loc = new LoacationUtils(MainActivity.this);
        Handler hander = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                switch (what) {
                    case 6:
                        String obj = (String) msg.obj;
                        Log.d("MainActiviy", "定位去获取到的坐标的值是" + obj);
                        mTxtMainTest.setText(obj);
                        //判断定位是否还在后台运行
                        if (loc.isStarted()) {
                            loc.stop();
                        }
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
        loc.start(hander);
    }

    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //无权限去申请权限
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);
        } else {
            //有权限直接做操作.
            location();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                location();
            }
        }
    }

    private void alipay() {
        startActivity(new Intent(this, PayDemoActivity.class));
    }

    private void netWork() {
        mApiRest.getBrand().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    mTxtMainTest.setText(string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void tuneUpWechatPay(String param) {
        Log.d("WechatPayUtils", "支付参数是==" + param);
       /* WeChatPayBean weChatPayBean = mGson.fromJson(param, WeChatPayBean.class);
        String appid = weChatPayBean.appid;*/
        //  Log.d("pay", "appidhah==" + appid);
        PayReq req;
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp("wx98d17f727ecaa444");
        req = new PayReq();
        req.appId = "wx98d17f727ecaa444";
        req.partnerId = "1266672501";
        req.prepayId = "wx201610121619407f7243c8b00918245321";
        req.packageValue = "Sign=WXPay";
        req.nonceStr = "9bPsPNhMbFoTxqsU";
        req.timeStamp = "1476260380";
        req.sign = "3915359165AB477A97E216CFFCB188DB";
        msgApi.sendReq(req);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag", "main on des");
        Toast.makeText(this, "manin界面被关闭了", Toast.LENGTH_SHORT).show();
    }


    public class Myada extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new myViewHo(getLayoutInflater().inflate(R.layout.xrectext, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class myViewHo extends RecyclerView.ViewHolder {

            public myViewHo(View itemView) {
                super(itemView);
            }
        }
    }

}
