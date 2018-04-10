package com.xi6666.productdetails.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xi6666.R;


import java.util.List;

/**
 * Created by Mr_yang on 2016/11/15.
 */

public class ProductBannerAdapter extends PagerAdapter {
    private List<String> mPicturesBean;
    private BannerOnclickListener mBannerOnclickListener;

    public void setBannerOnclickListener(BannerOnclickListener bannerOnclickListener) {
        mBannerOnclickListener = bannerOnclickListener;
    }

    public ProductBannerAdapter(List<String> picturesBean) {
        mPicturesBean = picturesBean;
    }

    @Override
    public int getCount() {
        return mPicturesBean.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(container.getContext()).load(mPicturesBean.get(position)).placeholder(R.drawable.bg_image_default).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBannerOnclickListener.bannerOnclick();
            }
        });
        container.addView(imageView);
        return imageView;
    }

    public interface BannerOnclickListener {
        void bannerOnclick();
    }
}
