package com.xi6666.main.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.feelwx.ubk.sdk.api.AdRequest;
import com.feelwx.ubk.sdk.update.UBKAd;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.xi6666.R;
import com.xi6666.app.ActManager;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.ImmerseBaseAct;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.common.UpVersionUtils;
import com.xi6666.common.UserData;
import com.xi6666.databean.MainVersionData;
import com.xi6666.eventbus.HomeItemChangeEvent;
import com.xi6666.home.view.HomeFrgm;
import com.xi6666.login.view.LoginAct;
import com.xi6666.main.contract.MainContract;
import com.xi6666.main.di.DaggerMainComponent;
import com.xi6666.main.di.MainModule;
import com.xi6666.main.presenter.MainPresenterImpl;
import com.xi6666.mine.view.MineFrgm;
import com.xi6666.network.ApiRest;
import com.xi6666.store.StoreFrgm;
import com.xi6666.utils.AppUitls;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Subscriber;

/**
 * @author peng
 * @data 创建时间:2016/10/21
 * @desc 主界面
 */
public class MainAct extends ImmerseBaseAct implements MainContract.View {

    private static final String TAG = "MainAct";
    @BindView(R.id.fl_main)
    FrameLayout mFlMain;
    @BindView(R.id.fth_main)
    FragmentTabHost mFthMain;
    private int[] mIcons;
    private String[] mStrings;
    private Class[] mFragments;
    private long mExitTime;
    @Inject
    ApiRest mApiRest;
    @Inject
    MainPresenterImpl mMainPresenter;

    //选择设置显示哪一个item
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setCurrentItem(HomeItemChangeEvent homeItemEvent) {
        int item = homeItemEvent.getItem();
        LogUtil.e(TAG, "currentItem" + item);
        new Handler().postDelayed(() -> {
            mFthMain.setCurrentTab(item);
        }, 100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSwipeBackEnable(false);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        DaggerMainComponent.builder().apiModule(new ApiModule((BaseApplication) getApplication())).
                mainModule(new MainModule()).build().Inject(this);
        mMainPresenter.attachView(this);
        mMainPresenter.setApiRest(mApiRest);
        mMainPresenter.loadVersion();
        if (!TextUtils.isEmpty(UserData.getUserId())) {
            mMainPresenter.LoginTime();
        }
        init();
        openAd();
    }

    private void init() {
        mFthMain.setup(this, getSupportFragmentManager(), R.id.fl_main);
        mFthMain.getTabWidget().setDividerDrawable(null);
        mIcons = new int[]{R.drawable.selector_bg_home, R.drawable.selector_bg_store, R.drawable.selector_bg_mine};
        mStrings = new String[]{"首页", "门店", "我的"};
        mFragments = new Class[]{HomeFrgm.class, StoreFrgm.class, MineFrgm.class};
        for (int x = 0; x < mFragments.length; x++) {
            //设置tab的布局
            TabHost.TabSpec tabSpec = mFthMain.newTabSpec(mStrings[x]).setIndicator(getTabItemView(x));
            // 将Tab按钮添加进Tab选项卡中
            mFthMain.addTab(tabSpec, mFragments[x], null);
        }
    }

    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = View.inflate(MainAct.this, R.layout.home_tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.home_tab_item_iv);
        imageView.setImageResource(mIcons[index]);
        TextView textView = (TextView) view.findViewById(R.id.home_tab_item_tv);
        textView.setText(mStrings[index]);
        return view;
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次,退出车小喜!", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            ActManager.getAppManager().AppExit();
        }
    }

    private void openAd() {
        AdRequest adReq = new AdRequest(this, AdRequest.AD_TYPE_INJECTION_HALF, "半屏演示");
        UBKAd.requestAd(adReq);
    }

    @Override
    public void showUpVersion(MainVersionData mainVersionData) {
        if (Integer.parseInt(mainVersionData.getVersion().getNow_version()) > AppUitls.getAppVersionCode(this)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = 0; x < mainVersionData.getUpdate_result().size(); x++) {
                stringBuilder.append(mainVersionData.getUpdate_result().get(x) + "\n");

            }
            String isImport = mainVersionData.getIs_important();
            //判断是否是重大版本
            if (TextUtils.equals(isImport, "true")) {
                //弹框提示是否需要升级
                showUpdateDialog(stringBuilder.toString(), true);
            } else {
                showUpdateDialog(stringBuilder.toString(), false);
            }
        }
    }

    @Override
    public void gotoLogin() {
        startActivity(new Intent(this, LoginAct.class));
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    private void showUpdateDialog(String upDataLog, boolean b) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("升级提醒");
        builder.setCancelable(false);
        builder.setOnKeyListener(keylistener);
        builder.setMessage(upDataLog);
        if (b) {
            builder.setNegativeButton("退出应用", (DialogInterface dialog, int which) -> finish());
        } else {
            builder.setNegativeButton("下次再说", (DialogInterface dia, int which) -> dia.dismiss());
        }

        builder.setPositiveButton("立刻升级", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RxPermissions.getInstance(MainAct.this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            if (AppUitls.isDownloadManagerAvailable(MainAct.this)) {
                                upDataVersion();
                            } else {
                                Toast.makeText(MainAct.this, "下载管理器处于关闭状态,请打开.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                finish();
            }
        });
        builder.show();
    }

    private void upDataVersion() {
        //下载新的app并且安装
        new UpVersionUtils().downLoadApk(this, "http://www.xi6666.com/d", "车小喜");
        Toast.makeText(this, "正在后台下载新版app", Toast.LENGTH_SHORT).show();
    }

    private DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
