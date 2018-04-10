package com.xi6666.illegal.see.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseAct;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.eventbus.IllegaCardChangeEvent;
import com.xi6666.illegal.see.bean.GenerateRedeemCodeBean;
import com.xi6666.illegal.see.mvp.RedeemCodeContract;
import com.xi6666.illegal.see.mvp.RedeemCodeModel;
import com.xi6666.illegal.see.mvp.RedeemCodePresenter;
import com.xi6666.view.dialog.BaseDialog;

import org.greenrobot.eventbus.EventBus;

/**
 * 创建者 sunsun
 * 时间 2017/2/7 上午9:56.
 * 个人公众号 ardays
 *
 *  违章卡 - 兑换码页面
 */

public class RedeemCodeAct extends BaseToolbarView<RedeemCodePresenter, RedeemCodeModel>
        implements RedeemCodeContract.View{

    Button mConfirmBtn;  //确定兑换
    EditText mRedeemCodeEt; //兑换码输入框
    TextView mRedeemCodeErrorTv;    //兑换码错误提示

    @Override
    public String title() {
        return "兑换码";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_redeem_code;
    }

    @Override
    public void setUp(View view) {
        initView(view);

    }

    /**
     * 初始化View
     */
    private void initView(View view) {
        mConfirmBtn = (Button) view.findViewById(R.id.reddem_code_btn);
        mRedeemCodeEt = (EditText) view.findViewById(R.id.redeem_code_et);
        mRedeemCodeErrorTv = (TextView) view.findViewById(R.id.redeem_code_error_tv);


        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                mPersenter.requestGenerateRedeemCode(mRedeemCodeEt.getText().toString());
            }
        });
    }

    @Override
    public void returnGenerateRedeemCode(GenerateRedeemCodeBean bean) {
        hiddenLoading();
        if(bean.success){
            EventBus.getDefault().post(new IllegaCardChangeEvent("fw"));
            new AlertDialog.Builder(getContext())
                    .setTitle("兑换成功!")
                    .setMessage("恭喜你，获得一张违章卡，可在\"卡卷包-违章卡\"中查看。")
                    .setNegativeButton("确定", (d,o) -> {
                        finish();
                    })
                    .show();
        }else{
            mRedeemCodeErrorTv.setText(bean.info);
            mRedeemCodeErrorTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void generateError() {
        make("网络超时，请在网络良好情况下重试");
    }


    /**
     *  BY:记得注册Activity
     */
    public static final void openActivity(Activity activity){
        Intent intent = new Intent(activity,RedeemCodeAct.class);
        activity.startActivity(intent);
    }

}
