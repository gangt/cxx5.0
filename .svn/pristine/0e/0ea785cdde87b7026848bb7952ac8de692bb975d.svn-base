package com.xi6666.cardbag.view.oilcard;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alipay.sdk.app.H5PayActivity;
import com.google.gson.Gson;
import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.api.APIUrls;
import com.xi6666.carWash.base.api.Api;
import com.xi6666.cardbag.view.custom.CardInputEditText;
import com.xi6666.cardbag.view.mvp.AddOilCardContract;
import com.xi6666.cardbag.view.mvp.AddOilCardModel;
import com.xi6666.cardbag.view.mvp.AddOilCardPresenter;
import com.xi6666.cardbag.view.mvp.bean.AddOilCardBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardGetNameBean;
import com.xi6666.common.UserData;
import com.xi6666.databean.UserDataBean;
import com.xi6666.eventbus.ChoiceDefaultOilCardEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;


/**
 * 创建者 sunsun
 * 时间 16/11/22 下午3:00.
 * 个人公众号 ardays
 */

public class  AddOilCardAct extends BaseToolbarView<AddOilCardPresenter,
        AddOilCardModel> implements AddOilCardContract.View {
    private static final String TAG = "AddOilCardAct";

    /**
     * 石化正则表达式
     */
    private final String SINOPEC_MATCHES = "100011[\\d]{13}";
    /**
     * 石油正则表达式
     */
    private final String PETRO_MATCHES = "9[\\d]{15}";
    /**
     * 判断油号是否存在
     * true 存在
     * false 不存在
     */
    private boolean mOilCardExistence = false;


    RadioButton mPetroRb;       //中国石油的单选按钮
    CardInputEditText mNumberEt;         //加油卡卡号的号码
    RadioButton mSinopecRb;     //中国石化的单选按钮
    TextView mPhoneEt;         //手机号码
    EditText mNameEt;           //名字
    Button mOilCardBtn;         //添加油卡按钮

    // 石化油卡  和 中国石油
    private String sinope_oil_card = "", petro_oil_card = "", sinope_name = "", petro_name = "";
    // 卡号
    private String card_number = "";

    @Override
    public String title() {
        return "添加加油卡";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_add_oil_card;
    }

    @Override
    public void setUp(View view) {
        initToolbar();
        initView(view);
        initTab();
        initOilCardNumber();
    }

    /**
     * 初始化标题栏
     */
    void initToolbar() {
        setToolbarRightText("没有加油卡");
        setToolbarRightColor(getResources().getColor(R.color.text_gray));
    }

    @Override
    public void onToolbarRightClick(View view) {
        super.onToolbarRightClick(view);
        HtmlAct.unsealActivity(getActivity(), Api.BASE_URL + APIUrls.HANDLE_OIL_CARD);
    }

    /**
     * 初始化绑定View
     */
    private void initView(View view) {
        mPetroRb = (RadioButton) view.findViewById(R.id.add_oil_card_petro_rb);
        mNumberEt = (CardInputEditText) view.findViewById(R.id.add_oil_card_number_et);
        mSinopecRb = (RadioButton) view.findViewById(R.id.add_oil_card_sinopec_rb);
        mPhoneEt = (TextView) view.findViewById(R.id.add_oil_card_phone_et);
        mNameEt = (EditText) view.findViewById(R.id.add_oil_card_name_et);
        mOilCardBtn = (Button) view.findViewById(R.id.add_oil_card_confirm);

        String json = UserData.getUserData();
        if (TextUtils.isEmpty(json)) {
            make("数据异常");
            finish();
        }
        UserDataBean bean = new Gson().fromJson(json, UserDataBean.class);
        mPhoneEt.setText(bean.getData().getUser_mobile());

    }

    /**
     * 中石化和中石油的切换
     */
    private void initTab() {
        /**
         * 中国石化
         */
        findViewById(R.id.add_oil_card_sinopec_ll)
                .setOnClickListener(v -> {
                    mNameEt.setHint("");
                    //单选按钮替换
                    mSinopecRb.setChecked(true);
                    mPetroRb.setChecked(false);
                    card_number = sinope_oil_card;
                    mNumberEt.setText(card_number + "");
                    mNameEt.setText(sinope_name + "");
                });

        /**
         * 中国石油
         */
        findViewById(R.id.add_oil_card_petro_ll)
                .setOnClickListener(v -> {
                    mNameEt.setHint("请输入与加油卡相匹配的姓名");
                    //单选按钮替换
                    mPetroRb.setChecked(true);
                    mSinopecRb.setChecked(false);
                    card_number = petro_oil_card;
                    mNumberEt.setText(card_number + "");
                    mNameEt.setText(petro_name);
                });
    }

    /**
     * 初始化监听加油卡输入事件
     */
    private void initOilCardNumber() {
        //油卡输入监听
        mNumberEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //获取用户输入的卡号
                card_number = mNumberEt.getTextWithoutSpace();
                /**
                 * 判断中国石化
                 */
                if (card_number.matches(SINOPEC_MATCHES)) {
                    onSelectClick(1);
                }
                /**
                 * 判断中国石油
                 */
                else if (card_number.matches(PETRO_MATCHES)) {
                    onSelectClick(2);
                }
            }
        });

        mOilCardBtn.setOnClickListener(v -> {
            addOilCard();
        });
    }

    void addOilCard() {
        //获取用户输入的卡号
        card_number = mNumberEt.getTextWithoutSpace();
        if (!card_number.matches(SINOPEC_MATCHES) && !card_number.matches(PETRO_MATCHES)) {
            make("请输入正确的卡号");
            return;
        }
        /**
         * @time: 2017年1月12日10:35:38
         * @注释 理由： IOS说这个由后台判断哇
         */
//        if (mSinopecRb.isChecked()) {
//            if (!mOilCardExistence) {
//                make("请输入正确的油卡");
//                return;
//            }
//        }
        //修改名字
        if (TextUtils.isEmpty(mNameEt.getText().toString())) {
            make("请输入你的名字");
            return;
        }
        //请求添加油卡
        mPersenter.requestAddOilCard(mNameEt.getText().toString(), mNumberEt.getTextWithoutSpace(), mPhoneEt.getText().toString());

//        RequestParams params = new RequestParams();
//        params.setPath(HttpConstants.ADD_OIL_CARD);
//        params.put("truename",mNameEt.getText().toString());
//        params.put("mobile_phone",mPhoneEt.getText().toString());
//        params.put("card_number", mNumberEt.getText().toString());
//        OkHttp.get(params, OilCarUserBean.class, this, new OkHttp.OkHttpCallBack<OilCarUserBean>() {
//            @Override
//            public void onFailure(Exception e) {
//                make("网络请求失败");
//            }
//
//            @Override
//            public void onResponse(OilCarUserBean bean) {
//                make(bean.info);
//                if(bean.success) {
//                    finish();
//                }
//            }
//        }
    }


    /**
     * 点击事件选择
     * <p>
     * 1:中国石化
     * 2:中古石油
     */
    void onSelectClick(int type) {
        if (type == 1) {
            sinope_oil_card = card_number;
            //单选按钮替换
            mSinopecRb.setChecked(true);
            mPetroRb.setChecked(false);
            make("获取姓名中");
            //TODO: 去获取姓名
            mPersenter.requestOilCardName(sinope_oil_card);
        } else if (type == 2) {
            petro_oil_card = card_number;
            //单选按钮替换
            mPetroRb.setChecked(true);
            mSinopecRb.setChecked(false);
        } else {
            //单选按钮替换
            mPetroRb.setChecked(false);
            mSinopecRb.setChecked(false);
        }
        mOilCardExistence = false;
    }


    @Override
    public void returnOilCardName(OilCardGetNameBean bean) {
        mNameEt.setText(bean.data.card_name);
        mOilCardExistence = true;
        make("获取成功");
    }

    @Override
    public void returnAddOilCardResult(AddOilCardBean bean) {
        make(bean.info);
        if (bean.success) {
            setResult(RESULT_OK);
            EventBus.getDefault().post(new ChoiceDefaultOilCardEvent("success"));
            finish();
        }
    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Fragment activity) {
        Intent intent = new Intent(activity.getActivity(), AddOilCardAct.class);
        activity.startActivityForResult(intent, OIL_CARD_RESULT);
    }

    public static final int OIL_CARD_RESULT = 101;

    //    @Override
    public void toastError(String data) {
        make(data);
    }
}
