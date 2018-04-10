package com.xi6666.order.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.evaluate.bean.UploadBean;
import com.xi6666.evaluate.other.EvaluateBar;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.multi_image_selector.MultiImageSelector;
import com.xi6666.order.bean.CommentDetailsBean;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoodsEvaluateActivity extends ToolBarBaseActivity {

    @BindView(R.id.iv_evaluate_goods_img)
    ImageView    mIvEvaluateGoodsImg;
    @BindView(R.id.evaluate_rating)
    EvaluateBar  mEvaluateRating;
    @BindView(R.id.et_evaluate_desc)
    EditText     mEtEvaluateDesc;
    @BindView(R.id.tv_input_num_evaluate)
    TextView     mTvInputNumEvaluate;
    @BindView(R.id.iv_add_img)
    ImageView    mIvAddImg;
    @BindView(R.id.iv_add_img1)
    ImageView    mIvAddImg1;
    @BindView(R.id.iv_add_img2)
    ImageView    mIvAddImg2;
    @BindView(R.id.iv_add_img3)
    ImageView    mIvAddImg3;
    @BindView(R.id.iv_add_img4)
    ImageView    mIvAddImg4;
    @BindView(R.id.iv_add_img5)
    ImageView    mIvAddImg5;
    @BindView(R.id.iv_cha1)
    ImageView    mIvCha1;
    @BindView(R.id.iv_cha2)
    ImageView    mIvCha2;
    @BindView(R.id.iv_cha3)
    ImageView    mIvCha3;
    @BindView(R.id.iv_cha4)
    ImageView    mIvCha4;
    @BindView(R.id.iv_cha5)
    ImageView    mIvCha5;
    @BindView(R.id.fl_add_img1)
    FrameLayout  mFlAddImg1;
    @BindView(R.id.fl_add_img2)
    FrameLayout  mFlAddImg2;
    @BindView(R.id.fl_add_img3)
    FrameLayout  mFlAddImg3;
    @BindView(R.id.fl_add_img4)
    FrameLayout  mFlAddImg4;
    @BindView(R.id.fl_add_img5)
    FrameLayout  mFlAddImg5;
    @BindView(R.id.cb_niming_comment)
    CheckBox     mCbIsNiMing;
    @BindView(R.id.ll_niming_comment)
    LinearLayout mLLIsNiMing;

    private static final   int REQUEST_IMAGE                           = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION  = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    private ArrayList<String>      mSelectPath;
    private ArrayList<ImageView>   mImageViews;
    private ArrayList<ImageView>   mChas;
    private ArrayList<FrameLayout> mGroups;
    private ArrayList<Bitmap>      mBitmaps;
    private MultiImageSelector     selector;
    private String                 goodsImg;
    private String                 order_sn;
    private boolean                isOneGoods;
    private Dialog                 mLoading;
    private String                 user_id;
    private String                 user_token;
    private String                 goods_id;
    private UploadBean             mUploadBean;
    private List<String> mUpLoadImgs = new ArrayList<>();
    private String       starMark    = "";
    private boolean            is_comment;
    private CommentDetailsBean mCommentDetailsBean;
    private List<CommentDetailsBean.DataBean.CommentImgBean> comment_img = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_evaluate);
        ButterKnife.bind(this);
        mTxtRight.setVisibility(View.VISIBLE);
        mTxtRight.setText("提交");
        mTxtRight.setTextColor(getResources().getColor(R.color.themeColor));
        selector = MultiImageSelector.create(GoodsEvaluateActivity.this);
        goodsImg = getIntent().getStringExtra("goodsImg");
        goods_id = getIntent().getStringExtra("goods_id");
        order_sn = getIntent().getStringExtra("order_sn");
        isOneGoods = getIntent().getBooleanExtra("isOneGoods", false);
        is_comment = getIntent().getBooleanExtra("is_comment", false);
        mLoading = ShowDialogUitls.showDio(this);
        mLoading.dismiss();
        user_id = UserData.getUserId();
        user_token = UserData.getUserToken();
        init();
        initData();
    }

    private void initData() {
        if (is_comment) {
            mTxtRight.setVisibility(View.GONE);
            mLLIsNiMing.setVisibility(View.GONE);
            mEvaluateRating.setEnabled(false);
            mEtEvaluateDesc.setEnabled(false);
            mIvAddImg.setVisibility(View.GONE);
            for (int i = 0; i < mChas.size(); i++) {
                mChas.get(i).setVisibility(View.GONE);
                mGroups.get(i).setClickable(false);
            }
            mLoading.show();
            Retrofit.Builder builder = new Retrofit.Builder();
            Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
            retrofit.create(NetAddress.class).getCommentDetails(goods_id, order_sn, user_id, user_token).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (mLoading.isShowing()) {
                        mLoading.dismiss();
                    }
                    try {
                        String result = response.body().string();
                        LogUtil.i("GoodsEvaluateActivity", result);
                        mCommentDetailsBean = GsonUtils.toEntityFromJson(result, CommentDetailsBean.class);
                        if (mCommentDetailsBean.isSuccess()) {
                            Glide.with(GoodsEvaluateActivity.this).load(mCommentDetailsBean.getData().getGoods_img()).placeholder(R.drawable.no_data_empty).centerCrop()
                                    .into(mIvEvaluateGoodsImg);
                            comment_img.addAll(mCommentDetailsBean.getData().getComment_img());
                            mEvaluateRating.setStarMark(Float.parseFloat(mCommentDetailsBean.getData().getComment_level()));
                            mEtEvaluateDesc.setText(mCommentDetailsBean.getData().getComment_content());
                            int size = comment_img.size();
                            for (int i = 0; i < size; i++) {
                                mGroups.get(i).setVisibility(View.VISIBLE);
                                Glide.with(GoodsEvaluateActivity.this).load(comment_img.get(i).getPhoto_source()).placeholder(R.drawable.no_data_empty).centerCrop()
                                        .into(mImageViews.get(i));
                            }
                            for (int i = size; i < mGroups.size(); i++) {
                                mGroups.get(i).setVisibility(View.GONE);
                            }
                        } else {
                            Utils.make(GoodsEvaluateActivity.this, mCommentDetailsBean.getInfo());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } else {
            Glide.with(this).load(goodsImg).placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(mIvEvaluateGoodsImg);
            mTxtRight.setVisibility(View.VISIBLE);
            mLLIsNiMing.setVisibility(View.VISIBLE);
            mEvaluateRating.setEnabled(true);
            mEtEvaluateDesc.setEnabled(true);
            mIvAddImg.setVisibility(View.VISIBLE);
        }

    }

    private void init() {
        mSelectPath = new ArrayList<>();
        mImageViews = new ArrayList<>();
        mChas = new ArrayList<>();
        mBitmaps = new ArrayList<>();
        mGroups = new ArrayList<>();
        mImageViews.add(mIvAddImg1);
        mImageViews.add(mIvAddImg2);
        mImageViews.add(mIvAddImg3);
        mImageViews.add(mIvAddImg4);
        mImageViews.add(mIvAddImg5);
        mGroups.add(mFlAddImg1);
        mGroups.add(mFlAddImg2);
        mGroups.add(mFlAddImg3);
        mGroups.add(mFlAddImg4);
        mGroups.add(mFlAddImg5);
        mChas.add(mIvCha1);
        mChas.add(mIvCha2);
        mChas.add(mIvCha3);
        mChas.add(mIvCha4);
        mChas.add(mIvCha5);
        mIvAddImg.setVisibility(View.VISIBLE);
        mEtEvaluateDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvInputNumEvaluate.setText(s.length() + "/" + "100");
                if (s.length() == 100) {
                    mTvInputNumEvaluate.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEvaluateRating.setIntegerMark(true);
        mEvaluateRating.setOnStarChangeListener(new EvaluateBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                starMark = String.valueOf((int) mark);
            }
        });

        for (int i = 0; i < mGroups.size(); i++) {
            final int temp = i;
            mGroups.get(i).setClickable(true);
            mGroups.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog(temp);
                }
            });
        }
        mTxtRight.setOnClickListener(v ->  {
                // 提交评价的操作
                if (TextUtils.isEmpty(starMark)) {
                    Utils.make(GoodsEvaluateActivity.this, "请选择评分");
                    return;
                }
                if (TextUtils.isEmpty(mEtEvaluateDesc.getText().toString()) || mEtEvaluateDesc.getText().toString().length() < 5) {
                    Utils.make(GoodsEvaluateActivity.this, "请至少输入5个文字的评价哦");
                    return;
                }
                if (mSelectPath.size() > 0)
                    uploadImgs();
                else
                    commitComment();
        });
    }

    @Override
    public void setToolbarIconDo() {
        successHandle();
    }

    @Override
    public String setToolbarTitle() {
        return "评价";
    }

    /**
     * 提交评价
     */
    private void commitComment() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        boolean checked = mCbIsNiMing.isChecked();
        String isNiming;
        if (checked) {
            isNiming = "1";
        } else {
            isNiming = "0";
        }
        JSONArray array = new JSONArray();
        for (int i = 0; i < mUpLoadImgs.size(); i++) {
            array.put(mUpLoadImgs.get(i));
        }
        mLoading.show();
        retrofit.create(NetAddress.class).goodsComment(mEtEvaluateDesc.getText().toString(), isNiming, starMark, order_sn, array.toString(), user_id, user_token)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            runOnUiThread(() -> {
                                if (mLoading.isShowing()) {
                                    mLoading.dismiss();
                                }
                            });
                            String result = response.body().string();
                            LogUtil.i("GoodsEvaluateActivity_commit", result);
                            JSONObject jsonObject = new JSONObject(result);
                            Utils.make(GoodsEvaluateActivity.this, jsonObject.optString("info"));
                            if (jsonObject.optBoolean("success")) {
                                // 提交成功
                                successHandle();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        runOnUiThread(() -> {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                                Utils.make(GoodsEvaluateActivity.this, "请求失败");
                            }
                        });
                    }
                });
    }

    private void successHandle() {
        if (isOneGoods) {
            // 跳转到订单详情页
            Intent intent = new Intent(GoodsEvaluateActivity.this, GoodsOrderDetailActivity.class);
            intent.putExtra("order_sn", order_sn);
            startActivity(intent);
            finish();
        } else {
            // 跳转到选择评价商品页
           /* Intent intent = new Intent(GoodsEvaluateActivity.this, SelectEvaluateGoodsActivity.class);
            intent.putExtra("order_sn", order_sn);
            startActivity(intent);*/
            finish();
        }
    }

    @OnClick(R.id.iv_add_img)
    public void onClick() {
        pickImage();
    }

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            boolean showCamera = true;
            int maxNum = 5;

            selector.showCamera(showCamera);
            selector.count(maxNum);
            selector.multi();
            selector.origin(mSelectPath);
            selector.start(GoodsEvaluateActivity.this, REQUEST_IMAGE);
        }
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(GoodsEvaluateActivity.this, new String[]{permission}, requestCode);
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
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath.clear();
                for (Bitmap bitmap : mBitmaps) {
                    bitmap.recycle();
                }
                mBitmaps.clear();
                mSelectPath.addAll(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT));

                for (int i = 0; i < mSelectPath.size(); i++) {
                    mGroups.get(i).setTag(mSelectPath.get(i));
                }

                for (int i = 0; i < mSelectPath.size(); i++) {
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 8;
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                        options.inPurgeable = true;
                        options.inInputShareable = true;
                        Bitmap bitmap = BitmapFactory.decodeFile(mSelectPath.get(i), options);
                        mBitmaps.add(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mGroups.get(i).setVisibility(View.VISIBLE);
                }
                for (int i = 0; i < mBitmaps.size(); i++) {
                    mImageViews.get(i).setImageBitmap(mBitmaps.get(i));
                }
                if (mSelectPath.size() >= 5) {
                    mIvAddImg.setVisibility(View.GONE);
                }
            }

        }
    }

    /**
     * 上传多张图片
     */
    private void uploadImgs() {
        Map<String, RequestBody> files = new HashMap<>();
        for (int i = 0; i < mSelectPath.size(); i++) {
            String substring = mSelectPath.get(i).substring(mSelectPath.get(i).lastIndexOf("/") + 1, mSelectPath.get(i).length());
            LogUtil.i("filename","filename=" + substring);
            files.put("file_" + i + "\"; filename=" + substring,
                    RequestBody.create(MediaType.parse("multipart/form-data"),
                            new File(mSelectPath.get(i))));
        }
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(NetAddress.class).uploadImgs(files).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(() -> {
                        if (mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                    });
                    String result = response.body().string();
                    LogUtil.i("GoodsEvaluateActivity_upload", result);
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.optBoolean("success")) {
                        // 上传成功
                        mUploadBean = GsonUtils.toEntityFromJson(result,UploadBean.class);
                        mUpLoadImgs.clear();
                        mUpLoadImgs.addAll(mUploadBean.getData());
                        commitComment();
                    } else {
                        Utils.make(GoodsEvaluateActivity.this, jsonObject.optString("info"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                runOnUiThread(() -> {
                    if (mLoading.isShowing()) {
                        mLoading.dismiss();
                        Utils.make(GoodsEvaluateActivity.this, "请求失败,请稍后重试！");
                    }
                });
            }
        });
    }

    /*
    * Dialog对话框提示用户删除操作
    * position为删除图片位置
    */

    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GoodsEvaluateActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mGroups.get(position).setVisibility(View.GONE);
                mSelectPath.remove(mGroups.get(position).getTag());
                mImageViews.get(position).setImageBitmap(null);
                if (mSelectPath.size() < 5) {
                    mIvAddImg.setVisibility(View.VISIBLE);
                }
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

    @Override
    public void onBackPressed() {
        successHandle();
    }
}
