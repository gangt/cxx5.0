package com.xi6666.illegal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.databean.IllegaHomeListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.id;

/**
 * Created by Mr_yang on 2017/2/7.
 */

public class IllegaHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private List<IllegaHomeListBean.DataBean> mListData = new ArrayList<>();
    private OnItemSwipeDeleteListnner mOnItemSwipeDeleteListnner;


    public void setListData(List<IllegaHomeListBean.DataBean> listData) {
        mListData = listData;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_illagehome, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //条目点击事件
        ((ViewHolder) holder).mItem.setOnClickListener(v -> {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onItemClick(position);
        });
        ((ViewHolder) holder).mTvItemIllagehome.setText(mListData.get(position).getCar_no());
        ((ViewHolder) holder).mImageView.setOnClickListener(v -> {

            if (mOnItemSwipeDeleteListnner != null) {
                mOnItemSwipeDeleteListnner.onItemDelete(position);
            }
        });
        //判断是否绑定
        if (mListData.get(position).getIs_binding() == 1) {
            ((ViewHolder) holder).mIvItemIllagehome.setVisibility(View.VISIBLE);
        } else {
            ((ViewHolder) holder).mIvItemIllagehome.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_illagehome)
        ImageView mIvItemIllagehome;
        @BindView(R.id.tv_item_illagehome)
        TextView mTvItemIllagehome;
        @BindView(R.id.iv_item_illagehome_delete)
        ImageView mImageView;
        @BindView(R.id.ll_item_illagehome)
        LinearLayout mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemSwipeDeleteListnner {
        void onItemDelete(int position);
    }

    public void setOnItemSwipeDeleteListnner(OnItemSwipeDeleteListnner onItemSwipeDeleteListnner) {
        mOnItemSwipeDeleteListnner = onItemSwipeDeleteListnner;
    }
}
