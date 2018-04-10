package com.xi6666.productdetails.view;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.BaseFrgm;
import com.xi6666.app.SuperFrgm;
import com.xi6666.html5show.JsCallAndroid;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.utils.LogUtil;
import com.xi6666.view.EmptyLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;


/**
 * Created by Mr_yang on 2016/11/15.
 */

public class ProductDetialFrgm extends SuperFrgm implements View.OnClickListener {
    private static final String TAG = "ProductDetialFrgm";
    @BindView(R.id.wv_fragment_product_detial)
    WebView mWebView;

    @BindView(R.id.emp_product_detial)
    EmptyLayout mEmpHtml;
    private String mUrl;
    private WebSettings mWebSettings;
    private boolean isError = false;

    private Map<String, Object> TitleMap = new HashMap();//存放标题 键是url 值是标题

    public static ProductDetialFrgm newInstance(String url) {
        ProductDetialFrgm productDetialFrgm = new ProductDetialFrgm();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        productDetialFrgm.setArguments(bundle);
        return productDetialFrgm;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_product_detial;
    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        mUrl = bundle.getString("url");
        mEmpHtml.setErrorButtonClickListener(this);
        WebViewSetting();
    }

    /**
     * webView相关设置
     */
    @SuppressLint("JavascriptInterface")
    private void WebViewSetting() {

        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setAppCacheEnabled(false);
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        String cacheDirPath = mActivity.getFilesDir().getAbsolutePath();
        mWebSettings.setGeolocationDatabasePath(cacheDirPath);
        mWebSettings.setAppCachePath(cacheDirPath);
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebViewChromeClient());
        mWebView.addJavascriptInterface(new JsCallAndroid(mActivity), "JsCallAndroid");
        mWebView.loadUrl(mUrl);

        mEmpHtml.showLoading();
    }

    public class MyWebViewClient extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            LogUtil.d(TAG, "onReceivedError");
            isError = true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d(TAG, "onPageStarted");
            isError = false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //设置界面错误的显示
            LogUtil.d(TAG, "onPageFinished");
            LogUtil.d(TAG, "Error==" + isError);

            if (isError) {
                mEmpHtml.showError();
            } else {
                mEmpHtml.hide();
            }
        }
    }

    /**
     * webView的代理器
     */
    public class MyWebViewChromeClient extends WebChromeClient {
        //设置标题头
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

            if (null != mWebView.getUrl())
                TitleMap.put(mWebView.getUrl(), title);//存放标题

        }

        //设置定位权限
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback);
            callback.invoke(origin, true, false);
        }

        //设置进度条
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            LogUtil.d(TAG, "onProgressChange---->" + newProgress);
            if (newProgress == 100 && isError == false) {
                mEmpHtml.hide();
            }
        }

    }

    @Override
    public void onClick(View v) {
        //重新加载
        mWebView.reload();
    }
}
