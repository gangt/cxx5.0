package com.xi6666.car.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.app.ActManager;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.car.SelectCarTypeAct;
import com.xi6666.car.adapter.MyCarAdapter;
import com.xi6666.car.bean.MyCarBean;
import com.xi6666.car.mp.MyCarContract;
import com.xi6666.car.mp.MyCarModel;
import com.xi6666.car.mp.MyCarPresenter;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.view.CxxErrorView;
import com.xi6666.cardbag.view.CardBagAct;
import com.xi6666.eventbus.CardNumEvent;
import com.xi6666.eventbus.ChoiceDefaultCarEvent;
import com.xi6666.eventbus.ChoiceDefaultOilCardEvent;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 创建者 sunsun
 * 时间 16/11/15 上午11:09.
 * 个人公众号 ardays
 * <p>
 * 我的爱车--- 首页
 */

public class MyCarActivity extends BaseToolbarView<MyCarPresenter, MyCarModel>
        implements MyCarContract.View {


    private static final String TAG = "MyCarActivity";
    /**
     * 我的爱车跳转 码
     */
    public static final int MY_CAR_CODE = 1221;
    /**
     * 我的爱车数据(key)
     */
    public static final String MY_DEFAULT_CAR = "com.xi6666.mydefaultcar";
    /**
     * 选择车型(key)
     */
    public static final String SEL_CAR_TYPE = "com.xi6666.sel_car";


    XRecyclerView mMyCarXrv;    //我的爱车列表
    TextView mMyCarNotTv;       //没有我的爱车时候显示
    Button mAddCarBtn;  //添加我的爱车
    CxxErrorView mErrorView;    //网络失败


    MyCarAdapter mMyCarAdapter; //我的爱车适配器


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shuaxin(ChoiceDefaultCarEvent addressEvent) {
        showLoading();
        //请求我的爱车列表
        mPersenter.requestMyCarList();
    }


    @Override
    public String title() {
        return "我的爱车";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_my_car;
    }

    @Override
    public void setUp(View view) {
        EventBus.getDefault().register(this);
        initView(view);
        initAddBtn();
        initMyCarList();
        showLoading();
        //请求我的爱车列表
        mPersenter.requestMyCarList();
    }

    /**
     * 初始化控件
     */
    private void initView(View view) {
        mMyCarXrv = (XRecyclerView) view.findViewById(R.id.my_car_xrv);
        mMyCarNotTv = (TextView) view.findViewById(R.id.my_car_not_tv);
        mAddCarBtn = (Button) view.findViewById(R.id.my_car_add_car_btn);
        mErrorView = (CxxErrorView) view.findViewById(R.id.my_car_error_view);
    }

    /**
     * 初始化点击事件
     */
    private void initAddBtn() {
        //添加我的爱车点击事件
        mAddCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 前往去 "添加我的爱车"
                SelectCarTypeAct.openActivity(getActivity(), true);
            }
        });
;
    }

    @Override
    public void onToolbarRightClick(View view) {
        super.onToolbarRightClick(view);
    }

    private void initMyCarList() {
        mMyCarAdapter = new MyCarAdapter(this);
        mMyCarXrv.setLayoutManager(new LinearLayoutManager(this));
        mMyCarXrv.setAdapter(mMyCarAdapter);
        mMyCarXrv.setPullRefreshEnabled(false);
        //我的爱车回调方式
        mMyCarAdapter.setOnMyCardClickListener(new MyCarAdapter.OnMyCardClickListener() {
            @Override
            public void onItemDeleteId(String c_id, int position) {
                showLoading();
                mPersenter.requestDelCar(c_id, position);
            }

            @Override
            public void onDefaultCar(MyCarBean.DataBean bean, int position) {
                mPersenter.requestSetDefaultCar(bean.car_id, bean.car_brand_id, bean.car_chexing_id, bean.car_pailiang_id, bean.car_nianfen_id, position);
            }

            @Override
            public void onItemClick(MyCarBean.DataBean bean) {
                AddCarSuccessAct.openActivity(getActivity(), bean);
            }
        });

        mErrorView.setOnErrorClickListener(v -> {
            mPersenter.requestMyCarList();
        });
    }


    //                      @网络数据返回
    @Override
    public void returnCarList(MyCarBean bean) {
        hiddenLoading();
        mMyCarXrv.refreshComplete();
        mErrorView.setVisibility(View.GONE);
        if (bean.data.size() == 0) {
            mMyCarNotTv.setVisibility(View.VISIBLE);
            mMyCarXrv.setVisibility(View.GONE);
        } else {
            mMyCarNotTv.setVisibility(View.GONE);
            mMyCarXrv.setVisibility(View.VISIBLE);
            mMyCarAdapter.addDatas(bean.data);
        }
    }

    @Override
    public void returnCarResult(BaseBean bean, int position) {
        if (bean.success) {
            mMyCarAdapter.setDefaultPosition(position);
            EventBus.getDefault().post(new ChoiceDefaultCarEvent("wfwfwf"));
        }else{
            make(bean.info);
        }
    }

    @Override
    public void returnDelResult(BaseBean bean, int position) {
        hiddenLoading();
        if (bean.success) {
            mMyCarAdapter.removeItem(position);
        }
    }

    @Override
    public void returnError(String error) {
        hiddenLoading();
        make(error);
    }

    @Override
    public void returnListNetError() {
        hiddenLoading();
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity) {
        Intent intent = new Intent(activity, MyCarActivity.class);
        activity.startActivity(intent);
    }
}
