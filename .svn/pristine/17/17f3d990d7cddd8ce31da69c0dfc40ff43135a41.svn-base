package com.xi6666.carWash.view.custom;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.carWash.view.DetermineOrderAct;
import com.xi6666.common.UserData;
import com.xi6666.login.view.LoginAct;
import com.xi6666.order.other.Utils;
import com.xi6666.store.StoreDetailsAct;
import com.xi6666.store.mvp.bean.StoreServiceBean;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/10/31 下午7:18.
 * 个人公众号 ardays
 * 门店View
 */

public class StoreView extends RelativeLayout {

    private Context mContext;

    @BindView(R.id.store_score_tv)
    TextView mScoreTv;  //评分
    @BindView(R.id.view_store_me_go_iv)
    View mMeGo;     //我去过
    @BindView(R.id.store_iv)
    ImageView mStoreIv; //门店头像
    @BindView(R.id.store_title)
    TextView mStoreTitleTv; //设置店铺名字
    @BindView(R.id.store_location)
    TextView mStoreLocationTv;  //距离显示
    @BindView(R.id.store_address)
    TextView mStoreAddressTv;   //地址名字
    @BindView(R.id.store_time_tv)
    TextView mStoreTimeTv;  //时间
    @BindView(R.id.store_item_title)
    TextView mStoreServiceNameTv;   //服务项目名称
    @BindView(R.id.store_moeny_tv)
    TextView mStoreServiceMoenyTv;  //服务项目价格
    @BindView(R.id.store_go_pay)
    TextView mStoreGoPay;           //去支付


    public StoreView(Context context) {
        this(context, null);
    }

    public StoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        View view = RelativeLayout.inflate(context, R.layout.view_store, this);
        ButterKnife.bind(this, view);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {

    }

    public void setData(StoreServiceBean.DataBean data) {

        //设置名字
        mStoreTitleTv.setText(data.shop_name);

        //设置距离
        mStoreLocationTv.setText(data.distance);

        //设置地址
        mStoreAddressTv.setText(data.shop_address);

        //设置时间
        mStoreTimeTv.setText(data.shop_opentime + "~" + data.shop_closetime);

        //设置评分
        String score = Utils.getScore(data.service_score);
        if (!score.equals("0.0")) {      //0分就不让他显示
            SpannableString ss = new SpannableString(score + "分");
            ss.setSpan(new AbsoluteSizeSpan(70), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mScoreTv.setText(ss);
            mScoreTv.setVisibility(VISIBLE);
        }else{
            mScoreTv.setVisibility(GONE);
        }

        //设置服务项目名称
        mStoreServiceNameTv.setText(data.service_name);

        //设置价格
        mStoreServiceMoenyTv.setText("¥   " + data.service_price);

        //去支付的回调事件
        mStoreGoPay.setOnClickListener(v -> {
            if (TextUtils.isEmpty(UserData.getUserId()) || TextUtils.isEmpty(UserData.getUserToken())) {
                mContext.startActivity(new Intent(mContext, LoginAct.class));
                return;
            }
            DetermineOrderAct.openActivity(getContext(), data.service_id);
        });


        //点击跳转到门店详情
        this.setOnClickListener(v -> {
            StoreDetailsAct.openActivity(mContext, data.store_id, data.service_store_id);
        });


        //设置头像
        Glide.with(mContext)
                .load(data.shop_banner)
                .centerCrop()
                .into(mStoreIv);

        //是否去过该门店
        int SHOW = data.has_go == 0.0 ? INVISIBLE : VISIBLE;
        mMeGo.setVisibility(SHOW);
    }
}
