package com.xi6666.address.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.address.fragment.mvp.bean.PersonalAddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/24 下午2:55.
 * 个人公众号 ardays
 */

public class PersonalAddressListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<PersonalAddressBean.DataBean> mDatas;  //数据
    private int mSelectedPosition = -1;

    public PersonalAddressListAdapter(Context context) {
        this.mContext = context;
        this.mDatas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_receipt_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindAddress((ViewHolder) holder, position);
    }

    private void onBindAddress(ViewHolder holder, int position) {
        PersonalAddressBean.DataBean data = mDatas.get(position);

        if (data.is_default.equals("1")) {
            data.is_default = "";
            mSelectedPosition = position;
        }
        //数据
        holder.mDefaultRb.setChecked(mSelectedPosition == position);       //是否默认地址
        holder.mNameTv.setText(data.consignee);     //收货人姓名
        holder.mTelTv.setText(data.mobile);     //收货人电话
        String address_str = data.province_name + data.city_name + data.district_name + data.address;
        holder.mAddressTv.setText(address_str);

        //点击事件
        //编辑地址的点击事件
//        holder.mEditIv.setOnClickListener(v -> {
//        });

        holder.mEditIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPersonalAddressListListener != null)
                    mPersonalAddressListListener.onEditClick(v, data);
            }
        });

        holder.mDefaultRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSelectedPosition != position){
                    holder.mDefaultRb.setChecked(false);
                }
                //设置默认地址的逻辑
                if (mPersonalAddressListListener != null) {
                    if (position != mSelectedPosition) {
                        mPersonalAddressListListener.onDefaultClick(v, data.address_id, position);
                    }
                }
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择收货地址
                if (mPersonalAddressListListener != null)
                    mPersonalAddressListListener.onItemClick(v, data);
            }
        });
    }


    /**
     * 设置默认坐标
     *
     * @param position
     */
    public void setDefaultPosition(int position) {
        this.mSelectedPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 添加数据
     */
    public void addAll(List<PersonalAddressBean.DataBean> data) {
        mSelectedPosition = -1;
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.receipt_address_default_rb)
        RadioButton mDefaultRb; //是否默认地址
        @BindView(R.id.receipt_address_name_tv)
        TextView mNameTv;   //名字
        @BindView(R.id.receipt_address_tel_tv)
        TextView mTelTv;    //电话
        @BindView(R.id.receipt_address_address_iv)
        TextView mAddressTv;    //地址
        @BindView(R.id.receipt_address_edit_iv)
        ImageView mEditIv;  //编辑


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    //                      @代理方法
    private PersonalAddressListListener mPersonalAddressListListener;

    public void setPersonalAddressListListener(PersonalAddressListListener listener) {
        this.mPersonalAddressListListener = listener;
    }

    public interface PersonalAddressListListener {
        /**
         * 点击默认
         */
        void onDefaultClick(View view, String address_id, int position);

        /**
         * 点击编辑
         */
        void onEditClick(View view, PersonalAddressBean.DataBean bean);


        /**
         * 点击中间内容
         */
        void onItemClick(View view, PersonalAddressBean.DataBean bean);
    }
}
