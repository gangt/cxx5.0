package com.xi6666.carWash.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.view.custom.BottomMoenyView;
import com.xi6666.carWash.mvp.bean.DetermineOrderBean;
import com.xi6666.carWash.view.mvp.DetermineOrderContract;
import com.xi6666.carWash.view.mvp.DetermineOrderModel;
import com.xi6666.carWash.view.mvp.DetermineOrderPresenter;
import com.xi6666.utils.LogUtil;
import com.xi6666.view.dialog.LoadingDialog;

/**
 * 创建者 sunsun
 * 时间 16/11/2 下午3:08.
 * 个人公众号 ardays
 */

public class DetermineOrderAct extends BaseToolbarView<DetermineOrderPresenter, DetermineOrderModel>
        implements BottomMoenyView.OnBottomViewListener, DetermineOrderContract.View {

    public Dialog loadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog().LoadingDialog(getContext());
        }
        return mLoadingDialog;
    }

    String mOrderId;

    @Override
    public String title() {
        return "确认订单";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_determine_order;
    }


    TextView mTitleTv;  //服务标题
    TextView mAddressTv; //地址
    TextView mOrderNumberTv;    //订单编号
    TextView mOrderTimeTv;  //订单时间
    TextView mOrderTypeTv;  //订单类型
    TextView mOrderMoenyTv; //订单价格
    BottomMoenyView mBottomPayView; //底部去支付View
    Dialog mLoadingDialog;


    String mServiceId;  //服务ID
    String mAddressStr; //门店地址


    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initClick();
        loadingDialog().show();
        mPersenter.requestGenerateOrder(mServiceId);
    }

    /**
     * 初始化View
     *
     * @param view
     */
    private void initView(View view) {
        mTitleTv = (TextView) view.findViewById(R.id.determine_order_server_title_tv);
        mAddressTv = (TextView) view.findViewById(R.id.determine_order_shop_title_tv);
        mOrderNumberTv = (TextView) view.findViewById(R.id.determine_order_order_number_tv);
        mOrderTimeTv = (TextView) view.findViewById(R.id.determine_order_order_time_tv);
        mOrderTypeTv = (TextView) view.findViewById(R.id.determine_order_order_type_tv);
        mOrderMoenyTv = (TextView) view.findViewById(R.id.determine_order_order_moeny_tv);
        mBottomPayView = (BottomMoenyView) view.findViewById(R.id.determine_order_pay);
    }

    /**
     * 初始化值
     */
    private void initValue() {
        mServiceId = getIntent().getStringExtra("service_id");
        mAddressStr = getIntent().getStringExtra("address");
    }


    /**
     * 初始化点击
     */
    private void initClick() {
        mBottomPayView.setOnBottomViewListener(this);
    }



    /*                             点击事件                             */

    /**
     * 点击"去支付"的回调
     */
    @Override
    public void onRightClick(View view) {
        if (TextUtils.isEmpty(mOrderId)) {
            make("订单ID错误，请重新打开");
        } else {
            CashierAct.openActivity(getActivity(), mOrderId);
            finish();
        }
    }


    //                              @返回
    @Override
    public void returnGenerateOrder(DetermineOrderBean bean) {
        loadingDialog().dismiss();
        hiddenErrorView();
        make(bean.info);
        if (bean.success) {
            DetermineOrderBean.DataBean data = bean.data;
            LogUtil.e("TAG", "data-->" + data.toString());
            mTitleTv.setText(data.cate_name + " - " + data.service_name); //服务标题
            mAddressTv.setText(data.shop_name);    //写入地址
            mOrderNumberTv.setText(data.order_sn);  //服务订单
            mOrderTimeTv.setText(data.add_datetime);    //订单地址
            mOrderTypeTv.setText(data.cate_name + " - " + data.service_name);    //写入服务分类
            mOrderMoenyTv.setText("¥" + data.order_amount);     //写入价格
            mBottomPayView.setMoeny(data.order_amount);         //写入价格
            mOrderId = data.order_sn;
        }

    }

    @Override
    public void returnNetError() {
        loadingDialog().hide();
        showErrorView();
        errorClick(v -> {
            loadingDialog().show();
            mPersenter.requestGenerateOrder(mServiceId);
        });
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Context activity, String service_id) {
        Intent intent = new Intent(activity, DetermineOrderAct.class);
        intent.putExtra("service_id", service_id);
        activity.startActivity(intent);
    }

}
