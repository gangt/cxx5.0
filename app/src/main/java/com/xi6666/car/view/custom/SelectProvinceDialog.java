package com.xi6666.car.view.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.xi6666.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午3:08.
 * 个人公众号 ardays
 */
public class SelectProvinceDialog extends Dialog {

    @BindView(R.id.select_province_gv)
    GridView mProvinceGv;

    private Context mContext;

    private OnItemProvinceClickListener mListener;

    /**
     * 车牌ID
     */
    private String[] province_str = {
            "京" , "津" , "冀" , "晋" , "蒙" ,"辽" , "吉" ,
            "黑" , "沪" , "苏" , "浙" , "皖" ,"闽" , "赣" ,
            "鲁" , "豫" , "鄂" , "湘" , "粤" ,"桂" , "琼" ,
            "渝" , "川" , "贵" , "云" , "藏" ,"峡" , "甘" ,
            "青" , "宁" , "新" , "港" , "澳" ,"台"
    };

    public SelectProvinceDialog(Context context) {
        super(context, R.style.dialog_untransparent);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_province,null);
        setContentView(view);
        ButterKnife.bind(this, view);

        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        //设置方向 (靠近底部)
        params.gravity = Gravity.BOTTOM;
        //大小
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        //
        getWindow().setAttributes(params);
        //写入省份
        ProvinceAdapter adapter = new ProvinceAdapter();
        mProvinceGv.setAdapter(adapter);
        //设置退出和进入动画
        getWindow().setWindowAnimations(R.style.dialog_bottom_top_anim);
    }

    public void setOnItemProvinceClickListener(OnItemProvinceClickListener listener){
        this.mListener = listener;
    }

    /**
     *
     */
    public interface OnItemProvinceClickListener{
        void onItemProvince(String province);
    }

    /**
     * 省份适配器
     */
    class ProvinceAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return province_str.length;
        }

        @Override
        public Object getItem(int position) {
            return province_str[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if(convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_province_tv,null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mProvinceTv.setText(getItem(position) + "");
            //点击事件
            viewHolder.mProvinceTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null) {
                        viewHolder.mProvinceTv.setTextColor(mContext.getResources().getColor(R.color.blueColor));
                        mListener.onItemProvince(getItem(position) + "");
                        //关闭
                        dismiss();
                    }
                }
            });
            return convertView;
        }

        class ViewHolder{
            @BindView(R.id.select_province_tv)
            TextView mProvinceTv;

            public ViewHolder(View view){
                ButterKnife.bind(this, view);
            }
        }
    }
}
