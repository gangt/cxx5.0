package com.xi6666.illegal.see.view;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.illegal.see.bean.IllegalCardStatusBean;
import com.xi6666.illegal.see.bean.RedeemCodeBean;
import com.xi6666.illegal.see.mvp.IllegalRedeemCodeContract;
import com.xi6666.illegal.see.mvp.IllegalRedeemCodeModel;
import com.xi6666.illegal.see.mvp.IllegalRedeemCodePresenter;

/**
 * 创建者 sunsun
 * 时间 2017/2/7 上午10:50.
 * 个人公众号 ardays
 * <p>
 * 违章卡查看 - 违章卡兑换码
 */

public class IllegalRedeemCodeAct extends BaseToolbarView<IllegalRedeemCodePresenter, IllegalRedeemCodeModel>
        implements IllegalRedeemCodeContract.View {

    TextView mReddemCodeTv; //兑换码框框
    Button mReddemCodeBtn;  //生成兑换码
    TextView mCardNumberTv; //卡号
    TextView mCardTypeTv;     //类型
    TextView mCardTimeTv;   //购买日期


    String mCardId; //违章卡的id

    @Override
    public String title() {
        return "违章卡兑换码";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_illgal_reddem_code;
    }

    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initHttp();
    }

    /**
     * 测试数据
     */
    private void initTest() {
        mCardId = "1";
    }


    /**
     * 初始化绑定控件
     */
    private void initView(View view) {
        mReddemCodeTv = (TextView) view.findViewById(R.id.illegal_reddem_code_tv);
        mReddemCodeBtn = (Button) view.findViewById(R.id.illegal_reddem_code_btn);
        mCardNumberTv = (TextView) view.findViewById(R.id.illegal_reddem_code_number_tv);
        mCardTypeTv = (TextView) view.findViewById(R.id.illegal_reddem_code_type_tv);
        mCardTimeTv = (TextView) view.findViewById(R.id.illegal_reddem_code_date_tv);


    }

    /**
     * 初始化值
     */
    private void initValue() {
        mCardId = getIntent().getStringExtra("card_id");
    }


    private void initHttp() {
        showLoading();
        mPersenter.requestIllegalCardStatus(mCardId);
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, String card_id) {
        Intent intent = new Intent(activity, IllegalRedeemCodeAct.class);
        intent.putExtra("card_id", card_id);
        activity.startActivity(intent);
    }

    @Override
    public void returnIllegalCardStatus(IllegalCardStatusBean bean) {
        hiddenLoading();
        hiddenErrorView();
        if (bean.success) {
            IllegalCardStatusBean.DataBean data = bean.data;
            //写入卡号
            mCardNumberTv.setText("卡号:" + data.card_number);
            //写入类型
            String type = data.surplus_number.equals("6") ? "6个月套餐--6次--¥" : "3个月套餐--3次--¥";
            type +=  data.card_amount;
            mCardTypeTv.setText("类        型 :  " + type);
            //购买日期
            mCardTimeTv.setText("购买日期 :  " + data.add_datetime);
            //兑换码
            mReddemCodeTv.setText(data.redeem_code);
            setRedeemBtnClick(TextUtils.isEmpty(data.redeem_code));

            //判断兑换码是否为空
            if (TextUtils.isEmpty(data.redeem_code)) {
                mReddemCodeBtn.setOnClickListener(v -> {
                    showLoading();
                    mPersenter.requestRedeemCode(mCardId);
                });
            }
        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnRedeemCode(RedeemCodeBean bean) {
        hiddenLoading();
        if (bean.success) {
            setRedeemBtnClick(false);
            mReddemCodeTv.setText(bean.data);
        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnError() {
        showErrorView();
        errorClick(v -> {
            showLoading();
            mPersenter.requestIllegalCardStatus(mCardId);
        });
    }

    @Override
    public void returnRedeemCodeError() {
        hiddenLoading();
        make("网络超时，请在网络良好情况下重试");
    }


    /**
     * 按钮状态
     * @param isClick 是否可以点击
     */
    public void setRedeemBtnClick(boolean isClick) {
        int color = getResources().getColor(isClick ? R.color.text_green : R.color.txthomeGoodsOld);
        mReddemCodeBtn.setBackgroundColor(color);
        mReddemCodeBtn.setClickable(isClick);

        //复制功能
        mReddemCodeTv.setOnClickListener(v -> {
            if (!isClick) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(mReddemCodeTv.getText());
                make("复制成功");
            }
        });
    }
}
