package com.xi6666.store.custom;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.order.other.Utils;
import com.xi6666.store.NaviActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

/**
 * 创建者 sunsun
 * 时间 16/11/5 下午12:00.
 * 个人公众号 ardays
 * <p>
 * 门店详情上面的View
 */

public class StoreDetailsHeadView extends LinearLayout implements StoreDetailsHeadViewImpl {

    private Context mContext;


    /**
     * banner 跳转时间
     */
    private final long BANNER_JUMP_TIME = 3000;

    TextView mTitleTv;  //店铺名称;
    TextView mAddressTv;    //地址详情
    ViewPager mBannerVp;    //banner轮播图
    CircleNavigator mBannerCn;//banner指示器小圆点
    TextView mTimeTv;   //时间文本
    TextView mLevelTv;  //评分文本
    TextView mStatusTv; //状态文本
    TextView mDistanceTv; //距离


    BannerAdapter mBannerAdapter;//banner适配器
    List<ImageView> mBannerIvs;//banner数组
    Timer mBannerTime;  //banner的子线程
    int mBannerIndexSelected = 0; //当前选中的张数


    public StoreDetailsHeadView(Context context) {
        this(context, null);
    }

    public StoreDetailsHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StoreDetailsHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_store_details, this);
        //初始化View
        initView(view);
        //初始化banner图
        initBanner();
    }

    private void initView(View view) {
        mBannerVp = (ViewPager) view.findViewById(R.id.store_details_banner_vp);
        mBannerCn = (CircleNavigator) view.findViewById(R.id.store_details_banner_cn);
        mTitleTv = (TextView) view.findViewById(R.id.store_details_title_tv);
        mAddressTv = (TextView) view.findViewById(R.id.store_details_address_tv);
        mTimeTv = (TextView) view.findViewById(R.id.store_details_time_tv);
        mLevelTv = (TextView) view.findViewById(R.id.store_details_level_tv);
        mStatusTv = (TextView) view.findViewById(R.id.store_details_status_tv);
        mDistanceTv = (TextView) view.findViewById(R.id.store_details_location_tv);
    }

    /**
     * 初始化banner图
     */
    private void initBanner() {
        mBannerIvs = new ArrayList<>();
        mBannerAdapter = new BannerAdapter();
        //添加适配器
        mBannerVp.setAdapter(mBannerAdapter);
        //添加手势监听
        mBannerVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //记录当前位置
                mBannerIndexSelected = position;
            }

            @Override
            public void onPageSelected(int position) {
                mBannerCn.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //创建轮播图循环
        mBannerTime = new Timer();
    }


    // 开启一个子线程，让他更改UI主线程循环
    Handler mBannerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //每次加一
            mBannerIndexSelected++;
            //设置选中
            int selectedIndex = mBannerIndexSelected % mBannerIvs.size();
            mBannerVp.setCurrentItem(selectedIndex);
        }
    };


    //banner轮播图适配器
    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mBannerIvs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mBannerIvs.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //取出当前第N个
            ImageView banner = mBannerIvs.get(position);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(banner, params);
            return banner;
        }
    }


    //                          #实现方法
    @Override
    public void setBannerUrls(List<String> banners) {
        if (Utils.isEmpty(banners)) return;
        //清楚图片数组
        mBannerIvs.clear();

        //遍历url链接并添加到banner图去
        for (String url : banners) {
            ImageView banner = new ImageView(mContext);
            Glide.with(mContext)
                    .load(url)
                    .placeholder(R.mipmap.bg_test_carwash_search_bannner)
                    .centerCrop()
                    .into(banner);

            mBannerIvs.add(banner);
        }
        //让他持续去轮播
        mBannerTime.schedule(new TimerTask() {
            @Override
            public void run() {

                //如果图片为空则不让他继续跳转
                if (mBannerIvs.size() <= 0) {
                    mBannerTime.cancel();
                    return;
                }
                //通知handler更新界面
                mBannerHandler.sendEmptyMessage(0);
            }
        }, BANNER_JUMP_TIME, BANNER_JUMP_TIME);

        //刷新数据
        mBannerAdapter.notifyDataSetChanged();
        if (mBannerIvs.size() <= 1) {
            mBannerCn.setVisibility(GONE);
        } else {
            //设置指示器个数
            mBannerCn.setCircleCount(mBannerIvs.size());
        }
        //
        mBannerCn.setCircleColor(Color.WHITE);
        //刷新控制器
        mBannerCn.notifyDataSetChanged();

    }

    @Override
    public int getBannerHeight() {
        return mBannerVp.getHeight();
    }

    //                          #写参数
    public void setData(StoreDetailsBean.DataBean.StoreInfo data) {
        //写入banner图
        setBannerUrls(data.shop_bannerall);
        //写入名字
        this.mTitleTv.setText(data.shop_name);
        //写入地址
        this.mAddressTv.setText(data.shop_address);
        //写入时间
        mTimeTv.setText(data.shop_opentime + "~" + data.shop_closetime);
        if (data.service_score > 0.0) {
            //写入评价
            SpannableString ss = new SpannableString(data.service_score + "分");
            ss.setSpan(new AbsoluteSizeSpan(70), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mLevelTv.setText(ss);
        } else {
            mLevelTv.setVisibility(GONE);
        }
        //点击进入定位
        mAddressTv.setOnClickListener(v -> {
            NaviActivity.openActivity(mContext, data.shop_lat, data.shop_lng, data.shop_address);
        });
        //写入营业状态
        mStatusTv.setText(data.openstat.equals("1") ? "门店营业中，欢迎光临" : "门店休息中");

        //距离
        mDistanceTv.setText(data.distance);
    }

}
