package com.xi6666.userdata.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.soundcloud.android.crop.Crop;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.xi6666.R;
import com.xi6666.view.dialog.AddOilServerDialog;
import com.xi6666.address.ReceiptAddressAct;
import com.xi6666.app.ComponetBaseAct;
import com.xi6666.common.CircleTransform;
import com.xi6666.common.UserData;
import com.xi6666.databean.UserDataBean;
import com.xi6666.userdata.UserDataPresenterImpl;
import com.xi6666.userdata.contract.UserDataContract;
import com.xi6666.userdata.di.DaggerUserDataComponent;
import com.xi6666.userdata.di.UserDataModule;
import com.xi6666.utils.LogUtil;
import com.xi6666.view.dialog.LoadingDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * @author peng
 * @data 创建时间:2016/11/3
 * @desc 用户资料
 */
public class UserDataAct extends ComponetBaseAct implements View.OnClickListener,
        OptionsPickerView.OnOptionsSelectListener,
        TimePickerView.OnTimeSelectListener, UserDataContract.View {

    private static final String TAG = "UserDataAct";
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int RESULT_LOAD_CAMERA = 2;
    private static final int CROP_FINISH = 3;
    @BindView(R.id.iv_setting_arraw)
    ImageView mIvSettingArraw;
    @BindView(R.id.iv_setting_head)
    ImageView mIvSettingHead;
    @BindView(R.id.rll_setting_head)
    RelativeLayout mRllSettingHead;
    @BindView(R.id.etxt_setting_username)
    EditText mEtxtSettingUsername;
    @BindView(R.id.txt_setting_gender)
    TextView mTxtSettingGender;
    @BindView(R.id.rll_setting_gender)
    RelativeLayout mRllSettingGender;
    @BindView(R.id.txt_setting_brithday)
    TextView mTxtSettingBrithday;
    @BindView(R.id.rll_setting_brithday)
    RelativeLayout mRllSettingBrithday;
    @BindView(R.id.rll_setting_address)
    RelativeLayout mRllSettingAddress;
    @BindView(R.id.iv_setting_code)
    ImageView mIvSettingCode;
    @BindView(R.id.tv_setting_phonenum)
    TextView mEtxtSettingPhonenum;
    private OptionsPickerView mOptionsPickerView;
    private TimePickerView mTimePickerView;

    @Inject
    UserDataPresenterImpl mUserDataPresenter;
    private UserDataBean mUserDataBean;
    private String mCameraImageName;

    private File mPathDir;
    private LoadingDialog mLoadingDialog;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);
        ButterKnife.bind(this);
        mTxtLeft.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//设置箭头可见
        mTxtLeft.setText("取消");
        mTxtLeft.setOnClickListener((v)->{finish();});
        mLoadingDialog = new LoadingDialog();
        DaggerUserDataComponent.builder().appComponets(mAppComponets).userDataModule(new UserDataModule()).build().Inject(this);
        setRightTxt("保存");
        mTxtRight.setTextColor(getResources().getColor(R.color.themeColor));
        mTxtRight.setOnClickListener(this);
        initPickView();
        mUserDataPresenter.attachView(this);
        mUserDataPresenter.loadUserData();
    }

    private void initPickView() {
        mOptionsPickerView = new OptionsPickerView(this);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("男");
        strings.add("女");
        mOptionsPickerView.setPicker(strings);
        mOptionsPickerView.setTitle("选择性别");
        mOptionsPickerView.setCyclic(false);
        mOptionsPickerView.setSelectOptions(0);
        mOptionsPickerView.setOnoptionsSelectListener(this);

        mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        Calendar calendar = Calendar.getInstance();
        mTimePickerView.setRange(calendar.get(Calendar.YEAR) - 100, calendar.get(Calendar.YEAR));
        mTimePickerView.setTime(new Date());
        mTimePickerView.setCyclic(false);
        mTimePickerView.setCancelable(true);
        mTimePickerView.setOnTimeSelectListener(this);
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "个人资料";
    }

    @OnClick({R.id.rll_setting_head, R.id.rll_setting_gender, R.id.rll_setting_brithday, R.id.rll_setting_address, R.id.iv_setting_code})
    void viewOnclick(View view) {
        switch (view.getId()) {
            //头像
            case R.id.rll_setting_head:
                RxPermissions.getInstance(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            mUserDataPresenter.showChoiceHead();
                        } else {
                            showToast("亲~请给予读取文件的权限");
                        }
                    }
                });
                break;
            //性别
            case R.id.rll_setting_gender:
                if (mOptionsPickerView.isShowing()) {
                    mOptionsPickerView.dismiss();
                } else {
                    mOptionsPickerView.show();
                }
                break;
            //生日
            case R.id.rll_setting_brithday:
                if (mTimePickerView.isShowing()) {
                    mTimePickerView.dismiss();
                } else {
                    mTimePickerView.show();
                }
                break;
            //地址
            case R.id.rll_setting_address:
                startActivity(new Intent(this, ReceiptAddressAct.class));
                break;
            //二维码点击事件
            case R.id.iv_setting_code:
                AddOilServerDialog addOilServerDialog = new AddOilServerDialog(this, R.style.transpant_bg_dialog);
                addOilServerDialog.show();
                addOilServerDialog.setBg(mUserDataBean.getData().getUser_qrcode());
                addOilServerDialog.setLookGone();
                addOilServerDialog.setKnowGone();
                addOilServerDialog.setOutSideClose();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        //提交个人资料
        if (mUserDataBean != null) {
            mUserDataBean.getData().setUser_nickname(mEtxtSettingUsername.getText().toString().trim());
            LogUtil.d(TAG, "UserData.getUser_birthday()---->" + mUserDataBean.getData().getUser_birthday());
            LogUtil.d(TAG, "UserData.getUser_face()---->" + mUserDataBean.getData().getUser_face());
            LogUtil.d(TAG, "UserData.User_nickname()---->" + mUserDataBean.getData().getUser_nickname());
            LogUtil.d(TAG, "UserData.getUser_sex()---->" + mUserDataBean.getData().getUser_sex());
            String userId = UserData.getUserId();
            String userToken = UserData.getUserToken();
            mUserDataPresenter.setUserData(mUserDataBean.getData().getUser_birthday(),
                    mUserDataBean.getData().getUser_face(), mUserDataBean.getData().getUser_nickname(),
                    mUserDataBean.getData().getUser_sex(), userId, userToken);
        } else {
            showToast("非法操作!");
            mUserDataPresenter.loadUserData();
        }
    }

    @Override
    public void onOptionsSelect(int options1, int option2, int options3) {
        Log.d(TAG, "sex---->" + options1);
        switch (options1) {
            case 0:
                mTxtSettingGender.setText("男");
                mUserDataBean.getData().setUser_sex("1");
                break;
            case 1:
                mTxtSettingGender.setText("女");
                mUserDataBean.getData().setUser_sex("2");
                break;
            case 3:
                mTxtSettingGender.setText("未知");
                mUserDataBean.getData().setUser_sex("3");
                break;
        }
    }

    @Override
    public void onTimeSelect(Date date) {
        //格式化时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String time = df.format(date);
        String[] split = time.split("-");
        //将时间设置回显
        mTxtSettingBrithday.setText(split[0] + "-" + split[1] + "-" + split[2]);
        mUserDataBean.getData().setUser_birthday(split[0] + "-" + split[1] + "-" + split[2]);
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clostAct() {
        finish();
    }

    @Override
    public void setUserData(String phoneNum, String userName, String sex, String brithDay) {
        mEtxtSettingPhonenum.setText(phoneNum);
        mEtxtSettingUsername.setText(userName);
        mEtxtSettingUsername.setSelection(mEtxtSettingUsername.getText().length());
        if (TextUtils.equals(sex, "2")) {
            mTxtSettingGender.setText("女");
            mUserDataBean.getData().setUser_sex("2");
        } else if (TextUtils.equals(sex, "1")) {
            mTxtSettingGender.setText("男");
            mUserDataBean.getData().setUser_sex("1");
        } else {
            mTxtSettingGender.setText("未知");
            mUserDataBean.getData().setUser_sex("3");
        }
        mTxtSettingBrithday.setText(brithDay);
        mUserDataBean.getData().setUser_sex("1");
    }

    @Override
    public void setHead(String imageUrl) {
        Glide.with(this).load(imageUrl).transform(new CircleTransform(this)).placeholder(R.drawable.ic_mine_head).into(mIvSettingHead);
    }

    @Override
    public void setQRCode(String code) {
        Glide.with(this).load(code).into(mIvSettingCode);
    }

    @Override
    public void setUserDataBean(UserDataBean userDataBean) {
        Log.d(TAG, "userDataBean--->" + userDataBean.getData().getUser_nickname());
        this.mUserDataBean = userDataBean;
    }

    @Override
    public void choicePic(final File pathDir) {
        this.mPathDir = pathDir;
        //创建提示窗体
        AlertDialog.Builder builder = new AlertDialog.Builder(UserDataAct.this);
        builder.setTitle("选择图片");
        builder.setNegativeButton("相册",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //关闭弹出窗体
                        dialog.dismiss();
                        //打开相册
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, RESULT_LOAD_IMAGE);
                    }
                });

        builder.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //关闭弹出窗体
                dialog.dismiss();
                //拍照并且保存
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mCameraImageName = DateFormat.format("yyyyMMdd_hhmmss",
                        Calendar.getInstance(Locale.CHINA))
                        + ".jpg";
                File file = new File(mPathDir, mCameraImageName);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, RESULT_LOAD_CAMERA);
            }
        });
        builder.show();
    }

    @Override
    public void setUserFaceUrl(String userFaceUrl) {
        mUserDataBean.getData().setUser_face(userFaceUrl);
    }

    @Override
    public void showLoadingDialog() {
        mDialog = new LoadingDialog().LoadingDialog(this);
    }

    @Override
    public void hidLoadingDialog() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }

    @Override
    public void setUserCode(String userCode) {
        Glide.with(this).load(userCode).into(mIvSettingCode);
    }

    @Override
    public void closeAct() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断是相机还是图库返回的值
        if (resultCode == -1 && requestCode == RESULT_LOAD_IMAGE && data != null) {
            LogUtil.d(TAG, "相册");
            //裁剪图片
            Uri data1 = data.getData();
            mCameraImageName = DateFormat.format("yyyyMMdd_hhmmss",
                    Calendar.getInstance(Locale.CHINA))
                    + ".jpg";
            File file = new File(mPathDir, mCameraImageName);
            Uri uri = Uri.fromFile(file);
            Crop.of(data1, uri).start(UserDataAct.this, CROP_FINISH);
        }
        if (resultCode == -1 && requestCode == RESULT_LOAD_CAMERA) {
            LogUtil.d(TAG, "相机");
            //裁剪图片
            File file = new File(mPathDir, mCameraImageName);
            Uri inUri = Uri.fromFile(file);
            mCameraImageName = DateFormat.format("yyyyMMdd_hhmmss",
                    Calendar.getInstance(Locale.CHINA))
                    + ".jpg";
            File file2 = new File(mPathDir, mCameraImageName);
            Uri outUri = Uri.fromFile(file2);
            Crop.of(inUri, outUri).start(UserDataAct.this, CROP_FINISH);
        }
        if (requestCode == CROP_FINISH) {
            //剪切完成
            File file = new File(mPathDir, mCameraImageName);
            Glide.with(this).load(file).transform(new CircleTransform(this)).into(mIvSettingHead);
            mUserDataPresenter.changeHeadFace(file);
        }
    }
}
