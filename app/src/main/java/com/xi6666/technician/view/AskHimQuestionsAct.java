package com.xi6666.technician.view;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.xi6666.R;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.multi_image_selector.MultiImageSelector;
import com.xi6666.store.custom.SelectImageView;
import com.xi6666.technician.mvp.AskHimQuestionsContract;
import com.xi6666.technician.mvp.AskHimQuestionsModel;
import com.xi6666.technician.mvp.AskHimQuestionsPresenter;
import com.xi6666.view.custom.LimitEditTextView;


/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午4:30.
 * 个人公众号 ardays
 */

public class AskHimQuestionsAct extends BaseToolbarView<AskHimQuestionsPresenter, AskHimQuestionsModel>
        implements AskHimQuestionsContract.View {


    @Override
    public String title() {
        return "向Ta提问";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_ask_him_question;
    }

    //选择图片返回
    private static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 2;
    boolean showCamera = true;  //是否显示拍照功能
    int maxNum = 5;             //最大图片数量


    LimitEditTextView mContentView; //问题
    SelectImageView mSelectImageView;   //选择图片

    private String mTechnicianId;   //技师ID
    private MultiImageSelector selector;

    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initToolbar();
        initSelectImage();
    }

    /**
     * 初始化绑定控件
     */
    private void initView(View view) {
        mContentView = (LimitEditTextView) view.findViewById(R.id.ask_him_question_content_etv);
        mSelectImageView = (SelectImageView) view.findViewById(R.id.ask_him_question_select_image_siv);
    }

    /**
     * 初始化值
     */
    private void initValue() {
        mTechnicianId = getIntent().getStringExtra(TECHNICIAN_ID);
    }


    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        //设置右边按钮
        setToolbarRightColor(getResources().getColor(R.color.text_green));
        setToolbarRightText("提交");
    }

    /**
     * 右边按钮点击事件
     */
    @Override
    public void onToolbarRightClick(View view) {
        super.onToolbarRightClick(view);
        if (!isLogin()) {
            return;
        }
        if(mContentView.getText().toString().length() < 5){
            make("提问字数不得少于5字");
            return;
        }
        showLoading();
        mPersenter.requestCommitAsk(mSelectImageView.getImageList(), mContentView.getText().toString(), mTechnicianId);
    }

//                              @初始化选择图片

    /**
     * 初始化选择图片
     */
    private void initSelectImage() {
        selector = MultiImageSelector.create();
        selector.showCamera(showCamera);
        selector.count(maxNum);
        selector.multi();


        mSelectImageView.setOnSelectImageListener(new SelectImageView.OnSelectImageListener() {

            @Override
            public void addImageClick(SelectImageView view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                            getString(R.string.mis_permission_rationale),
                            REQUEST_STORAGE_READ_ACCESS_PERMISSION);
                } else {
                    view.openSystemPhoto(getActivity());
                }
            }

        });
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mSelectImageView.openSystemPhoto(getActivity());
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelectImageView.REQUEST_IMAGE_DATA) {
            if (resultCode == RESULT_OK) {
                //添加图片成功代码
                mSelectImageView.onActivityResult(data);
            }

        }
    }


//                      @网络请求

    @Override
    public void returnCommitResult(BaseBean bean) {
        hiddenLoading();
        make(bean.info);
        if (bean.success) {
            finish();
        }
    }

    @Override
    public void returnError(String error) {
        make(error);
        hiddenLoading();
    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, String technician_id) {
        Intent intent = new Intent(activity, AskHimQuestionsAct.class);
        intent.putExtra(TECHNICIAN_ID, technician_id);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }
    public static final int REQUEST_CODE = 100; //请求码；

    private static final String TECHNICIAN_ID = "com.xi6666.technician_id"; //技师ID

}
