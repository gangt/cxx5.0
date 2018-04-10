package com.xi6666.illegal.see.view;

import android.app.Activity;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.eventbus.UsageRecordEvent;
import com.xi6666.illegal.see.bean.CancelOrderBean;
import com.xi6666.illegal.see.bean.UsageDetailsBean;
import com.xi6666.illegal.see.mvp.UsageDetailsContract;
import com.xi6666.illegal.see.mvp.UsageDetailsModel;
import com.xi6666.illegal.see.mvp.UsageDetailsPresenter;
import com.xi6666.order.other.Utils;
import com.xi6666.view.dialog.BaseDialog;

import org.greenrobot.eventbus.EventBus;

/**
 * 创建者 sunsun
 * 时间 2017/2/8 上午10:17.
 * 个人公众号 ardays
 * 违章卡 - "使用详情"
 */

public class UsageDetailsAct extends BaseToolbarView<UsageDetailsPresenter, UsageDetailsModel>
        implements UsageDetailsContract.View {
    @Override
    public String title() {
        return "使用详情";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_usage_detials;
    }

    View mStatusRv; //状态布局
    TextView mStatusTv;    //审核状态
    TextView mStatusContentTv;  //审核状态内容
    TextView mOrderSnTv;     //详情编号
    TextView mAddressTv;    //违章地址
    TextView mDetailsTv;    //违章信息
    TextView mTimeTv;       //使用时间
    TextView mFineTv;   //罚款
    TextView mPointsTv; //扣分
    TextView mCardNumberTv; //违章卡号
    TextView mPackageTv;    //套餐
    TextView mPhoneTv;      //电话
    TextView mIllegalTimeTv;    //违章时间
    Button mCancelBtn;  //申请取消按钮

    BaseDialog mCardDialog; //弹框

    String mLogId;      //违章卡订单id

    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initHttp();
    }


    /**
     * 初始化绑定控件
     */
    private void initView(View view) {
        mStatusRv = view.findViewById(R.id.usage_details_status_rv);
        mStatusTv = (TextView) view.findViewById(R.id.usage_details_status_tv);
        mStatusContentTv = (TextView) view.findViewById(R.id.usage_details_status_details_tv);
        mTimeTv = (TextView) view.findViewById(R.id.usage_details_time_tv);
        mOrderSnTv = (TextView) view.findViewById(R.id.usage_details_number_tv);
        mAddressTv = (TextView) view.findViewById(R.id.usage_details_illegal_address_tv);
        mDetailsTv = (TextView) view.findViewById(R.id.usage_details_illegal_details_tv);
        mPointsTv = (TextView) view.findViewById(R.id.usage_details_illegal_points_tv);
        mCardNumberTv = (TextView) view.findViewById(R.id.usage_details_illegal_number_tv);
        mPackageTv = (TextView) view.findViewById(R.id.usage_details_illegal_package_tv);
        mCancelBtn = (Button) view.findViewById(R.id.usage_details_cancel_application_btn);
        mPhoneTv = (TextView) view.findViewById(R.id.usage_details_illegal_phone_tv);
        mFineTv = (TextView) view.findViewById(R.id.usage_details_illegal_fine_tv);
        mIllegalTimeTv = (TextView) view.findViewById(R.id.usage_details_illegal_time_tv);
    }

    /**
     * 初始化值
     */
    private void initValue() {
        mLogId = getIntent().getStringExtra("log_id");
    }


    /**
     * 初始化网络请求
     */
    private void initHttp() {
        showLoading();
        mPersenter.requestUsageDetails(mLogId);
    }

    @Override
    public void returnUsageDetails(UsageDetailsBean bean) {
        hiddenLoading();
        hiddenErrorView();
        if (bean.success) {
            UsageDetailsBean.DataBean data = bean.data;
            int stautsImg;//订单状态图片
            switch (Integer.parseInt(data.do_status)) {
                case 1: //未审核
                    stautsImg = R.mipmap.bg_to_audit;
                    //显示申请取消按钮
                    mCancelBtn.setVisibility(View.VISIBLE);
                    mCancelBtn.setOnClickListener(v -> {
                        cancelOrder();
                    });
                    break;
                case 2: //处理中
                    mCancelBtn.setVisibility(View.GONE);
                    stautsImg = R.mipmap.bg_in_treatment;
                    break;
                case 3: //已取消
                    mCancelBtn.setVisibility(View.GONE);
                    stautsImg = R.mipmap.bg_canceled;
                    break;
                case 4: //已完成
                    mCancelBtn.setVisibility(View.GONE);
                    stautsImg = R.mipmap.bg_completed;
                    break;
                default:
                    stautsImg = R.mipmap.bg_completed;
                    break;
            }
            mStatusTv.setText(data.order_status);       //设置状态信息
            mStatusContentTv.setText(data.order_info);  //设置状态名字
            mStatusRv.setBackgroundResource(stautsImg); //设置状态图片
            mOrderSnTv.setText(data.order_sn);       //详情编号
            mTimeTv.setText(data.add_datetime);         //使用日期
            mAddressTv.setText(data.area);              //违章地点
            mDetailsTv.setText(data.act);               //违章信息
            mIllegalTimeTv.setText(data.date_time);
            mCardNumberTv.setText(data.card_number);     //卡编号
            mPackageTv.setText(data.surplus_number + "个月套餐");     //卡套餐

            //文字颜色
            String moenyStr = "罚款 " + data.money + "元";
            SpannableString moeny = new SpannableString(moenyStr);
            moeny.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_text)), 3, moenyStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mFineTv.setText(moeny);//罚款金额

            String fenStr = "扣分 " + data.fen + "分";
            SpannableString fen = new SpannableString(fenStr);
            fen.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_orange)), 3, fenStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mPointsTv.setText(fen);//扣分

            //设置电话
            String phoneStr = mPhoneTv.getText().toString();
            SpannableString phone = new SpannableString(phoneStr);
            phone.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_green)), 12, phoneStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mPhoneTv.setText(phone);

            mPhoneTv.setOnClickListener(v -> {
                Utils.callTel("400-9999-353", this);
            });
        } else {
            showErrorView();
            make(bean.info);
        }
    }


    private void cancelOrder() {
        if (mCardDialog == null) {
            mCardDialog = new BaseDialog(this);
            mCardDialog.setTitle("取消后，你的违章记录将继续保存在交管局哦？！");
            mCardDialog.setLeftAndRight("去意已决", "处理违章");
            mCardDialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
                @Override
                public void onLeftOnclick() {
                    showLoading();
                    mPersenter.requestCancelOrder(mLogId);
                    mCardDialog.dismiss();
                }

                @Override
                public void onRightOncklick() {
                    mCardDialog.dismiss();
                }
            });
        }
        mCardDialog.show();
    }

    @Override
    public void returnCancelOrder(CancelOrderBean bean) {
        make(bean.info);
        if (bean.success) {
            mPersenter.requestUsageDetails(mLogId);
            EventBus.getDefault().post(new UsageRecordEvent("yes"));
        } else {
            hiddenLoading();
        }
    }

    @Override
    public void returnCancelOrderError() {
        hiddenLoading();
        make("网络请求失败，请在网络情况良好下试试");
    }


    @Override
    public void returnUsageDetailsError() {
        showErrorView();
        errorClick(v -> {
            showLoading();
            mPersenter.requestUsageDetails(mLogId);
        });
    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, String log_id) {
        Intent intent = new Intent(activity, UsageDetailsAct.class);
        intent.putExtra("log_id", log_id);
        activity.startActivity(intent);
    }
}
