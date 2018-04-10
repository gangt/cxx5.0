package com.xi6666.carWash.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.databean.AddOilDataBean;
import com.xi6666.store.bean.StoreBannerBean;

/**
 * 创建者 sunsun
 * 时间 2016/12/6 下午9:14.
 * 个人公众号 ardays
 */

public class CarWashImageHolderView implements Holder<StoreBannerBean.StoreBannerData> {
    private ImageView imageView;
    private Context mContext;

    @Override
    public View createView(Context context) {
        this.mContext = context;
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, StoreBannerBean.StoreBannerData data) {
        Glide.with(mContext)
                .load(data.img)
                .placeholder(R.drawable.bg_defaut_1080_433)
                .centerCrop()
                .into(imageView);

    }

}
