package com.xi6666.guide.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.app.ImmerseBaseAct;
import com.xi6666.main.view.MainAct;
import com.xi6666.multi_image_selector.bean.Image;
import com.xi6666.utils.AppUitls;
import com.xi6666.utils.DimenUtils;
import com.xi6666.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author peng
 * @data 创建时间:2016/11/17
 * @desc 引导界面
 */
public class GuideAct extends ImmerseBaseAct implements ViewPager.OnPageChangeListener {


    @BindView(R.id.vp_guide)
    ViewPager mVpGuide;
    @BindView(R.id.ll_guide)
    LinearLayout mLlGuide;
    private List<Integer> images = new ArrayList<>();
    private List<ImageView> mImageViewsPoints = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        images.add(R.drawable.ic_lead_zero);
        images.add(R.drawable.ic_lead_one);
        images.add(R.drawable.ic_lead_two);
        images.add(R.drawable.ic_lead_thr);
        initPoint();

        mVpGuide.setAdapter(new MyViewPagerAdapter());
        mVpGuide.addOnPageChangeListener(this);

    }

    private void initPoint() {
        for (int x = 0; x < images.size(); x++) {
            ImageView pointView = new ImageView(this);
            pointView.setImageResource(R.drawable.ic_point_welcome);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = DimenUtils.dip2px(this, 10);
            pointView.setLayoutParams(layoutParams);
            mLlGuide.addView(pointView);
            mImageViewsPoints.add(pointView);
        }
        mImageViewsPoints.get(0).setImageResource(R.drawable.ic_point_welcome_pre);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < images.size(); i++) {
            if (i == position) {
                mImageViewsPoints.get(i).setImageResource(R.drawable.ic_point_welcome_pre);
            } else {
                mImageViewsPoints.get(i).setImageResource(R.drawable.ic_point_welcome);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View inflate = LayoutInflater.from(GuideAct.this).inflate(R.layout.activity_guid_page, null);
            ImageView guideIv = (ImageView) inflate.findViewById(R.id.iv_guide);
            TextView guideTxt = (TextView) inflate.findViewById(R.id.txt_guide);
            guideTxt.setOnClickListener(v ->
                    startNext());
            guideIv.setImageResource(images.get(position));
            if (position == images.size() - 1) {
                guideTxt.setVisibility(View.VISIBLE);
            }
            container.addView(inflate);
            return inflate;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void startNext() {
        SpUtils.setBoolean(GuideAct.this, "start" + AppUitls.getAppVersionName(GuideAct.this), true);
        startActivity(new Intent(GuideAct.this, MainAct.class));
        finish();
    }
}
