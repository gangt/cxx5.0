package com.xi6666.carWash.view.custom;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.order.other.Utils;
import com.xi6666.store.mvp.bean.StoreServiceBean;
import com.xi6666.store.mvp.bean.StoreServiceTypeBean;
import com.xi6666.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/2 下午2:28.
 * 个人公众号 ardays
 * 特惠洗车搜索的结果item
 */

public class CarWashSearchView extends RelativeLayout {

    @BindView(R.id.carWash_search_banner_iv)
    ImageView mBannerIv;
    @BindView(R.id.carWash_search_title_tv)
    TextView mTitleTv;  //门店标题
    @BindView(R.id.carWash_search_time_tv)
    TextView mTimeTv;  //时间文本
    @BindView(R.id.carWash_search_location_tv)
    TextView mLocationTv;   //定位文本
    @BindView(R.id.carWash_search_address_tv)
    TextView mAddressTv;    //地址
    @BindView(R.id.carWash_search_score_tv)
    TextView mScoreTv;  //评分
    @BindView(R.id.carWash_search_me_go_iv)
    View mMegoView;


    StoreServiceBean.DataBean mData; //数据源
    Context mContext;       //上下文

    public CarWashSearchView(Context context) {
        this(context, null);
    }

    public CarWashSearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarWashSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = RelativeLayout.inflate(context, R.layout.view_carwash_search, this);
        ButterKnife.bind(this, view);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
    }


    public void setData(StoreServiceBean.DataBean data) {
        this.mData = data;
        //写入banner图
        Glide.with(mContext)
                .load(data.shop_banner)
                .placeholder(R.drawable.bg_image_default)
                .error(R.drawable.bg_image_default)
                .crossFade(1000)
                .fitCenter()
                .into(mBannerIv);

        //门店标题
        mTitleTv.setText(data.shop_name);

        //时间
        mTimeTv.setText(data.shop_opentime + "~" + data.shop_closetime);

        //定位
        mLocationTv.setText(data.distance);

        //门店地址
        mAddressTv.setText(data.shop_address);

        //我去过
        boolean bol = data.has_go == 1f;
        mMegoView.setVisibility(bol ? View.VISIBLE : View.GONE);

        String score = Utils.getScore(data.service_score);
        Log.e("TAG", "score--->" + score);
        if (score.equals("0.0")) {
            mScoreTv.setVisibility(GONE);
        } else {
            //评分
            mScoreTv.setVisibility(VISIBLE);
            setScore(score + "分");
        }
    }


    /**
     * 写入评分
     */
    public void setScore(String text) {
        //让评分功能的首字母大写
        SpannableString ss = new SpannableString(text);
        ss.setSpan(new AbsoluteSizeSpan(70), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mScoreTv.setText(ss);
    }

}
