package com.xi6666.illegal.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.illegal.bean.IllegalUseBean;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.order.other.CustomAdapter;
import com.xi6666.order.other.CustomViewHolder;
import com.xi6666.order.other.SpaceItemDecoration;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConfirmUseActivity extends ToolBarBaseActivity {

    @BindView(R.id.use_xRecyclerView)
    XRecyclerView mUseXRecyclerView;
    @BindView(R.id.tv_cs_illegal_card_num)
    TextView      mTvCsIllegalCardNum;
    @BindView(R.id.tv_cs_illegal_card_type)
    TextView      mTvCsIllegalCardType;
    @BindView(R.id.tv_cs_illegal_details)
    TextView      mTvCsIllegalDetails;
    @BindView(R.id.tv_cs_illegal_count)
    TextView      mTvCsIllegalCount;
    @BindView(R.id.et_illegal_phone)
    EditText      mEtIllegalPhone;
    @BindView(R.id.btn_confirm_use)
    Button        mBtnConfirmUse;
    @BindView(R.id.btn_reload_data)
    Button        mBtnReloadData;
    @BindView(R.id.ll_content)
    LinearLayout  mLlContent;
    @BindView(R.id.ll_reload_data)
    LinearLayout  mLlReloadData;

    private MyAdapter mAdapter;
    private List<IllegalUseBean.DataBean.ListBean> mList = new ArrayList<>();

    private String                       userId;
    private String                       user_token;
    private String                       log_id_str;
    private String                       car_no;
    private String                       card_id;
    private IllegalUseBean               mIllegalUseBean;

    private Dialog mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_use);
        ButterKnife.bind(this);
        init();
        initData();
    }

    private void init() {
        mLoading = ShowDialogUitls.showDio(this);
        userId = UserData.getUserId();
        user_token = UserData.getUserToken();
        card_id = getIntent().getStringExtra("card_id");

        log_id_str = getIntent().getStringExtra("log_id_str");

        mUseXRecyclerView.setPullRefreshEnabled(false);
        mUseXRecyclerView.setLoadingMoreEnabled(false);
        mUseXRecyclerView.setHasFixedSize(true);
        mUseXRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(this,mList,R.layout.item_use_illegal_card);
        mUseXRecyclerView.addItemDecoration(new SpaceItemDecoration(this,11,mAdapter));
        mUseXRecyclerView.setAdapter(mAdapter);

        mLlReloadData.setVisibility(View.VISIBLE);
        mLlContent.setVisibility(View.GONE);
        mBtnConfirmUse.setVisibility(View.GONE);

        mBtnReloadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    private void initData() {
        if (!mLoading.isShowing()) mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).confirmUseIllegalCard(userId,user_token,log_id_str).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                try {
                    mLlReloadData.setVisibility(View.GONE);
                    mLlContent.setVisibility(View.VISIBLE);
                    mBtnConfirmUse.setVisibility(View.VISIBLE);
                    String result = response.body().string();
                    LogUtil.i("confirmUseIllegalCard",result);
                    mIllegalUseBean = GsonUtils.toEntityFromJson(result,IllegalUseBean.class);
                    if (mIllegalUseBean.isSuccess()) {
                        IllegalUseBean.DataBean data = mIllegalUseBean.getData();
                        mList.clear();
                        mList.addAll(data.getList());
                        mAdapter.notifyDataSetChanged();
                        car_no =mList.get(0).getCarno();

                        card_id = data.getCard_id();
                        mTvCsIllegalCardNum.setText(data.getCard_number());
                        mTvCsIllegalCardType.setText(data.getCard_type() + "个月套餐");
                        mTvCsIllegalCount.setText(data.getCount());
                        mTvCsIllegalDetails.setText("(共罚款 ¥ " + data.getMoney() + "，扣" + data.getFen() + "分)");
                    } else {
                        Utils.make(ConfirmUseActivity.this,mIllegalUseBean.getInfo());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mLlReloadData.setVisibility(View.VISIBLE);
                    mLlContent.setVisibility(View.GONE);
                    mBtnConfirmUse.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                mLlReloadData.setVisibility(View.VISIBLE);
                mLlContent.setVisibility(View.GONE);
                mBtnConfirmUse.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "确认使用";
    }

    @OnClick(R.id.btn_confirm_use)
    public void onClick() {
        // 确认使用违章卡
        commit();
    }

    private void commit() {
        if (!mLoading.isShowing()) mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        String mobile = "";
        if (!TextUtils.isEmpty(mEtIllegalPhone.getText().toString().trim())) {
            mobile = mEtIllegalPhone.getText().toString().trim();
        }
        retrofit.create(NetAddress.class).commitUseIllegalCard(userId,user_token,car_no,mobile,log_id_str).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                try {
                    String result = response.body().string();
                    LogUtil.i("commitUseIllegalCard",result);
                    JSONObject jsonObject = new JSONObject(result);
                    Utils.make(ConfirmUseActivity.this,jsonObject.optString("info"));
                    if (jsonObject.optBoolean("success")) {
                        // 跳转到提交成功页面
                        Intent intent = new Intent(ConfirmUseActivity.this, CommitSuccessActivity.class);
                        intent.putExtra("card_id",card_id);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                Utils.make(ConfirmUseActivity.this,"使用违章卡失败,请稍后重试！");
            }
        });
    }

    class MyAdapter extends CustomAdapter<IllegalUseBean.DataBean.ListBean> {

        /**
         * 构造器
         *
         * @param context
         * @param mDatas
         * @param itemLayoutId
         */
        protected MyAdapter(Context context, List<IllegalUseBean.DataBean.ListBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(CustomViewHolder holder, IllegalUseBean.DataBean.ListBean item) {
            TextView address = holder.findViewById(R.id.item_illegal_address);
            TextView date = holder.findViewById(R.id.item_tv_illegal_date);
            TextView comment = holder.findViewById(R.id.item_tv_illegal_comment);
            TextView money = holder.findViewById(R.id.item_tv_illegal_money);
            TextView fen = holder.findViewById(R.id.item_tv_illegal_fen);

            address.setText(item.getArea());
            String time = item.getDate_time();
            date.setText(time.substring(0, time.length() - 3));
            comment.setText(item.getAct());
            money.setText(item.getMoney() + " 元");
            fen.setText(item.getFen() + " 分");
        }
    }
}
