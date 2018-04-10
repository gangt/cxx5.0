package com.xi6666.car.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.xi6666.R;
import com.xi6666.car.bean.MyCarBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人 孙孙啊i
 * 时间 2016/6/11 0011.
 * 功能
 */
public class MyCarAdapter extends RecyclerSwipeAdapter {

    /**
     * 这是上下文
     */
    private Activity mContext;
    /**
     * 参数
     */
    private List<MyCarBean.DataBean> mDatas;
    /**
     * 下标多少
     */
    private int mIndex = -1;
    /**
     * 判断是否默认轩车
     */
    private boolean mSelTypeBol = false;


    /**
     * 重写构造方法 把参数传进来
     *
     * @param context 上下文
     */
    public MyCarAdapter(Activity context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_car_details, parent, false);
        return new MyCarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ((MyCarViewHolder) viewHolder).mSwipe.setShowMode(SwipeLayout.ShowMode.PullOut);
        ((MyCarViewHolder) viewHolder).mSwipe.setDragEdge(SwipeLayout.DragEdge.Right);
        /**
         * 判断当前是加载还是我的爱车界面
         */
        if (viewHolder instanceof MyCarViewHolder) {
            MyCarViewHolder holder = (MyCarViewHolder) viewHolder;
            //图片
            Glide.with(mContext)
                    .load(mDatas.get(position).car_logo)
                    .placeholder(R.drawable.bg_image_default)
                    .error(R.drawable.bg_image_default)
                    .override(36, 36)
                    .into(holder.mCarImage);

            //
            holder.mCarTitle.setText(mDatas.get(position).car_brand + "  " + mDatas.get(position).car_chexing);
            holder.mCarType.setText(mDatas.get(position).car_pailiang + "  " + mDatas.get(position).car_nianfen);

            if (mDatas.get(position).is_default.equals("1")) {
                mDatas.get(position).is_default = "0";
                mIndex = position;
            }

            //设置默认点击事件
            holder.mDefaultRb.setOnClickListener(v -> {
                if(mIndex != position){
                    holder.mDefaultRb.setChecked(false);
                }
                if (mOnMyCardClickListener != null) {
                    //传出cid
                    mOnMyCardClickListener.onDefaultCar(mDatas.get(position), position);
                }
            });

            if (mIndex == position) {
                holder.mDefaultRb.setChecked(true);
            } else {
                holder.mDefaultRb.setChecked(false);
            }

            ((MyCarViewHolder) viewHolder).itemView.setOnClickListener(v -> {
                if (mOnMyCardClickListener != null) {
                    mOnMyCardClickListener.onItemClick(mDatas.get(position));
                }
            });

            ((MyCarViewHolder) viewHolder).mDeleteView.setOnClickListener(v -> {
                if (mOnMyCardClickListener != null) {
                    mOnMyCardClickListener.onItemDeleteId(mDatas.get(position).car_id, position);
                }
            });

        }
        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }


    /**
     * 设置是否默认轩车
     *
     * @param bol
     */
    public void setSelType(boolean bol) {
        this.mSelTypeBol = bol;
    }

    /**
     * 添加参数
     */
    public void addDatas(List<MyCarBean.DataBean> params) {
        mDatas = params;
        mIndex = -1;
        //强制刷新页面
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
        mItemManger.closeAllItems();
    }


    /**
     * 设置默认爱车
     */
    public void setDefaultPosition(int position) {
        mIndex = position;
        notifyDataSetChanged();
    }


    /**
     * 可以在这些点击事件之类的逻辑
     */
    class MyCarViewHolder extends RecyclerView.ViewHolder {


        View mItemView;
        @BindView(R.id.delete)
        View mDeleteView;
        @BindView(R.id.swipe)
        SwipeLayout mSwipe;
        @BindView(R.id.add_car_image)
        ImageView mCarImage;
        @BindView(R.id.add_car_title)
        TextView mCarTitle;
        @BindView(R.id.add_car_type)
        TextView mCarType;
        @BindView(R.id.add_car_rb)
        RadioButton mDefaultRb;


        public MyCarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemView = itemView;
        }
    }

    private OnMyCardClickListener mOnMyCardClickListener;

    public void setOnMyCardClickListener(OnMyCardClickListener listener) {
        this.mOnMyCardClickListener = listener;
    }

    public interface OnMyCardClickListener {
        /**
         * 删除
         */
        void onItemDeleteId(String c_id, int position);

        /**
         * 设置默认爱车
         *
         * @param bean
         */
        void onDefaultCar(MyCarBean.DataBean bean, int position);


        /**
         * 行点击事件
         */
        void onItemClick(MyCarBean.DataBean bean);


    }

}
