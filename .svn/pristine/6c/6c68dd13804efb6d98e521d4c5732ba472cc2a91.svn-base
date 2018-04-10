package com.xi6666.order.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.order.bean.DrawbackDetailsBean;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DrawBackHandlingActivity extends ToolBarBaseActivity {

    @BindView(R.id.tv_drawback)
    TextView  mTvDrawback;
    @BindView(R.id.tv_drawback_apply)
    TextView  mTvDrawbackApply;
    @BindView(R.id.iv_drawback_apply)
    ImageView mIvDrawbackApply;
    @BindView(R.id.tv_drawback_handle)
    TextView  mTvDrawbackHandle;
    @BindView(R.id.iv_drawback_handle)
    ImageView mIvDrawbackHandle;
    @BindView(R.id.tv_drawback_done)
    TextView     mTvDrawbackDone;
    @BindView(R.id.iv_drawback_done)
    ImageView    mIvDrawbackDone;
    @BindView(R.id.left_line)
    View         mLeftLine;
    @BindView(R.id.line_right)
    View         mLineRight;
    @BindView(R.id.tv_drawback_amount)
    TextView     mTvDrawbackAmount;
    @BindView(R.id.tv_pay_type)
    TextView     mTvPayType;
    @BindView(R.id.tv_dbh_call_phone)
    TextView     mTvCallPhone;
    @BindView(R.id.tv_drawback_reason)
    TextView     mTvDrawbackReason;
    @BindView(R.id.tv_drawback_desc)
    TextView     mTvDrawbackDesc;
    @BindView(R.id.ll_draw_back_handling)
    LinearLayout mLLDrawBackHandling;
    @BindView(R.id.ll_reload_data)
    LinearLayout mLLReloadData;
    @BindView(R.id.btn_reload_data)
    Button       mBtnReloadData;

    private String              order_sn;
    private Dialog              mLoading;
    private String              user_id;
    private String              user_token;
    private DrawbackDetailsBean mDrawbackDetailsBean;
    private DecimalFormat       mDecimalFormat;
    private String              start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_back_handling);
        ButterKnife.bind(this);
        init();
        initData();
    }

    private void init() {
        mLoading = ShowDialogUitls.showDio(this);
        order_sn = getIntent().getStringExtra("order_sn");
        user_id = getIntent().getStringExtra("user_id");
        user_token = getIntent().getStringExtra("user_token");
        start = getIntent().getStringExtra("start");
        mDecimalFormat = new DecimalFormat("#####0.00");

        mLLDrawBackHandling.setVisibility(View.GONE);
        mLLReloadData.setVisibility(View.GONE);

        mBtnReloadData.setOnClickListener(v -> {
            initData();
        });

        mTvCallPhone.setOnClickListener(v -> {
            // 拨打客服电话
            Utils.callTel("4009999353", DrawBackHandlingActivity.this);
        });
    }

    private void initData() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).drawbackDetails(order_sn, user_id, user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                        }
                    });
                    String result = response.body().string();
                    LogUtil.i("GoodsOrderDetailActivity_cancel", result);
                    mLLDrawBackHandling.setVisibility(View.VISIBLE);
                    mLLReloadData.setVisibility(View.GONE);
                    mDrawbackDetailsBean = GsonUtils.toEntityFromJson(result,DrawbackDetailsBean.class);
                    if (mDrawbackDetailsBean.isSuccess()) {
                        DrawbackDetailsBean.DataBean data = mDrawbackDetailsBean.getData();
                        int status = data.getRefund_status();
                        resetColor();
                        switch (status) {
                            case 1:
                                mTvDrawback.setText("您的退款申请，正等待卖家处理！");
                                mTvDrawbackApply.setTextColor(getResources().getColor(R.color.whiteTextColor));
                                mIvDrawbackApply.setImageResource(R.drawable.done_point);
                                break;
                            case 2:
                                mTvDrawback.setText("您的退款申请，正等待卖家处理！");
                                mTvDrawbackHandle.setTextColor(getResources().getColor(R.color.whiteTextColor));
                                mIvDrawbackHandle.setImageResource(R.drawable.done_point);
                                mLeftLine.setBackgroundColor(getResources().getColor(R.color.whiteTextColor));
                                break;
                            case 3:
                                mTvDrawbackDone.setTextColor(getResources().getColor(R.color.whiteTextColor));
                                mIvDrawbackDone.setImageResource(R.drawable.done_point);
                                mLeftLine.setBackgroundColor(getResources().getColor(R.color.whiteTextColor));
                                mLineRight.setBackgroundColor(getResources().getColor(R.color.whiteTextColor));
                                mTvDrawback.setText("您的退款申请审核通过，款将原路退还到您的账户。");
                                break;
                        }

                        mTvDrawbackAmount.setText("¥" + mDecimalFormat.format(data.getRefund_money()));
                        mTvDrawbackReason.setText(data.getReason_reason());
                        mTvDrawbackDesc.setText(data.getRemark());
                        mTvPayType.setText(data.getPay_name());
                    } else {
                        Utils.make(DrawBackHandlingActivity.this, mDrawbackDetailsBean.getInfo());
                    }
                } catch (Exception e) {
                    Utils.make(DrawBackHandlingActivity.this, "网络异常");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                mLLDrawBackHandling.setVisibility(View.GONE);
                mLLReloadData.setVisibility(View.VISIBLE);
            }
        });
    }

    private void resetColor() {
        mTvDrawbackApply.setTextColor(getResources().getColor(R.color.alphaWhiteTextColor));
        mTvDrawbackHandle.setTextColor(getResources().getColor(R.color.alphaWhiteTextColor));
        mTvDrawbackDone.setTextColor(getResources().getColor(R.color.alphaWhiteTextColor));
        mIvDrawbackApply.setImageResource(R.drawable.undone_point);
        mIvDrawbackHandle.setImageResource(R.drawable.undone_point);
        mIvDrawbackDone.setImageResource(R.drawable.undone_point);
        mLeftLine.setBackgroundColor(getResources().getColor(R.color.alphaWhiteTextColor));
        mLineRight.setBackgroundColor(getResources().getColor(R.color.alphaWhiteTextColor));
    }

    @Override
    public void setToolbarIconDo() {
        if ("GoodsOrderDetailActivity".equals(start)) {
            finish();
        } else {
            Intent intent = new Intent(DrawBackHandlingActivity.this, GoodsOrderDetailActivity.class);
            intent.putExtra("order_sn",order_sn);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public String setToolbarTitle() {
        return "退款处理中";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case GoodsOrderAffirmActivity.MY_PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    // 拨号：激活系统的拨号组件
                    Intent intent = new Intent(); // 意图对象：动作 + 数据
                    intent.setAction(Intent.ACTION_CALL); // 设置动作
                    Uri uri = Uri.parse("tel:" + "4009999353"); // 设置数据
                    intent.setData(uri);
                    startActivity(intent); // 激活Activity组件
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if ("GoodsOrderDetailActivity".equals(start)) {
            finish();
        } else {
            Intent intent = new Intent(DrawBackHandlingActivity.this, GoodsOrderDetailActivity.class);
            intent.putExtra("order_sn",order_sn);
            startActivity(intent);
            finish();
        }
    }
}
