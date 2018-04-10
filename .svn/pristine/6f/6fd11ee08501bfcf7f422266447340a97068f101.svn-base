package com.xi6666.order.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.order.bean.DeleteBean;
import com.xi6666.order.bean.ShoppingCartBean;
import com.xi6666.order.other.CustomAdapter;
import com.xi6666.order.other.CustomViewHolder;
import com.xi6666.order.other.Utils;
import com.xi6666.productdetails.view.ProductDetailsAct;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;
import com.xi6666.view.dialog.BaseDialog;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
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

public class MyShoppingCartActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.iv_msc_back)
    ImageView     mIvMscBack;
    @BindView(R.id.tv_msc_edit)
    TextView      mTvMscEdit;
    @BindView(R.id.tv_msc_done)
    TextView       mTvMscDone;
    @BindView(R.id.msc_tb)
    Toolbar        mMscTb;
    @BindView(R.id.msc_XRecyclerView)
    XRecyclerView  mMscXRecyclerView;
    @BindView(R.id.tv_no_sp)
    TextView       mTvNoSp;
    @BindView(R.id.msc_cb_bottom)
    CheckBox       mMscCbBottom;
    @BindView(R.id.tv_msc_need_pay)
    TextView       mTvMscNeedPay;
    @BindView(R.id.ll_msc_total_money)
    LinearLayout   mLLMscToatlMoney;
    @BindView(R.id.ll_reload_data)
    LinearLayout   mLLRelaodData;
    @BindView(R.id.rl_msc_bottom)
    RelativeLayout mRLMscBottom;
    @BindView(R.id.tv_msc_to_pay)
    TextView       mTvMscToPay;
    @BindView(R.id.btn_msc_delete)
    Button         mBtnMscDelete;
    @BindView(R.id.btn_reload_data)
    Button         mBtnReloadData;
    private String                          userId;
    private String                          user_token;
    private ShoppingCartBean                mShoppingCartBean;
    private List<ShoppingCartBean.DataBean.GoodsListBean> mGoodsData = new ArrayList<>();

    private int page = 1;

    private boolean isEdit          = false;
    private double  needPayMoneyAll = 0.00;

    private LinearLayoutManager mLayoutManager;

    private DecimalFormat             df             = new DecimalFormat("######0.00");
    private List<Boolean> mItemIsSelects = new ArrayList<>();
    private List<Boolean> tempSelecets = new ArrayList<>();
    private Dialog mLoading;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shopping_cart);
        ButterKnife.bind(this);
        setSupportActionBar(mMscTb);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        ToolBarBaseActivity.setMiuiStatusBarDarkMode(this,true);
        init();
        initData();
        initEvent();
    }

    private void initEvent() {
        mMscXRecyclerView.setLoadingListener(this);

        mBtnMscDelete.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                controlBottomBtn();
            }
        });
    }

    private void init() {
        userId = UserData.getUserId();
        user_token = UserData.getUserToken();
        mLoading = ShowDialogUitls.showDio(this);

        mLayoutManager = new LinearLayoutManager(this);
        mMscXRecyclerView.setLayoutManager(mLayoutManager);
        mMscXRecyclerView.setPullRefreshEnabled(true);
        mMscXRecyclerView.setLoadingMoreEnabled(false);
        mAdapter = new MyAdapter(this, mGoodsData, R.layout.item_shopping_cart);
//        mMscXRecyclerView.addItemDecoration(new SpaceItemDecoration(MyShoppingCartActivity.this, 10, mAdapter));
        mMscXRecyclerView.setAdapter(mAdapter);
        mMscCbBottom.setChecked(false);

        mLLRelaodData.setVisibility(View.GONE);
        mMscXRecyclerView.setVisibility(View.GONE);
        mRLMscBottom.setVisibility(View.GONE);
        mTvNoSp.setVisibility(View.GONE);

        controlBottomBtn();
    }

    private void initData() {
        // 获取购物车商品列表数据
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).myShoppingCart(userId,user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(() -> {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                    });
                    String result = response.body().string();
                    LogUtil.i("MyshoppingCartActivity",result);
                    mShoppingCartBean = GsonUtils.toEntityFromJson(result, ShoppingCartBean.class);
                    if (mShoppingCartBean.isSuccess()) {
                        List<ShoppingCartBean.DataBean> data = mShoppingCartBean.getData();
                        for (int i = 0; i < data.size(); i ++) {
                            mGoodsData.addAll(data.get(i).getGoods_list());
                        }
                        if (mGoodsData.size() <= 0) {
                            mTvNoSp.setVisibility(View.VISIBLE);
                            mRLMscBottom.setVisibility(View.VISIBLE);
                            mMscXRecyclerView.setVisibility(View.GONE);
                            mTvMscEdit.setVisibility(View.GONE);
                            mLLRelaodData.setVisibility(View.GONE);
                            mMscCbBottom.setChecked(false);
                            mTvMscEdit.setVisibility(View.GONE);
                            mTvMscNeedPay.setText("¥ 0.00");
                        } else {
                            mLLRelaodData.setVisibility(View.GONE);
                            mTvNoSp.setVisibility(View.GONE);
                            mRLMscBottom.setVisibility(View.VISIBLE);
                            mMscXRecyclerView.setVisibility(View.VISIBLE);
                            mTvMscEdit.setVisibility(View.VISIBLE);
                            mMscCbBottom.setChecked(true);
                            for (int i = 0; i <= mGoodsData.size(); i++) {
                                mItemIsSelects.add(true);
                            }
                            calculateMoney();
                            controlBottomBtn();
                            mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Utils.make(MyShoppingCartActivity.this, mShoppingCartBean.getInfo());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mLLRelaodData.setVisibility(View.VISIBLE);
                    mMscXRecyclerView.setVisibility(View.GONE);
                    mRLMscBottom.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                mLLRelaodData.setVisibility(View.VISIBLE);
                mTvMscEdit.setVisibility(View.GONE);
                mMscXRecyclerView.setVisibility(View.GONE);
                mRLMscBottom.setVisibility(View.GONE);
                mTvNoSp.setVisibility(View.GONE);
            }
        });
    }

    /**
     *  计算所选商品的总价
     */
    private void calculateMoney() {
        // 遍历所有的商品
        double money = 0;
        double zhe_money = 0;
        double jian_money = 0;
        double zhe_active_money = 0;
        double zhe_discount = 0;
        double jian_active_money = 0;
        double jian_discount = 0;
        for (int i = 0; i < mGoodsData.size(); i ++) {
            if (mItemIsSelects.get(i)) {
                // 被选中的需要计算总价
                if (mGoodsData.get(i).getIs_on_sale() == 1) {
                    if ("1".equals(mGoodsData.get(i).getActive_type())) {
                        // 满折活动
                        String shop_price = mGoodsData.get(i).getShop_price();
                        String number = mGoodsData.get(i).getGoods_number();
                        zhe_money += (Double.parseDouble(shop_price) * Integer.parseInt(number));
                        zhe_active_money = Double.parseDouble(mGoodsData.get(i).getActive_money());
                        zhe_discount = Double.parseDouble(mGoodsData.get(i).getActive_discount());
                    } else if ("2".equals(mGoodsData.get(i).getActive_type())) {
                        // 满减活动
                        String shop_price = mGoodsData.get(i).getShop_price();
                        String number = mGoodsData.get(i).getGoods_number();
                        jian_money += (Double.parseDouble(shop_price) * Integer.parseInt(number));
                        jian_active_money = Double.parseDouble(mGoodsData.get(i).getActive_money());
                        jian_discount = Double.parseDouble(mGoodsData.get(i).getActive_discount());
                    } else {
                        String shop_price = mGoodsData.get(i).getShop_price();
                        String number = mGoodsData.get(i).getGoods_number();
                        money += (Double.parseDouble(shop_price) * Integer.parseInt(number));
                    }
                }
            }
        }
        if (zhe_money >= zhe_active_money && jian_money >= jian_active_money) {
            double zhe_s = zhe_money - (zhe_money * (zhe_discount / 100));
            double jian_s = jian_money - jian_discount;
            needPayMoneyAll = money + zhe_s + jian_s;
        } else if (zhe_money < zhe_active_money && jian_money >= jian_active_money) {
            double jian_s = jian_money - jian_discount;
            needPayMoneyAll = money + zhe_money + jian_s;
        } else if (zhe_money >= zhe_active_money && jian_money < jian_active_money) {
            double zhe_s = zhe_money - (zhe_money * (zhe_discount / 100));
            needPayMoneyAll = money + zhe_s + jian_money;
        } else {
            needPayMoneyAll = money + zhe_money + jian_money;
        }
        mTvMscNeedPay.setText("¥ " + df.format(needPayMoneyAll));
    }

    /**
     * 控制底部按钮的状态
     */
    private void controlBottomBtn() {
        int count = 0;
        for (int i = 0; i < mGoodsData.size(); i++) {
            if (mItemIsSelects.get(i)) {
                count++;
            }
        }
        if (count == 0) {
//            mBtnMscDelete.setTextColor(getResources().getColor(R.color.alpha_red_text));
//            mBtnMscDelete.setEnabled(false);
            mTvMscToPay.setBackgroundColor(getResources().getColor(R.color.light_gray_text));
            mTvMscToPay.setClickable(false);
        } else {
            // 控制去结算按钮
            mTvMscToPay.setClickable(true);
            mTvMscToPay.setBackgroundColor(getResources().getColor(R.color.themeColor));
            // 控制删除按钮
//            mBtnMscDelete.setEnabled(true);
//            mBtnMscDelete.setTextColor(getResources().getColor(R.color.red_text));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.iv_msc_back, R.id.tv_msc_edit, R.id.tv_msc_done, R.id.tv_msc_to_pay, R.id.btn_msc_delete,R.id.msc_cb_bottom,R.id.btn_reload_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_msc_back:
                finish();
                break;
            case R.id.tv_msc_edit:
                if (!isEdit) {
                    isEdit = true;
                    for (int i = 0; i < mItemIsSelects.size(); i ++) {
                        tempSelecets.add(mItemIsSelects.get(i));
                    }
                    mItemIsSelects.clear();
                    for (int i = 0; i < tempSelecets.size(); i ++) {
                        mItemIsSelects.add(false);
                    }
                    mTvMscEdit.setVisibility(View.GONE);
                    mTvMscDone.setVisibility(View.VISIBLE);
                    mLLMscToatlMoney.setVisibility(View.GONE);
                    mTvMscToPay.setVisibility(View.GONE);
                    mBtnMscDelete.setVisibility(View.VISIBLE);
                    mAdapter.notifyDataSetChanged();
                    controlBottomBtn();
                }
                break;
            case R.id.tv_msc_done:
                if (isEdit) {
                    isEdit = false;
                    mItemIsSelects.clear();
                    mItemIsSelects.addAll(tempSelecets);
                    mTvMscEdit.setVisibility(View.VISIBLE);
                    mTvMscDone.setVisibility(View.GONE);
                    mLLMscToatlMoney.setVisibility(View.VISIBLE);
                    mTvMscToPay.setVisibility(View.VISIBLE);
                    mBtnMscDelete.setVisibility(View.GONE);
                    mTvMscNeedPay.setText("¥ " + df.format(needPayMoneyAll));
                    mAdapter.notifyDataSetChanged();
                    controlBottomBtn();
                    calculateMoney();
                }
                break;
            case R.id.tv_msc_to_pay:
                // 去订单确认页结算
                gotoOrderConfirm();
                break;
            case R.id.btn_msc_delete:
                int count = 0;
                for (int i = 0; i < mGoodsData.size(); i++) {
                    if (mItemIsSelects.get(i)) {
                        count++;
                    }
                }
                if (count > 0) {
                    showDeleteDialog();
                } else {
                    Utils.make(this,"请选择至少一件商品！");
                }
                break;
            case R.id.msc_cb_bottom:
                mItemIsSelects.clear();
                if (mMscCbBottom.isChecked()) {
                    for (int i = 0; i < mGoodsData.size(); i++) {
                        mItemIsSelects.add(true);
                    }
                } else {
                    for (int i = 0; i < mGoodsData.size(); i++) {
                        mItemIsSelects.add(false);
                    }
                }
                controlBottomBtn();
                calculateMoney();
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_reload_data:
                initData();
                break;
        }
    }

    /**
     * 去结算
     */
    private void gotoOrderConfirm() {
        int count = 0;
        int xiaJia = 0;
        JSONArray array = new JSONArray();
        for (int i = 0; i < mGoodsData.size(); i++) {
            if (mItemIsSelects.get(i)) {
                try {
                    count++;
                    if (mGoodsData.get(i).getIs_on_sale() != 1) {
                        xiaJia++;
                    } else {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("goods_id",mGoodsData.get(i).getGoods_id());
                        jsonObject.put("sku_value_id",mGoodsData.get(i).getSku_value_id());
                        array.put(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if (count == 0) {
            Utils.make(this,"请选择您需要购买的商品！");
        } else {
            Intent intent = new Intent(this,GoodsOrderAffirmActivity.class);
            intent.putExtra("goods_arr",array.toString());
            startActivity(intent);
        }
        /*if (xiaJia > 0) {
            Utils.make(this,"下架商品不能结算！");
        } else {
            if (count == 0) {
                Utils.make(this,"请选择您需要购买的商品！");
            } else {
                Intent intent = new Intent(this,GoodsOrderAffirmActivity.class);
                intent.putExtra("goods_arr",array.toString());
                startActivity(intent);
            }
        }*/

    }

    private void showDeleteDialog() {
        BaseDialog baseDialog = new BaseDialog(this);
        baseDialog.setLeftAndRight("取消","删除");
        baseDialog.setTitle("确定删除选中的商品吗？");
        baseDialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
            @Override
            public void onLeftOnclick() {
                baseDialog.dismiss();
            }

            @Override
            public void onRightOncklick() {
                try {
                    List<List<DeleteBean>> list = new ArrayList<>();
                    JSONArray array = new JSONArray();
                    for (int i = 0; i < mGoodsData.size(); i ++) {
                        if (mItemIsSelects.get(i)) {
                            List<DeleteBean> beans = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("user_id",userId);
                            jsonObject.put("goods_id",mGoodsData.get(i).getGoods_id());
                            jsonObject.put("sku_value_id",mGoodsData.get(i).getSku_value_id());
                            beans.add(new DeleteBean(mGoodsData.get(i).getGoods_id(),mGoodsData.get(i).getSku_value_id()));
                            array.put(jsonObject);
                        }
                    }
                    deleteGoods(array.toString(),false,0,list);
                    baseDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        baseDialog.show();
    }

    /**
     *  删除商品
     */
    private void deleteGoods(String json, final boolean isOne, final int position, final List<List<DeleteBean>> list) {
        LogUtil.i("要删除的商品：",json);
        mLoading.show();
        new Retrofit.Builder().baseUrl(NetAddress.baseUrl).build().create(NetAddress.class).deleteCartGoods(json).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                runOnUiThread(() -> {
                        if (mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                });
                try {
                    String s = response.body().string();
                    LogUtil.i("delete_cart_goods",s);
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.optBoolean("success")) {
                        // 更新条目
                        if (isOne) {
                            // 删除一条
                            mGoodsData.remove(position);
                            mItemIsSelects.remove(position);
                            if (tempSelecets.size() > 0) {
                                tempSelecets.remove(position);
                            }
                            mAdapter.notifyItemRemoved(position);
                        } else {
                            // 删除多条
                            List<Integer> indexs = new ArrayList<>();
                            for (int i = 0; i < list.size(); i ++) {
                                List<DeleteBean> deleteBeen = list.get(i);
                                if (deleteBeen.get(i).getGoods_id().equals(mGoodsData.get(i).getGoods_id())
                                        && deleteBeen.get(i).getSku_value_id().equals(mGoodsData.get(i).getSku_value_id())) {
                                    indexs.add(i);
                                }
                            }

                            for (int i = 0; i < indexs.size(); i ++) {
                                mGoodsData.remove(i);
                                mItemIsSelects.remove(i);
                                if (tempSelecets.size() > 0) {
                                    tempSelecets.remove(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                        int visibility = mTvMscEdit.getVisibility();
                        if (visibility == View.VISIBLE) {
                            mTvMscEdit.setVisibility(View.GONE);
                        }
                        onRefresh();
                        calculateMoney();
                    } else {
                        Utils.make(MyShoppingCartActivity.this,jsonObject.optString("info"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRefresh() {
        mGoodsData.clear();
        initData();
        mAdapter.notifyDataSetChanged();
        mMscXRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {

    }

    class MyAdapter extends CustomAdapter<ShoppingCartBean.DataBean.GoodsListBean> {

        /**
         * 构造器
         *
         * @param context
         * @param mDatas
         * @param itemLayoutId
         */
        protected MyAdapter(Context context, List<ShoppingCartBean.DataBean.GoodsListBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(final CustomViewHolder holder, final ShoppingCartBean.DataBean.GoodsListBean item) {
            final int position = holder.getAdapterPosition() - 1;
            LinearLayout header = holder.findViewById(R.id.ll_shopcart_header);
            TextView active_name = holder.findViewById(R.id.item_tv_active_name);
            TextView active_desc = holder.findViewById(R.id.item_tv_active_desc);
            TextView couDan = holder.findViewById(R.id.item_tv_cou_dan);
            LinearLayout llCouDan = holder.findViewById(R.id.ll_cou_dan);
            View divide = holder.findViewById(R.id.view_no_active);

            TextView name = holder.findViewById(R.id.item_tv_sc_goods_desc);
            TextView color = holder.findViewById(R.id.item_tv_sc_color);
            TextView size = holder.findViewById(R.id.item_tv_sc_size);
            TextView menPrice = holder.findViewById(R.id.item_tv_sc_men_price);
            TextView xiPrice = holder.findViewById(R.id.item_tv_sc_xi_price);
            ImageView ivGoodsImg = holder.findViewById(R.id.item_iv_sc_goods_img);
            ImageView delete = holder.findViewById(R.id.sc_delete);
            LinearLayout llInfo = holder.findViewById(R.id.ll_goods_jiang_jia);
            LinearLayout content = holder.findViewById(R.id.ll_msc_content);
            TextView tvInfo = holder.findViewById(R.id.tv_goods_jiang_jia_money);
            final EditText tvNum = holder.findViewById(R.id.item_sc_et_goods_num);
            final CheckBox checkBox = holder.findViewById(R.id.item_sc_cb);

            double active_money = Double.parseDouble(item.getActive_money());
            double active_discount = Double.parseDouble(item.getActive_discount());

            String active_type = item.getActive_type();
            String active_id = item.getActive_id();
            if ("1".equals(active_type) || ("2".equals(active_type))) {
                active_name.setText(item.getActive_type_name());
                active_desc.setText(item.getActive_name());
                if (position > 0) {
                    if (mGoodsData.get(position - 1).getActive_id().equals(active_id)) {
                        header.setVisibility(View.GONE);
                        divide.setVisibility(View.GONE);
                    } else {
                        header.setVisibility(View.VISIBLE);
                        divide.setVisibility(View.GONE);
                    }
                } else {
                    header.setVisibility(View.VISIBLE);
                    divide.setVisibility(View.GONE);
                }
            } else {
                // 普通商品
                header.setVisibility(View.GONE);
                divide.setVisibility(View.VISIBLE);
            }

            llCouDan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO 去凑单
                }
            });

            //设置文字斜杠
            TextPaint paint = menPrice.getPaint();
            paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            paint.setAntiAlias(true);

            name.setText(item.getGoods_name());
            int sku1_id = Integer.parseInt(item.getSku1_id());
            int sku2_id = Integer.parseInt(item.getSku2_id());
            if (sku1_id > 0) {
                color.setText(item.getSku1_name() + " : " + item.getSku1_value());
            }
            if (sku2_id > 0) {
                size.setText(item.getSku2_name() + " : " + item.getSku2_value());
            }
            menPrice.setText("¥ " + item.getMarket_price());
            xiPrice.setText("¥ " + item.getShop_price());
            tvNum.setText(item.getGoods_number());

            Glide.with(MyShoppingCartActivity.this)
                    .load(item.getGoods_thumb_img()).placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(ivGoodsImg);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mItemIsSelects.remove(position);
                    mItemIsSelects.add(position,isChecked);
                    calculateMoney();
                    calcActive(active_desc, couDan, active_money, active_discount, active_type);
                    controlBottomBtn();
                    controlBottomCB();
                }
            });

            int is_on_sale = item.getIs_on_sale();
            double reduce_price = item.getReduce_price();
            final String goods_id = item.getGoods_id();
            final String sku_value_id = item.getSku_value_id();
            final String goods_stock_number = item.getGoods_stock_number();

            checkBox.setChecked(mItemIsSelects.get(position));
//            checkBox.setChecked(item.getIsSelect());

            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isEdit) {
                        // 改变条目checkBox的状态
                        checkBox.toggle();
                        // 记录改变后的状态值
                        mItemIsSelects.remove(position);
                        mItemIsSelects.add(position, checkBox.isChecked());
                    } else {
                        // 跳转到商品详情页
                        if (is_on_sale != 1) {
                            return;
                        }
                        Intent intent = new Intent(MyShoppingCartActivity.this, ProductDetailsAct.class);
                        intent.putExtra("goodId",goods_id);
                        startActivity(intent);
                    }
                    controlBottomCB();
                    controlBottomBtn();
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 删除单个商品
                    try {
                        JSONArray array = new JSONArray();
                        JSONObject object = new JSONObject();
                        object.put("user_id",userId);
                        object.put("goods_id",goods_id);
                        object.put("sku_value_id",sku_value_id);
                        array.put(object);
                        deleteGoods(array.toString(),true,position,null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            if (is_on_sale == 1 && reduce_price == 0) {
                // 还在售且无降价
                llInfo.setVisibility(View.GONE);
                checkBox.setEnabled(true);
                checkBox.setVisibility(View.VISIBLE);
            } else if (is_on_sale == 1 && reduce_price != 0) {
                llInfo.setVisibility(View.VISIBLE);
                tvInfo.setText("当前商品已降价：¥" + df.format(reduce_price));
                checkBox.setEnabled(true);
                checkBox.setVisibility(View.VISIBLE);
            } else if (is_on_sale != 1) {
                llInfo.setVisibility(View.VISIBLE);
                tvInfo.setText("当前商品已下架!");
                checkBox.setEnabled(false);
                checkBox.setVisibility(View.INVISIBLE);
            }

            holder.findViewById(R.id.item_sc_goods_min).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 减少商品数量
                    int number = Integer.parseInt(item.getGoods_number());
                    if (number > 1) {
                        number--;
                        final int finalNum = number;
                        mLoading.show();
                        new Retrofit.Builder().baseUrl(NetAddress.baseUrl).build().create(NetAddress.class).editGoodsNum(goods_id,number + "",sku_value_id,userId,user_token).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                runOnUiThread(() -> {
                                        if (mLoading.isShowing()) {
                                            mLoading.dismiss();
                                        }
                                });
                                try {
                                    String s = response.body().string();
                                    LogUtil.i("----",s);
                                    JSONObject jsonObject = new JSONObject(s);
                                    if (jsonObject.optBoolean("success")) {
                                        mGoodsData.get(position).setGoods_number(finalNum + "");
                                        mAdapter.notifyDataSetChanged();
                                        calculateMoney();
                                    } else {
                                        Utils.make(MyShoppingCartActivity.this,jsonObject.optString("info"));
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
                        Utils.make(MyShoppingCartActivity.this,"商品数量不能小于1");
                    }
                }
            });

            holder.findViewById(R.id.item_sc_goods_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 增加商品数量
                    int number = Integer.parseInt(item.getGoods_number());
                    number++;
                    final int finalNum = number;
                    mLoading.show();
                    new Retrofit.Builder().baseUrl(NetAddress.baseUrl).build().create(NetAddress.class).editGoodsNum(goods_id,number + "",sku_value_id,userId,user_token).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            runOnUiThread(() -> {
                                    if (mLoading.isShowing()) {
                                        mLoading.dismiss();
                                    }
                            });
                            try {
                                String s = response.body().string();
                                LogUtil.i("++++",s);
                                JSONObject jsonObject = new JSONObject(s);
                                if (jsonObject.optBoolean("success")) {
                                    mGoodsData.get(position).setGoods_number(finalNum + "");
                                    mAdapter.notifyDataSetChanged();
                                    calculateMoney();
                                } else {
                                    Utils.make(MyShoppingCartActivity.this,jsonObject.optString("info"));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            });

            tvNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        final String content = v.getText().toString().trim();
                        final int num = Integer.parseInt(content);
                        if (num < 1) {
                            Utils.make(MyShoppingCartActivity.this,"商品数量不能小于1");
                            tvNum.setText(item.getGoods_number());
                        } else {
                            mLoading.show();
                            new Retrofit.Builder().baseUrl(NetAddress.baseUrl).build().create(NetAddress.class).editGoodsNum(goods_id,content,sku_value_id,userId,user_token).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    runOnUiThread(() -> {
                                            if (mLoading.isShowing()) {
                                                mLoading.dismiss();
                                            }
                                    });
                                    try {
                                        String s = response.body().string();
                                        LogUtil.i("editNum",s);
                                        JSONObject jsonObject = new JSONObject(s);
                                        if (jsonObject.optBoolean("success")) {
                                            mGoodsData.get(position).setGoods_number(content);
                                            mAdapter.notifyDataSetChanged();
                                            calculateMoney();
                                        } else {
                                            tvNum.setText(goods_stock_number);
                                            mGoodsData.get(position).setGoods_number(goods_stock_number);
                                            mAdapter.notifyDataSetChanged();
                                            Utils.make(MyShoppingCartActivity.this,jsonObject.optString("info"));
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }
                    }
                    return false;
                }
            });

            calcActive(active_desc, couDan, active_money, active_discount, active_type);

        }


    }

    private void calcActive(TextView active_desc, TextView couDan, double active_money, double active_discount, String active_type) {
        double zhe_money = 0;
        double jian_money = 0;
        for (int i = 0; i < mGoodsData.size(); i++) {
            if ("1".equals(mGoodsData.get(i).getActive_type())) {
                // 满折活动
                if (mItemIsSelects.get(i)) {
                    String shop_price = mGoodsData.get(i).getShop_price();
                    String number = mGoodsData.get(i).getGoods_number();
                    zhe_money += (Double.parseDouble(shop_price) * Integer.parseInt(number));
                }
            } else if ("2".equals(mGoodsData.get(i).getActive_type())) {
                // 满减活动
                if (mItemIsSelects.get(i)) {
                    String shop_price = mGoodsData.get(i).getShop_price();
                    String number = mGoodsData.get(i).getGoods_number();
                    jian_money += (Double.parseDouble(shop_price) * Integer.parseInt(number));
                }
            }
        }
        if ("1".equals(active_type)) {
            int zhe = (int) (active_discount / 10);
            if (zhe_money >= active_money) {
                double sheng = zhe_money * (active_discount / 100);
                active_desc.setText("已购满" + df.format(active_money) + "元,打" + zhe + "折减" + df.format(sheng) + "元");
                couDan.setText("再逛逛");
            } else {
                double cha = active_money - zhe_money;
                active_desc.setText("满" + df.format(active_money) + "元打" + zhe + "折,还差" + df.format(cha) + "元");
                couDan.setText("去凑单");
            }
        }

        if ("2".equals(active_type)) {
            if (jian_money >= active_money) {
                active_desc.setText("已购满" + df.format(active_money) + "元,已减" + active_discount + "元");
                couDan.setText("再逛逛");
            } else {
                double cha = active_money - jian_money;
                active_desc.setText("满" + df.format(active_money) + "元减" + active_discount + "元,还差" + cha + "元");
                couDan.setText("去凑单");
            }
        }
    }

    private void controlBottomCB() {
        int count = 0;
        for (int i = 0; i < mGoodsData.size(); i++) {
            if (mItemIsSelects.get(i)) {
                count++;
            }
        }
        if (count == mGoodsData.size()) {
            mMscCbBottom.setChecked(true);
            // 控制删除按钮
//            mBtnMscDelete.setTextColor(getResources().getColor(R.color.red_text));
//            mBtnMscDelete.setEnabled(true);
            // 控制去结算按钮
            mTvMscToPay.setBackgroundColor(getResources().getColor(R.color.themeColor));
            mTvMscToPay.setClickable(true);
        } else if (count == 0) {
            mMscCbBottom.setChecked(false);
            // 控制删除按钮
//            mBtnMscDelete.setTextColor(getResources().getColor(R.color.alpha_red_text));
//            mBtnMscDelete.setEnabled(false);
            // 控制去结算按钮
            mTvMscToPay.setBackgroundColor(getResources().getColor(R.color.light_gray_text));
            mTvMscToPay.setClickable(false);
        } else {
            mMscCbBottom.setChecked(false);
            // 控制删除按钮
//            mBtnMscDelete.setTextColor(getResources().getColor(R.color.red_text));
//            mBtnMscDelete.setEnabled(true);
            // 控制去结算按钮
            mTvMscToPay.setBackgroundColor(getResources().getColor(R.color.themeColor));
            mTvMscToPay.setClickable(true);
        }
    }
}
