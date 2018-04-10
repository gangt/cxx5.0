package com.xi6666.classification.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.car.adapter.AssortPinyinList;
import com.xi6666.car.adapter.HashList;
import com.xi6666.car.adapter.KeySort;
import com.xi6666.car.adapter.LanguageComparator_EN;
import com.xi6666.classification.view.mvp.bean.AllBrandBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午5:19.
 * 个人公众号 ardays
 */

public class AllBrandAdapter  extends BaseExpandableListAdapter {

    /**
     * 即将要展示的数据
     */
    private List<AllBrandBean.DataBean> mStrData;
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
    private CitySortComparator mCitySortComparator;
    /**
     * 比较英文顺序
     */
    private LanguageComparator_EN enSort = new LanguageComparator_EN();

    /**
     *
     */
    private OnItemClassifyClick mListener;

    public AllBrandAdapter(Context context) {
        super();
        mInflater = LayoutInflater.from(context);
        mStrData = new ArrayList<>();
        mCitySortComparator = new CitySortComparator();
        this.mContext = context;
        /**
         * 排序
         */
        sort();
    }

    /**
     * 排序
     */
    private void sort() {
        /**
         * 便利所有数据
         */
        for (AllBrandBean.DataBean bean : mStrData) {
            if(bean.first_chr == null){
                //
                bean.first_chr = "#";
            }
            if (bean != null)
                mLetter.add(bean);//添加到集合去
        }
        mLetter.sortKeyComparator(enSort);
        for (int i = 0, length = mLetter.size(); i < length; i++) {
            Collections.sort((mLetter.getValueListIndex(i)), mCitySortComparator);
        }
    }

    private HashList<String, AllBrandBean.DataBean> mLetter = new HashList<>(new KeySort<String, AllBrandBean.DataBean>() {
        public String getKey(AllBrandBean.DataBean selectCarBean) {
            return AssortPinyinList.getFirstChar(selectCarBean.first_chr);
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
        GroupViewHolder holder;
        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.item_group_letter, null);
            holder = new GroupViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (GroupViewHolder) contentView.getTag();
        }

        holder.mGroudTv.setText(AssortPinyinList.getFirstChar(mLetter.getValueIndex(groupPosition, 0).first_chr));
        return contentView;

    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View contentView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (contentView == null) {
            contentView = mInflater.inflate(R.layout.item_one_classify, null);
            viewHolder = new ViewHolder((TextView) contentView);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) contentView.getTag();
        }

        viewHolder.mClassifyTv.setText(mLetter.getValueIndex(groupPosition, childPosition).brand_name);
        viewHolder.mClassifyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onItemView(mLetter.getValueIndex(groupPosition, childPosition));
                }
            }
        });
        return contentView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public HashList<String, AllBrandBean.DataBean> getAssortList() {
        return mLetter;
    }


    class ViewHolder {
        TextView mClassifyTv;

        public ViewHolder(TextView view) {
            this.mClassifyTv = view;
        }
    }

    class GroupViewHolder {
        @BindView(R.id.groud_tv)
        TextView mGroudTv;

        public GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 比较中文顺序
     */
    class CitySortComparator implements Comparator<AllBrandBean.DataBean> {

        @Override
        public int compare(AllBrandBean.DataBean lhs, AllBrandBean.DataBean rhs) {
            //两个参数作比较忽略大小写  string.compareToIgnoreCase(string2);
            return lhs.first_chr.compareToIgnoreCase(rhs.first_chr);
        }
    }

    /**
     * 更新
     * @param data
     */
    public void update(List<AllBrandBean.DataBean> data){
        mLetter.clear();
        this.mStrData = data;
        sort();
    }

    /**
     * 写入接口事件
     */
    public void setOnItemClassifyClick(OnItemClassifyClick listener) {
        this.mListener = listener;
    }

    public interface OnItemClassifyClick {
        void onItemView(AllBrandBean.DataBean bean);
    }
}