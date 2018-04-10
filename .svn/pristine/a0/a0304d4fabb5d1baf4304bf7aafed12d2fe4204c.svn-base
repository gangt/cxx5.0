package com.xi6666.store.custom;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xi6666.R;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.store.mvp.bean.StoreDetailsServiceBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/7 下午4:35.
 * 个人公众号 ardays
 * 门店详情 - 本店服务项目
 */

public class StoreDetailsServiceView extends LinearLayout implements StoreDetailsServiceViewImpl {
    /**
     * 上下文
     */
    private Context mContext;


    @BindView(R.id.store_details_service_cn)
    CircleNavigator mServiceCn;   //指示器小圆点
    @BindView(R.id.store_details_service_vp)
    ViewPager mServiceVp;   //服务页数分页


    //服务项目View
    List<StoreDetailsServiceItemView> mServiceView;
    //服务适配器
    ServiceAdapter mServiceAdapter;

    private String mStoreId;    //门店ID


    public StoreDetailsServiceView(Context context) {
        this(context, null);
    }

    public StoreDetailsServiceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StoreDetailsServiceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //注册注入
        View view = View.inflate(mContext, R.layout.view_store_service, this);
        ButterKnife.bind(this, view);


        initService();

    }

    /**
     * 初始化服务
     */
    private void initService() {
        mServiceView = new ArrayList<>();
        mServiceAdapter = new ServiceAdapter();
        mServiceVp.setAdapter(mServiceAdapter);
        mServiceVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mServiceCn.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    //服务项目适配器
    class ServiceAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mServiceView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(mServiceView.get(position), params);
            return mServiceView.get(position);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(mServiceView.get(position));
        }
    }


    //                          @对外公开的调用方法
    @Override
    public void setServiceData(List<StoreDetailsBean.DataBean.ServiceListBean> serviceData) {
        if(serviceData.size() == 0){
            setVisibility(GONE);
            return;
        }
        //清除之前的ServiceView
        mServiceView.clear();
        mServiceAdapter.notifyDataSetChanged();


        //获取分页数量
        int count = serviceData.size() % 4 != 0 ? serviceData.size() / 4 + 1 : serviceData.size() / 4;
        if (count > 1) {
            Log.e("TAG","count--->" + count);
            mServiceCn.setVisibility(VISIBLE);
            //分页数量两页以上的逻辑
            mServiceCn.setCircleCount(count);
            mServiceCn.setCircleColor(Color.BLACK);
            mServiceCn.notifyDataSetChanged();


            //创建一个数组，用来存放遍历一组的数据
            List<StoreDetailsBean.DataBean.ServiceListBean> serviceGroup = new ArrayList<>();
            //遍历所有数据
            for (int i = 0; i < serviceData.size(); i++) {
                //将数据添加里面
                serviceGroup.add(serviceData.get(i));
                //每4条为一组
                if ((i + 1) % 4 == 0) {
                    addServiceView(serviceGroup);
                    serviceGroup = new ArrayList<>();
                }
            }
//            addServiceView(serviceGroup);
        } else {
            //分页数量一页的逻辑
            mServiceCn.setVisibility(GONE);
            addServiceView(serviceData);
        }

        mServiceAdapter.notifyDataSetChanged();
    }

    @Override
    public void setStoreId(String store_id) {
        this.mStoreId = store_id;
    }

    //实例化一个服务组项目
    private void addServiceView(List<StoreDetailsBean.DataBean.ServiceListBean> data) {
        StoreDetailsServiceItemView view = new StoreDetailsServiceItemView(mContext);
        view.setStoreId(mStoreId);
        view.setData(data);
        mServiceView.add(view);
    }
}
