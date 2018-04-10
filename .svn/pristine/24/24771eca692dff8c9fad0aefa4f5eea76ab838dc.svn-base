package com.xi6666.store.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建者 sunsun
 * 时间 16/11/4 下午4:07.
 * 个人公众号 ardays
 * <p>
 * 功能 ： 门店右上角的筛选功能 popow
 */

public class StoreScreenPopow extends PopupWindow {

    /**
     * 存储上下文
     */
    public Context mContext;
    TextView mSelectTv;


    public StoreScreenPopow(Context context) {
        this.mContext = context;

        //设置布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.popow_store_screen, null);
        setContentView(view);
        //设置ButterKnife
        ButterKnife.bind(this, view);

        mSelectTv = (TextView) view.findViewById(R.id.popop_store_screen_distance_tv);
        //设置大小
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 选项点击
     */
    @OnClick({
            R.id.popop_store_screen_distance_tv,
            R.id.popop_store_screen_popularity_tv,
            R.id.popop_store_screen_score_tv
    })
    void onItemClick(TextView view) {
        if (mSelectTv != null)
            mSelectTv.setTextColor(mContext.getResources().getColor(R.color.text_black));
        view.setTextColor(mContext.getResources().getColor(R.color.text_green));
        if (mOnStoreScreenItemListener != null)
            mOnStoreScreenItemListener.onItemClick(view.getText().toString());
        mSelectTv = view;
        dismiss();
    }

    @OnClick(R.id.popop_store_screen_view)
    void onDismissClick() {
        dismiss();
    }

    /*                      @事件回调                   */
    public OnStoreScreenItemListener mOnStoreScreenItemListener;

    public void setOnStoreScreenItemListener(OnStoreScreenItemListener listener) {
        this.mOnStoreScreenItemListener = listener;
    }

    public interface OnStoreScreenItemListener {
        void onItemClick(String type);
    }


}
