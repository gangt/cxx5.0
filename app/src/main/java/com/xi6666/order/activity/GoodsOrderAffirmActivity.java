package com.xi6666.order.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.address.ReceiptAddressAct;
import com.xi6666.address.fragment.mvp.bean.DistributionShopBean;
import com.xi6666.address.fragment.mvp.bean.PersonalAddressBean;
import com.xi6666.common.UserData;
import com.xi6666.eventbus.GoodsAddressNotEvent;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.order.bean.OrderComfirmBean;
import com.xi6666.order.other.ListViewForScrollView;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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

public class GoodsOrderAffirmActivity extends AppCompatActivity {

    @BindView(R.id.tv_order_affirm_receive_name)
    TextView              mTvOrderAffirmReceiveName;
    @BindView(R.id.tv_order_affirm_receive_phone)
    TextView              mTvOrderAffirmReceivePhone;
    @BindView(R.id.tv_order_affirm_receive_address)
    TextView              mTvOrderAffirmReceiveAddress;
    @BindView(R.id.ll_order_affirm_receive_address)
    LinearLayout          mLlOrderAffirmReceiveAddress;
    @BindView(R.id.iv_order_affirm_store_img)
    ImageView             mIvOrderAffirmStoreImg;
    @BindView(R.id.tv_order_affirm_store_name)
    TextView              mTvOrderAffirmStoreName;
    @BindView(R.id.tv_order_affirm_store_distance)
    TextView              mTvOrderAffirmStoreDistance;
    @BindView(R.id.tv_order_affirm_store_address)
    TextView              mTvOrderAffirmStoreAddress;
    @BindView(R.id.ll_order_affirm_store_address)
    LinearLayout          mLlOrderAffirmStoreAddress;
    @BindView(R.id.rl_add_receive_address)
    RelativeLayout        mRlAddReceiveAddress;
    @BindView(R.id.goods_affirm_listView)
    ListViewForScrollView mGoodsListView;
    @BindView(R.id.tv_affirm_goods_amount)
    TextView   mTvGoodsAmount;
    @BindView(R.id.tv_affirm_goods_freight)
    TextView   mTvGoodsFreight;
    @BindView(R.id.tv_affirm_goods_jian)
    TextView   mTvGoodsJian;
    @BindView(R.id.tv_amount_integer)
    TextView   mTvAmountInteger;
    @BindView(R.id.tv_amount_point)
    TextView   mTvAmountPoint;
    @BindView(R.id.btn_affirm_to_pay)
    Button     mBtnAffirmToPay;
    @BindView(R.id.order_affirm_tb)
    Toolbar    mToolbar;
    @BindView(R.id.scrollView_order_affirm)
    ScrollView mScrollView;
    @BindView(R.id.ll_bottom_order_affirm)
    LinearLayout mllBottom;
    @BindView(R.id.ll_reload_data)
    LinearLayout mllReloadData;
    @BindView(R.id.btn_reload_data)
    Button mBtnReloadData;
    @BindView(R.id.rl_you_hui_quan)
    RelativeLayout mRlQuan;
    @BindView(R.id.rl_affirm_goods_jian)
    RelativeLayout mRlJian;
    @BindView(R.id.tv_you_hui_quan)
    TextView mTvQuan;
    @BindView(R.id.iv_quan)
    ImageView mIvQuan;

    private MyAdapter        mAdapter;
    private String           goods_arr;
    private String           userId;
    private String           user_token;
    private OrderComfirmBean mOrderComfirmBean;
    private Dialog           mLoading;
    private List<OrderComfirmBean.DataBean.GoodsInfoBean.GoodsListBean> mGoodsListBeen = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("######0.00");
    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 10086;
    public static final int REQUEST_SELECT_COUPON = 1234;
    private String mAddress_id;
    private String mTo_supplier_id;
    private String coupon_id;
    private String coupon_money;
    private String secKill_id;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_affirm);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        EventBus.getDefault().register(this);
        init();
        initData();
    }

    private void init() {
        userId = UserData.getUserId();
        user_token = UserData.getUserToken();

        mLoading = ShowDialogUitls.showDio(this);
        mScrollView.setVisibility(View.GONE);
        mllBottom.setVisibility(View.GONE);
        mllReloadData.setVisibility(View.GONE);

        type = getIntent().getStringExtra("type");
        if (type == null || "".equals(type)) {
            // 从购物车来的商品
            secKill_id = "";
            goods_arr = getIntent().getStringExtra("goods_arr");
            initData();
        } else if ("secKill".equals(type)) {
            // 立即秒杀
            secKill_id = getIntent().getStringExtra("secID");
            secKillGoods();
        } else if ("surprised".equals(type)) {
            // 惊喜价购买
            secKill_id = "";
            String secID = getIntent().getStringExtra("secID");
            JSONArray array = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("goods_id",secID);
                jsonObject.put("sku_value_id","");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(jsonObject);
            goods_arr = array.toString();
            initData();
        }
    }

    private void secKillGoods() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).seckillOrderInfo(mAddress_id,mTo_supplier_id,userId,user_token,secKill_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(() ->{
                        if (mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                    });
                    mScrollView.setVisibility(View.VISIBLE);
                    mllBottom.setVisibility(View.VISIBLE);
                    mllReloadData.setVisibility(View.GONE);
                    String result = response.body().string();
                    LogUtil.i("GoodsOrderAffirmActivity", result);
                    mOrderComfirmBean = GsonUtils.toEntityFromJson(result, OrderComfirmBean.class);
                    if (mOrderComfirmBean.isSuccess()) {
                        OrderComfirmBean.DataBean.AddrBean addr = mOrderComfirmBean.getData().getAddr();
                        if (addr.getAddress_id() != null && !TextUtils.isEmpty(addr.getAddress_id())) {
                            mTvOrderAffirmReceiveName.setText(addr.getConsignee());
                            mTvOrderAffirmReceivePhone.setText(addr.getMobile());
                            mTvOrderAffirmReceiveAddress.setText(addr.getProvince_name() + addr.getCity_name() + addr.getDistrict_name() + addr.getAddress());
                            mAddress_id = addr.getAddress_id();
                            mRlAddReceiveAddress.setVisibility(View.GONE);
                            mLlOrderAffirmReceiveAddress.setVisibility(View.VISIBLE);
                            mLlOrderAffirmStoreAddress.setVisibility(View.GONE);
                        } else {
                            mRlAddReceiveAddress.setVisibility(View.VISIBLE);
                            mLlOrderAffirmReceiveAddress.setVisibility(View.GONE);
                            mLlOrderAffirmStoreAddress.setVisibility(View.GONE);
                        }

                        mTvQuan.setText("暂无券可用");
                        mRlQuan.setClickable(false);
                        mIvQuan.setVisibility(View.GONE);
                        mRlJian.setVisibility(View.GONE);

                        mTvGoodsAmount.setText("¥ " + mOrderComfirmBean.getData().getGoods_info().getGoods_total_price());
                        mTvGoodsFreight.setText("¥ " + df.format(Double.parseDouble(mOrderComfirmBean.getData().getPost_fee())));
                        double pay_money = Double.parseDouble(mOrderComfirmBean.getData().getPay_money());
                        mTvAmountInteger.setText("¥ " + df.format(pay_money));
                        mGoodsListBeen.clear();
                        mGoodsListBeen.addAll(mOrderComfirmBean.getData().getGoods_info().getGoods_list());
                        mAdapter = new MyAdapter(mGoodsListBeen);
                        mGoodsListView.setAdapter(mAdapter);

                    } else {
                        Utils.make(GoodsOrderAffirmActivity.this,mOrderComfirmBean.getInfo());
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
                mScrollView.setVisibility(View.GONE);
                mllBottom.setVisibility(View.GONE);
                mllReloadData.setVisibility(View.VISIBLE);
            }
        });
    }

    private void caculateYunFei() {
        if (!"secKill".equals(type)) {
            mLoading.show();
            Retrofit.Builder builder = new Retrofit.Builder();
            Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
            retrofit.create(NetAddress.class).orderConfirm(mAddress_id,goods_arr,mTo_supplier_id,userId,user_token,"android",coupon_id).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        runOnUiThread(() -> {
                                    if (mLoading.isShowing()) {
                                        mLoading.dismiss();
                                    }
                                }
                        );
                        String result = response.body().string();
                        LogUtil.i("yunfei", result);
                        mOrderComfirmBean = GsonUtils.toEntityFromJson(result, OrderComfirmBean.class);
                        if (mOrderComfirmBean.isSuccess()) {
                            mTvGoodsAmount.setText("¥ " + mOrderComfirmBean.getData().getGoods_info().getGoods_total_price());
                            mTvGoodsFreight.setText("¥ " + df.format(Double.parseDouble(mOrderComfirmBean.getData().getPost_fee())));
                            double pay_money = Double.parseDouble(mOrderComfirmBean.getData().getPay_money());
                            mTvAmountInteger.setText("¥ " + df.format(pay_money));
                        } else {
                            Toast.makeText(GoodsOrderAffirmActivity.this, mOrderComfirmBean.getInfo(),Toast.LENGTH_SHORT).show();
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
                    Utils.make(GoodsOrderAffirmActivity.this, "网络异常");
                }
            });
        }
    }

    private void initData() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).orderConfirm(mAddress_id,goods_arr,mTo_supplier_id,userId,user_token,"android",coupon_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(() ->{
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                    });
                    mScrollView.setVisibility(View.VISIBLE);
                    mllBottom.setVisibility(View.VISIBLE);
                    mllReloadData.setVisibility(View.GONE);
                    String result = response.body().string();
                    LogUtil.i("GoodsOrderAffirmActivity", result);
                    mOrderComfirmBean = GsonUtils.toEntityFromJson(result, OrderComfirmBean.class);
                    if (mOrderComfirmBean.isSuccess()) {

                        OrderComfirmBean.DataBean.AddrBean addr = mOrderComfirmBean.getData().getAddr();
                        if (addr.getAddress_id() != null && !TextUtils.isEmpty(addr.getAddress_id())) {
                            mTvOrderAffirmReceiveName.setText(addr.getConsignee());
                            mTvOrderAffirmReceivePhone.setText(addr.getMobile());
                            mTvOrderAffirmReceiveAddress.setText(addr.getProvince_name() + addr.getCity_name() + addr.getDistrict_name() + addr.getAddress());
                            mAddress_id = addr.getAddress_id();
                            mRlAddReceiveAddress.setVisibility(View.GONE);
                            mLlOrderAffirmReceiveAddress.setVisibility(View.VISIBLE);
                            mLlOrderAffirmStoreAddress.setVisibility(View.GONE);
                        } else {
                            mRlAddReceiveAddress.setVisibility(View.VISIBLE);
                            mLlOrderAffirmReceiveAddress.setVisibility(View.GONE);
                            mLlOrderAffirmStoreAddress.setVisibility(View.GONE);
                        }
                        if (!TextUtils.isEmpty(coupon_money)) {
                            mTvQuan.setText("-¥ " + coupon_money);
                            mTvQuan.setTextColor(Color.parseColor("#f53825"));
                            mIvQuan.setVisibility(View.VISIBLE);
                        } else {
                            double reduce_total_price = mOrderComfirmBean.getData().getGoods_info().getReduce_total_price();
                            int can_use_coupon = mOrderComfirmBean.getData().getGoods_info().getCan_use_coupon();
                            if (reduce_total_price > 0) {
                                mTvQuan.setText("无符合条件优惠券");
                                mRlQuan.setClickable(false);
                                mIvQuan.setVisibility(View.GONE);
                                mRlJian.setVisibility(View.VISIBLE);
                                mTvGoodsJian.setText("-¥ " + df.format(reduce_total_price));
                                mTvQuan.setTextColor(Color.parseColor("#121212"));
                            } else {
                                if (can_use_coupon == 0) {
                                    mTvQuan.setText("无符合条件优惠券");
                                    mRlQuan.setClickable(false);
                                    mIvQuan.setVisibility(View.GONE);
                                    mRlJian.setVisibility(View.GONE);
                                } else {
                                    mTvQuan.setText("有券可用");
                                    mRlQuan.setClickable(true);
                                    mIvQuan.setVisibility(View.GONE);
                                    mRlJian.setVisibility(View.GONE);
                                }
                                mTvQuan.setTextColor(Color.parseColor("#121212"));
                            }
                        }
                        mTvGoodsAmount.setText("¥ " + mOrderComfirmBean.getData().getGoods_info().getGoods_total_price());
                        mTvGoodsFreight.setText("¥ " + df.format(Double.parseDouble(mOrderComfirmBean.getData().getPost_fee())));
                        double pay_money = Double.parseDouble(mOrderComfirmBean.getData().getPay_money());
                        mTvAmountInteger.setText("¥ " + df.format(pay_money));
                        mGoodsListBeen.clear();
                        mGoodsListBeen.addAll(mOrderComfirmBean.getData().getGoods_info().getGoods_list());
                        mAdapter = new MyAdapter(mGoodsListBeen);
                        mGoodsListView.setAdapter(mAdapter);

                    } else {
                        Utils.make(GoodsOrderAffirmActivity.this,mOrderComfirmBean.getInfo());
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
                mScrollView.setVisibility(View.GONE);
                mllBottom.setVisibility(View.GONE);
                mllReloadData.setVisibility(View.VISIBLE);
            }
        });
    }

    @OnClick({R.id.ll_order_affirm_receive_address, R.id.ll_order_affirm_store_address, R.id.rl_add_receive_address,
            R.id.btn_affirm_to_pay,R.id.iv_order_affirm_back,R.id.ctv_call_phone,R.id.btn_reload_data,R.id.rl_you_hui_quan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_order_affirm_receive_address:
                // 跳转到选择收货地址页面
                ReceiptAddressAct.SelectAddress(GoodsOrderAffirmActivity.this,0);
                break;
            case R.id.ll_order_affirm_store_address:
                // 跳转到选择门店地址页面
                ReceiptAddressAct.SelectAddress(GoodsOrderAffirmActivity.this,1);
                break;
            case R.id.rl_add_receive_address:
                // 跳转到选择收货地址页面
                ReceiptAddressAct.SelectAddress(GoodsOrderAffirmActivity.this,0);
                break;
            case R.id.btn_affirm_to_pay:
                // 生成订单
                if (!TextUtils.isEmpty(mAddress_id) || !TextUtils.isEmpty(mTo_supplier_id)) {
                    if ("secKill".equals(type)) {
                        seckillGenerateOrder();
                    } else {
                        generateOrder();
                    }
                } else {
                    Toast.makeText(GoodsOrderAffirmActivity.this,"请选择收货地址或者配送门店地址！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_order_affirm_back:
                finish();
                break;
            case R.id.ctv_call_phone:
                // 拨打客服电话
                Utils.callTel("4009999353", GoodsOrderAffirmActivity.this);
                break;
            case R.id.btn_reload_data:
                // 重新加载数据
                initData();
                break;
            case R.id.rl_you_hui_quan:
                // 选择优惠券
                Intent intent = new Intent(GoodsOrderAffirmActivity.this, UseCouponActivity.class);
                intent.putExtra("money", mOrderComfirmBean.getData().getGoods_info().getGoods_total_price());
                startActivityForResult(intent,REQUEST_SELECT_COUPON);
                break;
        }
    }

    /**
     * 生成订单
     */
    private void generateOrder() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        LogUtil.i("request","mAddress_id=" + mAddress_id + "&goods_arr=" + goods_arr + "&mTo_supplier_id=" + mTo_supplier_id
                + "&userId=" + userId + "&user_token" + user_token);
        retrofit.create(NetAddress.class).generateOrder(mAddress_id,goods_arr,mTo_supplier_id,userId,user_token,secKill_id,coupon_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(() -> {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                        }
                    );
                    String result = response.body().string();
                    LogUtil.i("GoodsOrderAffirmActivity_createOrder", result);
                    JSONObject jsonObject = new JSONObject(result);
                    Utils.make(GoodsOrderAffirmActivity.this,jsonObject.optString("info"));
                    if (jsonObject.optBoolean("success")) {
                        // 生成订单成功，跳转到收银台页面
                        JSONObject data = jsonObject.optJSONObject("data");
                        String order_sn = data.optString("order_sn");
                        String order_id = data.optString("id");
                        Intent intent = new Intent(GoodsOrderAffirmActivity.this,CashDeskActivity.class);
                        intent.putExtra("order_sn",order_sn);
                        intent.putExtra("order_id",order_id);
                        startActivity(intent);
                        finish();
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

    /**
     * 立即秒杀生成订单
     */
    private void seckillGenerateOrder() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).seckillGenerateOrder(mAddress_id,mTo_supplier_id,userId,user_token,secKill_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(() -> {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                        }
                    );
                    String result = response.body().string();
                    LogUtil.i("seckillGenerateOrder", result);
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.optBoolean("success")) {
                        // 生成订单成功，跳转到收银台页面
                        JSONObject data = jsonObject.optJSONObject("data");
                        String order_sn = data.optString("order_sn");
                        String order_id = data.optString("id");
                        Intent intent = new Intent(GoodsOrderAffirmActivity.this,CashDeskActivity.class);
                        intent.putExtra("order_sn",order_sn);
                        intent.putExtra("order_id",order_id);
                        startActivity(intent);
                        finish();
                    } else {
                        Utils.make(GoodsOrderAffirmActivity.this,jsonObject.optString("info"));
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

    class MyAdapter extends BaseAdapter {
        private List<OrderComfirmBean.DataBean.GoodsInfoBean.GoodsListBean> mDatas;

        private MyAdapter(List<OrderComfirmBean.DataBean.GoodsInfoBean.GoodsListBean> datas) {
            this.mDatas = datas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(GoodsOrderAffirmActivity.this,R.layout.item_goods_order_affirm,null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.iv_order_affirm_goods_img);
                holder.name = (TextView) convertView.findViewById(R.id.tv_order_affirm_goods_desc);
                holder.color = (TextView) convertView.findViewById(R.id.tv_order_affirm_color);
                holder.size = (TextView) convertView.findViewById(R.id.tv_order_affirm_size);
                holder.xiaoXiPrice = (TextView) convertView.findViewById(R.id.tv_order_affirm_goods_xi_price);
                holder.marketPrice = (TextView) convertView.findViewById(R.id.tv_order_affirm_goods_menshi_price);
                holder.num = (TextView) convertView.findViewById(R.id.tv_order_affirm_goods_num);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            OrderComfirmBean.DataBean.GoodsInfoBean.GoodsListBean goodsListBean = mDatas.get(position);

            //设置文字斜杠
            TextPaint paint = holder.marketPrice.getPaint();
            paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            paint.setAntiAlias(true);

            holder.name.setText(goodsListBean.getGoods_name());
            holder.marketPrice.setText("¥ " + goodsListBean.getMarket_price());
            if ("secKill".equals(type)) {
                holder.xiaoXiPrice.setText("¥ " + goodsListBean.getGoods_seckill_price());
            } else {
                holder.xiaoXiPrice.setText("¥ " + goodsListBean.getGoods_price());
            }
            holder.num.setText("x" + goodsListBean.getGoods_number());

            int sku1_id = Integer.parseInt(goodsListBean.getSku1_id());
            int sku2_id = Integer.parseInt(goodsListBean.getSku2_id());
            if (sku1_id > 0) {
                holder.color.setText(goodsListBean.getSku1_name() + " : " + goodsListBean.getSku1_value());
            }
            if (sku2_id > 0) {
                holder.size.setText(goodsListBean.getSku2_name() + " : " + goodsListBean.getSku2_value());
            }

            Glide.with(GoodsOrderAffirmActivity.this)
                    .load(goodsListBean.getGoods_thumb_img()).placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(holder.img);
            return convertView;
        }
    }

    class ViewHolder {
        ImageView img;
        TextView  name;
        TextView  color;
        TextView  size;
        TextView  xiaoXiPrice;
        TextView  marketPrice;
        TextView  num;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ReceiptAddressAct.SELECT_CODE) {
                PersonalAddressBean.DataBean addressBean = data.getParcelableExtra(ReceiptAddressAct.SELECT_DATA_ADDRESS);
                if (addressBean != null) {
                    mTvOrderAffirmReceiveName.setText(addressBean.consignee);
                    mTvOrderAffirmReceivePhone.setText(addressBean.mobile);
                    mTvOrderAffirmReceiveAddress.setText(addressBean.province_name + addressBean.city_name + addressBean.district_name + addressBean.address);
                    mLlOrderAffirmReceiveAddress.setVisibility(View.VISIBLE);
                    mLlOrderAffirmStoreAddress.setVisibility(View.GONE);
                    mRlAddReceiveAddress.setVisibility(View.GONE);
                    mAddress_id = addressBean.address_id;
                    mTo_supplier_id = "";
                    caculateYunFei();
                } else {
                    DistributionShopBean.DataBean storeBean  = data.getParcelableExtra(ReceiptAddressAct.SELECT_DATA_SERVICE_STORE);
                    mTvOrderAffirmStoreName.setText(storeBean.shop_name);
                    mTvOrderAffirmStoreAddress.setText(storeBean.shop_address);
                    String shop_banner = storeBean.shop_banner;
                    mTvOrderAffirmStoreDistance.setText(storeBean.distance);
                    Glide.with(GoodsOrderAffirmActivity.this)
                            .load(shop_banner).placeholder(R.drawable.no_data_empty).centerCrop()
                            .into(mIvOrderAffirmStoreImg);
                    mAddress_id = "";
                    mTo_supplier_id = storeBean.id;
                    mLlOrderAffirmReceiveAddress.setVisibility(View.GONE);
                    mLlOrderAffirmStoreAddress.setVisibility(View.VISIBLE);
                    mRlAddReceiveAddress.setVisibility(View.GONE);
                    caculateYunFei();
                }
            } else if (requestCode == REQUEST_SELECT_COUPON) {
                coupon_id = data.getStringExtra("coupon_id");
                coupon_money = data.getStringExtra("coupon_money");
                initData();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selectAddress(GoodsAddressNotEvent event) {
        boolean code = event.code;
        if (code) {
            mTo_supplier_id = "";
            mAddress_id = "";
            mLlOrderAffirmReceiveAddress.setVisibility(View.GONE);
            mLlOrderAffirmStoreAddress.setVisibility(View.GONE);
            mRlAddReceiveAddress.setVisibility(View.VISIBLE);
        }
    }
}
