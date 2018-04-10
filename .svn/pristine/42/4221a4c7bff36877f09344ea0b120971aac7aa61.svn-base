package com.xi6666.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.network.ApiRest;


/**
 * Created by Mr_yang on 2016/8/25.
 */
public class AddOilServerDialog extends Dialog implements View.OnClickListener {
    private Context mContext;

    private static final String TAG = "AddOilServerDialog";
    private ImageView mIvBg;
    private TextView mIvLook;
    private TextView mknow;
    private View mView;

    public AddOilServerDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        init(context);
    }

    private void init(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_addoil_server, null);
        mIvBg = (ImageView) mView.findViewById(R.id.iv_bg);
        mIvLook = (TextView) mView.findViewById(R.id.iv_look);
        mIvLook.setOnClickListener(this);
        mknow = (TextView) mView.findViewById(R.id.txt_konw);
        mknow.setOnClickListener(this);
        setContentView(mView);
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        //大小
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
        mView.setOnClickListener(v -> {
            this.dismiss();
        });
    }
    /**
     * @data 创建时间:2016/8/25
     * @author peng
     * @desc 隐藏查看证明合同
     * @version
     */
    public void setLookGone() {
        mIvLook.setVisibility(View.GONE);

    }

    public void setKnowGone() {
        mknow.setVisibility(View.GONE);

    }

    public void setOutSideClose() {

        this.setCanceledOnTouchOutside(true);
    }


    /**
     * @data 创建时间:2016/8/25
     * @author peng
     * @desc 设置背景颜色
     * @version
     */
    public void setBg(Drawable drawable) {
        mIvBg.setImageDrawable(drawable);
    }

    //加载网络地址图片
    public void setBg(String url) {
        Glide.with(mContext).load(url).into(mIvBg);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txt_konw:
                this.dismiss();
                break;
            case R.id.iv_look:
                //TODO 查看合同
                HtmlAct.unsealActivity((Activity) mContext, ApiRest.baseUrl + ApiRest.ADDOILCONTRACT);
                this.dismiss();
                break;
        }
    }
}
