package com.xi6666.addoil;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xi6666.R;

import com.xi6666.databean.AddOilDataBean;

/**
 * Created by Mr_yang on 2016/11/23.
 */

public class AddOilLocalImageHolderView implements Holder<AddOilDataBean.BannerBean> {
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
    public void UpdateUI(Context context, int position, AddOilDataBean.BannerBean data) {
        Glide.with(mContext)
                .load(data.getImg()).placeholder(R.drawable.bg_defaut_1080_433)
                .into(imageView);
    }
}
