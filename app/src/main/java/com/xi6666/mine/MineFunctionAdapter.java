package com.xi6666.mine;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xi6666.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.ui.ListViewActivity;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Mr_yang on 2016/11/12.
 */

public class MineFunctionAdapter extends BaseAdapter {
    private String[] names;
    private int[] images;
    private Context mContext;
    private boolean mShowPointer = false;


    public MineFunctionAdapter(String[] names, int[] images) {
        this.names = names;
        this.images = images;
    }

    public void setPointer(boolean show) {
        mShowPointer = show;
        this.notifyDataSetChanged();

    }


    @Override
    public int getCount() {
        return names.length;
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
        mContext = parent.getContext();
        MyViewHolder myViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mine_function, null);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
            myViewHolder.badge = new QBadgeView(mContext).bindTarget(myViewHolder.mIvItemMineFunction);
            myViewHolder.badge.setBadgeTextSize(12, true);


        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        if (position == 0 && mShowPointer) {
            myViewHolder.badge.setBadgeNumber(-1).setBadgeBackgroundColor(mContext.getResources().getColor(R.color.red_text)).
                    setBadgeGravity(Gravity.END | Gravity.TOP).setGravityOffset(30, 5, true).setBadgePadding(5, true);
        }
        if (!mShowPointer) {
            myViewHolder.badge.hide(false);
        }
        Glide.with(mContext).load(images[position]).into(myViewHolder.mIvItemMineFunction);
        myViewHolder.mTxtItemMineFunction.setText(names[position]);
        return convertView;
    }

    public class MyViewHolder {
        @BindView(R.id.iv_item_mine_function)
        ImageView mIvItemMineFunction;
        @BindView(R.id.txt_item_mine_function)
        TextView mTxtItemMineFunction;
        Badge badge;

        public MyViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
