package com.xi6666.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xi6666.eventbus.WeChatPayEvent;
import com.xi6666.network.ApiRest;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Mr_yang on 2016/5/6.
 * 微信支付的回调界面
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    LocalBroadcastManager localBroadcastManager;
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        api = WXAPIFactory.createWXAPI(this, ApiRest.WECHATAPPID);

        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
          /*
          * 微信支付结果的回调
          * */
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0:
                    //TODO 支付成功以后的逻辑
                    Toast.makeText(WXPayEntryActivity.this, "支付成功!", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new WeChatPayEvent(0));
                    finish();
                    break;
                case -1:
                    //TODO 支付失败以后的逻辑
                    Toast.makeText(WXPayEntryActivity.this, "支付失败!", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new WeChatPayEvent(-1));
                    finish();
                    break;
                case -2:
                    //TODO 取消支付以后的逻辑
                    Toast.makeText(WXPayEntryActivity.this, "您已经取消支付!", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new WeChatPayEvent(-2));
                    finish();
                    break;
                default:
                    //TODO 未知错误
                    Toast.makeText(WXPayEntryActivity.this, "未知错误!", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    }
}
