package com.xi6666.cityaddress.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xi6666.R;
import com.xi6666.address.fragment.mvp.bean.CityGeographyBean;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ComponetBaseAct;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.cityaddress.adapter.AddressCityAdapter;
import com.xi6666.cityaddress.di.AddressModule;
import com.xi6666.cityaddress.di.DaggerAddressComponet;
import com.xi6666.cityaddress.presenter.AddressPresenterCityImpl;
import com.xi6666.databean.AddressBean;
import com.xi6666.cityaddress.contract.AddressContract;

import com.xi6666.common.CityBean;
import com.xi6666.common.OnRecyclerItemClickListener;
import com.xi6666.eventbus.AddressEvent;
import com.xi6666.main.view.MainAct;
import com.xi6666.network.ApiRest;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.view.EmptyLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityAddressAct extends ComponetBaseAct implements View.OnClickListener, AddressContract.CityView, OnRecyclerItemClickListener {

    private static final String TAG = "CityAddressAct";
    @BindView(R.id.rl_city_address)
    RecyclerView mRlCityAddress;
    @BindView(R.id.el_city_address)
    EmptyLayout mElCityAddress;

    @Inject
    AddressPresenterCityImpl mAddressPresenterCity;

    @Inject
    ApiRest mApiRest;


    private AddressCityAdapter mAddressAdapter;
    List<AddressBean.DataBean.CitysBean> mDataBeen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_address);
        ButterKnife.bind(this);

        List<AddressBean.DataBean.CitysBean> data = (List<AddressBean.DataBean.CitysBean>) getIntent().getSerializableExtra("data");
        mDataBeen.addAll(data);
        mRlCityAddress.setLayoutManager(new LinearLayoutManager(this));
        mAddressAdapter = new AddressCityAdapter();
        mAddressAdapter.setDataBeen(mDataBeen);
        mRlCityAddress.setAdapter(mAddressAdapter);
        mAddressAdapter.setOnRecyclerItemClickListener(this);
        mElCityAddress.setErrorButtonClickListener(this);

        //注入
        DaggerAddressComponet.builder().apiModule(new ApiModule((BaseApplication) getApplication())).
                addressModule(new AddressModule()).build().Inject(this);
        mAddressPresenterCity.attachView(this);
        mAddressPresenterCity.setApiRest(mApiRest);


        //   mAddressPresenterCity.loadData(mProvinceId);

    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "选择城市";
    }

    @Override
    public void onClick(View v) {
        mElCityAddress.hide();
        //  mAddressPresenterCity.loadData(mProvinceId);
    }

    @Override
    public void showLoading() {
        mElCityAddress.showLoading();
    }

    @Override
    public void hideStateView() {
        mElCityAddress.hide();
    }

    @Override
    public void showError() {
        mElCityAddress.showError();
    }

    @Override
    public void addData(List<AddressBean.DataBean> dataBeen) {
        // mDataBeen.addAll(dataBeen);
        LogUtil.d(TAG, "dataBeen.size----->" + dataBeen.size());
        // mAddressAdapter.setDataBeen(mDataBeen);
    }

    @Override
    public void onRecyclerItemClick(int position) {
        CityBean.setCity(mDataBeen.get(position).getRegion_name());
        CityBean.setRegionId(mDataBeen.get(position).getRegion_id());
        //获取经纬度
        List<String> location = CityGeographyBean.getLocation(mDataBeen.get(position).getRegion_name());
        if (!Utils.isEmpty(location)) {
            CityBean.setLng(location.get(0));
            CityBean.setLat(location.get(1));
        }
        EventBus.getDefault().post(new AddressEvent(mDataBeen.get(position).getRegion_name()));

        startActivity(new Intent(this, MainAct.class));
    }
}
