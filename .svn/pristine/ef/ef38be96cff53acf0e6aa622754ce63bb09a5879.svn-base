package com.xi6666.store.custom;

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
 * 时间 16/11/7 下午6:20.
 * 个人公众号 ardays
 * <p>
 * 技师的ItemView
 */
public class StoreTechnicianTeamItemView extends LinearLayout implements StoreTechnicianTeamItemViewImpl {
    /**
     * 上下文
     */
    Context mContext;

    public StoreTechnicianTeamItemView(Context context) {
        this(context, null);
    }

    public StoreTechnicianTeamItemView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public StoreTechnicianTeamItemView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }

    @BindView(R.id.store_technician_team_head_iv)
    ImageView mHeadIv;  //头像
    @BindView(R.id.store_technician_team_name_tv)
    TextView mNameTv;   //名字


    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.view_store_technician_team, this);
        ButterKnife.bind(this, view);
    }


    @Override
    public void setHeadImage(String url) {
        //写入圆头像
        Glide.with(mContext).load(url).asBitmap().placeholder(R.drawable.ic_mine_head).error(R.mipmap.ic_technician_error_head).centerCrop().into(new BitmapImageViewTarget(mHeadIv) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                mHeadIv.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    @Override
    public void setName(String name) {
        mNameTv.setText(name);
    }
}