package com.xi6666.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.xi6666.alipay.SignUtils;
import com.xi6666.utils.LogUtil;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Mr_yang on 2016/7/6.
 * 阿里pay的支付工具类,此处暂时在本地进行签名,后续更改为服务器签名
 */
public class AlipayUtils {
    // 商户PID
    public static final String PARTNER = "2088911786934158";
    // 商户收款账号
    public static final String SELLER = "xiaoxilx@163.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANLSaCi+J5f6qUhKG26SWCBqLrQXzQatQqyaMxaNP3zH0dpTsdBBpROt5zlwH/gIBdZmz3hPlIFaJAX369vJ+DT7g+pdoD+724S94jDsGeiTsJo28Q3M0x7WHGwf4ttOLxW74CnW/n2Aph2r2fPupQEmVHc31AetxEqyvjoSfHpvAgMBAAECgYApSO8A24whZQL1lx2cH6iGYITY27hY0GE4L9JFXk/Kfc8ItE8j+1b6bZnUU7LO/bM281c5tkvcsOxRBuX3WqvySOx4yeBeEqea6V2aYdbcWY70djIsu07WB5b1p9zb6sJsaoyEiNlCQFb8c90dRZyYCFU2NBiC0mvPuuErZCtvMQJBAPwC52UWoimKjR9utN9udlcs05Q+beLp1iP0MfhdpYwzfWxFYtvTYixvNPGluJaZpfx1Lpt3rnIhxGeTtcn3sBsCQQDWKJy3KwccWyYds+sgJzGZhYfmL43kVbBzqdFqiDTZCOVoHuyxkWm6jtHZ43axFyv9hm3OSzc5ggIH93Jshsw9AkAdbqFjtv79ZwcQUrehGJ+NktirCZd/1tuBm3Vykk1RUHonePsQvWFL2zkNt9MVE6DFGVJ2KcYf4j2z8xZaHildAkBxM5JzN+lQUdX6CWqV2mHbdrv5LtXgBnN5ECDEIS9eMdehjActbWHn3SqL9DJ/NXJpVq+gRh3w5gnQ0DnJqA85AkEAvEQnih5CigKCY4jwGiV74xK9WrYhOnxLqrPT0kOz/DOrnlLtAKpQiodrDsN5Zkm3C/ToxRIMK4Vr7sDj/Wz/hg==";
    // 支付宝公钥
    //public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    private static final int SDK_PAY_FLAG = 1;

    private Gson mGson;

    private Context mContext;

    private String mAttach;
    private String mBody;
    private String mOut_trade;
    private String mTotal_fee;
    private String mZhifubao_url;

    public AlipayUtils(Context context) {
        mContext = context;
        mGson = new Gson();
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay(String mBody, String mOut_trade, String mTotal_fee, String mZhifubao_url, final Handler mHandler) {
        this.mBody = mBody;
        this.mOut_trade = mOut_trade;
        this.mTotal_fee = mTotal_fee;
        this.mZhifubao_url = mZhifubao_url;

        LogUtil.d("pay",
                "body==" + mBody + "\n" + "out_trade==" + mOut_trade + "\n" + "total_fee==" + mTotal_fee +
                        "\n" + "zhifubao_url" + mZhifubao_url);


        //没有公钥和私钥
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(mContext).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //

                        }
                    }).show();
            return;
        }

        String orderInfo = getOrderInfo(mBody, mBody, mTotal_fee);

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        //开启线程去支付
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay(String mAttach, String mBody, String mOut_trade, String mTotal_fee, String mZhifubao_url, final Handler mHandler) {
        LogUtil.d("AlipayUtils", "mAttach==" + mAttach + "mBody==" + mBody + "mOut_trade==" + mOut_trade + "mTotal_fee==" + mTotal_fee + "mZhifubao_url==" + mZhifubao_url);
        this.mAttach = mAttach;
        this.mBody = mBody;
        this.mOut_trade = mOut_trade;
        this.mTotal_fee = mTotal_fee;
        this.mZhifubao_url = mZhifubao_url;

        LogUtil.d("pay",
                "body==" + mBody + "\n" + "out_trade==" + mOut_trade + "\n" + "total_fee==" + mTotal_fee +
                        "\n" + "zhifubao_url" + mZhifubao_url);


        //没有公钥和私钥
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(mContext).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //

                        }
                    }).show();
            return;
        }

        String orderInfo = getOrderInfo(mBody, mAttach, mTotal_fee);

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
        Log.d("alipay", "payInfo-->"+payInfo);

        //开启线程去支付
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + mOut_trade + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + mZhifubao_url + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
