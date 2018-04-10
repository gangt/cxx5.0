package com.xi6666.view.popuwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.common.OnRecyclerItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr_yang on 2016/11/14.
 */

public class ProductMorePopu extends PopupWindow implements AdapterView.OnItemClickListener {
    @BindView(R.id.rc_popumore)
    ListView mRcPopumore;
    private Context mContext;
    private int[] images = {R.drawable.ic_product_popu_more_home, R.drawable.ic_product_popu_more_like, R.drawable.ic_product_popu_more_order, R.drawable.ic_product_popu_more_phone};
    private String[] mStrings = {"首页", "我的收藏", "商品订单", "客服"};

    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;

    public ProductMorePopu(Context context) {
        super(context);
        mContext = context;
        intPopu();
    }

    private void intPopu() {
        this.setBackgroundDrawable(new ColorDrawable(0));
        View view = LayoutInflater.from(mContext).inflate(R.layout.popu_product_more, null);
        ButterKnife.bind(this, view);
        mRcPopumore.setAdapter(new MyPopuAdapter());
        //设置宽高
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setContentView(view);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(0));
        this.setFocusable(true);
        mRcPopumore.setOnItemClickListener(this);
    }

    public void showPopuWindow(View view) {
        if (isShowing()) {
            this.dismiss();
        } else {
            this.showAsDropDown(view);
        }
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        mOnRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mOnRecyclerItemClickListener != null) {
            mOnRecyclerItemClickListener.onRecyclerItemClick(position);
            this.dismiss();
        }
    }

    public class MyPopuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popu_more, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_item_product_more_popu);
            TextView textView = (TextView) view.findViewById(R.id.txt_item_product_more_popu);
            imageView.setImageResource(images[position]);
            textView.setText(mStrings[position]);
            return view;
        }
    }
}
