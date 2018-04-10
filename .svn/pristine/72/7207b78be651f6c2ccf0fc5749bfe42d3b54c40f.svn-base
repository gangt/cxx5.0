package com.xi6666.view.popuwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xi6666.R;

/**
 * Created by Mr_yang on 2016/11/16.
 */

public class ProductAddCarPopu extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    private int mPopupWidth;
    private int mPopupHeight;
    private TextView mBuyNow;
    private OnBuyNowClickLisntner mOnBuyNowClickLisntner;

    public void setOnBuyNowClickLisntner(OnBuyNowClickLisntner onBuyNowClickLisntner) {
        mOnBuyNowClickLisntner = onBuyNowClickLisntner;
    }

    public ProductAddCarPopu(Context context) {
        super(context);
        mContext = context;
        initPopu();
    }

    private void initPopu() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.popu_product_addcar, null);
        mBuyNow = (TextView) inflate.findViewById(R.id.txt_item_popu_buynow);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setContentView(inflate);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(0));
        this.setFocusable(true);
        inflate.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mPopupWidth = inflate.getMeasuredWidth();
        mPopupHeight = inflate.getMeasuredHeight();
        mBuyNow.setOnClickListener(this);
        Log.d("ProductAddCarPopu", "高度为---->" + mPopupWidth);
        Log.d("ProductAddCarPopu", "高度为2---->" + mPopupHeight);
    }

    public void showPopu(View view) {
        if (isShowing()) {
            this.dismiss();
        } else {

            //保存anchor在屏幕中的位置
            int[] location = new int[2];
            //保存anchor上部中点
            int[] anchorCenter = new int[2];
            //读取位置anchor座标
            view.getLocationOnScreen(location);
            //计算anchor中点
            anchorCenter[0] = location[0] + view.getWidth() / 2;
            anchorCenter[1] = location[1];
            this.showAtLocation(view, Gravity.TOP, 0, anchorCenter[1] - mPopupHeight);
        }
    }

    public void dismissPopu() {
        if (isShowing()) {
            this.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnBuyNowClickLisntner != null) {
            mOnBuyNowClickLisntner.onBuyNoewClick();
        }
    }

    public interface OnBuyNowClickLisntner {
        void onBuyNoewClick();
    }


}
