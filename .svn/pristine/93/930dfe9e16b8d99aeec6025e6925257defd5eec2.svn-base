package com.xi6666.address.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.xi6666.R;
import com.xi6666.address.EditAddressAct;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.address.fragment.adapter.PersonalAddressListAdapter;
import com.xi6666.address.fragment.mvp.PersonalAddressContract;
import com.xi6666.address.fragment.mvp.PersonalAddressModel;
import com.xi6666.address.fragment.mvp.PersonalAddressPresenter;
import com.xi6666.address.fragment.mvp.bean.PersonalAddressBean;
import com.xi6666.carWash.base.BaseFrgm;
import com.xi6666.eventbus.ChangeAddressEvent;
import com.xi6666.eventbus.GoodsAddressNotEvent;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者 sunsun
 * 时间 16/11/12 下午4:29.
 * 个人公众号 ardays
 * <p>
 * 选择收货地址 --- 个人地址
 */

public class PersonalAddressFrgm extends BaseFrgm<PersonalAddressPresenter, PersonalAddressModel>
        implements PersonalAddressContract.View {

//    private static PersonalAddressFrgm mPersonalAddressFrgm;
//    public static PersonalAddressFrgm getInstance() {
//        if(mPersonalAddressFrgm == null)
//            mPersonalAddressFrgm = new PersonalAddressFrgm();
//        return mPersonalAddressFrgm;
//    }


    public void setPersonalAddressFrgm(PersonalAddressListAdapter.PersonalAddressListListener listener) {
        this.mListener = listener;
    }


    @BindView(R.id.personal_address_xrv)
    XRecyclerView mPersonalAddressXrv;      //配送地址
    @BindView(R.id.personal_address_not_ll)
    View mNotll;                    //没有数据

    PersonalAddressListAdapter mAddressListAdapter; //配送地址适配器
    private PersonalAddressListAdapter.PersonalAddressListListener mListener;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_personal_address;
    }

    @Override
    protected void setUp() {
        initAddress();
    }

    /**
     * 初始化地址
     */
    private void initAddress() {
        mPersonalAddressXrv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAddressListAdapter = new PersonalAddressListAdapter(mActivity);
        mAddressListAdapter.setPersonalAddressListListener(mListener);
        mPersonalAddressXrv.setAdapter(mAddressListAdapter);
        //请求地址
        mPersenter.requestAddressList();

        mPersonalAddressXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //请求地址
                mPersenter.requestAddressList();
            }

            @Override
            public void onLoadMore() {
                //请求地址
                mPersenter.requestAddressList();
            }
        });
    }

    public void onEditClick(PersonalAddressBean.DataBean data) {
        //点击编辑
        EditAddressAct.openActivity(this, data);
    }


    /**
     * 设置默认地址
     */
    public void requestSetDefaultAddress(String address_id, int position) {
        //设置默认
        mPersenter.requestSetDefaultAddress(address_id, position);
    }

    @OnClick(R.id.personal_add_address_btn)
    void addAddress() {
        EditAddressAct.openActivity(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            mPersenter.requestAddressList();
            EventBus.getDefault().post(new GoodsAddressNotEvent(false));
        }
    }

    //              网络返回
    @Override
    public void returnAddressList(PersonalAddressBean bean) {
        mPersonalAddressXrv.refreshComplete();
        if (bean.success) {
            mAddressListAdapter.addAll(bean.data);

            if (bean.data.size() == 0) {
                mNotll.setVisibility(View.VISIBLE);
                mPersonalAddressXrv.setVisibility(View.GONE);
                EventBus.getDefault().post(new GoodsAddressNotEvent(true));
            } else {
                mNotll.setVisibility(View.GONE);
                mPersonalAddressXrv.setVisibility(View.VISIBLE);
            }

        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnSetDefaultResult(BaseBean bean, int position) {
        make(bean.info);
        if (bean.success) {
            mAddressListAdapter.setDefaultPosition(position);
            EventBus.getDefault().post(new ChangeAddressEvent("wuow"));
        }
    }
}
