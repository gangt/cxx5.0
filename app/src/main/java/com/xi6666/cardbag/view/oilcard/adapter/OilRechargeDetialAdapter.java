package com.xi6666.cardbag.view.oilcard.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.databean.RechargeDetialBean;
import com.xi6666.view.MesureListView;

import java.util.List;

/**
 * Created by Mr_yang on 2017/3/11.
 */

public class OilRechargeDetialAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private final int TYPE_1 = 0;
    private final int TYPE_2 = 1;

    private final LayoutInflater mLayoutInflater;
    private List<RechargeDetialBean.DataBeanX.BackListBean> mDataBeen;
    private List<RechargeDetialBean.DataBeanX.ShouldDataBean> mShouldDataBeen;

    public void setDataBeen(List<RechargeDetialBean.DataBeanX.BackListBean> dataBeen, List<RechargeDetialBean.DataBeanX.ShouldDataBean> shouldDataBeen) {
        mDataBeen = dataBeen;
        this.mShouldDataBeen = shouldDataBeen;
        this.notifyDataSetChanged();
    }

    public OilRechargeDetialAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public int getGroupCount() {
        return mDataBeen.size() + 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        int type = getItemViewType(groupPosition);
        switch (type) {
            case TYPE_1:
                convertView = mLayoutInflater.inflate(R.layout.item_recharge_tab_one, null);
                TextView tvTitle = (TextView) convertView.findViewById(R.id.txt_recharge_tab_title);
                TextView tvSave = (TextView) convertView.findViewById(R.id.txt_recharge_tab_save);
                TextView tvUsing = (TextView) convertView.findViewById(R.id.tv_recharge_tab_using);
                ImageView ivState = (ImageView) convertView.findViewById(R.id.iv_recharge_tab_arrow);
                // String package_name = mDataBeen.get(groupPosition + 1).getPackage_name();
                //设置标题
                tvTitle.setText(mDataBeen.get(groupPosition - 1).getPackage_name());
                //设置剩余金额
                if (mDataBeen.get(groupPosition - 1).getNot_already() == 0) {
                    tvSave.setVisibility(View.GONE);
                } else {
                    tvSave.setText("(剩余" + mDataBeen.get(groupPosition - 1).getNot_already() + "元未到账)");
                }
                //设置是否是使用中
                if (TextUtils.equals(mDataBeen.get(groupPosition - 1).getPackage_left_number(), "0")) {
                    //已完成
                    tvUsing.setText("已完成");
                    tvUsing.setTextColor(mContext.getResources().getColor(R.color.themeColor));
                    if (isExpanded) {
                        ivState.setBackgroundResource(R.mipmap.ic_recharge_tab_green_down);
                    } else {
                        ivState.setBackgroundResource(R.mipmap.ic_recharge_tab_green_up);
                    }
                } else {
                    //使用中
                    tvUsing.setText("使用中");
                    tvUsing.setTextColor(mContext.getResources().getColor(R.color.orange));
                    if (isExpanded) {
                        ivState.setBackgroundResource(R.mipmap.ic_recharge_tab_yellow_down);
                    } else {
                        ivState.setBackgroundResource(R.mipmap.ic_recharge_tab_yellow_up);
                    }
                }


                break;
            case TYPE_2:
                convertView = mLayoutInflater.inflate(R.layout.item_recharge_tab_two, null);
                MesureListView mesureListView = (MesureListView) convertView.findViewById(R.id.mlv_recharge_tab);
                mesureListView.setAdapter(new TabAdapter());

                break;

        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        int type = getItemViewType(groupPosition);

        switch (type) {
            case TYPE_1:
                convertView = mLayoutInflater.inflate(R.layout.item_recharge_one, null);
                MesureListView mesureListView1 = (MesureListView) convertView.findViewById(R.id.mlv_recharge_detial);

                switch (childPosition) {
                    case 0:
                        PackageAdapter packageAdapter = new PackageAdapter();
                        packageAdapter.setGropCount(groupPosition - 1);
                        mesureListView1.setAdapter(packageAdapter);
                        break;
                    default:
                        break;

                }

                break;
            case TYPE_2:

                convertView = mLayoutInflater.inflate(R.layout.item_recharge_two, null);


                switch (childPosition) {
                    case 0:

                        break;

                    default:

                        break;

                }
                break;

        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public int getItemViewType(int groupPosition) {
        int p = groupPosition;
        if (p == 0) {
            return TYPE_2;
        } else if (p == 1) {
            return TYPE_1;
        } else {
            return TYPE_1;
        }
    }

    public class PackageAdapter extends BaseAdapter {
        private int mGropCount;

        public void setGropCount(int gropCount) {
            mGropCount = gropCount;
        }

        @Override
        public int getCount() {
            if (mGropCount >= 0) {
                return mDataBeen.get(mGropCount).getBack_data().size();
            } else {
                return 0;
            }
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
            View rootView = mLayoutInflater.inflate(R.layout.item_recharge_package, null);
            TextView tvOne = (TextView) rootView.findViewById(R.id.txt_package_one);
            TextView tvTwo = (TextView) rootView.findViewById(R.id.txt_package_two);
            TextView tvThr = (TextView) rootView.findViewById(R.id.txt_package_thr);
            tvOne.setText(mDataBeen.get(mGropCount).getBack_data().get(position).getBack_money());
            tvTwo.setText(mDataBeen.get(mGropCount).getBack_data().get(position).getBack_time());
            //设置到账字体颜色
            if (TextUtils.equals(mDataBeen.get(mGropCount).getBack_data().get(position).getBack_status(), "已到账")) {
                tvThr.setTextColor(mContext.getResources().getColor(R.color.themeColor));
            }
            if (TextUtils.equals(mDataBeen.get(mGropCount).getBack_data().get(position).getBack_status(), "未到账")) {
                tvThr.setTextColor(mContext.getResources().getColor(R.color.txthomeGoodsOld));
            }
            if (TextUtils.equals(mDataBeen.get(mGropCount).getBack_data().get(position).getBack_status(), "到账中")) {
                tvThr.setTextColor(mContext.getResources().getColor(R.color.orange));
            }
            tvThr.setText(mDataBeen.get(mGropCount).getBack_data().get(position).getBack_status());
            return rootView;
        }
    }


    public class TabAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mShouldDataBeen.size();
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
            View rootView = mLayoutInflater.inflate(R.layout.item_recharge_package, null);
            TextView tvOne = (TextView) rootView.findViewById(R.id.txt_package_one);
            TextView tvTwo = (TextView) rootView.findViewById(R.id.txt_package_two);
            TextView tvThr = (TextView) rootView.findViewById(R.id.txt_package_thr);
            tvOne.setText(mShouldDataBeen.get(position).getBack_money());
            tvTwo.setText(mShouldDataBeen.get(position).getBack_time());

            if (TextUtils.equals(mShouldDataBeen.get(position).getBack_status(), "已到账")) {
                tvThr.setTextColor(mContext.getResources().getColor(R.color.themeColor));
            }
            if (TextUtils.equals(mShouldDataBeen.get(position).getBack_status(), "未到账")) {
                tvThr.setTextColor(mContext.getResources().getColor(R.color.txthomeGoodsOld));
            }
            if (TextUtils.equals(mShouldDataBeen.get(position).getBack_status(), "到账中")) {
                tvThr.setTextColor(mContext.getResources().getColor(R.color.orange));
            }
            tvThr.setText(mShouldDataBeen.get(position).getBack_status());
            return rootView;
        }
    }
}


