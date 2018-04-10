package com.xi6666.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xi6666.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr_yang on 2016/12/20.
 */

public class BaseDialog extends Dialog {

    @BindView(R.id.txt_basedialog_title)
    TextView mTxtBasedialogTitle;
    @BindView(R.id.txt_basedialog_left)
    TextView mTxtBasedialogLeft;
    @BindView(R.id.txt_basedialog_right)
    TextView mTxtBasedialogRight;
    private Context mContext;

    private DialogButtonOnclick mDialogButtonOnclick;

    public BaseDialog(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.base_diolag, null);
        ButterKnife.bind(this, view);
        this.setContentView(view);
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        Window window = this.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setWindowAnimations(R.style.dialogAnima);
        WindowManager.LayoutParams params = window.getAttributes();
        //低版本蓝色分割线去掉
        try {
            int divierId = mContext.getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider = this.findViewById(divierId);
            divider.setBackgroundColor(Color.TRANSPARENT);
        } catch (Exception e) {

        }
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        //设置弹框的大小
        this.getWindow().setAttributes(params);
    }

    public void setLeftAndRight(String left, String right) {
        this.mTxtBasedialogLeft.setText(left);
        this.mTxtBasedialogRight.setText(right);
    }

    public void setTitle(String title) {
        this.mTxtBasedialogTitle.setText(title);
    }

    public interface DialogButtonOnclick {
        void onLeftOnclick();

        void onRightOncklick();
    }

    @OnClick({R.id.txt_basedialog_left, R.id.txt_basedialog_right})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.txt_basedialog_left:
                mDialogButtonOnclick.onLeftOnclick();
                break;
            case R.id.txt_basedialog_right:
                mDialogButtonOnclick.onRightOncklick();
                break;
        }
    }
    public void setDialogButtonOnclick(DialogButtonOnclick dialogButtonOnclick) {
        mDialogButtonOnclick = dialogButtonOnclick;
    }
}
