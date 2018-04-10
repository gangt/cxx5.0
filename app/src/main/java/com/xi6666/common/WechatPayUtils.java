package com.xi6666.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xi6666.app.BaseApplication;
import com.xi6666.databean.WeChatPayBean;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;


/**
 * Created by Mr_yang on 2016/7/6.
 * 微信支付的帮助类
 */
public class WechatPayUtils {
   // private Context mContext;
    private final Gson mGson;

    public WechatPayUtils(Context context) {
       // mContext = context;
        mGson = new Gson();
    }

    /**
     * 调起微信支付
     *
     * @param param
     */
    public void tuneUpWechatPay(String param) {
        LogUtil.d("WechatPayUtils", "支付参数是==" + param);
        WeChatPayBean weChatPayBean = mGson.fromJson(param, WeChatPayBean.class);
        String appid = weChatPayBean.appid;
        LogUtil.d("WechatPayUtils", "appidhah==" + appid);
      //  LogUtil.d("WechatPayUtils", "mContext==" + mContext);
        PayReq req;
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(BaseApplication.getmAppContext(), null);
        msgApi.registerApp(ApiRest.WECHATAPPID);
        req = new PayReq();
        req.appId = weChatPayBean.appid;
        req.partnerId = ApiRest.WECHATMCHID;
        req.prepayId = weChatPayBean.prepay_id;
        req.packageValue = "Sign=WXPay";
        req.nonceStr = weChatPayBean.nonce_str;
        req.timeStamp = weChatPayBean.timestamp;
        req.sign = weChatPayBean.sign;
        msgApi.sendReq(req);
       // Toast.makeText(mContext, "微信支付调起", Toast.LENGTH_SHORT).show();
    }


    public void tuneUpWechatPay(String appid, String mch_id, String prepay_id, String nonce_str, String timestamp, String sign) {

        LogUtil.d("pay", "appidhah==" + appid);
        PayReq req;
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(BaseApplication.getmAppContext(), null);
        msgApi.registerApp(appid);
        req = new PayReq();
        req.appId = appid;
        req.partnerId = mch_id;
        req.prepayId = prepay_id;
        req.packageValue = "Sign=WXPay";
        req.nonceStr = nonce_str;
        req.timeStamp = "" + timestamp;
        req.sign = sign;
        msgApi.sendReq(req);
    }
}
