package com.xi6666.illegal.other;

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
 * 创建人 黄应得
 * 时间 2016/6/13 0013.
 * 功能
 */
public class WeiZhangSelectProvinceDialog extends Dialog {

    @BindView(R.id.select_province_gv)
    GridView mProvinceGv;

    private Context mContext;

    private OnItemProvinceClickListener mListener;

    /**
     * 车牌ID
     */
    /*private String[] province_str = {
            "京" , "津" , "冀" , "晋" , "蒙" ,"辽" , "吉" ,
            "黑" , "沪" , "苏" , "浙" , "皖" ,"闽" , "赣" ,
            "鲁" , "豫" , "鄂" , "湘" , "粤" ,"桂" , "琼" ,
            "渝" , "川" , "贵" , "云" , "藏" ,"峡" , "甘" ,
            "青" , "宁" , "新" , "港" , "澳" ,"台"
    };*/
    private String[] province_str;

    public WeiZhangSelectProvinceDialog(Context context,String[] province_str) {
        super(context, R.style.dialog_untransparent);
        this.mContext = context;
        this.province_str = province_str;
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
    class ProvinceAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if (province_str != null)
                return province_str.length;
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (province_str != null)
                return province_str[position];
            return null;
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
