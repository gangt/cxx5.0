package com.xi6666.html5show;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xi6666.addoil.view.AddOilPayAct;
import com.xi6666.addoil.view.AddoOilAct;
import com.xi6666.address.DistributionShopAct;
import com.xi6666.address.ReceiptAddressAct;
import com.xi6666.app.ActManager;
import com.xi6666.carWash.view.CarWashPayAct;
import com.xi6666.cardbag.view.CardBagAct;
import com.xi6666.cardbag.view.oilcard.AddOilCardAct;
import com.xi6666.common.Constant;
import com.xi6666.common.ShareUtils;
import com.xi6666.common.UserData;
import com.xi6666.common.WechatPayUtils;
import com.xi6666.evaluate.activity.MyCollectionActivity;
import com.xi6666.eventbus.H5ToolbarChangeEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.illegal.Activity.IllageSurePayAct;
import com.xi6666.login.view.LoginAct;
import com.xi6666.main.view.MainAct;
import com.xi6666.order.activity.GoodsOrderAffirmActivity;
import com.xi6666.order.activity.MyOrderListActivity;
import com.xi6666.order.activity.MyShoppingCartActivity;
import com.xi6666.productdetails.view.ProductDetailsAct;
import com.xi6666.seckill.view.SecKillAct;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.SpUtils;
import com.xi6666.view.dialog.CallDialog;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mr_yang on 2016/11/17.
 */

public class JsCallAndroid {
    private static final String TAG = "JsCallAndroid";
    private Context mContext;


    public JsCallAndroid(Context context) {
        mContext = context;
    }

    //登录
    @JavascriptInterface
    public void openLogin() {
        mContext.startActivity(new Intent(mContext, LoginAct.class));
    }

    //打开加油界面
    @JavascriptInterface
    public void openAddOil() {
        mContext.startActivity(new Intent(mContext, AddoOilAct.class));
        ActManager.getAppManager().finishActivity(HtmlAct.class);
    }

    //油卡列表
    @JavascriptInterface
    public void openOilCardList() {
        Constant.fromAddOilH5 = "true";
        mContext.startActivity(new Intent(mContext, CardBagAct.class));
    }

    //添加油卡
    @JavascriptInterface
    public void openAddOilCard() {
        Constant.fromAddOilH5 = "true";
        mContext.startActivity(new Intent(mContext, AddOilCardAct.class));
    }

    //打开商品详情
    @JavascriptInterface
    public void openProduct(String goodsId) {
        Intent intent = new Intent(mContext, ProductDetailsAct.class);
        intent.putExtra("goodId", goodsId);
        mContext.startActivity(intent);
    }

    //打开加油支付界面
    @JavascriptInterface
    public void openAddOilPay(String card_id, String package_id,
                              String package_cash, String package_zhekou,
                              String package_return_number, String package_support,
                              String package_amount) {
        LogUtil.d(TAG, "card_id--->" + card_id + "package_id--->" + package_id + "package_cash--->" + package_cash
                + "package_zhekou--->" + package_zhekou + "package_return_number--->" + package_return_number
                + "package_support--->" + package_support + "package_amount--->" + package_amount
        );
        int month = Integer.parseInt(package_return_number);
        double descount = Double.parseDouble(package_zhekou);
        int money = Integer.parseInt(package_cash);
        Intent intent = new Intent(mContext, AddOilPayAct.class);
        intent.putExtra("money", money);
        intent.putExtra("chargeType", package_id);
        intent.putExtra("cardId", card_id);
        intent.putExtra("descount", descount * 0.1);
        intent.putExtra("month", month);
        intent.putExtra("isDiscount", true);
        mContext.startActivity(intent);
    }

    //打开洗车卡充值界面
    @JavascriptInterface
    public void openCleanCarRecharge(String order_sn) {
        LogUtil.d(TAG, "package_id---->" + order_sn);
        Intent intent = new Intent(mContext, CarWashPayAct.class);
        intent.putExtra("package_id", order_sn);
        mContext.startActivity(intent);
    }

    //打开收货地址的接口
    @JavascriptInterface
    public void openChoiceAddress() {
        ReceiptAddressAct.openActivity((Activity) mContext);
    }

    //打开分享的功能
    @JavascriptInterface
    public void openShareDialog(String webPageUrl, String title, String content, String imageUrl) {
        new ShareUtils(mContext, webPageUrl, title, content, imageUrl).showDialog();
    }

    //打开订单确认界面
    @JavascriptInterface
    public void openOrderSure(String gooId, String Sku_value_id) {
        try {
            JSONArray array = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("goods_id", gooId);
            jsonObject.put("sku_value_id", Sku_value_id);
            array.put(jsonObject);
            Intent intent = new Intent(mContext, GoodsOrderAffirmActivity.class);
            intent.putExtra("goods_arr", array.toString());
            mContext.startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //打开购物车界面
    @JavascriptInterface
    public void openShopCar() {
        mContext.startActivity(new Intent(mContext, MyShoppingCartActivity.class));
    }

    //打开服务门店
    @JavascriptInterface
    public void openServerStore() {
        mContext.startActivity(new Intent(mContext, DistributionShopAct.class));
    }

    //微信支付
    @JavascriptInterface
    public void weChatPay(String json) {
        Toast.makeText(mContext, "请稍等,正在加载微信支付", Toast.LENGTH_SHORT).show();
        LogUtil.d(TAG, "微信支付的参数是" + json);
        try {
            JSONObject object = new JSONObject(json);
            LogUtil.d(TAG, "微信支付的参数是" + json.toString());
            SpUtils.setString(mContext, "success_url", object.getString("success_url"));
            SpUtils.setString(mContext, "fail_url", object.getString("fail_url"));
            SpUtils.setString(mContext, "cancel_url", object.getString("cancel_url"));
            //微信支付
            PayReq req;
            final IWXAPI msgApi = WXAPIFactory.createWXAPI(mContext, null);
            msgApi.registerApp(object.getString("appid"));
            req = new PayReq();
            req.appId = object.getString("appid");
            req.partnerId = object.getString("mch_id");
            req.prepayId = object.getString("prepay_id");
            req.packageValue = "Sign=WXPay";
            req.nonceStr = object.getString("nonce_str");
            req.timeStamp = object.getString("timestamp");
            req.sign = object.getString("sign");
            msgApi.sendReq(req);
        } catch (JSONException e) {
            LogUtil.d(TAG, "json解析错误");
            e.printStackTrace();
        }
    }

    //微信支付重载
    @JavascriptInterface
    public void weChatPay(String appid, String mch_id, String prepay_id, String nonce_str,
                          String timestamp, String sign, String success_url,
                          String fail_url, String cancel_url) {
        LogUtil.d(TAG, "微信支付的参数是" + "appid==" + appid + "mch_id==" + mch_id +
                "prepay_id==" + prepay_id + "nonce_str" + nonce_str + "timestamp==" +
                timestamp + "sign==" + sign + "success_url==" + success_url + "fail_url==" +
                fail_url + "cancel_url");
        SpUtils.setString(mContext, "success_url", success_url);
        SpUtils.setString(mContext, "fail_url", fail_url);
        SpUtils.setString(mContext, "cancel_url", cancel_url);


        SpUtils.setString(mContext, "success_url", success_url);
        SpUtils.setString(mContext, "fail_url", fail_url);
        SpUtils.setString(mContext, "cancel_url", cancel_url);
        new WechatPayUtils(mContext).tuneUpWechatPay(appid, mch_id, prepay_id, nonce_str, timestamp, sign);
    }

    //调起分享共能
    @JavascriptInterface
    public void shareToWechat(String title, String context, String imageUrl, String url) {
        new ShareUtils(mContext, url, title, context, imageUrl).showDialog();
    }

    //调起大转盘分享
    @JavascriptInterface
    public void wheelShareToWechat(String title, String context, String imageUrl, String url) {
        Constant.ISWHEELSHARE = "1";
        new ShareUtils(mContext, url, title, context, imageUrl).showDialog();
    }

    @JavascriptInterface
    public void shareToWechat(String title, String context, String imageUrl, String url, String flag) {
        LogUtil.d(TAG, "title" + title + "context" + context + "imageUrl" + imageUrl + "url" + url + "flag" + flag);
        new ShareUtils(mContext, url, title, context, imageUrl).shareText(url, title, context, imageUrl, Integer.parseInt(flag));
    }

    //打开首页界面
    @JavascriptInterface
    public void openHome() {
        mContext.startActivity(new Intent(mContext, MainAct.class));
    }

    //隐藏或者显示toolbar
    @JavascriptInterface
    public void changeToolbar(boolean isOpen) {
        LogUtil.d(TAG, "isopen------>" + isOpen);
        EventBus.getDefault().post(new H5ToolbarChangeEvent(isOpen));
    }

    //打开我的收藏功能q
    @JavascriptInterface
    public void openCollection() {
        if (UserData.isLoginIn()) {
            mContext.startActivity(new Intent(mContext, MyCollectionActivity.class));
        } else {
            Intent intent = new Intent(mContext, LoginAct.class);
            mContext.startActivity(intent);
        }
    }

    //打开订单界面
    @JavascriptInterface
    public void openOrder() {
        if (!TextUtils.isEmpty(UserData.getUserId())) {
            Intent intent = new Intent(mContext, MyOrderListActivity.class);
            intent.putExtra("index", 0);
            intent.putExtra("type", "ProductDetailsAct");
            mContext.startActivity(intent);
        } else {
            Intent intent = new Intent(mContext, LoginAct.class);
            mContext.startActivity(intent);
        }
    }

    //收货地址
    @JavascriptInterface
    public void openAddressList() {
        ReceiptAddressAct.SelectAddress((Activity) mContext, 0);
    }

    @JavascriptInterface
    public void callPhone(String num) {
        RxPermissions.getInstance(mContext).request(Manifest.permission.CALL_PHONE).subscribe(aBoolean -> {
            if (aBoolean) {
                new CallDialog(mContext).MakePhoneCall(num);
            } else {
                Toast.makeText(mContext, "没有给予权限", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void startAddOil() {
        ActManager.getAppManager().finishActivity(HtmlAct.class);
        Intent intent = new Intent(mContext, AddoOilAct.class);
        mContext.startActivity(intent);
    }

    @JavascriptInterface
    public void startSecKill(String goodId) {
        Intent intent = new Intent(mContext, SecKillAct.class);
        intent.putExtra("goodId", goodId);
        mContext.startActivity(intent);
    }

    //打开违章确认支付界面
    @JavascriptInterface
    public void openIllegaSurePay(String orderNumber) {
        Log.d("orderNumber", orderNumber);
        Intent intent = new Intent(mContext, IllageSurePayAct.class);
        intent.putExtra("orderNumber", orderNumber);
        mContext.startActivity(intent);
    }
}
