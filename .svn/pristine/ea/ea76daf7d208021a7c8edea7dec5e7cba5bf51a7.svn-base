package com.xi6666.bannerdetial.view;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.app.ImmerseBaseAct;
import com.xi6666.view.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author peng
 * @data 创建时间:2016/12/9
 * @desc
 */
public class BannerDetialAct extends ImmerseBaseAct implements
        ViewPager.OnPageChangeListener {

    private static final String TAG = "BannerDetialAct";
    @BindView(R.id.vp_bannerdetial)
    PhotoViewPager mVpBannerdetial;
    @BindView(R.id.rv_bannerdetial_gallery)
    RecyclerView mRvBannerdetialGallery;
    private int flag = 0;
    private List<String> mBanner = new ArrayList<>();
    private goodBannerPicsAdapter mGoodBannerPicsAdapter;
    private BannerPicAdapter mBannerPicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_detial);
        ButterKnife.bind(this);
        mBanner.addAll((List<String>) getIntent().getSerializableExtra("images"));

        mGoodBannerPicsAdapter = new goodBannerPicsAdapter();
        mVpBannerdetial.setAdapter(mGoodBannerPicsAdapter);
        mVpBannerdetial.addOnPageChangeListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvBannerdetialGallery.setLayoutManager(linearLayoutManager);

        mBannerPicAdapter = new BannerPicAdapter();
        mRvBannerdetialGallery.setAdapter(mBannerPicAdapter);
    }

    public class goodBannerPicsAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mBanner.size();
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
            View rootView = View.inflate(BannerDetialAct.this, R.layout.banner_view, null);
            PhotoView view = (PhotoView) rootView.findViewById(R.id.banner_pic);
            Glide.with(BannerDetialAct.this).load(mBanner.get(position)).placeholder(R.drawable.bg_image_default).into(view);
            view.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float v, float v1) {
                    finish();
                }

                @Override
                public void onOutsidePhotoTap() {
                    finish();
                }
            });
            container.addView(rootView);
            return rootView;
        }
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

    public class BannerPicAdapter extends RecyclerView.Adapter<BannerPicAdapter.MyHoler> {

        @Override
        public BannerPicAdapter.MyHoler onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = View.inflate(BannerDetialAct.this, R.layout.item_goods_banner_scal, null);

            return new MyHoler(rootView);
        }

        @Override
        public void onBindViewHolder(final BannerPicAdapter.MyHoler holder, final int position) {
            Glide.with(BannerDetialAct.this).load(mBanner.get(position)).placeholder(R.drawable.bg_image_default).into(holder.mImageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag = position;
                    mVpBannerdetial.setCurrentItem(flag);
                    notifyDataSetChanged();
                }
            });
            for (int x = 0; x < mBanner.size(); x++) {
                holder.mRelativeLayout.setVisibility(View.GONE);
            }
            if (position == flag) {
                holder.mRelativeLayout.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return mBanner.size();
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
