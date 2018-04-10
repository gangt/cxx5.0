package com.xi6666.address;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.address.fragment.mvp.EditAddressContract;
import com.xi6666.address.fragment.mvp.EditAddressModel;
import com.xi6666.address.fragment.mvp.EditAddressPresenter;
import com.xi6666.address.fragment.mvp.bean.PersonalAddressBean;
import com.xi6666.address.fragment.mvp.bean.AddressInitTask;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.illegal.other.StringUtil;
import com.xi6666.order.other.Utils;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/24 下午5:12.
 * 个人公众号 ardays
 */

public class EditAddressAct extends BaseToolbarView<EditAddressPresenter, EditAddressModel> implements EditAddressContract.View {
    @Override
    public String title() {
        return "";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_edit_address;
    }


    EditText mConsigneeEt;  //收货人名字
    EditText mTelEt;        //电话号码
    TextView mAddressTv;    //收货地址
    EditText mAddressDetailsEt; //详情收货地址
    CheckBox mDefaultCk;    //默认地址
    Button mKeepBtn;    //保存地址

    //判断当前是编辑还是添加 true 代表编辑
    private boolean isEdit = false;
    //编辑数据
    PersonalAddressBean.DataBean mEditData;


    @Override
    public void setUp(View view) {
        initView(view);
        initValue();
        initAddressClick();
        initKeep();
    }

    /**
     * 初始化值
     */
    private void initValue() {
        //获取历史数据
        mEditData = getIntent().getParcelableExtra(ADDRESSS_DATA);
        isEdit = mEditData != null;
        setToolbarTitle(isEdit ? "编辑地址" : "添加地址");

        //判断是不是编辑模式下，如果是写入对应参数
        if (isEdit) {
            mConsigneeEt.setText(mEditData.consignee);
            mTelEt.setText(mEditData.mobile);
            mAddressDetailsEt.setText(mEditData.address);
            mAddressTv.setText(mEditData.province_name + mEditData.city_name + mEditData.district_name);

            //设置标题栏右边按钮
            setToolbarRightText("删除");
            setToolbarRightColor(getResources().getColor(R.color.red_text));
        }
    }


    @Override
    public void onToolbarRightClick(View view) {
        super.onToolbarRightClick(view);
        showLoading();
        mPersenter.requestDeleteAddress(mEditData.address_id);
    }

    /**
     * 初始化View
     */
    private void initView(View view) {
        mConsigneeEt = (EditText) view.findViewById(R.id.add_goods_consignee_et);
        mTelEt = (EditText) view.findViewById(R.id.add_goods_tel_et);
        mAddressDetailsEt = (EditText) view.findViewById(R.id.add_goods_details_et);
        mAddressTv = (TextView) view.findViewById(R.id.add_goods_address_tv);
        mDefaultCk = (CheckBox) view.findViewById(R.id.add_goods_default_ck);
        mKeepBtn = (Button) view.findViewById(R.id.add_goods_keep_btn);
    }

    /**
     * 初始化点击
     */
    private void initAddressClick() {
        mAddressTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddressInitTask(getActivity()).execute(mAddressTv.getText().toString());
            }
        });
    }

    /**
     * 初始化保存
     */
    private void initKeep() {
        mKeepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.isTvNull(mConsigneeEt)) {//收货人
                    make("请填写收货人");
                } else if (Utils.isTvNull(mTelEt)) { //电话
                    make("请填写电话");
                } else if (!StringUtil.isTel(mTelEt)) {
                    make("请输入正确的电话号码，\n如：0591-6487256，13435352763");
                } else if (Utils.isTvNull(mAddressTv)) {//地址
                    make("请选择地址");
                } else if (Utils.isTvNull(mAddressDetailsEt)) {//详细收货地址
                    make("请填写详细收货地址");
                } else {
                    //拿出经纬度
                    List<String> list = (List<String>) mAddressTv.getTag();
                    String address = gt(mAddressDetailsEt);
                    String city = list != null ? list.get(1) : mEditData.city;
                    String consignee = gt(mConsigneeEt);
                    String district = list != null ? list.get(2) : mEditData.district;
                    String is_default = mDefaultCk.isChecked() ? "1" : "0";
                    String mobile = gt(mTelEt);
                    String province = list != null ? list.get(0) : mEditData.province;

                    showLoading();
                    if (isEdit) {
                        mPersenter.requestUpdateAddress(mEditData.address_id, address, city, consignee, district, is_default, mobile, province);
                    } else {
                        mPersenter.requestAddAddress(address, city, consignee, district, is_default, mobile, province);
                    }
                }

            }
        });
    }


    private String gt(TextView tv) {
        return tv.getText().toString();
    }


    //                          网络数据返回
    @Override
    public void returnResult(BaseBean bean) {
        make(bean.info);
        hiddenLoading();
        if (bean.success) {
            setResult(RESULT_OK);
            finish();
        }
    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Fragment activity) {
        Intent intent = new Intent(activity.getContext(), EditAddressAct.class);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Fragment fragment, PersonalAddressBean.DataBean data) {
        Intent intent = new Intent(fragment.getContext(), EditAddressAct.class);
        intent.putExtra(ADDRESSS_DATA, data);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    private static final String ADDRESSS_DATA = "com.xi6666.address_data";
    private static final int REQUEST_CODE = 214;

}

