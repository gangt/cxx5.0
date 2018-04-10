package com.xi6666.order.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.app.SuperAct;
import com.xi6666.illegal.other.TouchImageView;
import com.xi6666.order.other.PhotoViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 查看大图
 */
public class OrderSeeLagerImgActivity extends SuperAct implements ViewPager.OnPageChangeListener {

    @BindView(R.id.goods_banner_vp)
    PhotoViewPager mGoodsBannerVp;
    @BindView(R.id.goods_banner_gallery)
    RecyclerView   mGoodsBannerGallery;
    private ArrayList<String>     mPicS;
    private int position;
    private GoodBannerPicsAdapter mGoodBannerPicsAdapter;
    private int flag = 0;
    private BannerPicAdapter mBannerPicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除遮罩
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            );
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_goods_img);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mPicS = intent.getStringArrayListExtra("picS");
        position = intent.getIntExtra("position",0);
        flag = position;
        mGoodBannerPicsAdapter = new GoodBannerPicsAdapter();
        mGoodsBannerVp.setAdapter(mGoodBannerPicsAdapter);
        mGoodsBannerVp.setCurrentItem(flag);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mGoodsBannerGallery.setLayoutManager(linearLayoutManager);
        mBannerPicAdapter = new BannerPicAdapter();
        mGoodsBannerGallery.setAdapter(mBannerPicAdapter);
        mGoodsBannerVp.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        flag = position;
        mBannerPicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class GoodBannerPicsAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPicS.size();
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
            View rootView = View.inflate(OrderSeeLagerImgActivity.this, R.layout.large_image_view, null);
            TouchImageView view = (TouchImageView) rootView.findViewById(R.id.tiv_pic);
            Glide.with(OrderSeeLagerImgActivity.this).load(mPicS.get(position))
                    .placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(view);
            view.setOnClickListener(v -> {
                    OrderSeeLagerImgActivity.this.finish();
            });
            container.addView(rootView);
            return rootView;
        }
    }

    public class BannerPicAdapter extends RecyclerView.Adapter<BannerPicAdapter.MyHoler> {

        @Override
        public MyHoler onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = View.inflate(OrderSeeLagerImgActivity.this, R.layout.item_goods_banner_scal, null);

            return new MyHoler(rootView);
        }

        @Override
        public void onBindViewHolder(final MyHoler holder, final int position) {
            Glide.with(OrderSeeLagerImgActivity.this).load(mPicS.get(position))
                    .placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(holder.mImageView);
            holder.itemView.setOnClickListener(v ->  {
                    mGoodsBannerVp.setCurrentItem(position, false);
                    flag = position;
                    notifyDataSetChanged();
            });
            for (int x = 0; x < mPicS.size(); x++) {
                holder.mRelativeLayout.setVisibility(View.GONE);
            }
            if (position == flag) {
                holder.mRelativeLayout.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return mPicS.size();
        }

        public class MyHoler extends RecyclerView.ViewHolder {
            private ImageView mImageView;
            private RelativeLayout mRelativeLayout;

            public MyHoler(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.goods_banner_scale);
                mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.goods_banner_rel);
            }
        }
    }
}
