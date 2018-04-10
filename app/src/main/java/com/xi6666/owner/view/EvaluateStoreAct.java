package com.xi6666.owner.view;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.classification.view.custom.TagCloudView;
import com.xi6666.evaluate.bean.EvaluateServiceBean;
import com.xi6666.owner.mvp.EvaluateStoreContract;
import com.xi6666.owner.mvp.EvaluateStoreModel;
import com.xi6666.owner.mvp.EvaluateStorePresenter;
import com.xi6666.store.adapter.TagAdapter;
import com.xi6666.store.custom.EvaluateItemView;
import com.xi6666.store.custom.FlowLayout;
import com.xi6666.store.custom.SelectImageView;
import com.xi6666.store.custom.TagFlowLayout;
import com.xi6666.view.custom.EvaluationTypeView;
import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.multi_image_selector.MultiImageSelector;
import com.xi6666.store.custom.EvaluateBar;
import com.xi6666.view.custom.LimitEditTextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * 创建者 sunsun
 * 时间 16/11/10 下午5:30.
 * 个人公众号 ardays
 * 门店详情 -- 评论
 */

public class EvaluateStoreAct extends BaseToolbarView<EvaluateStorePresenter, EvaluateStoreModel>
        implements EvaluateStoreContract.View {

    //选择图片返回
    private static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 2;
    boolean showCamera = true;
    int maxNum = 5;
    private MultiImageSelector selector;


    EvaluateBar mEvaluateStoreStarEb;   //评分(星星)
    TextView mEvaluateStoreStarTv; //评分（文字)
    //    EvaluateItemView mEvaluateEiv;  //选择服务
    TagFlowLayout mEvaluateTfl;      //选择服务
    SelectImageView mSelectImageView; //选择图片上传
    LimitEditTextView mContentLetv;     //评论内容
    View mSelectTypeView;           //选择品牌


    private String mContextStr;          //评论内容
    private float mStar;                 //评价
    private String mStoreId;            //门店ID；
    private int mType = -1;              //服务类型
    private String mOrderSn;            //订单ID；


    @Override
    public String title() {
        return "评价门店";
    }

    @Override
    public void uiCreate() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.uiCreate();
    }

    @Override
    public int mainResId() {
        return R.layout.activity_evaluate_store;
    }

    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initToolbar();
        initSelStore();
        initSelectImage();
        initStar();
    }


    /**
     * 初始化控件
     */
    private void initView(View view) {
//        mEvaluateEiv = (EvaluateItemView) view.findViewById(R.id.evaluate_store_evaluate_eiv);
        mEvaluateTfl = (TagFlowLayout) view.findViewById(R.id.evaluate_store_evaluate_tfl);
        mEvaluateStoreStarEb = (EvaluateBar) view.findViewById(R.id.evaluate_store_star_eb);
        mEvaluateStoreStarTv = (TextView) view.findViewById(R.id.evaluate_store_star_tv);
        mSelectImageView = (SelectImageView) findViewById(R.id.evaluate_store_select_image_siv);
        mContentLetv = (LimitEditTextView) view.findViewById(R.id.evaluate_store_content_letv);
        mSelectTypeView = view.findViewById(R.id.evaluate_store_select_type_ll);
    }

    /**
     * 初始化参数
     */
    private void initValue() {
        mStoreId = getIntent().getStringExtra(STORE_ID);
        mOrderSn = getIntent().getStringExtra(ORDER_SN);

        //判断是否从订单那边来
        boolean bol = getIntent().getIntExtra(TYPE, 0) == 1;
        if (bol) {
            mSelectTypeView.setVisibility(View.GONE);
            mType = 0;
        }
    }

    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        setToolbarRightText("提交");
    }

    /**
     * 选择门店服务
     */
    private void initSelStore() {
        mPersenter.requestServiceList(mStoreId);
//        mEvaluateEiv.setOnEvaluationTypeViewListener(new EvaluationTypeView.OnEvaluationTypeViewListener() {
//            @Override
//            public void onSelectType(int type) {
////                make(mServiceTypeStr[type]);
//                mType = type;
//            }
//        });
    }

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

    /*
    * Dialog对话框提示用户删除操作
    * position为删除图片位置
    */
    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                mGroups.get(position).setVisibility(View.GONE);
//                mSelectPath.remove(mGroups.get(position).getTag());
//                mImageViews.get(position).setImageBitmap(null);
//                if (mSelectPath.size() < 5) {
//                    mIvAddImg.setVisibility(View.VISIBLE);
//                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * 初始化星星
     */
    private void initStar() {
        /**
         * 监听星星滚动
         */
        mEvaluateStoreStarEb.setOnStarChangeListener(new EvaluateBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                //把星星拖动的值赋值在上面
                mEvaluateStoreStarTv.setText(mark + "分");
            }
        });
    }


    @Override
    public void onToolbarRightClick(View view) {
        super.onToolbarRightClick(view);
        if (!isLogin()) {
            return;
        }
        mStar = mEvaluateStoreStarEb.getStarMark();
        mContextStr = mContentLetv.getText().toString();

        if (mType < 1) { //服务类型
            if (mEvaluateTfl.getVisibility() == View.GONE) {
                if (TextUtils.isEmpty(mOrderSn)) {
                    make("你的订单编号不能为空");
                    return;
                }
            }
        }
        if (mStar < 1.0) {    //评分
            make("请评分");
            return;
        }
        if (mContextStr.length() < 4) {
            make("请评论五字以上");
            return;
        }

        make("正在提交评论，请耐心等待一下。谢谢");
        showLoading();
        mPersenter.requestUpEvaluate(mSelectImageView.getImageList(), mContextStr, mStoreId, mStar, mType, mOrderSn);
    }

    @Override
    public void returnCommitResult(BaseBean bean) {
        make(bean.info);
        hiddenLoading();
        if (bean.success) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void returnError(String bean) {
        make(bean);
        hiddenLoading();
    }

    @Override
    public void returnServiceList(EvaluateServiceBean bean) {
        if (bean.success) {
            if(bean.data.size() == 0){
                mSelectTypeView.setVisibility(View.GONE);
                return;
            }
            //写入tag
            mEvaluateTfl.setAdapter(new TagAdapter<StoreDetailsBean.DataBean.ServiceListBean>(bean.data) {
                @Override
                public View getView(FlowLayout parent, int position, StoreDetailsBean.DataBean.ServiceListBean data) {
                    TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.view_evaluate_store_tv,
                            mEvaluateTfl, false);
                    tv.setText(data.cate_name);
                    return tv;
                }
            });


            //监听tag点击
            mEvaluateTfl.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                @Override
                public void onSelected(Set<Integer> selectPosSet) {
                    Iterator<Integer> it = selectPosSet.iterator();
                    Integer i = it.next();

                    mType = Integer.parseInt(bean.data.get(i).service_cate_id);
                }
            });

        }
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, String store_id) {
        Intent intent = new Intent(activity, EvaluateStoreAct.class);
        intent.putExtra(STORE_ID, store_id);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    public static final int REQUEST_CODE = 1012;
    private static final String STORE_ID = "com.xi6666.store_id";   //初始化门店参数


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity, String order_sn, String store_id) {
        Intent intent = new Intent(activity, EvaluateStoreAct.class);
        intent.putExtra(ORDER_SN, order_sn);
        intent.putExtra(STORE_ID, store_id);
        intent.putExtra(TYPE, 1);
        activity.startActivity(intent);
    }

    private static final String TYPE = "com.xi666.type";    //分类 1：代表订单，默认是直接从门店点评进入
    private static final String ORDER_SN = "com.xi6666.order_sn";   //订单ID
}
