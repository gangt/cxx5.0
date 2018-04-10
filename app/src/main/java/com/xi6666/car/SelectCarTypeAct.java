package com.xi6666.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.app.SuperAct;
import com.xi6666.car.adapter.CarEngineAdapter;
import com.xi6666.car.adapter.CartYearTypeAdapter;
import com.xi6666.car.adapter.SelectCarAdapter;
import com.xi6666.car.adapter.SelectCarDialog;
import com.xi6666.car.bean.AddCarParams;
import com.xi6666.car.bean.CarBrandTypeBean;
import com.xi6666.car.bean.CarYearTypeBean;
import com.xi6666.car.bean.SelectCarBean;
import com.xi6666.car.bean.CarEngineBean;
import com.xi6666.car.mp.SelectCarTypePresenter;
import com.xi6666.car.mp.SelectCarTypePresenterImpl;
import com.xi6666.car.mp.SelectCarTypeView;
import com.xi6666.car.view.AddCarSuccessAct;
import com.xi6666.car.view.MyCarActivity;
import com.xi6666.car.view.SettingCarTypeAct;
import com.xi6666.car.view.custom.BorderTextView;
import com.xi6666.car.view.custom.LetterView;
import com.xi6666.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人 孙孙啊i
 * 时间 2016/6/11 0011.
 * 功能 选择车牌类型
 */
public class SelectCarTypeAct extends SuperAct implements LetterView.OnTouchAssortListener, ExpandableListView.OnGroupClickListener
        , SelectCarTypeView, SelectCarAdapter.OnItemClickListener, SelectCarDialog.OnItemClickListener,CartYearTypeAdapter.OnItemClickListener,CarEngineAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitleBar;
    @BindView(R.id.select_car_sel)
    LetterView mLtterView;
    @BindView(R.id.select_car_elv)
    ExpandableListView mExpandableListView;
    @BindView(R.id.select_car_one_rv)
    RelativeLayout mOneRv;
    @BindView(R.id.select_car_menu)
    LinearLayout mCarMenuLl;
    @BindView(R.id.select_data_lv)
    ListView mDataLv;
    @BindView(R.id.toolbar_left_iv)
    ImageView mLeftIv;


    @BindView(R.id.fdj_tv)
    TextView mFdjTv;    //放大镜

    private SelectCarAdapter mAdapter;
    //
    private List<SelectCarBean.DataBean.CharBean> mDatas;
    //
    private SelectCarTypePresenter mPresenter;
    //提示语
    SelectCarDialog mDialog;

    //存储参数
    private String car_name, car_type, car_year, car_engine;
    //参数
    private AddCarParams mAddCarParams;

    private String mBrandNumber;

    private boolean m_SEL_CAR_TYPE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(true);
        setContentView(R.layout.activity_select_car_type);
        //
        setSupportActionBar(mToolbar);
        init();
        m_SEL_CAR_TYPE = true;
    }


    /**
     * 初始化
     */
    private void init() {
        mPresenter = new SelectCarTypePresenterImpl(this);

        mDatas = new ArrayList<>();
        mPresenter.getCarTypeList();

        mAddCarParams = new AddCarParams();
    }


    /**
     * 放大镜后
     */
    @Override
    public void onTouchAssortXyListener(float x, float y) {
        mFdjTv.setVisibility(View.VISIBLE);
        y += 30;
        mFdjTv.setY(y);
    }

    /**
     * 导航条返回的中文字母
     */
    @Override
    public void onTouchAssortListener(String s) {
        mFdjTv.setText(s + "");

        int index = mAdapter.getAssortList().indexOfKey(s);
        if (index != -1) {
            mExpandableListView.setSelectedGroup(index);
        }
    }


    @Override
    public void onTouchAssortUP() {
        mFdjTv.setVisibility(View.GONE);
    }

    /**
     * 标题栏左边按键
     */
    @OnClick(R.id.toolbar_left_iv)
    void onCloseClick() {
        finish();
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
    }

    /**
     * 这里是返回数据源
     */
    @Override
    public void carTypeList(SelectCarBean bean) {
        if (bean == null) {
            LogUtil.e("TAG", "没有数据");
            return;
        }

        SelectCarBean.DataBean charBean = bean.data;
        if (charBean.A != null)
            mDatas.addAll(charBean.A);
        if (charBean.B != null)
            mDatas.addAll(charBean.B);
        if (charBean.C != null)
            mDatas.addAll(charBean.C);
        if (charBean.D != null)
            mDatas.addAll(charBean.D);
        if (charBean.E != null)
            mDatas.addAll(charBean.E);
        if (charBean.F != null)
            mDatas.addAll(charBean.F);
        if (charBean.G != null)
            mDatas.addAll(charBean.G);
        if (charBean.H != null)
            mDatas.addAll(charBean.H);
        if (charBean.I != null)
            mDatas.addAll(charBean.I);
        if (charBean.J != null)
            mDatas.addAll(charBean.J);
        if (charBean.K != null)
            mDatas.addAll(charBean.K);
        if (charBean.L != null)
            mDatas.addAll(charBean.L);
        if (charBean.M != null)
            mDatas.addAll(charBean.M);
        if (charBean.N != null)
            mDatas.addAll(charBean.N);
        if (charBean.O != null)
            mDatas.addAll(charBean.O);
        if (charBean.P != null)
            mDatas.addAll(charBean.P);
        if (charBean.Q != null)
            mDatas.addAll(charBean.Q);
        if (charBean.R != null)
            mDatas.addAll(charBean.R);
        if (charBean.S != null)
            mDatas.addAll(charBean.S);
        if (charBean.T != null)
            mDatas.addAll(charBean.T);
        if (charBean.U != null)
            mDatas.addAll(charBean.U);
        if (charBean.V != null)
            mDatas.addAll(charBean.V);
        if (charBean.W != null)
            mDatas.addAll(charBean.W);
        if (charBean.X != null)
            mDatas.addAll(charBean.X);
        if (charBean.Y != null)
            mDatas.addAll(charBean.Y);
        if (charBean.Z != null)
            mDatas.addAll(charBean.Z);
        mAdapter = new SelectCarAdapter(this, mDatas);
        Looper.prepare();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.setOnItemClickListener(SelectCarTypeAct.this);
                mExpandableListView.setAdapter(mAdapter);
                //展开所有
                for (int i = 0, length = mAdapter.getGroupCount(); i < length; i++) {
                    mExpandableListView.expandGroup(i);
                }
                //导航条点击事件
                mLtterView.setOnTouchAssortListener(SelectCarTypeAct.this);
//
                mExpandableListView.setOnGroupClickListener(SelectCarTypeAct.this);
            }
        });
        Looper.loop();
    }


    /**
     * 列表点击事件
     */
    @Override
    public void onItemClick(SelectCarBean.DataBean.CharBean data) {
        if (mDialog == null) {
            mDialog = new SelectCarDialog(this);
        }
        //添加汽车图片 名字 ID 参数 首字母
        mAddCarParams.cx_img = data.brand_logo;
        mAddCarParams.cx_name = data.brand_name;
//        mAddCarParams.setCity(data.getFirstchar());

        car_name = data.brand_name;
        mDialog.setTypeText(data.brand_name);
        //写入接口
        mDialog.setOnItemClickListener(this);
        mDialog.show();
        mBrandNumber = data.brand_number;
        mAddCarParams.car_brand = mBrandNumber;
        mPresenter.getCarTypeSpList(data.brand_number);
    }

    /*  第二图逻辑 */
    @Override
    public void myCarDataSpList(CarBrandTypeBean bean) {
        mDialog.setTypeList(this, bean);
    }

    /**
     * 第三图
     */
    @Override
    public void myCarYearDataList(final CarYearTypeBean bean) {
        Looper.prepare();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDataLv.setVisibility(View.VISIBLE);
                CartYearTypeAdapter adapter = new CartYearTypeAdapter(SelectCarTypeAct.this, bean, SelectCarTypeAct.this);
                mDataLv.setAdapter(adapter);
            }
        });
    }

    @Override
    public void myCarEngineList(final CarEngineBean bean) {
        Looper.prepare();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDataLv.setVisibility(View.VISIBLE);
                CarEngineAdapter adapter = new CarEngineAdapter(SelectCarTypeAct.this, bean, SelectCarTypeAct.this);
                mDataLv.setAdapter(adapter);
            }
        });
    }

    /**
     * 车类型
     */
    @Override
    public void onItemClick(CarBrandTypeBean.TypeBean bean, int position) {
        car_type = bean.car_type;
        //添加汽车图片 名字 ID 参数 首字母
        mAddCarParams.cx_name += " " + bean.car_type;
        //隐藏dialog
        mDialog.dismiss();
        //写入标题
        mTitleBar.setText(getString(R.string.select_car_year_title));
        mOneRv.setVisibility(View.GONE);
        //添加两个
        addMenu(car_name);
        addMenu(car_type);
        mAddCarParams.car_chexing = bean.cartype_number;
        mPresenter.getCarYearTypeList(bean.cartype_number);
    }

    /**
     * 期限返回
     */
    @Override
    public void setOnItemClickListener(CarYearTypeBean.TypeBean bean, int position) {
        car_year = bean.year_name;
        mTitleBar.setText(getString(R.string.select_car_engine_title));
        addMenu(car_year);
        //
        mAddCarParams.car_nianfen = bean.year_number;
        mPresenter.getCarEngineList(mBrandNumber, bean.cartype_number);
    }

    /**
     * 排量
     */
    @Override
    public void setOnItemClickListener(CarEngineBean.TypeBean bean, int position) {
        car_engine = bean.pailiang;
        //添加排量参数
        mAddCarParams.car_pailiang = bean.pailiang_number;
        mAddCarParams.cx_pailiang = car_engine + " " + car_year;
        LogUtil.e("TAG", "mAddCarParams--->" + mAddCarParams);
        AddCarSuccessAct.openActivity(this, mAddCarParams, m_SEL_CAR_TYPE);
        finish();
    }

    /**
     * 在顶端添加想东西
     *
     * @param text 内容
     */
    private void addMenu(String text) {
        if (mCarMenuLl.getVisibility() == View.GONE) {
            mCarMenuLl.setVisibility(View.VISIBLE);
        }
        BorderTextView menuText = new BorderTextView(this);
        menuText.setText(text);
        mCarMenuLl.addView(menuText);
    }

    /**
     * 打开当前activity,记得注册哦！！！
     */
    public static void openActivity(Activity mActivity, boolean bol) {
        Intent intent = new Intent(mActivity, SettingCarTypeAct.class);
        intent.putExtra(MyCarActivity.SEL_CAR_TYPE, bol);
        mActivity.startActivity(intent);
    }

}