package com.xi6666.carWash.view.custom;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.carWash.base.BaseAct;
import com.xi6666.carWash.base.BaseSearchToolbarImpl;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.utils.CxxUtils;
import com.xi6666.utils.TUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;

/**
 * 创建者 sunsun
 * 时间 16/11/2 上午10:32.
 * 个人公众号 ardays
 * 搜索文本
 */

public abstract class BaseSearchToolbarView<P extends BasePresenter, M extends BaseModel>
        extends BaseAct implements BaseSearchToolbarImpl {

    /**
     * 设置搜索标题
     */
    public abstract String searchTitle();

    /**
     * Main布局
     */
    public abstract int mainResId();

    /**
     * 初始化
     */
    public abstract void setUp(View view);


    @BindView(R.id.toolbar)
    public View mToolbarV; //标题栏,标题栏颜色
    @BindView(R.id.search_toolbar_left_iv)
    public ImageView mToolbarLeftIv;    //标题栏左边搜索的按钮
    @BindView(R.id.search_toolbar_title_iv)
    public EditText mToolbarTitleTv;    //标题栏中间名称
    @BindView(R.id.search_toolbar_right_tv)
    public TextView mToolbarRightTv;    //标题栏右边的文本
    @BindView(R.id.toolbar_body)
    LinearLayout mBodyView; //身体内容
    @BindView(R.id.search_toolbar_ll)
    View mSearchToolbarLl;  //搜索框


    /**
     * 身体
     */
    public View mMainView;

    /**
     * 历史记录编号参数
     */
    HistoryPreferences mHistoryPreferences;

    /**
     * 历史记录存储
     */
    List<String> mHistoryDatas;

    public P mPersenter; //业务逻辑层
    public M mModel;     //网络层

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //沉侵式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        super.onCreate(savedInstanceState);
    }


    @Override
    public int getLayoutId() {
        return R.layout.toolbar_search_main;
    }

    @Override
    public void init() {
        mPersenter = TUtils.getT(this, 0);
        mModel = TUtils.getT(this, 1);
        if (this instanceof BaseView) mPersenter.setVm(this, mModel); //添加MVP的View回调事件
        initToolbar();
        setMainView(mainResId());
        setUp(mMainView);
        initHistory();
    }


    @Override
    public void setMainView(int resId) {
        mMainView = getLayoutInflater().inflate(resId, mBodyView);
    }

    @Override
    public void setLeftIcon(int resId) {
        mToolbarLeftIv.setImageResource(resId);
    }


    /**
     * 获取聊天记录
     */
    private void initHistory() {
        mHistoryPreferences = new HistoryPreferences(this);
        //获取聊天记录
        mHistoryDatas = mHistoryPreferences.getRecords();
        historyData(mHistoryDatas);
    }


    /**
     * 每当回到页面时，把最新数据显示回馈到子布局，并存储当前最新搜索记录
     */
    @Override
    public void finish() {
        super.finish();
        historyData(mHistoryDatas);
    }

    @Override
    public void setSearchClicked(boolean bol) {
        mToolbarTitleTv.setFocusable(bol);
        mToolbarTitleTv.setOnClickListener(v -> {
            clickSearchsBox();
        });
    }

    @Override
    public void setSearchHint(String hint) {
        mToolbarTitleTv.setHint(hint);
    }

    @Override
    public void clickSearch(String keyWord) {
        //如果搜索记录为空让他直接弹出

        if (TextUtils.isEmpty(keyWord)) return;
        //遍历当前存储的历史记录是否重复了 如果为重组就删除
        for (String keyword : mHistoryDatas) {
            if (TextUtils.equals(keyword, keyWord)) {
                mHistoryDatas.remove(keyword);
                break;
            }
        }
        CxxUtils.hideSoftInput(getContext(), mToolbarTitleTv);
        searchKeyWord(keyWord);
        mHistoryDatas.add(0, keyWord);

    }

    @Override
    public void historyData(List<String> historyDatas) {
        mHistoryPreferences.setRecordList(mHistoryDatas);
    }

    @Override
    public void clearHistory() {
        mHistoryDatas.clear();
        historyData(mHistoryDatas);
    }

    @Override
    public void searchKeyWord(String keyWord) {
        mToolbarTitleTv.setText(keyWord);
    }


    @Override
    public void clickSearchsBox() {

    }

    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        //沉侵式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mToolbarV.setPadding(0, 60, 0, 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                setMiuiStatusBarDarkMode(this, true);
            else
                mToolbarV.setBackgroundColor(Color.rgb(240, 240, 240));
        }

        //设置搜索内容
        mToolbarTitleTv.setText(searchTitle());

        //监听键盘回车事件
        mToolbarTitleTv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String keyword = mToolbarTitleTv.getText().toString();
                    clickSearch(keyword);
                }
                return false;
            }
        });

        //设置右边按钮
        mToolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置中间搜索点击事件
        mSearchToolbarLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSearchsBox();
            }
        });
    }

    /**
     * 适配miui系统标题栏颜色
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 存储历史记录
     */
    class HistoryPreferences {

        /**
         * 存储上下文
         */
        public Context mContext;


        /**
         * 偏好存储
         */
        SharedPreferences mHistoryPreferences;


        /**
         * 偏好名字
         */
        String mPreferencesName;

        public int number = 10;

        public HistoryPreferences(Context context) {
            this.mContext = context;
            mPreferencesName = context.getClass().getName();
            // 数据名字为此类名
            mHistoryPreferences = context.getSharedPreferences(mPreferencesName, Context.MODE_PRIVATE);
        }


        /**
         * 根据数组存储聊天记录
         *
         * @param records
         */
        public void setRecordList(List<String> records) {
            SharedPreferences.Editor editor = mHistoryPreferences.edit();
            editor.putString(mPreferencesName + ".history", formRecordJson(records));   //提交json格式
            editor.commit();//提交
        }


        /**
         * 获取历史纪录  json数据
         *
         * @return
         */
        public String getRecord() {
            return mHistoryPreferences.getString(mPreferencesName + ".history", "[]");
        }


        /**
         * 获取历史记录 json数组
         */
        public List<String> getRecords() {
            //json解析
            return new Gson().fromJson(getRecord(), new TypeToken<List<String>>() {
            }.getType());
        }


        /**
         * 把历时记录封装为bean
         */
        public String formRecordJson(List<String> records) {
            //创建一个String数组;
            StringBuilder recordJson = new StringBuilder("[");
            int index =  records.size() > number ? number : records.size();
            for (int i = 0; i < index; i++) {
                recordJson.append("\"");
                recordJson.append(records.get(i));
                recordJson.append("\"");
                recordJson.append(",");
            }

            //删除最后一个"," 并且历史记录大于0
            if (records.size() > 0)
                recordJson.setLength(recordJson.length() - 1);
            recordJson.append("]");
            Log.e("TAG", " recordJson.toString();" + recordJson.toString());

            return recordJson.toString();
        }


    }

}
