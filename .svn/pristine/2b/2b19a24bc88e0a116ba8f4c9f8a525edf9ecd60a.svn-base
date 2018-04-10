package com.xi6666.cityaddress.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ComponetBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.cityaddress.adapter.AddressAdapter;
import com.xi6666.cityaddress.di.AddressModule;
import com.xi6666.cityaddress.di.DaggerAddressComponet;
import com.xi6666.cityaddress.presenter.AddressPresenterImpl;
import com.xi6666.databean.AddressBean;
import com.xi6666.cityaddress.contract.AddressContract;
import com.xi6666.common.CityBean;
import com.xi6666.common.LocationBean;
import com.xi6666.common.LocationService;
import com.xi6666.common.OnRecyclerItemClickListener;
import com.xi6666.eventbus.AddressEvent;
import com.xi6666.view.EmptyLayout;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * @author peng
 * @data 创建时间:2016/11/11
 * @desc 省份选择
 */
public class AddressAct extends ComponetBaseAct implements
        AddressContract.View,
        OnRecyclerItemClickListener, View.OnClickListener {
    private static final String TAG = "AddressAct";
    @BindView(R.id.txt_address_location)
    TextView mTxtAddressLocation;
    @BindView(R.id.rl_address_location)
    RelativeLayout mRlAddressLocation;
    @BindView(R.id.rl_address_prover)
    RelativeLayout mRlAddressProver;
    @BindView(R.id.rl_address_fail)
    RelativeLayout mRlAddressFail;
    @BindView(R.id.rv_address)
    RecyclerView mRvAddress;
    @BindView(R.id.el_address)
    EmptyLayout mEmptyLayout;

    @Inject
    AddressPresenterImpl mAddressPresenter;


    private List<AddressBean.DataBean> mDataBeen = new ArrayList<>();

    private AddressAdapter mAddressAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);

        mEmptyLayout.setErrorButtonClickListener(this);

        //设置
        mRvAddress.setLayoutManager(new LinearLayoutManager(this));
        mAddressAdapter = new AddressAdapter();
        mAddressAdapter.setDataBeen(mDataBeen);
        mRvAddress.setAdapter(mAddressAdapter);
        mAddressAdapter.setOnRecyclerItemClickListener(this);

        //注入
        DaggerAddressComponet.builder().apiModule(new ApiModule((BaseApplication) getApplication())).
                addressModule(new AddressModule()).build().Inject(this);
        mAddressPresenter.attachView(this);
        mAddressPresenter.setContext(this);
        mAddressPresenter.loadData();

        //获取定位权限
        if (!RxPermissions.getInstance(this).isGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            showLocationPower();
        } else {
            mAddressPresenter.location();
        }

        mTxtAddressLocation.setText(CityBean.getAddress());
    }


    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "选择地区";
    }

    //展示地址
    @Override
    public void showLocation() {
        mRlAddressLocation.setVisibility(View.VISIBLE);
        mRlAddressProver.setVisibility(View.GONE);
        mRlAddressFail.setVisibility(View.GONE);

    }

    //展示没有定位权限
    @Override
    public void showLocationPower() {
        mRlAddressLocation.setVisibility(View.GONE);
        mRlAddressProver.setVisibility(View.VISIBLE);
        mRlAddressFail.setVisibility(View.GONE);
    }

    //展示定位错误
    @Override
    public void showLocationFial() {
        mRlAddressLocation.setVisibility(View.GONE);
        mRlAddressProver.setVisibility(View.GONE);
        mRlAddressFail.setVisibility(View.VISIBLE);
    }

    @Override
    public void addData(List<AddressBean.DataBean> dataBeen) {
        Log.d(TAG, "mDataBeen--->" + mDataBeen.size());
        mDataBeen.addAll(dataBeen);
        mAddressAdapter.setDataBeen(mDataBeen);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        mEmptyLayout.showError();
    }

    @Override
    public void hideError() {
        mEmptyLayout.hide();
    }

    @Override
    public void showLoding() {
        mEmptyLayout.showLoading();
    }

    //点击事件
    @OnClick({R.id.rl_address_location, R.id.rl_address_prover, R.id.rl_address_fail})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.rl_address_location:
                //关闭界面设置为当前定位
                locationPermissions();
                break;
            case R.id.rl_address_prover:
                //去设置界面
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                break;
            case R.id.rl_address_fail:
                //重新定位
                locationPermissions();
                break;
        }
    }

    private void locationPermissions() {
        RxPermissions.getInstance(this).request(Manifest.permission.ACCESS_FINE_LOCATION).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    startLocation();
                } else {
                    Toast.makeText(AddressAct.this, "为了能够正常使用请给予相关的权限!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRecyclerItemClick(int position) {
        CityBean.setProvince(mDataBeen.get(position).getRegion_name());
        Log.d(TAG, "mDataBeen--->" + mDataBeen.size());
        Intent intent = new Intent(this, CityAddressAct.class);
        //  List<AddressBean.DataBean.CitysBean> citys = mDataBeen.get(position).getCitys();
        intent.putExtra("data", (Serializable) mDataBeen.get(position).getCitys());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonError:
                mAddressPresenter.loadData();
                break;
        }
    }

    /**
     * 开始定位
     */
    @SuppressLint("HandlerLeak")
    private void startLocation() {
        final LocationService locationService = new LocationService(this);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                switch (what) {
                    case LocationService.DATA:
                        String location = (String) msg.obj;
                        Log.d(TAG, "location--->" + location);
                        LocationBean locationBean = new Gson().fromJson(location, LocationBean.class);
                        String city = locationBean.getCity().substring(0, locationBean.getCity().length() - 1);
                        CityBean.setAddress(locationBean.getAddress());
                        CityBean.setProvince(locationBean.getProvince());
                        CityBean.setLat(locationBean.getLatitude());
                        CityBean.setLng(locationBean.getLongitude());
                        CityBean.setCity(city);
                        CityBean.setRegionId("");
                        EventBus.getDefault().post(new AddressEvent(city));
                        Toast.makeText(AddressAct.this, "已定位到当前位置!", Toast.LENGTH_SHORT).show();
                        //如果定位开启,就将定位关闭
                        if (locationService.isStarted()) {
                            locationService.stop();
                        }
                        if (mRlAddressLocation.getVisibility() == View.VISIBLE) {
                            finish();
                        }
                        if (mRlAddressFail.getVisibility() == View.VISIBLE) {
                            showLocation();
                            mTxtAddressLocation.setText(CityBean.getAddress());
                        }
                        break;
                    case LocationService.NODATA:
                        Log.d(TAG, "nodata");
                        showLocationFial();
                        showToast("定位失败!");
                        //如果定位开启,就将定位关闭
                        if (locationService.isStarted())
                            locationService.stop();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        //开启定位
        locationService.start(handler);
    }
}
