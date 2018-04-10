package com.xi6666.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.databean.AddOilPopuBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr_yang on 2017/2/22.
 */

public class AddOilPromotionDialog extends Dialog {
    @BindView(R.id.lv_item_popu)
    ListView mLvItemPopu;
    @BindView(R.id.tv_item_receive)
    TextView mTvItemReceive;
    private MyAdapter mMyAdapter;
    private List<AddOilPopuBean.DataBean.CouponListBean> mCouponListBeen = new ArrayList<>();
    private OnReceiveButtonListener mOnReceiveButtonListener;


    public AddOilPromotionDialog(Context context) {
        super(context);
        init(context);
    }


    public AddOilPromotionDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected AddOilPromotionDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_addoil_promotion, null);
        ButterKnife.bind(this, inflate);
        setContentView(inflate);
        setCancelable(true);
        mMyAdapter = new MyAdapter();
        mLvItemPopu.setAdapter(mMyAdapter);
    }

    @OnClick({R.id.tv_item_receive, R.id.iv_item_cancle})
    public void viewOnclick(View view) {
        switch (view.getId()) {
            case R.id.tv_item_receive:
                if (mOnReceiveButtonListener != null) {
                    mOnReceiveButtonListener.onClick();
                }
                this.dismiss();
                break;
            case R.id.iv_item_cancle:
                this.dismiss();
                break;
        }
    }

    public void setOnReceiveButtonListener(OnReceiveButtonListener onReceiveButtonListener) {
        mOnReceiveButtonListener = onReceiveButtonListener;
    }

    public void setCouponListBeen(List<AddOilPopuBean.DataBean.CouponListBean> couponListBeen) {
        mCouponListBeen = couponListBeen;
        mMyAdapter.notifyDataSetChanged();
    }

    public interface OnReceiveButtonListener {
        void onClick();
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mCouponListBeen.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Context context = parent.getContext();
            View inflate = LayoutInflater.from(context).inflate(R.layout.addoil_promotion_item, null);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_addoil_popu);
            textView.setText(mCouponListBeen.get(position).getCoupon_money());
            return inflate;
        }
    }
}
