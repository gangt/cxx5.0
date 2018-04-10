package com.xi6666.car.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.car.adapter.BrandCarAdapter;
import com.xi6666.car.adapter.BrandTypeCarAdapter;
import com.xi6666.car.adapter.CarEngineAdapter;
import com.xi6666.car.adapter.CartYearTypeAdapter;
import com.xi6666.car.bean.AddCarParams;
import com.xi6666.car.bean.BrandCarBean;
import com.xi6666.car.bean.CarBrandTypeBean;
import com.xi6666.car.bean.CarEngineBean;
import com.xi6666.car.bean.CarYearTypeBean;
import com.xi6666.car.mp.SettingCarTypeContract;
import com.xi6666.car.mp.SettingCarTypeModel;
import com.xi6666.car.mp.SettingCarTypePresenter;
import com.xi6666.car.view.custom.BorderTextView;
import com.xi6666.car.view.custom.LetterView;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.view.CxxErrorView;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/26 下午2:56.
 * 个人公众号 ardays
 * <p>
 * <p>
 * 这个公司没有年终奖的,兄弟别指望了,也别来了,我准备辞职了
 * 另外这个项目有很多*Bug* 你坚持不了多久的,拜拜!
 */

public class SettingCarTypeAct extends BaseToolbarView<SettingCarTypePresenter, SettingCarTypeModel>
        implements SettingCarTypeContract.View {

    @Override
    public String title() {
        return "请选择品牌";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_setting_car_type;
    }

    LetterView mCharLv; //字母导航栏
    ExpandableListView mBrandLv;    //车类型选择
    TextView mFdjTv;    //放大镜
    CxxErrorView mBrandErrorView;   //车品牌获取数据失败
    BrandCarAdapter mBrandAdapter;  //品牌列表

    /* 车的品牌型号 */
    View mBrandTypeRl;
    View mBrandTypeLl;
    BorderTextView mBrandTypeBtv;
    ListView mBrandTypeLv;
    CxxErrorView mBrandTypeError;

    String mBrandNumber;

    /*  车的年限  和 排动机 */
    ListView mCarLl;
    View mCarView;

    CartYearTypeAdapter mCarYearAdapter;
    CarEngineAdapter mCarEngineAdapter;
    LinearLayout mCarMenuLl;
    List<View> mMenuView = new ArrayList<>();   //顶部菜单

    AddCarParams mAddCarParams = new AddCarParams();


    @Override
    public void setUp(View view) {
        initView(view);
        showLoading();
        mPersenter.requestCarBrand();
    }


    /**
     * 初始化控件
     */
    private void initView(View view) {
        mCharLv = (LetterView) view.findViewById(R.id.setting_car_type_char_lv);
        mBrandLv = (ExpandableListView) view.findViewById(R.id.setting_car_type_elv);
        mFdjTv = (TextView) view.findViewById(R.id.setting_car_type_fdj_tv);
        mBrandErrorView = (CxxErrorView) view.findViewById(R.id.setting_car_brand_error_view);

        mBrandTypeRl = view.findViewById(R.id.setting_car_brand_type_rl);
        mBrandTypeLl = view.findViewById(R.id.setting_car_brand_type_ll);
        mBrandTypeBtv = (BorderTextView) view.findViewById(R.id.setting_car_brand_type_btv);
        mBrandTypeLv = (ListView) view.findViewById(R.id.setting_car_brand_type_lv);
        mBrandTypeError = (CxxErrorView) view.findViewById(R.id.setting_car_brand_type_error_view);

        mCarLl = (ListView) view.findViewById(R.id.setting_car_type_lv);
        mCarView = view.findViewById(R.id.setting_car_type_ll);
        mCarMenuLl = (LinearLayout) view.findViewById(R.id.setting_car_type_menu_ll);


        //监听字母滚动
        mCharLv.setOnTouchAssortListener(new LetterView.OnTouchAssortListener() {
            @Override
            public void onTouchAssortXyListener(float x, float y) {
                //当移动的时候滚动放大镜的距离
                mFdjTv.setVisibility(View.VISIBLE);
                mFdjTv.setY(y);
            }

            @Override
            public void onTouchAssortListener(String s) {
                //当滚动到某个字母范围的时候让他显示
                mFdjTv.setText(s + "");

                if (mBrandAdapter != null) {
                    int index = mBrandAdapter.getAssortList().indexOfKey(s);
                    if (index != -1) {
                        mBrandLv.setSelectedGroup(index);
                    }
                }
            }

            @Override
            public void onTouchAssortUP() {
                //当松开的时候隐藏
                mFdjTv.setVisibility(View.GONE);
            }
        });


    }


    /*                     品牌                       */
    @Override
    public void resultCarType(BrandCarBean bean) {
        hiddenLoading();
        mBrandErrorView.setVisibility(View.GONE);
        if (bean.success) {
            mBrandAdapter = new BrandCarAdapter(this, bean.data);
            mBrandAdapter.setOnItemClickListener(data -> {
                //请求车品牌参数
                showLoading();
                mBrandNumber = data.brand_number;
                mPersenter.requestCarBrandType(mBrandNumber);
                //商品名称
                mBrandTypeBtv.setText(data.brand_name);
                //添加
                addMenu(data.brand_name);

                mAddCarParams.cx_img = data.brand_logo;
                mAddCarParams.cx_name = data.brand_name;
                mAddCarParams.car_brand = data.brand_number;

                mBrandTypeRl.setVisibility(View.VISIBLE);
                mBrandTypeRl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setVisibility(View.GONE);
                    }
                });
                //做个显示动画
                ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mBrandTypeRl, "alpha", 0, 1);
                alphaAnim.setDuration(1400);
                alphaAnim.start();
            });
            mBrandLv.setAdapter(mBrandAdapter);
            //展开所有
            for (int i = 0, length = mBrandAdapter.getGroupCount(); i < length; i++) {
                mBrandLv.expandGroup(i);
            }
            //设置能点击组缩放
            mBrandLv.setOnGroupClickListener((parent, v, groupPosition, id) -> true);
        } else {
            make(bean.info);
        }
    }

    @Override
    public void brandNetError() {
        hiddenLoading();
        mBrandErrorView.setVisibility(View.VISIBLE);
        mBrandErrorView.setOnErrorClickListener(v -> {
            showLoading();
            mPersenter.requestCarBrand();
        });
    }

    /*                     品牌编号                       */
    @Override
    public void resultCarBrandType(CarBrandTypeBean bean) {
        hiddenLoading();
        mBrandTypeError.setVisibility(View.GONE);
        if (bean.success) {
            BrandTypeCarAdapter adapter = new BrandTypeCarAdapter(this, bean);
            mBrandTypeLv.setAdapter(adapter);
            //下载
            adapter.setOnBrandTypeCarListener(new BrandTypeCarAdapter.OnBrandTypeCarListener() {
                @Override
                public void onBrandTypeClick(CarBrandTypeBean.TypeBean data) {
                    mAddCarParams.cx_name += " " + data.car_type;
                    mAddCarParams.car_chexing = data.cartype_number;
                    //添加
                    addMenu(data.car_type);
                    showLoading();
                    mPersenter.requestCarEngine(mBrandNumber, data.cartype_number);
                }
            });
        } else {
            make(bean.info);
        }
    }


    @Override
    public void brandTypeNetError() {
        hiddenLoading();
        mBrandErrorView.setVisibility(View.VISIBLE);
        mBrandErrorView.setOnErrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                mPersenter.requestCarBrandType(mBrandNumber);
            }
        });
    }

    /*      年限      */
    @Override
    public void resultCarYear(CarYearTypeBean bean) {
        hiddenLoading();
        if (bean.success) {
            setToolbarTitle("请选择年份");
            mCarYearAdapter = new CartYearTypeAdapter(this, bean, new CartYearTypeAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(CarYearTypeBean.TypeBean data, int position) {
                    mAddCarParams.car_nianfen = data.year_number;
                    mAddCarParams.cx_pailiang = data.year_name;
                    AddCarSuccessAct.openActivity(getActivity(), mAddCarParams, true);
                    finish();
                }
            });
            mCarLl.setAdapter(mCarYearAdapter);
        } else {
            make(bean.info);
        }
    }

    /*      排油      */
    @Override
    public void resultCarEngine(CarEngineBean bean) {
        hiddenLoading();
        if (bean.success) {
            setToolbarTitle("请选择发动机排量");
            mCarView.setVisibility(View.VISIBLE);
            //做个显示动画
            ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mCarView, "alpha", 0, 1);
            alphaAnim.setDuration(1400);
            alphaAnim.start();
            mCarEngineAdapter = new CarEngineAdapter(this, bean, new CarEngineAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(CarEngineBean.TypeBean bean, int position) {
                    //添加排量参数
                    mAddCarParams.car_pailiang = bean.pailiang_number;
                    mAddCarParams.cx_pailiang = bean.pailiang + " " + mAddCarParams.cx_pailiang;
                    mPersenter.reqeustCarYear(bean.cartype_number);
                    //添加
                    addMenu(bean.pailiang);
                    showLoading();
                }
            });
            mCarLl.setAdapter(mCarEngineAdapter);
        } else {
            make(bean.info);
        }
    }


    /*              */

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
        mMenuView.add(menuText);
        mCarMenuLl.addView(menuText);
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Activity activity) {
        Intent intent = new Intent(activity, SettingCarTypeAct.class);
        activity.startActivity(intent);
    }

}
