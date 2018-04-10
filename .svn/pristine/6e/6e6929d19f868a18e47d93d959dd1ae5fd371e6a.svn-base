package com.xi6666.view.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi6666.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by Mr_yang on 2016/11/2.
 */

public class RefreshHeader extends FrameLayout implements PtrUIHandler {

    private TextView mTextView;
    private ImageView mGif;
    private AnimationDrawable mAnimationDrawable;

    public RefreshHeader(Context context) {
        this(context, null);
    }

    public RefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.refresh_layout, this, true);
        mGif = (ImageView) view.findViewById(R.id.gif);
        mTextView = (TextView) view.findViewById(R.id.text);
        mAnimationDrawable = new AnimationDrawable();
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00009), 40);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00015), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00016), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00017), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00018), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00019), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00020), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00021), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00022), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00023), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00024), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00025), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00026), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00027), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00028), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00029), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00030), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00031), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00032), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00033), 260);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00059), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00060), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00061), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00062), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00063), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00064), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00065), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00066), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00067), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00068), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00069), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00070), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00071), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00072), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00073), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00074), 10);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.home_loading_00075), 290);
        mGif.setBackgroundDrawable(mAnimationDrawable);
        mAnimationDrawable.setOneShot(false);

    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mTextView.setText("下拉刷新");
        mAnimationDrawable.stop();
        mAnimationDrawable.selectDrawable(0);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mTextView.setText("下拉刷新");
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mTextView.setText("正在刷新...");
        mAnimationDrawable.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        mTextView.setText("下拉刷新");
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        int offsetToRefresh = frame.getOffsetToRefresh();
        int currentPosY = ptrIndicator.getCurrentPosY();
        int lastPosY = ptrIndicator.getLastPosY();
        if (offsetToRefresh > currentPosY && offsetToRefresh <= lastPosY) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                mTextView.setText("下拉刷新");
            }
        }
        if (offsetToRefresh < currentPosY && offsetToRefresh > lastPosY) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                mTextView.setText("松开刷新");
            }
        }
    }
}
