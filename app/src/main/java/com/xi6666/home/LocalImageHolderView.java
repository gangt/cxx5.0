package com.xi6666.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.databean.HomeHeadBean;

/**
 * Created by Mr_yang on 2016/11/7.
 */

public class LocalImageHolderView implements Holder<HomeHeadBean.BannerBean> {
    private ImageView imageView;
    private Context mActivity;

    public LocalImageHolderView(Context activity) {
        mActivity = activity;
    }

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, HomeHeadBean.BannerBean data) {
        Glide.with(mActivity)
                .load(data.getImg()).placeholder(R.drawable.bg_home_banner_default)
                .into(imageView);
    }
}
