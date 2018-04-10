package com.xi6666.illegal.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.eventbus.LoginEvent;
import com.xi6666.illegal.bean.BreakListBean;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.login.view.LoginAct;
import com.xi6666.order.other.CustomAdapter;
import com.xi6666.order.other.CustomViewHolder;
import com.xi6666.order.other.SpaceItemDecoration;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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


public class IllegalQueryListActivity extends ToolBarBaseActivity {

    @BindView(R.id.tv_no_query_list)
    TextView       mTvNoQueryList;
    @BindView(R.id.xrlv_query_list)
    XRecyclerView  mXrlvQueryList;
    @BindView(R.id.rl_add_query)
    RelativeLayout mRlAddQuery;
    @BindView(R.id.ll_query_list)
    LinearLayout   mLlQueryList;
    @BindView(R.id.ll_reload_data)
    LinearLayout   mLlReloadData;
    @BindView(R.id.btn_reload_data)
    Button         mBtnReloadData;

    private List<BreakListBean.DataBean> dataBeans = new ArrayList<>();
    private MyAdapter             mAdapter;
    private BreakListBean                breakListBean;
    private String                       userId;
    private String                       user_token;

    private int page = 1;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lllegal_query_list);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
        initData();
        initEvent();
    }

    private void initEvent() {
        mXrlvQueryList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initData();
                mXrlvQueryList.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                initData();
                mXrlvQueryList.loadMoreComplete();
            }
        });
        mBtnReloadData.setOnClickListener(v -> {
            initData();
        });

    }


    private void init() {
        mDialog = ShowDialogUitls.showDio(this);
        userId = UserData.getUserId();
        user_token = UserData.getUserToken();
        mXrlvQueryList.setPullRefreshEnabled(true);
        mXrlvQueryList.setLoadingMoreEnabled(true);
        mXrlvQueryList.setLayoutManager(new LinearLayoutManager(this));
        mXrlvQueryList.setHasFixedSize(true);
        mAdapter = new MyAdapter(this,dataBeans,R.layout.breaklist_item);
        mXrlvQueryList.addItemDecoration(new SpaceItemDecoration(this,11,mAdapter));
        mXrlvQueryList.setAdapter(mAdapter);

        mLlReloadData.setVisibility(View.GONE);
        mLlQueryList.setVisibility(View.GONE);
        mRlAddQuery.setVisibility(View.GONE);
    }

    private void initData() {
        mDialog.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).getBreakList(page + "", userId,user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(() -> {
                            if (mDialog.isShowing()) {
                                mDialog.dismiss();
                            }
                    });
                    String result = response.body().string();
                    LogUtil.e("BreakListAct", result);
                    breakListBean = GsonUtils.toEntityFromJson(result, BreakListBean.class);
                    List<BreakListBean.DataBean> data = breakListBean.getData();
                    if (page == 1) {
                        if (data.size() <= 0) {
                            mLlReloadData.setVisibility(View.GONE);
                            mLlQueryList.setVisibility(View.VISIBLE);
                            mTvNoQueryList.setVisibility(View.VISIBLE);
                            mXrlvQueryList.setVisibility(View.GONE);
                        } else {
                            mLlReloadData.setVisibility(View.GONE);
                            mLlQueryList.setVisibility(View.VISIBLE);
                            mTvNoQueryList.setVisibility(View.GONE);
                            mXrlvQueryList.setVisibility(View.VISIBLE);
                        }
                    }
                    if (data.size() > 0) {
                        if (page == 1) {
                            dataBeans.clear();
                        }
                        dataBeans.addAll(data);
                        if (data.size() < 15) {
                            mXrlvQueryList.loadMoreComplete();
                        }
                    }
                    mRlAddQuery.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                mLlReloadData.setVisibility(View.VISIBLE);
                mLlQueryList.setVisibility(View.GONE);
                mRlAddQuery.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "违章查询";
    }

    @OnClick(R.id.rl_add_query)
    public void onClick() {
        // 跳转到违章查询界面
        // 判断用户是否登录，如果没有登录则跳转到登录界面
        if (userId == null || TextUtils.isEmpty(userId)) {
            // 登录
            startActivity(new Intent(IllegalQueryListActivity.this, LoginAct.class));
        } else {
            startActivity(new Intent(IllegalQueryListActivity.this, IllegalFindActivity.class));
        }
    }

    //登陆成功返回的event事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginSuccess(LoginEvent loginEvent) {
        userId = UserData.getUserId();
        page = 1;
        dataBeans.clear();
        initData();
        mXrlvQueryList.refreshComplete();
    }

    class MyAdapter extends CustomAdapter<BreakListBean.DataBean> {

        /**
         * 构造器
         *
         * @param context
         * @param mDatas
         * @param itemLayoutId
         */
        protected MyAdapter(Context context, List<BreakListBean.DataBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(CustomViewHolder holder, BreakListBean.DataBean item) {
            int RealPosition = holder.getAdapterPosition() - 1;

            TextView listcar = holder.findViewById(R.id.list_card);
            TextView wei_num = holder.findViewById(R.id.weizhang_num);
            TextView pay_money = holder.findViewById(R.id.pay_money);
            TextView kou_fen = holder.findViewById(R.id.koufen);
            TextView history_tv_address = holder.findViewById(R.id.history_tv_address);
            ImageView delete = holder.findViewById(R.id.delete);
            LinearLayout content = holder.findViewById(R.id.ll_illegal_content);

            listcar.setText(item.getCar_no());
            wei_num.setText(item.getCount());
            pay_money.setText("¥" + item.getMoney());
            kou_fen.setText(item.getFen());
            history_tv_address.setText("(" + item.getCity() + ")");

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
                    LogUtil.i("params","user_id=" + UserData.getUserId() + "user_token=" + UserData.getUserToken()
                    + "car_no=" + item.getCar_no() + "city_code=" + item.getCity_code() + "province=" + item.getProvince_code());
                    retrofit.create(NetAddress.class).getBreakDelete(UserData.getUserId(),UserData.getUserToken(), item.getCar_no(), item.getCity_code(), item.getProvince_code()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String result = response.body().string();
                                LogUtil.i("delete response", result);
                                JSONObject object = new JSONObject(result);
                                if (object.optBoolean("success")) {
                                    Toast.makeText(IllegalQueryListActivity.this, object.optString("info"), Toast.LENGTH_SHORT).show();
                                    dataBeans.remove(item);
                                    notifyItemRemoved(RealPosition);
                                    LogUtil.i("position", RealPosition + "");
                                    if (RealPosition != dataBeans.size()) {
                                        notifyItemRangeChanged(RealPosition, dataBeans.size() - RealPosition);
                                    }
                                } else {
                                    Toast.makeText(IllegalQueryListActivity.this, object.optString("info"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                        }
                    });
                }
            });

            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(IllegalQueryListActivity.this, IllegalFindResultAct.class);
                    intent.putExtra("userid", userId);
                    intent.putExtra("car_no", item.getCar_no());
                    intent.putExtra("city_code", item.getCity_code());
                    intent.putExtra("engineno", item.getEngineno());
                    intent.putExtra("classno", item.getClassno());
                    intent.putExtra("province_code", item.getProvince_code());
                    startActivity(intent);
                }
            });
        }
    }
}
