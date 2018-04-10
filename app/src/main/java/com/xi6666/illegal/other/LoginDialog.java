package com.xi6666.illegal.other;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.login.view.LoginAct;

/**
 * Created by Mr_yang on 2016/6/16.
 */
public class LoginDialog {
    private Context mContext;

    public LoginDialog(Context context) {
        mContext = context;
    }

    /**
     * 跳转到登录的界面
     *
     * @param
     */
    public void JumpLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = View.inflate(mContext, R.layout.sign_dialog, null);
        TextView sure = (TextView) view.findViewById(R.id.diolag_sure);
        TextView cansel = (TextView) view.findViewById(R.id.diolag_cansel);
        builder.setView(view);
        final AlertDialog mDialog = builder.show();
        //获取屏幕信息
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        Window window = mDialog.getWindow();
        window.setWindowAnimations(R.style.dialogAnima);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) ((dm.widthPixels) * 0.792);
        params.height = (int) ((dm.heightPixels) * 0.287);
        //设置弹框的大小
        mDialog.getWindow().setAttributes(params);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                //TODO 去做登录的逻辑.
                mContext.startActivity(new Intent(mContext, LoginAct.class));
            }
        });
        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });
    }
}
