package com.xi6666.order.other;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.MainActivity;
import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.order.activity.GoodsOrderAffirmActivity;
import com.xi6666.view.dialog.BaseDialog;

import java.text.DecimalFormat;
import java.util.List;

public class Utils {

    /**
     * 拨打电话
     *
     * @param number 电话号码
     */
    public static void callTel(String number, final Activity mActivity) {
        String message = "确定拨打电话:" + number + "吗?";
        if (TextUtils.isEmpty(number)) {
            message = "门店未上传联系方式,请联系平台:400-9999-353";
            number = "4009999353";
        }

        final String tel = number;


        BaseDialog dialog = new BaseDialog(mActivity);
        dialog.setLeftAndRight("取消","拨打");
        dialog.setTitle(message);
        dialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
            @Override
            public void onLeftOnclick() {
                dialog.dismiss();
            }

            @Override
            public void onRightOncklick() {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
                if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CALL_PHONE}, GoodsOrderAffirmActivity.MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else {
                    mActivity.startActivity(intent);
                }
            }
        });
        dialog.show();
    }

    /**
     * 弹出框
     *
     * @param message             弹框提示内容
     * @param mActivity           上下文
     * @param mOnAlterDialogClick 点击事件
     */
    public static void alterDialog(String message, final Activity mActivity, final OnAlterDialogClick mOnAlterDialogClick, String sureText, String cancelText) {
        final Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        View view = View.inflate(mActivity, R.layout.sign_dialog, null);
        TextView message_tv = (TextView) view.findViewById(R.id.message);
        TextView sure = (TextView) view.findViewById(R.id.diolag_sure);
        TextView cansel = (TextView) view.findViewById(R.id.diolag_cansel);
        sure.setText(sureText);
        cansel.setText(cancelText);
        message_tv.setText(message);
        builder.setView(view);
        dialog = builder.show();
        //获取屏幕信息
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.dialogAnima);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) ((dm.widthPixels) * 0.792);
        params.height = (int) ((dm.heightPixels) * 0.287);
        //设置弹框的大小
        dialog.getWindow().setAttributes(params);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (mOnAlterDialogClick != null)
                    mOnAlterDialogClick.onSuccess();
            }
        });
        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (mOnAlterDialogClick != null)
                    mOnAlterDialogClick.onCancel();
            }
        });
    }


    /**
     * 判断文本是否为空
     * 参数: true  为空
     * false  不为空
     */
    public static boolean isTvNull(TextView tv) {
        if (TextUtils.isEmpty(tv.getText().toString())) {
            return true;
        }
        return false;
    }


    /**
     * Dialog接口
     */
    public interface OnAlterDialogClick {
        /**
         * 左边事件
         */
        void onCancel();

        /**
         * 右边事件
         */
        void onSuccess();
    }


    /**
     * 简洁性Toast,防止点击多次 toast要排队输出
     */
    private static Toast mToast;

    public static void make(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }

        mToast.show();
    }

    /**
     * 将传入的dp值转化为px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 判断数组里面是否为空或者数量小于1
     */
    public static boolean isEmpty(List list) {
        return !(list != null && list.size() > 0);
    }


    /**
     * 获取XX.00价钱
     */
    private static DecimalFormat mDoubleDf = new DecimalFormat("######0.00");

    public static String getDoubleMoeny(String moeny) {
        if (TextUtils.isEmpty(moeny)) {
            return mDoubleDf.format(0);
        }
        return mDoubleDf.format(Double.parseDouble(moeny));
    }

    public static String getDoubleMoeny(float moeny) {
        return mDoubleDf.format(moeny);
    }

    /**
     * 分数强制转换
     */
    private static DecimalFormat mScoreDf = new DecimalFormat("######0.0");
    public static String getScore(float score) {
        return mScoreDf.format(score);
    }
    public static String getScore(String score) {
        return getScore(Float.parseFloat(score));
    }

}

