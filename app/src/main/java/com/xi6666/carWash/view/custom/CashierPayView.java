package com.xi6666.carWash.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/3 上午11:10.
 * 个人公众号 ardays
 * 收银台下面的itemView  选择支付方式部分
 */

public class CashierPayView extends LinearLayout implements CashierPayViewImpl {
    @BindView(R.id.cashier_pay_lv)
    ListView mItemLv;   //item
    @BindView(R.id.cashier_pay_title_tv)
    TextView mTitleTv;  //标题

    /**
     * 上下文
     */
    private Context mContext;
    /**
     * itemAdapter
     */
    private CashierItemAdapter mCashierAdapter;
    /**
     * item数据
     */
    List<CashierBean> mDatas;

    /**
     * 是否可以取消选择(默认是不可以)
     */
    boolean IS_UNSELECT = false;

    /**
     * 判断是否取消点击
     */
     boolean IS_CLICK = true;


    public CashierPayView(Context context) {
        this(context, null);
    }

    public CashierPayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CashierPayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //获取上下文
        this.mContext = context;
        View view = LinearLayout.inflate(context, R.layout.view_cashier_pay, this);
        ButterKnife.bind(this, view);
        initXml(attrs);
        mCashierAdapter = new CashierItemAdapter();
        mItemLv.setAdapter(mCashierAdapter);
    }

    /**
     * 初始化属性
     *
     * @param attrs
     */
    private void initXml(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.CashierPayView);
            int type = a.getInt(R.styleable.CashierPayView_cashierItemType, 0);
            //设置标题
            String title = a.getString(R.styleable.CashierPayView_cashierTitle);
            mTitleTv.setText(title);

            if (type == 1) {
                mDatas = CashierBean.getPayData();
            }

            //回收
            a.recycle();
        }
    }


    /**
     * 判断数据是否需要隐藏
     */
    private void dataNotNil() {
        //如果数据为空 或者 数据条数小于1 就让他隐藏
        if (mDatas == null || mDatas.size() < 0) setVisibility(GONE);
        else setVisibility(VISIBLE);
    }


    @Override
    public void setItemDatas(List<CashierBean> datas) {
        if (datas == null) return;
        //刷新数据
        this.mDatas = datas;
        dataNotNil();
        mCashierAdapter.notifyDataSetChanged();
    }

    @Override
    public void setUnSelect(boolean bol) {
        this.IS_UNSELECT = bol;
        mCashierAdapter.notifyDataSetChanged();
    }

    public void setSelectPosition(int selectPosition) {
        mCashierAdapter.selectPosition(selectPosition);
    }


    /**
     * 取消选择
     */
    public void unSelect(){
        mCashierAdapter.unSelect();
    }


    /**
     * 是否能选择支付方式
     * @param bol
     */
    public void setPayClick(boolean bol){
        this.IS_CLICK = bol;
        mCashierAdapter.notifyDataSetChanged();
    }


    /**
     * ItemAdapter
     */
    class CashierItemAdapter extends BaseAdapter {
        private int mSelectIndex = 0;

        @Override
        public int getCount() {
            if (mDatas == null) return 0;
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            /**
             * 复用item，防止数据过多
             */
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_view_cashier_pay, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //写入数据
            //获取data
            final CashierBean data = (CashierBean) getItem(position);

            //判断icon是url还是本地写入
            if (TextUtils.isEmpty(data.icon)) {
                if(IS_CLICK) {
                    holder.mIconIv.setImageResource(data.icon_resId);
                }else{
                    holder.mIconIv.setImageResource(data.gone_icon_resId);
                }
            } else {
                Glide.with(mContext)
                        .load(data.icon)
                        .centerCrop()
                        .into(holder.mIconIv);
            }

            //写入标题
            holder.mTitleTv.setText(data.title);


            if(IS_CLICK) {
                //是否选中
                holder.mSelectCK.setChecked(mSelectIndex == position);
            }else{
                holder.mSelectCK.setChecked(false);
            }

            //点击事件
            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (IS_CLICK) {
                        if (IS_UNSELECT) {  //判断是否可以取消选择
                            if (mOnCashierItemListener != null)
                                mOnCashierItemListener.onIteClick(position, holder.mSelectCK.isChecked());
                        } else {
                            mSelectIndex = position;
                        }
                        notifyDataSetChanged();
                        if (mOnCashierPayListener != null)
                            mOnCashierPayListener.onPayListener(data.title, data.id);
                    }else{

                    }
                }
            });

            return convertView;
        }

        /**
         * 取消所有选中
         */
        public void unSelect() {
            mSelectIndex = -1;
            notifyDataSetChanged();
        }

        /**
         * 选中的下标
         */
        public void selectPosition(int position) {
            this.mSelectIndex = position;
            notifyDataSetChanged();
        }


        /**
         * 缓存的item
         */
        class ViewHolder {
            @BindView(R.id.item_view_cashier_pay_icon_iv)
            ImageView mIconIv;      //图标
            @BindView(R.id.item_view_cashier_pay_title_tv)
            TextView mTitleTv;      //标题
            @BindView(R.id.item_view_cashier_pay_select_ck)
            CheckBox mSelectCK;     //是否选中


            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


    /*                                事件回调                                */


    /**
     * 选择支付方式
     */
    private OnCashierPayListener mOnCashierPayListener;

    public void setOnCashierPayListener(OnCashierPayListener listener) {
        this.mOnCashierPayListener = listener;
    }

    public interface OnCashierPayListener {
        void onPayListener(String type, int id);
    }


    /**
     * 列表点击事件 优惠方式
     */
    private OnCashierItemListener mOnCashierItemListener;

    public void setOnCashierItemListener(OnCashierItemListener listener) {
        this.mOnCashierItemListener = listener;
    }

    public interface OnCashierItemListener {
        /**
         * 判断是否选中
         *
         * @param position 下标
         * @param select   是否选中
         */
        void onIteClick(int position, boolean select);
    }
}
