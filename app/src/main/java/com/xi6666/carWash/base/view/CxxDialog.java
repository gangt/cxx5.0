package com.xi6666.carWash.base.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.utils.DimenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建者 sunsun
 * 时间 16/11/22 下午5:21.
 * 个人公众号 ardays
 */

public class CxxDialog extends Dialog {

    private Context mContext;


    @BindView(R.id.title)
    TextView mTitleTv;      //这是标题
    @BindView(R.id.message)
    TextView mMessageTv;      //内容
    @BindView(R.id.dialog_left_tv)
    TextView mLeftTv;      //这是设置左边
    @BindView(R.id.dialog_right_tv)
    TextView mRightTv;      //这是右边


    /**
     * 左边点击事件
     */
    private OnDialogLeftClickListener mLeftListener;
    /**
     * 左边点击事件
     */
    private OnDialogRightClickListener mRightListener;


    public CxxDialog(Context context) {
        super(context,R.style.transpant_bg_dialog);
        this.mContext = context;
        init(context);
    }

    /**
     * 进行初始化
     */
    private void init(Context context){
        //创建布局
        View view = View.inflate(context, R.layout.dialog_cxx,null);
        setContentView(view);
        ButterKnife.bind(this,view);
        //获取屏幕信息
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        Window window = getWindow();
        window.setWindowAnimations(R.style.dialogAnima);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置弹框的大小
        getWindow().setAttributes(params);
    }


    /**
     * 设置弹窗左边按钮文字
     * @param text
     * @return
     */
    public CxxDialog setLeftText(String text)
    {
        mLeftTv.setText(text);
        return this;
    }
    public CxxDialog setLeftText(int resId)
    {
        String text = mContext.getString(resId);
        mLeftTv.setText(text);
        return this;
    }


    /**
     * 设置左边按钮文字颜色
     */
    public CxxDialog setLeftTextColor(int resId)
    {
        mLeftTv.setTextColor(mContext.getResources().getColor(resId));
        return this;
    }

    /**
     * 实例化左边点击事件接口
     */
    public CxxDialog setOnDialogLeftClickListener(OnDialogLeftClickListener listener)
    {
        this.mLeftListener = listener;
        return this;
    }


    /**
     * 弹窗左边按钮点击事件
     */
    @OnClick(R.id.dialog_left_tv)
    void onLeftClick(View view){
        if(mLeftListener != null) mLeftListener.onClick(view);
    }


    /**
     * 设置弹窗右边按钮文字
     * @param text
     * @return
     */
    public CxxDialog setRightText(String text)
    {
        mRightTv.setText(text);
        return this;
    }
    public CxxDialog setRightText(int resId)
    {
        String text = mContext.getString(resId);
        mRightTv.setText(text);
        return this;
    }


    /**
     * 设置右边按钮文字颜色
     */
    public CxxDialog setRightTextColor(int resId)
    {
        mRightTv.setTextColor(resId);
        return this;
    }

    /**
     * 实例化右边点击事件接口
     */
    public CxxDialog setOnDialogRightClickListener(OnDialogRightClickListener listener)
    {
        this.mRightListener = listener;
        return this;
    }


    /**
     * 弹窗右边按钮点击事件
     */
    @OnClick(R.id.dialog_right_tv)
    void onRightText(View view){
        if(mRightListener != null) mRightListener.onClick(view);
    }


    /**
     * 设置弹窗标题栏文字
     * @param text
     * @return
     */
    public CxxDialog setTitleText(String text)
    {
        mTitleTv.setText(text);
        return this;
    }
    public CxxDialog setTitleText(int resId)
    {
        String text = mContext.getString(resId);
        mTitleTv.setText(text);
        return this;
    }

    /**
     * 设置弹窗内容消息
     * @param text
     * @return
     */
    public CxxDialog setMessageText(String text)
    {
        mMessageTv.setText(text);
        return this;
    }
    public CxxDialog setMessageText(int resId)
    {
        String text = mContext.getString(resId);
        mMessageTv.setText(text);
        return this;
    }


    /**
     * 弹窗左边的点击事件
     */
    public interface OnDialogLeftClickListener{
        void onClick(View view);
    }

    /**
     * 弹窗左边的点击事件
     */
    public interface OnDialogRightClickListener{
        void onClick(View view);
    }

}
