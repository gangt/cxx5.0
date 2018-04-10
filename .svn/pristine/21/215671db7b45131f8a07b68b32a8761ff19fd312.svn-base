package com.xi6666.car.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.car.bean.AddCarParams;
import com.xi6666.car.bean.MyCarBean;
import com.xi6666.car.mp.AddCarPresenter;
import com.xi6666.car.mp.AddCarPresenterImpl;
import com.xi6666.car.mp.AddCarSuccessView;
import com.xi6666.car.view.custom.SelectProvinceDialog;
import com.xi6666.car.view.custom.YearMonthPicker;
import com.xi6666.carWash.base.BaseAct;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.eventbus.ChoiceDefaultCarEvent;
import com.xi6666.utils.SpUtils;
import com.xi6666.view.dialog.BaseDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午3:05.
 * 个人公众号 ardays
 */

public class AddCarSuccessAct extends BaseToolbarView implements SelectProvinceDialog.OnItemProvinceClickListener
        , AddCarSuccessView {

    ImageView mCarIv;
    TextView mCarTitle;
    TextView mCarType;
    TextView mProvinceTv;
    EditText mProvinceCarTv;
    EditText mProvinceRoadTv;
    TextView mProvinceTimeTv;
    RadioButton mDefaultRb;
    CheckBox mDefaultCk;
    ImageView mAddCarSuccessIv;

    //
    private static final String ADD_CAR = "com.xi6666.add_car";
    private static final String UPDATE_CAR = "com.xi6666.update_car";

    private AddCarParams mParams;
    //
    private AddCarPresenter mPresenter;
    private boolean bol = true;
    private boolean isSuccess = false;


    private MyCarBean.DataBean mData;

    @Override
    public String title() {
        return "添加爱车";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_addcar;
    }


    public void setUp(View view) {
        initView(view);
        setToolbarRightText("保存");
        setToolbarRightColor(getResources().getColor(R.color.text_green));
        //初始化接口
        mPresenter = new AddCarPresenterImpl(this);
        mData = getIntent().getParcelableExtra(UPDATE_CAR);
        if (mData == null) {
            initData();
        } else {
            setToolbarTitle("修改爱车");
            mParams = new AddCarParams();
            //写入图片
            Glide.with(this)
                    .load(mData.car_brand)
                    .override(36, 36)
                    .into(mCarIv);

            mParams.car_id = mData.car_id;
            mParams.car_kilometre = mData.car_kilometre;
            mParams.car_plate = mData.car_plate;
            mParams.car_brand = mData.car_brand_id;
            mParams.car_chexing = mData.car_chexing_id;
            mParams.car_nianfen = mData.car_nianfen_id;
            mParams.car_pailiang = mData.car_pailiang_id;
            //写入标题
            mCarTitle.setText(mData.car_brand + " " + mData.car_chexing);
            mCarType.setText(mData.car_nianfen + " " + mData.car_pailiang);
            mDefaultRb.setVisibility(View.GONE);
            mAddCarSuccessIv.setVisibility(View.VISIBLE);
            bol = false;
        }


        if (getIntent().getBooleanExtra(MyCarActivity.SEL_CAR_TYPE, false)) {
            initSelCarView();
        }
    }


    /**
     * 初始化绑定控件
     *
     * @param view
     */
    private void initView(View view) {
        mCarIv = (ImageView) view.findViewById(R.id.add_car_image);
        mCarTitle = (TextView) view.findViewById(R.id.add_car_title);
        mCarType = (TextView) view.findViewById(R.id.add_car_type);
        mProvinceTv = (TextView) view.findViewById(R.id.add_province_tv);
        mProvinceCarTv = (EditText) view.findViewById(R.id.add_province_car_tv);
        mProvinceRoadTv = (EditText) view.findViewById(R.id.add_province_road_tv);
        mProvinceTimeTv = (TextView) view.findViewById(R.id.add_province_time_tv);
        mDefaultRb = (RadioButton) view.findViewById(R.id.add_car_rb);
        mDefaultCk = (CheckBox) view.findViewById(R.id.add_province_default_ck);
        mAddCarSuccessIv = (ImageView) view.findViewById(R.id.add_car_success);


        view.findViewById(R.id.add_province_time_ll).setOnClickListener(v -> {
            onTimeLlClick();
        });

        view.findViewById(R.id.add_car_select_province).setOnClickListener(v -> {
            onSelectProvinceClick();
        });
    }


    /**
     * 添加爱车成功
     */
    private void initSelCarView() {
        setToolbarRightColor(getResources().getColor(R.color.text_black));
        setToolbarTitleColor(getResources().getColor(R.color.text_black));
    }


    /**
     * 初始化
     */
    private void initData() {
        //获取参数
        mParams = getIntent().getParcelableExtra(ADD_CAR);
        //写入图片
        Glide.with(this)
                .load(mParams.cx_img)
                .placeholder(R.drawable.bg_image_default)
                .error(R.drawable.bg_image_default)
                .override(36, 36)
                .into(mCarIv);
        //写入标题
        mCarTitle.setText(mParams.cx_name);
        mCarType.setText(mParams.cx_pailiang);
        mDefaultRb.setVisibility(View.GONE);
        mAddCarSuccessIv.setVisibility(View.VISIBLE);
    }

    /**
     * 选择时间
     */
    void onTimeLlClick() {
        YearMonthPicker picker = new YearMonthPicker(AddCarSuccessAct.this);
        picker.setRange(1995, 2016);//年份范围
        picker.setOnDatePickListener(new YearMonthPicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                mProvinceTimeTv.setText(year + "-" + month);
            }
        });
        picker.show();
    }


    @Override
    public void onToolbarRightClick(View view) {
        super.onToolbarRightClick(view);
        onRightClick();
    }

    /**
     * 右上角保存
     */
    void onRightClick() {
        mParams.is_default = mDefaultCk.isChecked() ? "1" : "0";
        //添加省
        mParams.car_displacemen = mProvinceTimeTv.getText().toString();
        mParams.car_kilometre = mProvinceRoadTv.getText().toString();
        if (!TextUtils.isEmpty(mParams.car_kilometre))
            mParams.car_plate = mProvinceTv.getText().toString();
        mPresenter.upCarData(mParams);
    }

    /**
     *
     */
    void onSelectProvinceClick() {
        SelectProvinceDialog dialog = new SelectProvinceDialog(this);
        dialog.setOnItemProvinceClickListener(this);
        dialog.show();
    }


    /**
     * 提示未登录弹窗
     */
    private void alertDotSign() {
        BaseDialog dialog = new BaseDialog(this);
        dialog.setTitle("你当前的车辆未被保存,请问是否退出?");
        dialog.setLeftAndRight("退出","取消");
        dialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
            @Override
            public void onLeftOnclick() {
                dialog.dismiss();
                isSuccess = true;
                finish();
            }

            @Override
            public void onRightOncklick() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 返回城市选择
     */
    @Override
    public void onItemProvince(String province) {
        mProvinceTv.setText(province + "");
    }




/*                   跳转页面               */

    /**
     * 跳转页面
     */
    public static void openActivity(Activity mActivity, AddCarParams mParams, boolean bol) {
        Intent intent = new Intent(mActivity, AddCarSuccessAct.class);
        intent.putExtra(ADD_CAR, mParams);
        intent.putExtra(MyCarActivity.SEL_CAR_TYPE, bol);
        mActivity.startActivity(intent);
    }


    /**
     * 跳转页面
     */
    public static void openActivity(Context mActivity, MyCarBean.DataBean bean) {
        Intent intent = new Intent(mActivity, AddCarSuccessAct.class);
        intent.putExtra(UPDATE_CAR, bean);
        mActivity.startActivity(intent);
    }

    /**
     * 成功添加爱车
     */
    @Override
    public void addCarSuccess() {
        Looper.prepare();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String toastName = bol ? "成功添加爱车" : "成功修改爱车";
                make(toastName);
                isSuccess = true;
                EventBus.getDefault().post(new ChoiceDefaultCarEvent("fsafsf"));
                finish();
            }
        });
    }

    @Override
    public void addCarError(String info) {
        Looper.prepare();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                make(info);
            }
        });
    }

    @Override
    public void finish() {
        if (!isSuccess) {
            alertDotSign();
            return;
        }
        super.finish();
    }
}
