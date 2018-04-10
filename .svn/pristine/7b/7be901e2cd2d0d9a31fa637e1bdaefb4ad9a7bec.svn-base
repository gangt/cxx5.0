package com.xi6666.car.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.car.bean.CarBrandTypeBean;
import com.xi6666.car.view.custom.BorderTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:38.
 * 个人公众号 ardays
 */

public class SelectCarDialog extends Dialog {

    @BindView(R.id.car_type_id)
    BorderTextView mTypeTv;
    @BindView(R.id.car_type_list)
    ListView mCarTypeLv;

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 点击事件
     */
    private OnItemClickListener mItemListener;

    public SelectCarDialog(Context context) {
        super(context, R.style.dialog_untransparent);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_car_type_sel, null);
        setContentView(view);
        ButterKnife.bind(this, view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        //设置方向 (靠近底部)
        params.gravity = Gravity.BOTTOM;
        //大小
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        //
        getWindow().setAttributes(params);
        //设置退出和进入动画
        getWindow().setWindowAnimations(R.style.dialogAnima);
    }


    public void setTypeText(String type){
        mTypeTv.setText(type);
    }



    /**
     *  写入品牌属性
     */
    public void setTypeList(Activity mActivity, CarBrandTypeBean bean){
        final CarTypeAdapter adapter = new CarTypeAdapter(bean);
        Looper.prepare();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCarTypeLv.setAdapter(adapter);
            }
        });
    }


    public SelectCarDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SelectCarDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    class CarTypeAdapter extends BaseAdapter {
        private List<CarBrandTypeBean.TypeBean> mBean;

        public CarTypeAdapter(CarBrandTypeBean bean){
            if(bean.data != null) {
                this.mBean = bean.data;
            }else{
                mBean = new ArrayList<>();
            }
        }

        @Override
        public int getCount() {
            return mBean.size();
        }

        @Override
        public Object getItem(int position) {
            return mBean.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_car_type,null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final CarBrandTypeBean.TypeBean data = (CarBrandTypeBean.TypeBean)getItem(position);
            viewHolder.mListTv.setText(data.car_type);
            viewHolder.mListTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mItemListener != null)
                        mItemListener.onItemClick(data,position);
                }
            });
            return convertView;
        }

        /**
         * 存储化减少findViewById
         */
        class ViewHolder{
            @BindView(R.id.car_type_list_tv)
            TextView mListTv;

            public ViewHolder(View view){
                ButterKnife.bind(this, view);
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(CarBrandTypeBean.TypeBean bean, int position);
    }

    /**
     * 写入接口
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mItemListener = listener;
    }
}
