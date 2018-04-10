package com.xi6666.order.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.xi6666.common.UserData;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.order.bean.GoodsOrderDetailsBean;
import com.xi6666.order.other.AdvancedCountdownTimer;
import com.xi6666.order.other.ListViewForScrollView;
import com.xi6666.order.other.Utils;
import com.xi6666.productdetails.view.ProductDetailsAct;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;
import com.xi6666.view.dialog.BaseDialog;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GoodsOrderDetailActivity extends ToolBarBaseActivity {

    @BindView(R.id.ll_wait_pay_top)
    LinearLayout          mLlWaitPayTop;
    @BindView(R.id.ll_wait_send_top)
    LinearLayout          mLlWaitSendTop;
    @BindView(R.id.ll_wait_receive_top)
    LinearLayout          mLlWaitReceiveTop;
    @BindView(R.id.ll_wait_has_done_top)
    LinearLayout          mLlWaitHasDoneTop;
    @BindView(R.id.ll_wait_has_cancel_top)
    LinearLayout          mLlWaitHasCancelTop;
    @BindView(R.id.tv_goods_order_num)
    TextView              mTvGoodsOrderNum;
    @BindView(R.id.tv_limit_time)
    TextView              mTvLimitTime;
    @BindView(R.id.tv_cancel_reason)
    TextView              mTvCancelReason;
    @BindView(R.id.tv_down_order_time)
    TextView              mTvDownOrderTime;
    @BindView(R.id.tv_goods_order_type)
    TextView              mTvGoodsOrderType;
    @BindView(R.id.tv_receive_name)
    TextView              mTvReceiveName;
    @BindView(R.id.tv_receive_phone)
    TextView              mTvReceivePhone;
    @BindView(R.id.tv_receive_address)
    TextView              mTvReceiveAddress;
    @BindView(R.id.tv_wuliu_info)
    TextView              mTvWuliuInfo;
    @BindView(R.id.ll_receive_address)
    LinearLayout          mLlReceiveAddress;
    @BindView(R.id.iv_store_img)
    ImageView             mIvStoreImg;
    @BindView(R.id.tv_store_name)
    TextView              mTvStoreName;
    @BindView(R.id.tv_goods_total_amount)
    TextView              mTvGoodsTotalAmonut;
    @BindView(R.id.tv_store_address)
    TextView              mTvStoreAddress;
    @BindView(R.id.iv_call_store_phone)
    ImageView             mIvCallStorePhone;
    @BindView(R.id.ll_store_address)
    LinearLayout          mLlStoreAddress;
    @BindView(R.id.listView_for_goods)
    ListViewForScrollView mListViewForGoods;
    @BindView(R.id.tv_translation_money)
    TextView              mTvTranslationMoney;
    @BindView(R.id.tv_you_hui_quan)
    TextView              mTvYouHuiQuan;
    @BindView(R.id.tv_order_total_amount)
    TextView              mTvOrderTotalAmount;
    @BindView(R.id.tv_call_kefu_phone)
    TextView              mTvCallKefuPhone;
    @BindView(R.id.btn_cancel_order)
    Button                mBtnCancelOrder;
    @BindView(R.id.btn_to_pay)
    Button                mBtnToPay;
    @BindView(R.id.ll_wait_pay_bottom)
    LinearLayout          mLlWaitPayBottom;
    @BindView(R.id.btn_apply_drawback)
    Button                mBtnApplyDrawback;
    @BindView(R.id.btn_tixing_send)
    Button                mBtnTixingSend;
    @BindView(R.id.ll_wait_send_bottom)
    LinearLayout          mLlWaitSendBottom;
    @BindView(R.id.btn_affirm_receive)
    Button                mBtnAffirmReceive;
    @BindView(R.id.ll_wait_receive_bottom)
    LinearLayout          mLlWaitReceiveBottom;
    @BindView(R.id.btn_to_evaluate_or_see_evaluate)
    Button                mBtnToEvaluateOrSeeEvaluate;
    @BindView(R.id.ll_has_done_bottom)
    LinearLayout          mLlHasDoneBottom;
    @BindView(R.id.btn_see_drawback)
    Button                mBtnSeeDrawback;
    @BindView(R.id.ll_has_cancel_bottom)
    LinearLayout          mLlHasCancelBottom;
    @BindView(R.id.ll_receive_see_wuliu)
    LinearLayout   mLlReceiveSeeWuliu;
    @BindView(R.id.ll_store_see_wuliu)
    LinearLayout   mLlStoreSeeWuliu;
    @BindView(R.id.ll_wuliu_info)
    LinearLayout   mLlWuliuInfo;
    @BindView(R.id.ll_reload_data)
    LinearLayout   mLlReloadData;
    @BindView(R.id.btn_reload_data)
    Button         mBtnReloadData;
    @BindView(R.id.god_scrollView)
    ScrollView     mScrollView;
    @BindView(R.id.rl_details_quan)
    RelativeLayout mRlquan;
    @BindView(R.id.rl_details_jian)
    RelativeLayout mRlJian;
    @BindView(R.id.tv_details_jian)
    TextView mTvJian;
    @BindView(R.id.iv_djq)
    ImageView mIvDjq;

    private String                order_sn;
    private String                order_id;
    private Dialog                mLoading;
    private String                user_id;
    private String                user_token;
    private GoodsOrderDetailsBean mGoodsOrderDetailsBean;
    private MyCount               mCount;
    private SimpleDateFormat      mFormat;
    private DecimalFormat         mDecimalFormat;
    private long                  orderTime;
    private String                money;
    private String                pay_type;
    private boolean               isComment;
    private int                   goodsNum;
    private String                goods_id;
    private String                goodsImg;
    private String                yundan_code;
    private String                invoice_no;
    private String                store_mobile;
    private List<GoodsOrderDetailsBean.DataBean.GoodsInfoBean> goodsList = new ArrayList<>();
    private BaseDialog baseDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_detail);
        ButterKnife.bind(this);
        mLoading = ShowDialogUitls.showDio(this);
        user_id = UserData.getUserId();
        user_token = UserData.getUserToken();
        mFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        mDecimalFormat = new DecimalFormat("#####0.00");
        order_sn = getIntent().getStringExtra("order_sn");
        baseDialog = new BaseDialog(this);
        resetTopAndBottomVG();
        initData();
        initEvent();
    }

    private void initData() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).getGoodsOrderDetails(order_sn, user_id, user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(() -> {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                    });
                    String result = response.body().string();
                    LogUtil.i("GoodsOrderDetailActivity", result);
                    mGoodsOrderDetailsBean = GsonUtils.toEntityFromJson(result, GoodsOrderDetailsBean.class);
                    if (mGoodsOrderDetailsBean.isSuccess()) {
                        GoodsOrderDetailsBean.DataBean data = mGoodsOrderDetailsBean.getData();
                        invoice_no = data.getInvoice_no();
                        yundan_code = data.getYundan_code();
                        order_id = data.getOrder_id();
                        mTvGoodsOrderNum.setText(data.getOrder_sn());
                        mTvDownOrderTime.setText(mFormat.format(new Date(Long.parseLong(data.getAdd_datetime()) * 1000)));
                        mTvGoodsOrderType.setText(data.getOrder_type());
                        mTvGoodsTotalAmonut.setText("¥ " + data.getGoods_amount());
                        mTvTranslationMoney.setText("¥ " + data.getShipping_amount());
//                        double total = Double.parseDouble(data.getGoods_amount()) + Double.parseDouble(data.getShipping_amount());
                        double total = Double.parseDouble(data.getOrder_money());
                        mTvOrderTotalAmount.setText("¥ " + mDecimalFormat.format(total));
                        money = mDecimalFormat.format(total);
                        initOrderType(data.getOrder_state(), data.getAdd_datetime(),data.getRefund_info(),data.getIs_refund(),data.getIs_comment());
                        mScrollView.setVisibility(View.VISIBLE);
                        mLlReloadData.setVisibility(View.GONE);
                        String consignee = data.getConsignee();
                        if (consignee != null && !TextUtils.isEmpty(consignee)) {
                            mLlReceiveAddress.setVisibility(View.VISIBLE);
                            mTvReceiveName.setText(consignee);
                            mTvReceiveAddress.setText(data.getAddress());
                            mTvReceivePhone.setText(data.getUser_mobile());
                            mTvWuliuInfo.setText(data.getYundan_name());
                            if (!"待付款".equals(data.getOrder_state()) && !"待发货".equals(data.getOrder_state()) && !"已取消".equals(data.getOrder_state())) {
                                mLlReceiveSeeWuliu.setVisibility(View.VISIBLE);
                            } else {
                                mLlReceiveSeeWuliu.setVisibility(View.GONE);
                            }
                        } else {
                            mLlStoreAddress.setVisibility(View.VISIBLE);
                            mTvStoreAddress.setText(data.getShop_address());
                            store_mobile = data.getShop_mobile();
                            Glide.with(GoodsOrderDetailActivity.this).load(data.getShop_banner()).placeholder(R.drawable.no_data_empty).into(mIvStoreImg);
                            mTvStoreName.setText(data.getShop_name());
                            if (!"待付款".equals(data.getOrder_state()) && !"待发货".equals(data.getOrder_state()) && !"已取消".equals(data.getOrder_state())) {
                                mLlStoreSeeWuliu.setVisibility(View.VISIBLE);
                            } else {
                                mLlStoreSeeWuliu.setVisibility(View.GONE);
                            }
                        }
                        pay_type = data.getPay_type();
                        goodsList.clear();
                        goodsList = data.getGoods_info();
                        goodsNum = goodsList.size();
                        goods_id = goodsList.get(0).getGoods_id();
                        goodsImg = goodsList.get(0).getGoods_soure_img();
                        mListViewForGoods.setAdapter(new MyAdapter(goodsList));
                        int discount_type = Integer.parseInt(data.getDiscount_type());
                        if (discount_type == 0) {
                            mRlquan.setVisibility(View.VISIBLE);
                            mRlJian.setVisibility(View.GONE);
                            mTvYouHuiQuan.setText("未使用优惠券");
                            mIvDjq.setVisibility(View.GONE);
                            mTvYouHuiQuan.setTextColor(Color.parseColor("#121212"));
                        } else if (discount_type == 1 || discount_type == 2) {
                            mRlquan.setVisibility(View.GONE);
                            mRlJian.setVisibility(View.VISIBLE);
                            mTvJian.setText("-¥ " + data.getDiscount_money());
                        } else if (discount_type == 3) {
                            mRlquan.setVisibility(View.VISIBLE);
                            mRlJian.setVisibility(View.GONE);
                            mIvDjq.setVisibility(View.VISIBLE);
                            mTvYouHuiQuan.setText("-¥ " + data.getDiscount_money());
                            mTvYouHuiQuan.setTextColor(Color.parseColor("#121212"));
                        }
                    } else {
                        Utils.make(GoodsOrderDetailActivity.this, mGoodsOrderDetailsBean.getInfo());
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
                mScrollView.setVisibility(View.VISIBLE);
                mLlReloadData.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 根据不同的订单状态显示对应的订单详情
     */
    private void initOrderType(String state, String time,String reason,int is_drawback,int is_comment) {
        resetTopAndBottomVG();
        orderTime = Long.parseLong(time) * 1000;
        if ("待付款".equals(state)) {
            mLlWaitPayTop.setVisibility(View.VISIBLE);
            mLlWaitPayBottom.setVisibility(View.VISIBLE);
            long nowTime = System.currentTimeMillis();
            long minute = (nowTime - orderTime) / (1000 * 60);
//            long second = (nowTime - orderTime) / (1000 * 60 * 60);
            long cSecond = (16 * 60) - (((nowTime - orderTime) / 1000));
            long start = (cSecond * 1000);   // 因为以ms为单位，所以乘以1000.

            if (cSecond < (16 * 60)) {
                // 开始倒计时
                mCount = new MyCount(start, 1000);
                mCount.start();
            }
            if (minute > 16) {
                mTvLimitTime.setText("");
            }
            mLlReceiveSeeWuliu.setVisibility(View.GONE);
            mLlStoreSeeWuliu.setVisibility(View.GONE);
        } else if ("待发货".equals(state)) {
            mLlWaitSendBottom.setVisibility(View.VISIBLE);
            mLlWaitSendTop.setVisibility(View.VISIBLE);
            long nowTime = System.currentTimeMillis();
//            long hour = Math.abs(orderTime - nowTime) / 1000;
            float hour = (float) ((nowTime - orderTime) / (1000 * 1000));
            LogUtil.i("hour","hour = " + hour );
            LogUtil.i("user_id","user_id = " + user_id);
            LogUtil.i("user_token","user_token = " + user_token);
            if (hour >= 24) {
                // 显示提醒发货按钮
                mBtnTixingSend.setVisibility(View.VISIBLE);
            } else {
                // 隐藏提醒发货按钮
                mBtnTixingSend.setVisibility(View.GONE);
            }
            mLlReceiveSeeWuliu.setVisibility(View.GONE);
            mLlStoreSeeWuliu.setVisibility(View.GONE);
        } else if ("待收货".equals(state)) {
            mLlWaitReceiveTop.setVisibility(View.VISIBLE);
            mLlWaitReceiveBottom.setVisibility(View.VISIBLE);
            mLlWuliuInfo.setVisibility(View.GONE);
            mLlReceiveSeeWuliu.setVisibility(View.VISIBLE);
            mLlStoreSeeWuliu.setVisibility(View.VISIBLE);
        } else if ("已完成".equals(state)) {
            if (is_comment == 1) {
                // 有评论
                mBtnToEvaluateOrSeeEvaluate.setText("查看评价");
                isComment = true;
            } else{
                // 无评论
                mBtnToEvaluateOrSeeEvaluate.setText("去评价");
                isComment = false;
            }
            mLlWaitHasDoneTop.setVisibility(View.VISIBLE);
            mLlHasDoneBottom.setVisibility(View.VISIBLE);
            mLlWuliuInfo.setVisibility(View.GONE);
            mLlReceiveSeeWuliu.setVisibility(View.GONE);
            mLlStoreSeeWuliu.setVisibility(View.GONE);
        } else if ("已取消".equals(state)) {
            mTvCancelReason.setText(reason);
            if (is_drawback == 1) {
                mBtnSeeDrawback.setVisibility(View.VISIBLE);
            } else {
                mBtnSeeDrawback.setVisibility(View.GONE);
            }
            mLlWaitHasCancelTop.setVisibility(View.VISIBLE);
            mLlHasCancelBottom.setVisibility(View.VISIBLE);
            mLlWuliuInfo.setVisibility(View.GONE);
            mLlReceiveSeeWuliu.setVisibility(View.GONE);
            mLlStoreSeeWuliu.setVisibility(View.GONE);
        }
    }

    private void resetTopAndBottomVG() {
        mLlHasCancelBottom.setVisibility(View.GONE);
        mLlWaitHasCancelTop.setVisibility(View.GONE);
        mLlHasDoneBottom.setVisibility(View.GONE);
        mLlReceiveAddress.setVisibility(View.GONE);
        mLlReceiveSeeWuliu.setVisibility(View.GONE);
        mLlStoreAddress.setVisibility(View.GONE);
        mLlStoreSeeWuliu.setVisibility(View.GONE);
        mLlWaitSendTop.setVisibility(View.GONE);
        mLlWaitSendBottom.setVisibility(View.GONE);
        mLlWaitReceiveBottom.setVisibility(View.GONE);
        mLlWaitReceiveTop.setVisibility(View.GONE);
        mLlWaitPayBottom.setVisibility(View.GONE);
        mLlWaitPayTop.setVisibility(View.GONE);
        mLlWaitHasDoneTop.setVisibility(View.GONE);
        mLlWuliuInfo.setVisibility(View.GONE);
        mScrollView.setVisibility(View.GONE);
        mLlReloadData.setVisibility(View.GONE);
        mLlReceiveSeeWuliu.setVisibility(View.GONE);
        mLlStoreSeeWuliu.setVisibility(View.GONE);
    }

    private void initEvent() {
        mListViewForGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 跳转到商品详情页
                Intent intent = new Intent(GoodsOrderDetailActivity.this, ProductDetailsAct.class);
                intent.putExtra("goodId",goodsList.get(position).getGoods_id());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "订单详情";
    }

    @OnClick({R.id.tv_call_kefu_phone, R.id.btn_cancel_order, R.id.btn_to_pay, R.id.btn_apply_drawback,
            R.id.btn_tixing_send, R.id.btn_affirm_receive, R.id.btn_to_evaluate_or_see_evaluate,
            R.id.btn_see_drawback, R.id.ll_receive_see_wuliu, R.id.ll_store_see_wuliu,R.id.btn_reload_data,R.id.iv_call_store_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_call_kefu_phone:
                // 拨打客服电话
                baseDialog.setTitle("确定拨打电话:4009999353吗?");
                baseDialog.setLeftAndRight("确定","取消");
                baseDialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
                    @Override
                    public void onLeftOnclick() {
                        // 向后台发送确认收货的请求
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "4009999353"));
                        if (ActivityCompat.checkSelfPermission(GoodsOrderDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(GoodsOrderDetailActivity.this, new String[]{Manifest.permission.CALL_PHONE}, GoodsOrderAffirmActivity.MY_PERMISSIONS_REQUEST_CALL_PHONE);
                        } else {
                            startActivity(intent);
                        }
                        baseDialog.dismiss();
                    }

                    @Override
                    public void onRightOncklick() {
                        baseDialog.dismiss();
                    }
                });
                baseDialog.show();
                break;
            case R.id.btn_cancel_order:
                baseDialog.setTitle("优惠不等人，请陛下三思！");
                baseDialog.setLeftAndRight("去意已决","朕再想想");
                baseDialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
                    @Override
                    public void onLeftOnclick() {
                        // 向后台发送确认收货的请求
                        cancelOrder();
                        baseDialog.dismiss();
                    }

                    @Override
                    public void onRightOncklick() {
                        baseDialog.dismiss();
                    }
                });
                baseDialog.show();
                break;
            case R.id.btn_to_pay:
                // 去支付
                Intent intent_pay = new Intent(GoodsOrderDetailActivity.this,CashDeskActivity.class);
                intent_pay.putExtra("order_sn",order_sn);
                startActivity(intent_pay);
                finish();
                break;
            case R.id.btn_apply_drawback:
                Intent intent = new Intent(GoodsOrderDetailActivity.this,ApplyDrawBackFormActivity.class);
                intent.putExtra("order_sn",order_sn);
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_token",user_token);
                intent.putExtra("money",money);
                intent.putExtra("pay_type",pay_type);
                startActivity(intent);
                break;
            case R.id.btn_tixing_send:
                // 提醒发货
                deliverySend();
                break;
            case R.id.btn_affirm_receive:
                baseDialog = new BaseDialog(this);
                baseDialog.setTitle("请收到货后再确认收货，否则可能钱财两空哦！");
                baseDialog.setLeftAndRight("确定","取消");
                baseDialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
                    @Override
                    public void onLeftOnclick() {
                        // 向后台发送确认收货的请求
                        comfirmReceive();
                        baseDialog.dismiss();
                    }

                    @Override
                    public void onRightOncklick() {
                        baseDialog.dismiss();
                    }
                });
                baseDialog.show();
                break;
            case R.id.btn_to_evaluate_or_see_evaluate:
                Intent intent1;
                if (isComment) {
                    // 有评论 跳转到查看评价页面
                    // 无评论 跳转到评价页面
                    if (goodsNum > 1) {
                        // 跳转到选择评价商品页面
                        intent1 = new Intent(GoodsOrderDetailActivity.this,SelectEvaluateGoodsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("order_sn",order_sn);
                        bundle.putBoolean("isOneGoods",false);
                        intent1.putExtras(bundle);
                        startActivity(intent1);
                    } else {
                        // 直接跳转到商品评价页面
                        intent1 = new Intent(GoodsOrderDetailActivity.this,GoodsEvaluateActivity.class);
                        intent1.putExtra("goods_id",goods_id);
                        intent1.putExtra("order_sn",order_sn);
                        intent1.putExtra("goodsImg",goodsImg);
                        intent1.putExtra("is_comment",true);
                        intent1.putExtra("isOneGoods",true);
                        startActivity(intent1);
                        finish();
                    }
                } else {
                    // 无评论 跳转到评价页面
                    if (goodsNum > 1) {
                        // 跳转到选择评价商品页面
                        intent1 = new Intent(GoodsOrderDetailActivity.this,SelectEvaluateGoodsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("order_sn",order_sn);
                        bundle.putBoolean("isOneGoods",false);
                        intent1.putExtras(bundle);
                        startActivity(intent1);
                    } else {
                        // 直接跳转到商品评价页面
                        intent1 = new Intent(GoodsOrderDetailActivity.this,GoodsEvaluateActivity.class);
                        intent1.putExtra("goods_id",goods_id);
                        intent1.putExtra("order_sn",order_sn);
                        intent1.putExtra("goodsImg",goodsImg);
                        intent1.putExtra("isOneGoods",true);
                        startActivity(intent1);
                        finish();
                    }
                }
                break;
            case R.id.btn_see_drawback:
                // 查看退款进度
                Intent intent_handle = new Intent(GoodsOrderDetailActivity.this,DrawBackHandlingActivity.class);
                intent_handle.putExtra("order_sn",order_sn);
                intent_handle.putExtra("start","GoodsOrderDetailActivity");
                intent_handle.putExtra("user_id",user_id);
                intent_handle.putExtra("user_token",user_token);
                startActivity(intent_handle);
                break;
            case R.id.ll_receive_see_wuliu:
                jumpSeeWuLiu();
                break;
            case R.id.ll_store_see_wuliu:
                jumpSeeWuLiu();
                break;
            case R.id.btn_reload_data:
                initData();
                break;
            case R.id.iv_call_store_phone:
                if ("".equals(store_mobile) || store_mobile == null) {
                    Toast.makeText(GoodsOrderDetailActivity.this,"该门店目前没有上传门店电话!",Toast.LENGTH_SHORT).show();
                } else {
                    // 拨打客服电话
                    baseDialog.setTitle("确定拨打该门店电话吗?");
                    baseDialog.setLeftAndRight("确定","取消");
                    baseDialog.setDialogButtonOnclick(new BaseDialog.DialogButtonOnclick() {
                        @Override
                        public void onLeftOnclick() {
                            // 向后台发送确认收货的请求
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + store_mobile));
                            if (ActivityCompat.checkSelfPermission(GoodsOrderDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(GoodsOrderDetailActivity.this, new String[]{Manifest.permission.CALL_PHONE}, GoodsOrderAffirmActivity.MY_PERMISSIONS_REQUEST_CALL_PHONE);
                            } else {
                                startActivity(intent);
                            }
                            baseDialog.dismiss();
                        }

                        @Override
                        public void onRightOncklick() {
                            baseDialog.dismiss();
                        }
                    });
                    baseDialog.show();
                }
                break;
        }
    }

    /**
     *  跳转到查看物流的界面
     */
    private void jumpSeeWuLiu() {
        Intent intent = new Intent(this, HtmlAct.class);
        String url = NetAddress.baseUrl + "index.php/Shop/user_wuliu?yundan_code=" + yundan_code + "&invoice_no="+ invoice_no;
        intent.putExtra("url",url);
        startActivity(intent);
    }

    /**
     *  提醒发货
     */
    private void deliverySend() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).delivery(order_sn, user_id, user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(() -> {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                    });
                    String result = response.body().string();
                    LogUtil.i("GoodsOrderDetailActivity_cancel", result);
                    JSONObject json = new JSONObject(result);
                    Utils.make(GoodsOrderDetailActivity.this, json.optString("info"));
                } catch (Exception e) {
                    Utils.make(GoodsOrderDetailActivity.this, "网络异常");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.make(GoodsOrderDetailActivity.this, "服务器异常");
            }
        });
    }

    /**
     *  取消订单
     */
    private void cancelOrder() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).cancelOrder(order_sn, user_id, user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    runOnUiThread(() -> {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                    });
                    String result = response.body().string();
                    LogUtil.i("GoodsOrderDetailActivity_cancel", result);
                    JSONObject json = new JSONObject(result);
                    Utils.make(GoodsOrderDetailActivity.this, json.optString("info"));
                    if (json.optBoolean("success")) {
                        initData();
                    }
                } catch (Exception e) {
                    Utils.make(GoodsOrderDetailActivity.this, "取消失败，请稍后重试！");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.make(GoodsOrderDetailActivity.this, "取消失败，请稍后重试！");
            }
        });
    }

    /**
     *  确认收货
     */
    private void comfirmReceive() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).comfirmReceive(order_sn, user_id, user_token).enqueue(new Callback<ResponseBody>() {
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
                    LogUtil.i("GoodsOrderDetailActivity_receive", result);
                    JSONObject json = new JSONObject(result);
                    Utils.make(GoodsOrderDetailActivity.this, json.optString("info"));
                    if (json.optBoolean("success")) {
                        initData();
                    }
                } catch (Exception e) {
                    Utils.make(GoodsOrderDetailActivity.this, "网络异常");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.make(GoodsOrderDetailActivity.this, "服务器异常");
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        List<GoodsOrderDetailsBean.DataBean.GoodsInfoBean> mDatas;

        public MyAdapter(List<GoodsOrderDetailsBean.DataBean.GoodsInfoBean> datas) {
            this.mDatas = datas;
        }

        @Override
        public int getCount() {
            if (mDatas != null)
                return mDatas.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mDatas != null)
                return mDatas.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(GoodsOrderDetailActivity.this, R.layout.item_goods_order_details, null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.iv_order_details_goods_img);
                holder.name = (TextView) convertView.findViewById(R.id.tv_order_details_goods_desc);
                holder.color = (TextView) convertView.findViewById(R.id.tv_order_details_color);
                holder.size = (TextView) convertView.findViewById(R.id.tv_order_details_size);
                holder.xiaoXiPrice = (TextView) convertView.findViewById(R.id.tv_order_details_goods_xi_price);
                holder.marketPrice = (TextView) convertView.findViewById(R.id.tv_order_details_goods_menshi_price);
                holder.num = (TextView) convertView.findViewById(R.id.tv_order_details_goods_num);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            GoodsOrderDetailsBean.DataBean.GoodsInfoBean goodsInfoBean = mDatas.get(position);

            //设置文字斜杠
            TextPaint paint = holder.marketPrice.getPaint();
            paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            paint.setAntiAlias(true);
            holder.name.setText(goodsInfoBean.getGoods_name());
            int sku1_id = Integer.parseInt(goodsInfoBean.getSku1_id());
            int sku2_id = Integer.parseInt(goodsInfoBean.getSku2_id());
            if (sku1_id > 0) {
                holder.color.setVisibility(View.VISIBLE);
                holder.color.setText(goodsInfoBean.getSku1_name() + " : " + goodsInfoBean.getSku1_value());
            } else {
                holder.color.setVisibility(View.GONE);
            }
            if (sku2_id > 0) {
                holder.size.setVisibility(View.VISIBLE);
                holder.size.setText(goodsInfoBean.getSku2_name() + " : " + goodsInfoBean.getSku2_value());
            } else {
                holder.size.setVisibility(View.GONE);
            }
//            holder.color.setText(goodsInfoBean.get);
            holder.xiaoXiPrice.setText("¥ " + goodsInfoBean.getGoods_shop_price());
            holder.marketPrice.setText("¥ " + goodsInfoBean.getMarket_price());
            holder.num.setText("x" + goodsInfoBean.getBuy_number());

            Glide.with(GoodsOrderDetailActivity.this)
                    .load(goodsInfoBean.getGoods_soure_img()).placeholder(R.drawable.no_data_empty).centerCrop()
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

    /**
     * 实现倒计时的类，以毫秒为单位
     */
    class MyCount extends AdvancedCountdownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {  //这两个参数在AdvancedCountdownTimer.java中均有(在“构造函数”中).
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
//            initData();
            mTvLimitTime.setText("0：0 分钟内不去支付，订单会自动取消哦！");
        }

        //更新剩余时间
        @Override
        public void onTick(long millisUntilFinished, int percent) {
            long myhour = (millisUntilFinished / 1000) / 3600;
            long myminute = ((millisUntilFinished / 1000) - myhour * 3600) / 60;
            long mysecond = millisUntilFinished / 1000 - myhour * 3600
                    - myminute * 60;
            mTvLimitTime.setText(myminute + ":" + mysecond + " 分钟内不去支付，订单会自动取消哦！");
        }
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
}
