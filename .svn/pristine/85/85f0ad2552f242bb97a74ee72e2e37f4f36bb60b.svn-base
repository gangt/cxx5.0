package com.xi6666.cardbag.view.oilcard.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.xi6666.R;
import com.xi6666.cardbag.view.mvp.bean.OilCardListBean;
import com.xi6666.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建者 sunsun
 * 时间 16/8/25 下午2:11.
 * 个人公众号 ardays
 * 我的加油卡适配器
 */
public class MyOilCardListAdapter extends RecyclerSwipeAdapter {


    /**
     * 图片加载
     */
    RequestManager mGlide;

    /**
     * 适配器
     */
    Activity mActivity;

    /**
     * 数据
     */
    List<OilCardListBean.DataBean> mDatas;

    /**
     * 判断当前选中数量是第几个
     */
    private int mPosition = -1;

    public MyOilCardListAdapter(RequestManager manager, Activity activity) {
        this.mGlide = manager;
        this.mActivity = activity;
        this.mDatas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_my_oil_card, parent, false);
        return new MyOilCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyOilCardViewHolder) {
            MyOilCardViewHolder viewHolder = (MyOilCardViewHolder) holder;
            OilCardListBean.DataBean data = mDatas.get(position);
            //这里写逻辑
            viewHolder.mSwipe.setShowMode(SwipeLayout.ShowMode.PullOut);
            viewHolder.mSwipe.setDragEdge(SwipeLayout.DragEdge.Right);


            /**
             * 判断是什么加油卡
             *  1是中石化
             *  2是中石油
             */
            if (TextUtils.equals(data.card_type, "1")) {
                viewHolder.mOilCardIv.setImageResource(R.mipmap.ic_sinopec);
                viewHolder.mOildTypeTv.setText("中石化");
                viewHolder.mBgView.setBackgroundResource(R.mipmap.bg_sinopec);
            } else if (TextUtils.equals(data.card_type, "2")) {
                viewHolder.mOilCardIv.setImageResource(R.mipmap.ic_petro_china);
                viewHolder.mOildTypeTv.setText("中石油");
                viewHolder.mBgView.setBackgroundResource(R.mipmap.bg_petro_china);
            }
            //填写持卡人
            viewHolder.mOilCardName.setText("持卡人:" + data.card_name);
            //格式化油卡
            String newStr = data.card_number;
            newStr = newStr.replace(" ", "");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < newStr.length(); i += 4) {
                if (i > 0) {
                    sb.append(" ");
                }
                if (i + 4 <= newStr.length()) {
                    sb.append(newStr.substring(i, i + 4));
                } else {
                    sb.append(newStr.substring(i, newStr.length()));
                }
            }
            //填写油卡编号
            viewHolder.mOilCardNum.setText(sb.toString());

            if (TextUtils.equals(data.is_default, "1")) {
                this.mPosition = position;
                data.is_default = "";
            }
            /**
             * 是否默认
             */
            if (mPosition == position) {
                viewHolder.mOidCardCheckRb.setChecked(true);
            } else {
                viewHolder.mOidCardCheckRb.setChecked(false);
            }


        }
        mItemManger.bindView(holder.itemView, position);
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
     * 添加数据
     *
     * @param data 数据源
     */
    public void addDatas(List<OilCardListBean.DataBean> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void update(List<OilCardListBean.DataBean> data) {
        mPosition = -1;
        mDatas.clear();
        addDatas(data);
    }

    /**
     * 删除某一项
     */
    public void remove(int position) {
        if (position == mPosition) {
            mPosition = -1;
        }
        mDatas.remove(position);
        notifyDataSetChanged();
        LogUtil.e("TAG", "index--->" + position);
        mItemManger.closeAllItems();
        if (mDatas.size() == 0) {
            if (mListener != null) mListener.listNull();
            return;
        }
    }


    /**
     * 删除数据
     */
    public void clear() {
        mDatas.clear();
        mPosition = -1;
        notifyDataSetChanged();
    }


    /**
     * 设置默认油卡
     */
    public void setDefaultOiLCard(int position) {
        this.mPosition = position;
        notifyDataSetChanged();
    }


    class MyOilCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_oil_ll)
        View mBgView;       //背景
        @BindView(R.id.swipe)
        SwipeLayout mSwipe;         //右滑文本
        @BindView(R.id.item_oil_card_iv)
        ImageView mOilCardIv;      //加油图片
        @BindView(R.id.item_oil_card_type)
        TextView mOildTypeTv;      //油卡类型 (中石化加油卡)
        @BindView(R.id.item_oil_card_name)
        TextView mOilCardName;      //持卡人
        @BindView(R.id.item_oil_card_number)
        TextView mOilCardNum;      //加油卡卡号 (中石化加油卡)
        @BindView(R.id.item_oil_card_rb)
        RadioButton mOidCardCheckRb; //默认油卡

        @BindView(R.id.btn_item_oil_card_arrival)
        Button mBtnArrival;//到账记录

        @BindView(R.id.btn_item_oil_card_recharge)
        Button mRecharge;//充值记录
        @BindView(R.id.txt_item_oil_card_default)
        TextView mTvDefault;

        public MyOilCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        /**
         * 设置默认油卡
         */
        @OnClick({R.id.item_oil_card_rb, R.id.txt_item_oil_card_default})
        void onDefaultCardClick() {
            if (mListener != null) {
                mListener.onOilCardData(mDatas.get(getAdapterPosition() - 1), getAdapterPosition() - 1);
            }
        }


        /**
         * 删除事件
         */
        @OnClick(R.id.delete)
        void onDeleteClick() {
            if (getAdapterPosition() - 1 >= 0) {
                if (mListener != null) {
                    mListener.onDeleteOilCard(mDatas.get(getAdapterPosition() - 1), getAdapterPosition() - 1);
                }
            }
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() - 1 >= 0) {
                if (mListener != null) {
                    mListener.onItemClick(mDatas.get(getAdapterPosition() - 1), getAdapterPosition() - 1);
                }
            }
        }

        /*
        * 到账记录
        * */
        @OnClick(R.id.btn_item_oil_card_arrival)
        void onArrivalClick() {
            if (getAdapterPosition() - 1 >= 0) {
                if (mListener != null) {
                    mListener.onArrivalClick(mDatas.get(getAdapterPosition() - 1), getAdapterPosition() - 1);
                }
            }
        }

        /*
        * 充值记录
        * */
        @OnClick(R.id.btn_item_oil_card_recharge)
        void onRechargeClick() {
            if (getAdapterPosition() - 1 >= 0) {
                if (mListener != null) {
                    mListener.onRechargeClick(mDatas.get(getAdapterPosition() - 1), getAdapterPosition() - 1);
                }
            }
        }
    }


    /**
     * 获取数据
     *
     * @return 数据多少条
     */
    public int getLength() {
        return mDatas.size();
    }


    private OnOilCardListener mListener;

    public void setOnOilCardListener(OnOilCardListener listener) {
        this.mListener = listener;
    }


    public interface OnOilCardListener {
        /**
         * 设置默认
         */
        void onOilCardData(OilCardListBean.DataBean bean, int position);

        /**
         * 删除
         */
        void onDeleteOilCard(OilCardListBean.DataBean bean, int position);

        /**
         * 点击事件
         */
        void onItemClick(OilCardListBean.DataBean bean, int position);

        /**
         * 列表为空
         */
        void listNull();

        void onArrivalClick(OilCardListBean.DataBean bean, int position);

        void onRechargeClick(OilCardListBean.DataBean bean, int position);

    }
}
