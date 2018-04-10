package com.xi6666.store.custom;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.evaluate.activity.StoreServiceItemActivity;
import com.xi6666.store.mvp.bean.StoreDetailsServiceBean;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/7 下午5:14.
 * 个人公众号 ardays
 */

public class StoreDetailsServiceItemView extends LinearLayout implements StoreDetailsServiceItemViewImpl {
    Context mContext;

    public StoreDetailsServiceItemView(Context context) {
        this(context, null);
    }

    public StoreDetailsServiceItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StoreDetailsServiceItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    @BindViews({R.id.store_details_service_one, R.id.store_details_service_two, R.id.store_details_service_three, R.id.store_details_service_four})
    View[] mServiceLl;      //布局
    @BindViews({R.id.store_details_service_one_tv, R.id.store_details_service_two_tv, R.id.store_details_service_three_tv, R.id.store_details_service_four_tv})
    TextView[] mServiceTv;      //服务名称
    @BindViews({R.id.store_details_service_one_iv, R.id.store_details_service_two_iv, R.id.store_details_service_three_iv, R.id.store_details_service_four_iv})
    ImageView[] mServiceIv;      //服务图片


    private String mStoreId;

    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.item_store_details_service, this);
        ButterKnife.bind(this, view);
    }


    //                                  #设置数据
    @Override
    public void setData(List<StoreDetailsBean.DataBean.ServiceListBean> data) {
        for (int i = 0; i < data.size(); i++) {
            StoreDetailsBean.DataBean.ServiceListBean bean = data.get(i);
            mServiceLl[i].setVisibility(VISIBLE);   //显示布局
            mServiceTv[i].setText(bean.cate_name);   //写入服务名称
                 //加载图片
            Glide.with(mContext)
                    .load(bean.cate_img)
                    .placeholder(R.drawable.bg_image_default)
                    .error(R.drawable.bg_image_default)
                    .into(mServiceIv[i]);

            mServiceLl[i].setOnClickListener(v -> {
                Intent intent = new Intent(mContext,StoreServiceItemActivity.class);
                intent.putExtra("service_id",bean.service_cate_id);
                intent.putExtra("service_name",bean.cate_name);
                intent.putExtra("shop_id",mStoreId);
                mContext.startActivity(intent);
            });

        }
    }

    @Override
    public void setStoreId(String store_id) {
        this.mStoreId = store_id;
    }
}
