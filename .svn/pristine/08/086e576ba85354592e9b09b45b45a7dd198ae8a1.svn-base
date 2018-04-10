package com.xi6666.technician.view.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.xi6666.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/9 下午3:10.
 * 个人公众号 ardays
 * <p>
 * 技师详情 - 技师简介
 */
public class TechnicianProfileView extends LinearLayout {
    /**
     * 上下文
     */
    Context mContext;

    @BindView(R.id.view_technician_profile_head_iv)
    ImageView mHeadIv;      //头像
    @BindView(R.id.view_technician_profile_name_tv)
    TextView mNameTv;      //头像
    @BindView(R.id.view_technician_profile_desc_tv)
    TextView mDescTv;      //简介

    public TechnicianProfileView(Context context) {
        this(context, null);
    }

    public TechnicianProfileView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public TechnicianProfileView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.view_technician_profile, this);
        ButterKnife.bind(this, view);
    }


    /**
     * 写入技师详情
     */
    public void setData(String head_url, String name, String desc) {
        //写入圆头像
        Glide.with(mContext)
                .load(head_url)
                .asBitmap()
                .placeholder(R.drawable.ic_mine_head)
                .error(R.drawable.ic_mine_head)
                .centerCrop()
                .into(new BitmapImageViewTarget(mHeadIv) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        mHeadIv.setImageDrawable(circularBitmapDrawable);
                    }
                });
        //技师名字
        mNameTv.setText(name);
        //技师详情
        mDescTv.setText(desc);
    }

}