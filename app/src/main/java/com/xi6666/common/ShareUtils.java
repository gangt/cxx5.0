package com.xi6666.common;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.cocosw.bottomsheet.BottomSheet;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xi6666.R;
import com.xi6666.network.ApiRest;
import com.xi6666.utils.LogUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;


/**
 * Created by Mr_yang on 2016/11/29.
 */

public class ShareUtils {
    private Context mContext;
    private BottomSheet.Builder mBuilder;
    private final IWXAPI mWxapi;
    private String mWebPageUrl;
    private String mTitle;
    private String mContent;
    private String mImageUrl;
    private static final String TAG = "ShareUtils";

    public ShareUtils(Context context, String webPageUrl, String title, String content, String imageUrl) {
        mContext = context;
        mWxapi = WXAPIFactory.createWXAPI(mContext, ApiRest.WECHATAPPID, true);
        mWxapi.registerApp(ApiRest.WECHATAPPID);
        init();
        this.mWebPageUrl = webPageUrl;
        this.mTitle = title;
        this.mContent = content;
        this.mImageUrl = imageUrl;
    }

    private void init() {
        mBuilder = new BottomSheet.Builder(mContext, R.style.BottomSheet_StyleDialog).title("分享到：").
                sheet(R.menu.menu_sweet).listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case R.id.action_wechat:
                        LogUtil.d(TAG, "mWebPageUrl-->" + mWebPageUrl);
                        LogUtil.d(TAG, "mTitle-->" + mTitle);
                        LogUtil.d(TAG, "mContent-->" + mContent);
                        LogUtil.d(TAG, "mImageUrl-->" + mImageUrl);
                        shareText(mWebPageUrl, mTitle, mContent, mImageUrl, 0);
                        break;
                    case R.id.action_wechat_friends:
                        shareText(mWebPageUrl, mTitle, mContent, mImageUrl, 1);
                        break;

                }
            }
        }).grid();

    }

    public BottomSheet showDialog() {
        return mBuilder.show();
    }

    // 文本分享
    public void shareText(String webPageUrl, String title, String content, final String imageUrl, final int flag) {

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = webPageUrl;
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = content;
        if (!TextUtils.isEmpty(imageUrl)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap thumb2 = Bitmap.createScaledBitmap(returnBitmap(imageUrl), 120, 120, true);//压缩Bitmap
                        msg.setThumbImage(thumb2);
                    } catch (Exception e) {

                    }
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = msg;
                    req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
                    mWxapi.sendReq(req);
                }
            }).start();
        }
    }

    private Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;
        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
