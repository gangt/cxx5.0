package com.xi6666.illegal.other;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.databean.HomeHeadBean;
import com.xi6666.databean.IllegaHomeListBean;

/**
 * Created by Mr_yang on 2017/2/8.
 */

public class IllegaBannerHolderView implements Holder<IllegaHomeListBean.BannerBean> {
    private ImageView imageView;
    private Context mActivity;

    public IllegaBannerHolderView(Context activity) {
        mActivity = activity;
    }

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, IllegaHomeListBean.BannerBean data) {
        Glide.with(mActivity)
                .load(data.getImg_url()).placeholder(R.drawable.bg_home_banner_default)
                .into(imageView);
    }
}
