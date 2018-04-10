package com.xi6666.order.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.xi6666.R;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApplyDrawBackFormActivity extends AppCompatActivity {

    @BindView(R.id.iv_apply_form_back)
    ImageView      mIvApplyFormBack;
    @BindView(R.id.apply_form_tb)
    Toolbar        mApplyFormTb;
    @BindView(R.id.tv_apply_form_money)
    TextView       mTvApplyFormMoney;
    @BindView(R.id.tv_input_num)
    TextView       mTvInputNumber;
    @BindView(R.id.tv_apply_form_server_type)
    TextView       mTvApplyFormServerType;
    @BindView(R.id.tv_apply_form_pay_type)
    TextView       mTvApplyFormPayType;
    @BindView(R.id.tv_apply_form_reason)
    TextView       mTvApplyFormReason;
    @BindView(R.id.tv_apply_form_select_reason)
    TextView       mTvApplyFormSelectReason;
    @BindView(R.id.rl_apply_form_select_reason)
    RelativeLayout mRlApplyFormSelectReason;
    @BindView(R.id.ll_top_half)
    LinearLayout   mLlTopHalf;
    @BindView(R.id.et_tui_huo_desc)
    EditText       mEtTuiHuoDesc;

    private String money;
    private String pay_type;
    private String order_sn;
    private Dialog mLoading;
    private String user_id;
    private String user_token;

    private OptionsPickerView mPickerViewTuikuan;
    private ArrayList<String> mOptionsItemsTuiKuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_draw_back_form);
        ButterKnife.bind(this);
        setSupportActionBar(mApplyFormTb);
        initPickerView();
        initData();
        initEvent();
    }

    private void initData() {
        mLoading = ShowDialogUitls.showDio(this);
        Intent intent = getIntent();
        money = intent.getStringExtra("money");
        pay_type = intent.getStringExtra("pay_type");
        order_sn = intent.getStringExtra("order_sn");
        user_id = intent.getStringExtra("user_id");
        user_token = intent.getStringExtra("user_token");
        mTvApplyFormMoney.setText("¥ " + money);
        mTvApplyFormPayType.setText(pay_type);
        if (mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }

    private void initEvent() {
        // 监听输入的字数变化
        mEtTuiHuoDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvInputNumber.setText(s.length() + "/" + "100");
                if (s.length() == 100) {
                    mTvInputNumber.setTextColor(Color.RED);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick({R.id.iv_apply_form_back, R.id.rl_apply_form_select_reason, R.id.tv_order_details_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_apply_form_back:
                finish();
                break;
            case R.id.rl_apply_form_select_reason:
                showReasonForSelect();
                break;

            case R.id.tv_order_details_commit:
                if ("请选择退款原因".equals(mTvApplyFormSelectReason.getText().toString())) {
                    Toast.makeText(ApplyDrawBackFormActivity.this, "请选择申请退款的原因", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mEtTuiHuoDesc.getText().toString())) {
                    Toast.makeText(ApplyDrawBackFormActivity.this, "请填写具体的说明,最多100字", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 像后台发送申请售后的请求
                appLyDrawBack();
                break;
        }
    }

    private void appLyDrawBack() {
        mLoading.show();
        new Retrofit.Builder().baseUrl(NetAddress.baseUrl).build().create(NetAddress.class).
                applyDrawback(order_sn, mTvApplyFormSelectReason.getText().toString(),
                        mEtTuiHuoDesc.getText().toString(),user_id,user_token).enqueue(new Callback<ResponseBody>() {
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
                    LogUtil.i("ApplyDrawBackFormActivity", result);
                    // 如果申请成功,跳转到申请完成的界面
                    JSONObject jsonObject = new JSONObject(result);
                    Toast.makeText(ApplyDrawBackFormActivity.this, jsonObject.optString("info"), Toast.LENGTH_SHORT).show();
                    if (jsonObject.optBoolean("success")) {
                        Intent intent = new Intent(ApplyDrawBackFormActivity.this, DrawBackHandlingActivity.class);
                        intent.putExtra("order_sn", order_sn);
                        intent.putExtra("user_id",user_id);
                        intent.putExtra("user_token",user_token);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                Toast.makeText(ApplyDrawBackFormActivity.this, "网络异常，请稍后重试!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showReasonForSelect() {
        mPickerViewTuikuan.show();
    }

    private void initPickerView() {
        mPickerViewTuikuan = new OptionsPickerView(ApplyDrawBackFormActivity.this);
        mOptionsItemsTuiKuan = new ArrayList<>();
        mOptionsItemsTuiKuan.add("现在不想购买");
        mOptionsItemsTuiKuan.add("商品价格比较贵");
        mOptionsItemsTuiKuan.add("价格波动");
        mOptionsItemsTuiKuan.add("商品缺货");
        mOptionsItemsTuiKuan.add("重复下单");
        mOptionsItemsTuiKuan.add("添加或删除商品");
        mOptionsItemsTuiKuan.add("收货人地址有误");
        mOptionsItemsTuiKuan.add("其他");
        mPickerViewTuikuan.setTitle("请选择退款原因");
        mPickerViewTuikuan.setPicker(mOptionsItemsTuiKuan);
        mPickerViewTuikuan.setCyclic(false);
        setSelectListener(mPickerViewTuikuan, mOptionsItemsTuiKuan);
    }

    private void setSelectListener(OptionsPickerView pickerView, final ArrayList<String> optionsItems) {
        pickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                mTvApplyFormSelectReason.setText(optionsItems.get(options1));
            }
        });
    }
}
