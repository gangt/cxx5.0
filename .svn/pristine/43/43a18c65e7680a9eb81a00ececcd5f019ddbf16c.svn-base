package com.xi6666.html5show.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.app.SuperAct;
import com.xi6666.common.UserData;
import com.xi6666.eventbus.ChangeAddressEvent;
import com.xi6666.eventbus.ChoiceAddressEvent;
import com.xi6666.eventbus.ChoiceDefaultOilCardEvent;
import com.xi6666.eventbus.LoginEvent;
import com.xi6666.eventbus.WeChatPayEvent;
import com.xi6666.eventbus.WheelShareEvent;
import com.xi6666.html5show.JsCallAndroid;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;
import com.xi6666.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author peng
 * @data 创建时间:2016/11/17
 * @desc web展示界面
 */
public class HtmlAct extends SuperAct implements View.OnClickListener {

    private static final String TAG = "HtmlAct";
    @BindView(R.id.web_html)
    WebView mWebView;
    @BindView(R.id.error)
    ImageView mError;
    @BindView(R.id.try_again)
    TextView mTryAgain;
    @BindView(R.id.error_layout)
    RelativeLayout mErrorLayout;

    @BindView(R.id.txt_html_title)
    TextView mTxtTiltle;
    @BindView(R.id.txt_html_right)
    TextView mTxtHtmlRight;
    @BindView(R.id.tb_html)
    Toolbar mTbHtml;

    private WebSettings mWebSettings;
    private String mUrl;
    private Map<String, Object> TitleMap = new HashMap();//存放标题 键是url 值是标题

    //private TextView mTxtTiltle;
    private Dialog mDialog;
    private boolean isError = false;

    //登录
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginSuccess(LoginEvent loginEvent) {
        String msg = loginEvent.getMsg();
        mUrl = mUrl + "&user_id=" + UserData.getUserId() + "&user_token=" + UserData.getUserToken();
        mWebView.loadUrl(mUrl);
    }

    //设置默认油卡
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeOilCard(ChoiceDefaultOilCardEvent choiceDefaultOilCardEvent) {
        choiceDefaultOilCardEvent.getMsg();
        mWebView.reload();
    }

    //设置支付后的界面跳转
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void weChatPayResult(WeChatPayEvent weChatPayEvent) {
        int msg = weChatPayEvent.getMsg();
        switch (msg) {
            case 0:
                if (!TextUtils.isEmpty(SpUtils.getString(this, "success_url"))) {
                    mWebView.loadUrl(SpUtils.getString(this, "success_url"));
                    SpUtils.setString(this, "success_url", "");
                }
                break;
            case -1:
                if (!TextUtils.isEmpty(SpUtils.getString(this, "fail_url"))) {
                    mWebView.loadUrl(SpUtils.getString(this, "fail_url"));
                    SpUtils.setString(this, "fail_url", "");
                }
                break;
            case -2:
                if (!TextUtils.isEmpty(SpUtils.getString(this, "cancel_url"))) {
                    mWebView.loadUrl(SpUtils.getString(this, "cancel_url"));
                    SpUtils.setString(this, "cancel_url", "");
                }
                break;
        }
    }

    //切换默认地址
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeAddress(ChangeAddressEvent changeAddressEvent) {
        mWebView.reload();
    }

    //选择收货地址
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void choiceAddress(ChoiceAddressEvent choiceAddressEvent) {
        new Handler().post(() -> {
            mWebView.loadUrl("javascript:supplierAddress('" + choiceAddressEvent.getId() + "','" + choiceAddressEvent.getType() + "')");
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void weelShareSuccess(WheelShareEvent wheelShareEvent) {
        mWebView.loadUrl("http://app.xiaoxi6.com/index.php/Zhuanpan/share_success" + "?user_id=" + UserData.getUserId()
                + "&user_token=" + UserData.getUserToken() + "?get_device_type=android");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
            FlymeSetStatusBarLightMode(this.getWindow(), true);
            MIUISetStatusBarLightMode(this.getWindow(), true);
            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_html);
        setSupportActionBar(mTbHtml);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置箭头可见
        mTbHtml.setNavigationIcon(R.drawable.ic_back);
        mTbHtml.setNavigationOnClickListener(v -> initBack());
        ButterKnife.bind(this);
        mUrl = getIntent().getStringExtra("url");
        LogUtil.d(TAG, "url--->" + mUrl);
        mTryAgain.setOnClickListener(this);
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
        String cacheDirPath = getFilesDir().getAbsolutePath();
        mWebSettings.setGeolocationDatabasePath(cacheDirPath);
        mWebSettings.setAppCachePath(cacheDirPath);
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebViewChromeClient());
        mWebView.addJavascriptInterface(new JsCallAndroid(this), "JsCallAndroid");
        mWebView.loadUrl(mUrl);
        mDialog = ShowDialogUitls.showDio(this);

    }

    public class MyWebViewClient extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            isError = true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            isError = false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //设置界面错误的显示
            if (null != TitleMap.get(url)) {
                mTxtTiltle.setText(TitleMap.get(url).toString());
            }
            if (isError) {
                mErrorLayout.setVisibility(View.VISIBLE);
            } else {
                mErrorLayout.setVisibility(View.GONE);
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
            mTxtTiltle.setText(title);
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
            if (newProgress == 100) {
                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            } else {
                if (!mDialog.isShowing() && !HtmlAct.this.isFinishing()) {
                    mDialog.show();
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        initBack();
    }

    /**
     * 重写返回键的逻辑
     */
    private void initBack() {
        if (mWebView.canGoBack() && !mWebView.getUrl().equals(mUrl)) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        //重新加载
        mWebView.reload();
    }

    public static final void unsealActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, HtmlAct.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    public boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }
}
