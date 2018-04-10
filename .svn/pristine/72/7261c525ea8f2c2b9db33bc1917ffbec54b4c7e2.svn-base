package com.xi6666.view.dialog;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xi6666.R;

/**
 * Created by Mr_yang on 2016/11/30.
 */

public class CallDialog {
    private Context mContext;

    public CallDialog(Context context) {
        mContext = context;
    }

    /**
     * 拨打电话
     *
     * @param phoneNum
     */
    public void MakePhoneCall(final String phoneNum) {
        //弹窗提示拨打电话号码
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = View.inflate(mContext, R.layout.delete_oilcard_diolag, null);
        TextView sure = (TextView) view.findViewById(R.id.diolag_sure);
        TextView cansel = (TextView) view.findViewById(R.id.diolag_cansel);
        TextView titleTv = (TextView) view.findViewById(R.id.alert_title);
        TextView contentTv = (TextView) view.findViewById(R.id.alert_content);
        titleTv.setText("提示");
        contentTv.setText("确定拨打电话号码:" + phoneNum + "吗?");
        builder.setView(view);
        final AlertDialog dialog = builder.show();
        //获取屏幕信息
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setWindowAnimations(R.style.dialogAnima);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) ((dm.widthPixels) * 0.792);
        params.height = (int) ((dm.heightPixels) * 0.247);
        //设置弹框的大小
        dialog.getWindow().setAttributes(params);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mContext.startActivity(intent);
            }
        });
        cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }
}