package com.xi6666.car.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.car.bean.BrandCarBean;
import com.xi6666.car.bean.SelectCarBean;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 2017/1/16 下午4:38.
 * 个人公众号 ardays
 */

public class BrandCarAdapter extends BaseExpandableListAdapter {

    /**
     * 即将要展示的数据
     */
    private List<BrandCarBean.DataBean> mStrData;
    /**
     *
     */
    private LayoutInflater mInflater;

    /**
     *
     */
    private Context mContext;

    /**
     * 比较中文顺序
     */
    private BrandCarAdapter.CitySortComparator mCitySortComparator;
    /**
     * 比较英文顺序
     */
    private LanguageComparator_EN enSort = new LanguageComparator_EN();

    /**
     *
     */
    private BrandCarAdapter.OnItemClickListener mListener;

    public BrandCarAdapter(Context context,List<BrandCarBean.DataBean> data){
        super();
        mInflater = LayoutInflater.from(context);
        mStrData = data;
        mCitySortComparator = new BrandCarAdapter.CitySortComparator();
        this.mContext = context;
        /**
         * 排序
         */
        sort();
    }

    /**
     * 排序
     */
    private void sort(){
        /**
         * 便利所有数据
         */
        for(BrandCarBean.DataBean bean : mStrData){
            if(bean != null && bean.brand_name != null)
                mLetter.add(bean);//添加到集合去
        }
        mLetter.sortKeyComparator(enSort);
        for (int i = 0, length = mLetter.size(); i < length; i++) {
            Collections.sort((mLetter.getValueListIndex(i)), mCitySortComparator);
        }
    }

    private HashList<String, BrandCarBean.DataBean> mLetter = new HashList<>(new KeySort<String, BrandCarBean.DataBean>() {
        public String getKey(BrandCarBean.DataBean brand) {
            return AssortPinyinList.getFirstChar(brand.brand_char);
        }
    });

    @Override
    public int getGroupCount() {
        return mLetter.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mLetter.getValueListIndex(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mLetter.getValueListIndex(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mLetter.getValueIndex(groupPosition, childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View contentView, ViewGroup parent) {
        BrandCarAdapter.GroupViewHolder holder;
        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.item_group_letter, null);
            holder = new BrandCarAdapter.GroupViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (BrandCarAdapter.GroupViewHolder) contentView.getTag();
        }

        holder.mGroudTv.setText(AssortPinyinList.getFirstChar(mLetter.getValueIndex(groupPosition, 0).brand_char));
        return contentView;

    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View contentView, ViewGroup parent) {
        final BrandCarAdapter.ViewHolder viewHolder;
        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.item_letter_sort, null);
            viewHolder = new BrandCarAdapter.ViewHolder(contentView);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = (BrandCarAdapter.ViewHolder) contentView.getTag();
        }
        //写入名字
        viewHolder.mChildTv.setText(mLetter.getValueIndex(groupPosition, childPosition).brand_name);
        //
        Glide.with(mContext)
                .load(mLetter.getValueIndex(groupPosition,childPosition).brand_logo)
                .override(36,36)
                .into(viewHolder.mChildIv);

        contentView.setOnClickListener(v->{
            if(mListener != null){
                mListener.onItemClick(mLetter.getValueIndex(groupPosition,childPosition));
                Log.e("TAG","调用了" + mLetter.getValueIndex(groupPosition,childPosition).toString());
            }
        });
        return contentView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public HashList<String, BrandCarBean.DataBean> getAssortList() {
        return mLetter;
    }

    public class ViewHolder {
        @BindView(R.id.child_tv)
        TextView mChildTv;
        @BindView(R.id.child_iv)
        ImageView mChildIv;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    class GroupViewHolder{
        @BindView(R.id.groud_tv)
        TextView mGroudTv;

        public GroupViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 比较中文顺序
     */
    class CitySortComparator implements Comparator<BrandCarBean.DataBean> {

        @Override
        public int compare(BrandCarBean.DataBean lhs, BrandCarBean.DataBean rhs) {
            //两个参数作比较忽略大小写  string.compareToIgnoreCase(string2);
            return lhs.brand_char.compareToIgnoreCase(rhs.brand_char);
        }
    }

    /**
     * 写入接口事件
     */
    public void setOnItemClickListener(BrandCarAdapter.OnItemClickListener listener){
        this.mListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(BrandCarBean.DataBean data);
    }

}
