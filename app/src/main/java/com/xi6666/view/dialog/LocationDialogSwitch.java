package com.xi6666.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi6666.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr_yang on 2016/11/12.
 */

public class LocationDialogSwitch {
    @BindView(R.id.txt_location_switch_title)
    TextView mTxtLocationSwitchTitle;
    @BindView(R.id.diolag_rl)
    RelativeLayout mDiolagRl;
    @BindView(R.id.txt_location_swtich_cancle)
    TextView mTxtLocationSwtichCancle;
    @BindView(R.id.txt_location_swtich_open)
    TextView mTxtLocationSwtichOpen;
    private Context mContext;
    private AlertDialog mAlertDialog;
    private OnLocationSwichOnclick mOnLocationSwichOnclick;


    public LocationDialogSwitch(Context context) {
        mContext = context;
    }

    public void showLocationSwitch() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_location_selecte, null);
        ButterKnife.bind(this, view);
        mAlertDialog = builder.show();
        mAlertDialog.setContentView(view);
        mAlertDialog.setCancelable(false);
        //获取屏幕信息
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        Window window = mAlertDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) ((dm.widthPixels) * 0.792);
        params.height = (int) ((dm.heightPixels) * 0.287);
        //设置弹框的大小
        mAlertDialog.getWindow().setAttributes(params);
    }

    public void closeDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    @OnClick({R.id.txt_location_swtich_cancle, R.id.txt_location_swtich_open})
    void onclick(View view) {
        switch (view.getId()) {
            case R.id.txt_location_swtich_cancle:
                mOnLocationSwichOnclick.cancle();
                break;
            case R.id.txt_location_swtich_open:
                mOnLocationSwichOnclick.swich();
                break;
        }
    }

    public void setOnLocationSwichOnclick(OnLocationSwichOnclick onLocationSwichOnclick) {
        mOnLocationSwichOnclick = onLocationSwichOnclick;
    }

    public interface OnLocationSwichOnclick {
        void cancle();

        void swich();
    }

    public void setCity(String city) {
        mTxtLocationSwitchTitle.setText("定位显示您在" + city + "，您可以手动切回当前城市");

    }
}
