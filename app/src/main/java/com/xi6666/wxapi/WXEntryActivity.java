/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package com.xi6666.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xi6666.common.Constant;
import com.xi6666.eventbus.WheelShareEvent;

import org.greenrobot.eventbus.EventBus;


/**
 * 微信客户端回调activity示例
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, "wx98d17f727ecaa444", false);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq arg0) {
// TODO Auto-generated method stub
       /* finish();*/
    }

    @Override
    public void onResp(BaseResp resp) {

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //判断是微信登录的状态还是微信分享的状态
                switch (resp.getType()) {
                    case ConstantsAPI.COMMAND_SENDAUTH:
                        break;
                    case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                       /* if (TextUtils.isEmpty(MyConstants.isDaZhuanPan)) {
                            finish();
                        } else {
                            LogUtil.d("weChat", "wechat分享");
                            String userId = SpUtils.getString(this, "userId");
                            new Retrofit.Builder().baseUrl(MyConstants.BASEURL).build().create(GetData.class).h5ZhuanPan(userId).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    finish();
                                    EventBus.getDefault().post(new ZhuanPanEvent("success"));
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    finish();
                                    EventBus.getDefault().post(new ZhuanPanEvent("success"));
                                }
                            });
                        }*/
                        Toast.makeText(WXEntryActivity.this, "微信分享成功!", Toast.LENGTH_LONG).show();
                        if (!TextUtils.isEmpty(Constant.ISWHEELSHARE)) {
                            EventBus.getDefault().post(new WheelShareEvent(""));
                            Constant.ISWHEELSHARE = "";
                        }
                        finish();
                        break;

                    default:
                        break;
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Toast.makeText(WXEntryActivity.this, "您已取消分享!", Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(Constant.ISWHEELSHARE)) {
                    Constant.ISWHEELSHARE = "";
                }
                finish();
                break;
        }

    }

}